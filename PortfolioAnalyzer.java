package jr.eecs1022.finpro;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jba.roumani.lib.Stock;

/**
 * This class encapsulates a portfolio of investments and
 * provides tools for performing portfolio analysis.
 *
 * @author H. Roumani
 * @since Winter 2016
 */
public class PortfolioAnalyzer
{
    private String title;
    private List<Equity> portfolio;
    public static final long MS_PER_DAY = 24L * 3600 * 1000;
    public static final int DAY_PER_YEAR = 365;


    public PortfolioAnalyzer(String title, String[] rows)
    {
        this.title = title;
        this.portfolio = new ArrayList<Equity>();
        for (String row : rows)
        {
            Equity equity;
            String[] fields = row.split(",");
            try
            {
                String symbol = fields[0];
                int qty = Integer.parseInt(fields[1]);
                double bookValue = Double.parseDouble(fields[2]);
                Date acquired = new SimpleDateFormat("d/m/y").parse(fields[3]);
                double marketValue = this.getInvestmentMarketValue(symbol);
                double yield = this.getInvestmentYield(bookValue, marketValue, acquired);
                equity = new Equity(symbol, qty, bookValue, acquired, marketValue, yield);
            } catch (Exception e)
            {
                equity = new Equity("?", 0, 0.0, null, 0.0, 0.0);
            }
            this.portfolio.add(equity);
        }
    }

    public List<Equity> getPortfolio()
    {
        return this.portfolio;
    }

    public int getPortfolioSize()
    {
        // compute it (1-3 lines of code)
        return this.portfolio.size();
    }



    public String toString()
    {
        int counter = 0;
        return "This portfolio:  \r\n" +
                "- Is worth about " +
                String.format("$%,.2f", this.getPortfolioMarketValue()) + "\r\n "+
                "- And has " +  counter + " bad investments " ;

    }

    private double getInvestmentMarketValue(String symbol)
    {
        // compute it (1-3 lines of code)
        Stock stocks = new Stock(symbol);
        return stocks.getPrice();
    }

    private double getInvestmentYield(double bookValue, double marketValue, Date acquired)
    {
        // compute it (3-5 lines of code)
        Date current = new Date(System.currentTimeMillis());
        long milliBetween = current.getTime() - acquired.getTime();
        double dBetween  = (double)milliBetween / MS_PER_DAY;
        return ((marketValue-bookValue)/ bookValue)*(DAY_PER_YEAR / dBetween);
    }

    public double getPortfolioMarketValue()
    {
        // compute it (3-5 lines of code)
        double marketval = 0;
        for (Equity equity: this.portfolio)
            marketval += equity.getMarketValue() * equity.getQty();
        return marketval;
    }

    public double getPortfolioYield()
    {
        double weightedYieldSum = 0;
        double weightSum = 0;
        int counter = 0;
        for (Equity equity : this.portfolio)
        {
            weightedYieldSum += equity.getQty() * equity.getBookValue() * equity.getYield();
            weightSum += equity.getQty() * equity.getBookValue();
        }
        return weightedYieldSum / weightSum;
        if ((weightedYieldSum/weightSum) > 0){
           counter++;
        }

    }
}
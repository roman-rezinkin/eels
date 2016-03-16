package jr.eecs1022.piglatin;

import java.util.StringTokenizer;

public class PigLatinTranslator
{
    private String english;
    private String pig;
    public static final String vowel = "aeiou";

    public PigLatinTranslator()
    {
        this.setEnglish("");
    }

    public PigLatinTranslator(String text)
    {
        this.setEnglish(text);
    }

    public String getEnglish()
    {
        return this.english;
    }

    public void setEnglish(String text)
    {
        this.english = text.toLowerCase();
        this.translate();
    }

    public String getPig()
    {
        return this.pig;
    }

    // Translate the state to PigLatin
    public void translate()
    {
        StringTokenizer st = new StringTokenizer(this.english);
        String result = "";
        while (st.hasMoreTokens())
        {
            String word = st.nextToken();
            String pig = this.translateWord(word);
            if (result.length() == 0)
            {
                result = pig;
            }
            else
            {
                result = result + " " + pig;
            }
        }
        this.pig = result;
    }

    // Translate the given word to PigLatin
    // and return the translation
    private String translateWord(String word)
    {
        // replace with correct code
        int hasVowel = -1;
        int i = 0;

        while((hasVowel == -1) && (i < word.length())){
            if (vowel.indexOf(word.charAt(i)) != -1){
                hasVowel = i;
            }
            i++;
        }
        if (hasVowel == -1){
            word += "ay";
        }else{
            if (hasVowel == 0){
                word += "way";
            }else{
                String newWordBegin = word.substring(hasVowel, word.length());
                String newWordEnd = word.substring(0, hasVowel);
                word = newWordBegin + newWordEnd + "ay";
            }
        }
        System.out.printf("/n");
        String result = word;

        return result;
    }
    public static void main(String[] args){
    PigLatinTranslator plt = new PigLatinTranslator();


    }
}

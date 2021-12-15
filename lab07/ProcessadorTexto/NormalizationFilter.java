package ProcessadorTexto;

import java.text.Normalizer;

public class NormalizationFilter extends TextDecorator {

    private String currentString;
    public NormalizationFilter(TextInterface text) {
        super(text);
    }
    
    @Override
    public boolean hasNext() {
        if (this.currentString == null || this.currentString.equals("")) {
            if(text.hasNext()){
                this.currentString = text.next();
            }

        }
        return !(this.currentString == null || this.currentString.equals(""));
    }


    /**
     * Explaining Normalize:
     * Latin characters with accent can be encoded in 2 different ways, using only 1 unicode "code" (for the whole letter) or using just one "code"
     * (this one for the letter and the accent). Usually these 2 different forms are not equivalent, since they represent "different" things. The character à is different 
     * from the characters a and `
     * Normalize not only will separate these two, it will make them equivalent. Here we are separating the characters and then erasing the accent, being the remainder, the letter itself.
     */
    @Override
    public String next() {
        String returnedWord = "";
        if(this.hasNext()){
            returnedWord = this.currentString.replaceAll("(?![@',&])\\p{Punct}", "");
            returnedWord = Normalizer.normalize(returnedWord, Normalizer.Form.NFD);
            returnedWord = returnedWord.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

            this.currentString = "";

        }
        return returnedWord;
    }

    
}

package ProcessadorTexto;

public class CapitalizationFilter extends TextDecorator {

    private String currentString;
    public CapitalizationFilter(TextInterface text) {
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

    /********************
     * Here we use an operation involving the binary value of characters
     * Doing a character & 0x5f will "translate" the character to uppercase
     * 
     */

    @Override
    public String next() {
        String returnedWord = "";
        if(this.hasNext()){
            returnedWord = this.currentString.replace(this.currentString.charAt(0), (char)(this.currentString.charAt(0) & 0x5f));
            returnedWord = returnedWord.replace(returnedWord.charAt(this.currentString.length()-1), (char)(returnedWord.charAt(this.currentString.length()-1) & 0x5f));
            this.currentString = "";
        }
        return returnedWord;
    }
    
}

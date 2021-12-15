package ProcessadorTexto;


public class VowelFilter extends TextDecorator {

    private String currentString;
    public VowelFilter(TextInterface text) {
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

    @Override
    public String next() {
        String returnedWord = "";
        if(this.hasNext()){
            returnedWord = this.currentString.replaceAll("[aeiou]", "");
            this.currentString = "";

        }
        return returnedWord;
    }
}

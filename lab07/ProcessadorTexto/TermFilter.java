package ProcessadorTexto;

public class TermFilter extends TextDecorator {

    private String[] currentString;
    public TermFilter(TextInterface text) {
        super(text);
    }

    @Override
    public boolean hasNext() {
        if (currentString == null || currentString.length ==0){
            if(text.hasNext()){
                String returnedString = text.next();
                currentString = returnedString.split(" ");
            }

        }
        return !(currentString == null || currentString.length ==0);
    }

    @Override
    public String next() {
        String returnedWord = "";
        if(this.hasNext()){
            returnedWord = currentString[0];
            String[] newCurrentString = new String[currentString.length-1];
            for (int i=1; i<currentString.length; i++){
                newCurrentString[i-1] = currentString[i];
            }
            currentString = newCurrentString;
        }
        return returnedWord;
    }
    
}

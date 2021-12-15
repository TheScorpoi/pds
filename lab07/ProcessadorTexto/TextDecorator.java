package ProcessadorTexto;

public class TextDecorator implements TextInterface {

    protected TextInterface text;

    public TextDecorator(TextInterface text) {
        this.text = text;
    }

    @Override
    public boolean hasNext() {
        text.hasNext();
        return false;
    }

    @Override
    public String next() {
        text.next();
        return null;
    }
    
}

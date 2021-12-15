package XI_3;

public class Book {

    private String title;
    private String ISBN;
    private String author;
    private int year;
    private State currentState;
    private static int count = 0;


    public Book(String title, String iSBN, String author, int year) {
        this.title = title;
        ISBN = iSBN;
        this.author = author;
        this.year = year;
        this.currentState = new Inventory();
    }

    public void setCount(int i) {
        count = i;
    }

    public void setState(State s) {
        currentState = s;
    }

    public State getState() {
        return currentState;
    }

    @Override
    public String toString() {
        count++;
        String bookToString = String.format("%-1d %-25s %-20s %-10s %-20s [%s]", count, this.title, this.author, this.year, this.ISBN, this.currentState);
        return bookToString;
    }

    

}


package XI_3;

public class Reserve implements State {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    @Override
    public void register(Book book) {
        System.err.println(ANSI_RED + "Operation not supported\n" + ANSI_RESET);

    }

    @Override
    public void request(Book book) {
        System.err.println(ANSI_RED + "Operation not supported\n" + ANSI_RESET);

    }

    @Override
    public void returnBook(Book book) {
        System.err.println(ANSI_RED + "Operation not supported\n" + ANSI_RESET);

    }

    @Override
    public void cancelReserve(Book book) {
        book.setState(new Available());
    }

    @Override
    public void reserve(Book book) {
        System.err.println(ANSI_RED + "Operation not supported\n" + ANSI_RESET);

    }

    @Override
    public String toString() {
        return "Reserve";
    }

}

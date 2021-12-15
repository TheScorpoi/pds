package XI_3;

public interface State {
    
    void register(Book book);
    void request(Book book);
    void returnBook(Book book);
    void cancelReserve(Book book);
    void reserve(Book book);
}

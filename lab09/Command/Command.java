package Command;

public interface Command<E> {
    
    public boolean execute(E element);

    public void undo();

}

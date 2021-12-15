package Command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommandRemove<E> implements Command<E> {

    private Collection<E> collection;
    private List<E> last = new ArrayList<E>();

    public CommandRemove(Collection<E> collection){
        this.collection = collection;
    }

    @Override
    public boolean execute(E element) {
        if (collection.remove(element)) {
            last.add(element);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void undo() {
        if (last != null) {
            collection.add(last.get(last.size() - 1));
            last.remove(last.get(last.size() - 1));
        }
    }
}

package Command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommandAdd<E> implements Command<E> {
    
    private Collection<E> collection;
    private List<E> last = new ArrayList<E>();

    public CommandAdd(Collection<E> collection){
        this.collection = collection;
    }

    @Override
    public boolean execute(E element) {
        if (collection.add(element)) {
            last.add(element);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void undo() {
        if (last != null) {
            collection.remove(last.get(last.size() - 1));
            last.remove(last.get(last.size() - 1));
        }
    }
    
}

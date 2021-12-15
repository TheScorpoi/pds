package Iterator;


import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class VectorGeneric<T> {
    private T[] vec;
        private int nElem;
        private final static int ALLOC = 50;
        private int dimVec = ALLOC;

        @SuppressWarnings("unchecked")
        public VectorGeneric() {
            vec = (T[]) new Object[dimVec];
            nElem = 0;
        }

        public boolean addElem(T elem) {
            if (elem == null)
                return false;
            ensureSpace();
            vec[nElem++] = elem;
            return true;
        }

        private void ensureSpace() {
            if (nElem >= dimVec) {
                dimVec += ALLOC;
                @SuppressWarnings("unchecked")
                T[] newArray = (T[]) new Object[dimVec];
                System.arraycopy(vec, 0, newArray, 0, nElem);
                vec = newArray;
            }
        }

        public boolean removeElem(T elem) {
            for (int i = 0; i < nElem; i++) {
                if (vec[i].equals(elem)) {
                    if (nElem - i - 1 > 0) // not last element
                        System.arraycopy(vec, i + 1, vec, i, nElem - i - 1);
                    vec[--nElem] = null; // libertar último objecto para o GC
                    return true;
                }
            }
            return false;
        }

        public int totalElem() {
            return nElem;
        }

        public T getElem(int i) {
            return (T) vec[i];
        }


        public Iterator<T> iterator(){
            return (this).new VectorIterator<T>();
        }
        
        private class VectorIterator<K> implements Iterator<K> {
            private int indice;
            
            VectorIterator(){
                indice=0;
            }

            @Override
            public boolean hasNext() {
                return indice<nElem;
            }

            @Override
            public K next() {
                if(hasNext())
                    return (K)VectorGeneric.this.vec[indice++];
                throw new NoSuchElementException("only" + nElem + "elements");
            }

            public void remove() {
                throw new UnsupportedOperationException("Operation not supported!");
            }


    
        }

        public ListIterator<T> listIterator() {
            return (this).new VectorListIterator<T>();
        }
    
        public ListIterator<T> listIterator(int indice) {
            return (this).new VectorListIterator<T>(indice);
        }
    
        private class VectorListIterator<K> implements ListIterator<K> {
            private int indice;
    
            VectorListIterator() {
                indice = 0;
            }
    
            VectorListIterator(int indice) {
                if(indice>=0 && indice <= nElem)
                    this.indice = indice;
                else
                    throw new NoSuchElementException("only " + nElem + " elements");
            }
    
            public boolean hasNext() {
                return (indice < nElem);
            }
    
            public K next() {
                if (hasNext())
                    return (K) VectorGeneric.this.vec[indice++];
                throw new NoSuchElementException("only " + nElem + " elements");
            }
    
            public void remove() { // default since Java 8
                throw new UnsupportedOperationException("Operacao nao suportada!");
            }
    
            @Override
            public boolean hasPrevious() {
                return (indice >= 0);
            }
    
            @Override
            public K previous() {
                if (hasPrevious())
                    return (K) VectorGeneric.this.vec[indice--];
                throw new NoSuchElementException("only " + nElem + " elements");
            }
    
            @Override
            public int nextIndex() {
                if (hasNext())
                    return indice++;
                else
                    return nElem;
            }
    
            @Override
            public int previousIndex() {
                if (hasPrevious())
                    return indice--;
                else
                    return -1;
            }
    
            @Override
            public void set(K e) {
                throw new UnsupportedOperationException("Operacao nao suportada!");
    
            }
    
            @Override
            public void add(K e) {
                throw new UnsupportedOperationException("Operacao nao suportada!");
            }
        }
    }


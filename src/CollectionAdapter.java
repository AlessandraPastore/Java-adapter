import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * <b>Homework 2, versione 1
 * <br>Alessandra Pastore 1191000 </b>
 *      <br>
 *      <br>La seguente classe CollectionAdapter implementa l'interfaccia Collection.
 *      <br>Questa Collection è implementata in modo da poter accettare doppi elementi ed elementi null.
 *      <br>Viene utilizzato il pattern Object Adapter per convertire l'interfaccia di Vector della Java Micro Edition, CLDC1.1,
 *      in quanto quest'ultima non è compatibile con quella di Collection della J2SE.
 *      <br>Ho preferito l'utilizzo del pattern Object Adapter, piuttosto che del ClassAdapter in modo da non estendere l'adaptee (Vector), bensì
 *      di mantenerne un istanza attiva e di lavorare con essa.
 *      In caso di mantenimento dell'adapter nel riconoscere nuove sottoclassi dell'adaptee, basterà aggiungere le istanze delle sottoclassi stesse
 *      piuttosto che implementare da capo una nuova classe Adapter.
 *
 */
public class CollectionAdapter<E>
        implements Collection<E> {

    Vector<E> v = new Vector<>(0);

    /**
     * Returns the number of elements in this collection.
     * If this collection contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     *
     * @return the number of elements in this collection
     */
    public int size() { return v.size(); }

    /**
     * Returns true if this collection contains no elements.
     *
     * @return true if this collection contains no elements
     */
    public boolean isEmpty() { return v.isEmpty(); }

    /**
     * Returns true if this collection contains the specified element.
     * More formally, returns true if and only if this collection contains at least one element e such that Objects.equals(o, e).
     *
     * @param o element whose presence in this collection  is to be tested
     * @return true if this collection  contains the specified element
     * @throws ClassCastException if the type of the specified element is incompatible with this collection
     */
    public boolean contains(Object o) {
        E el = (E) o;   //classcast
        return v.contains(el);
    }

    /**
     * Returns an iterator over the elements in this collection.
     * There are no guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a guarantee).
     *
     * @return an Iterator over the elements in this collection
     */
    public Iterator<E> iterator() { return new CollIterator(); }

    /**
     * Returns an array containing all of the elements in this collection.
     * If this collection makes any guarantees as to what order its elements are returned by its iterator, this method must return the elements in the same order.
     * The returned array will be "safe" in that no references to it are maintained by this collection.
     * (In other words, this method must allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * @return an array containing all of the elements in this collection
     */
    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        Iterator<E> iter = this.iterator();
        int i=0;
        while(iter.hasNext()){
            array[i] = iter.next();
            i++;
        }
        return array;
    }

    /**
     * Returns an array containing all of the elements in this collection;
     * the runtime type of the returned array is that of the specified array.
     *
     * @param a
     * @param <T>
     * @throws UnsupportedOperationException
     */
    public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException(); }

    /**
     * Ensures that this collection contains the specified element (optional operation).
     * Returns true if this collection changed as a result of the call.
     *
     * @param e element whose presence in this collection is to be ensured
     * @return true if this collection changed as a result of the call
     * @throws ClassCastException if the class of the specified element prevents it from being added to this collection
     */
    public boolean add(E e) {
        E el = (E) e;   //classcast
        v.addElement(el);
        return true;
    }

    /**
     * Removes a single instance of the specified element from this collection, if it is present (optional operation).
     * More formally, removes an element e such that Objects.equals(o, e), if this collection contains one or more such elements.
     * Returns true if this collection contained the specified element (or equivalently, if this collection changed as a result of the call).
     *
     * @param o element to be removed from this collection, if present
     * @return true if an element was removed as a result of this call
     * @throws ClassCastException if the class of the specified element prevents it from being added to this collection
     */
    public boolean remove(Object o) {
        if(this.contains(o)) return v.removeElement(o);
        return false;
    }

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     *
     * @param c collection to be checked for containment in this collection
     * @return true if this collection contains all of the elements in the specified collection
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this collection
     * @throws NullPointerException if the specified collection is null
     */
    public boolean containsAll(Collection<?> c) {
        CollectionAdapter<E> coll = (CollectionAdapter<E>) c;       //classcast
        Iterator<E> iter = coll.iterator();
        while(iter.hasNext()){
            E el = iter.next();
            if(!this.contains(el)) return false;
        }
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this collection (optional operation).
     * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the specified collection is this collection, and this collection is nonempty.)
     *
     * @param c collection containing elements to be added to this collection
     * @return true if this collection changed as a result of the call
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this collection
     * @throws NullPointerException if the specified collection is null
     */
    public boolean addAll(Collection<? extends E> c) {
        CollectionAdapter<E> coll = (CollectionAdapter<E>) c;       //classcast
        Iterator<E> iter = coll.iterator();
        boolean change = true;
        while(iter.hasNext()){
            if(!this.add(iter.next())) change = false;
        }
        return change;
    }

    /**
     * Removes all of this collection's elements that are also contained in the specified collection (optional operation).
     * After this call returns, this collection will contain no elements in common with the specified collection.
     *
     * @param c collection containing elements to be removed from this collection
     * @return true if this collection changed as a result of the call
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this collection
     * @throws NullPointerException if the specified collection is null
     */
    public boolean removeAll(Collection<?> c) {
        CollectionAdapter<E> coll = (CollectionAdapter<E>) c;       //classcast
        Iterator<E> iter = coll.iterator();
        boolean change = false;
        while(iter.hasNext()){
            E el = iter.next();
            if(this.contains(el)) {
                change = true;
                while(change) change = this.remove(el);
                change = true;
            }
        }
        return change;
    }

    /**
     * Removes all of the elements of this collection that satisfy the given predicate.
     *
     * @param filter
     * @throws UnsupportedOperationException
     */
    public boolean removeIf(Predicate<? super E> filter) {throw new UnsupportedOperationException();}

    /**
     * Retains only the elements in this collection that are contained in the specified collection (optional operation).
     * In other words, removes from this collection all of its elements that are not contained in the specified collection.
     *
     * @param c collection containing elements to be retained in this collection
     * @return true if this collection changed as a result of the call
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this collection
     * @throws NullPointerException if the specified collection is null
     */
    public boolean retainAll(Collection<?> c) {
        if(c == null) throw new NullPointerException();
        CollectionAdapter<E> coll = (CollectionAdapter<E>) c;       //classcast
        Iterator<E> iter = this.iterator();
        boolean change = false;
        while(iter.hasNext()){
            E el = iter.next();
            if(!coll.contains(el)) {
                change = true;
                iter.remove();
            }
        }
        return change;
    }

    /**
     * Removes all of the elements from this collection (optional operation).
     * The collection will be empty after this method returns.
     */
    public void clear() { v.removeAllElements(); }

    /**
     * Compares the specified object with this collection for equality.
     *
     * @param o object to be compared for equality with this collection
     * @return true if the specified object is equal to this collection
     */
    public boolean equals(Object o) {
        CollectionAdapter<E> obj = null;
        try{
           obj = (CollectionAdapter<E>) o;
        }
        catch(ClassCastException cce) {
            return false;
        }
        return o == this || v.equals(obj.v);
    }

    /**
     * Returns the hash code value for this collection.
     * While the Collection interface adds no stipulations to the general contract for the Object.hashCode method,
     * programmers should take note that any class that overrides the Object.equals method must also override the Object.hashCode method in order to satisfy the general contract for the Object.hashCode method.
     * In particular, c1.equals(c2) implies that c1.hashCode()==c2.hashCode().
     *
     * @return the hash code value for this collection
     */
    public int hashCode() {
        return v.hashCode();
    }

    /**
     * Creates a Spliterator over the elements in this collection.
     *
     * @throws UnsupportedOperationException
     */
    public Spliterator<E> spliterator() {throw new UnsupportedOperationException();}

    /**
     * Returns a sequential Stream with this collection as its source.
     *
     * @throws UnsupportedOperationException
     */
    public Stream<E> stream() {throw new UnsupportedOperationException();}

    /**
     * Returns a possibly parallel Stream with this collection as its source.
     *
     * @throws UnsupportedOperationException
     */
    public Stream<E> parallelStream() {throw new UnsupportedOperationException();}

    private class CollIterator
            implements ListIterator {

        private int current;
        private boolean next;
        private boolean previous;

        public CollIterator() {
            current = 0;
            next = false;
            previous = false;
        }

        /**
         * Returns true if this list iterator has more elements when traversing the list in the forward direction.
         * (In other words, returns true if next() would return an element rather than throwing an exception.)
         *
         * @return true if the list iterator has more elements when traversing the list in the forward direction
         */
        public boolean hasNext() { return (v.size() > current); }

        /**
         * Returns the next element in the list and advances the cursor position.
         * This method may be called repeatedly to iterate through the list, or intermixed with calls to previous() to go back and forth.
         * (Note that alternating calls to next and previous will return the same element repeatedly.)
         *
         * @return the next element in the list
         * @throws NoSuchElementException if the iteration has no next element
         */
        public E next() {
            if(!this.hasNext()) throw new NoSuchElementException();
            next = true;
            previous = false;
            return (E) v.elementAt(current++);
        }

        /**
         * Returns true if this list iterator has more elements when traversing the list in the reverse direction.
         * (In other words, returns true if previous() would return an element rather than throwing an exception.)
         *
         * @return true if the list iterator has more elements when traversing the list in the reverse direction
         */
        public boolean hasPrevious() { return current != 0; }

        /**
         * Returns the previous element in the list and moves the cursor position backwards.
         * This method may be called repeatedly to iterate through the list backwards, or intermixed with calls to next() to go back and forth.
         * (Note that alternating calls to next and previous will return the same element repeatedly.)
         *
         * @return the previous element in the list
         * @throws NoSuchElementException if the iteration has no previous element
         */
        public E previous() {
            if(!this.hasPrevious()) throw new NoSuchElementException();
            previous = true;
            next = false;
            return (E) v.elementAt(--current);
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to next().
         * (Returns list size if the list iterator is at the end of the list.)
         *
         * @return the index of the element that would be returned by a subsequent call to next,
         * or list size if the list iterator is at the end of the list
         */
        public int nextIndex() {
            return current;
        }

        /**
         * Returns the index of the element that would be returned by a subsequent call to previous().
         * (Returns -1 if the list iterator is at the beginning of the list.)
         *
         * @return the index of the element that would be returned by a subsequent call to previous,
         *  or -1 if the list iterator is at the beginning of the list
         */
        public int previousIndex() {
            return current-1;
        }

        /**
         * Removes from the list the last element that was returned by next() or previous() (optional operation).
         * This call can only be made once per call to next or previous.
         * It can be made only if add(E) has not been called after the last call to next or previous.
         *
         * @throws IllegalStateException - if neither next nor previous have been called,
         *  or remove or add have been called after the last call to next or previous
         */
        public void remove() {
            if(!next && !previous) throw new IllegalStateException();
            if(next) v.removeElementAt(--current);
            if(previous) v.removeElementAt(current);
            next = false;
            previous = false;
        }

        /**
         * Replaces the last element returned by next() or previous() with the specified element (optional operation).
         * This call can be made only if neither remove() nor add(E) have been called after the last call to next or previous.
         *
         * @param e the element with which to replace the last element returned by next or previous
         * @throws ClassCastException if the class of the specified element prevents it from being added to this list
         * @throws IllegalStateException if neither next nor previous have been called, or remove or add have been called after the last call to next or previous
         */
        public void set(Object e) {
            if(!next && !previous) throw new IllegalStateException();
            E el = (E) e;       //CLASSCAST

            if(next) v.setElementAt(el, current-1);
            if(previous) v.setElementAt(el, current);

            next = false;
            previous = false;
        }

        /**
         * Inserts the specified element into the list (optional operation).
         * The element is inserted immediately before the element that would be returned by next(), if any, and after the element that would be returned by previous(), if any.
         * (If the list contains no elements, the new element becomes the sole element on the list.)
         * The new element is inserted before the implicit cursor: a subsequent call to next would be unaffected, and a subsequent call to previous would return the new element.
         * (This call increases by one the value that would be returned by a call to nextIndex or previousIndex.)
         *
         * @param e the element to insert
         * @throws ClassCastException if the class of the specified element prevents it from being added to this list
         */
        public void add(Object e) {
            E el = (E) e;       //CLASSCAST
            v.insertElementAt(el,current);
            next = false;
            previous = false;
        }
    }
}
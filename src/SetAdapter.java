import java.util.*;

/**
 * <b>Homework 2, versione 1
 * <br>Alessandra Pastore 1191000 </b>
 *      <br>
 *      <br>La seguente classe SetAdapter implementa l'interfaccia Set.
 *      <br>Viene utilizzato il pattern Object Adapter per convertire l'interfaccia di HashTable della Java Micro Edition, CLDC1.1,
 *      in quanto quest'ultima non è compatibile con quella di Set della J2SE.
 *      <br>Ho preferito l'utilizzo del pattern Object Adapter, piuttosto che del ClassAdapter in modo da non estendere l'adaptee (HashTable), bensì
 *      di mantenerne un istanza attiva e di lavorare con essa.
 *      In caso di mantenimento dell'adapter nel riconoscere nuove sottoclassi dell'adaptee, basterà aggiungere le istanze delle sottoclassi stesse
 *      piuttosto che implementare da capo una nuova classe Adapter.
 *
 */
public class SetAdapter<E>
        implements Set<E>
{
    private Hashtable<E,E> a = new Hashtable<E,E>();    //tutti i value sono uguali alla key e ha solo chiavi uniche

    /**
     * Returns the number of elements in this set (its cardinality).
     * If this set contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     *
     * @return the number of elements in this set (its cardinality)
     */
    public int size() {
        return a.size();
    }

    /**
     * Returns true if this set contains no elements.
     *
     * @return true if this set contains no elements
     */
    public boolean isEmpty() { return a.isEmpty(); }

    /**
     * Returns true if this set contains the specified element.
     * More formally, returns true if and only if this set contains an element e such that Objects.equals(o, e).
     *
     * @param o element whose presence in this set is to be tested
     * @return true if this set contains the specified element
     * @throws ClassCastException if the type of the specified element is incompatible with this set
     * @throws NullPointerException if the specified element is null
     */
    public boolean contains(Object o) {
        if(o == null)   throw new NullPointerException();
        E e =(E)o;  //classcast
        return a.containsKey(e);
    }

    /**
     * Returns an iterator over the elements in this set.
     * The elements are returned in no particular order
     * (unless this set is an instance of some class that provides a guarantee).
     *
     * @return an iterator over the elements in this set
     */
    public Iterator<E> iterator() {
        return new SetIterator();
    }

    /**
     * Returns an array containing all of the elements in this set. If this set makes any guarantees as to what order its elements are returned by its iterator, this method must return the elements in the same order.
     * The returned array will be "safe" in that no references to it are maintained by this set. (In other words, this method must allocate a new array even if this set is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * @return an array containing all the elements in this set
     */
    public Object[] toArray() {
        Object[] array = new Object[this.size()];
        Iterator<E> iter = this.iterator();
        int i = 0;
        while(iter.hasNext()){
            array[i++] = iter.next();
        }
        return array;
    }

    /**
     * Returns an array containing all of the elements in this set;
     * the runtime type of the returned array is that of the specified array.
     *
     * @param a
     * @param <T>
     * @throws UnsupportedOperationException
     */
    public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException();}

    /**
     * Adds the specified element to this set if it is not already present (optional operation).
     * More formally, adds the specified element e to this set if the set contains no element e2 such that Objects.equals(e, e2).
     * If this set already contains the element, the call leaves the set unchanged and returns false.
     * In combination with the restriction on constructors, this ensures that sets never contain duplicate elements.
     *
     * @param e element to be added to this set
     * @return true if this set did not already contain the specified element
     * @throws ClassCastException if the class of the specified element prevents it from being added to this set
     * @throws NullPointerException if the specified element is null
     */
    public boolean add(E e) {
        if(!this.contains(e)) {     //classcast e nullpointer
            a.put(e,e);
            return true;
        }
        return false;
    }

    /**
     * Removes the specified element from this set if it is present (optional operation).
     * More formally, removes an element e such that Objects.equals(o, e), if this set contains such an element.
     * Returns true if this set contained the element (or equivalently, if this set changed as a result of the call).
     * (This set will not contain the element once the call returns.)
     *
     * @param o object to be removed from this set, if present
     * @return true if this set contained the specified element
     * @throws ClassCastException if the type of the specified element is incompatible with this set
     * @throws NullPointerException if the specified element is null and this set does not permit null elements
     */
    public boolean remove(Object o) {
        if(this.contains(o)) {  //classcast nullptr
            a.remove(o);
            return true;
        }
        return false;
    }

    /**
     * Returns true if this set contains all of the elements of the specified collection.
     * If the specified collection is also a set, this method returns true if it is a subset of this set.
     *
     * @param c collection to be checked for containment in this set
     * @return true if this set contains all of the elements of the specified collection
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this set
     * @throws NullPointerException if the specified collection contains one or more null elements or if the specified collection is null
     */
    public boolean containsAll(Collection<?> c) {
        CollectionAdapter<E> coll = (CollectionAdapter<E>) c;   //classcast
        ListIterator iter = (ListIterator) coll.iterator();
        while(iter.hasNext()) {
            if(iter.next() == null) throw new NullPointerException();
        }
        while(iter.hasPrevious()){
            if(!this.contains(iter.previous())) return false;   //nullptr
        }
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this set if they're not already present (optional operation).
     * If the specified collection is also a set, the addAll operation effectively modifies this set so that its value is the union of the two sets.
     * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
     *
     * @param c collection containing elements to be added to this set
     * @return true if this set changed as a result of the call
     * @throws ClassCastException if the class of an element of the specified collection prevents it from being added to this set
     * @throws NullPointerException if the specified collection contains one or more null elements or if the specified collection is null
     */
    public boolean addAll(Collection<? extends E> c) {
        CollectionAdapter<E> coll = (CollectionAdapter<E>) c;   //classcast
        ListIterator iter = (ListIterator) coll.iterator();
        boolean change = false;
        while(iter.hasNext()) {
            if(iter.next() == null) throw new NullPointerException();
        }
        while(iter.hasPrevious()){
            E tmp = (E) iter.previous();
            if(!this.contains(tmp)) change = this.add(tmp);

        }
        return change;
    }

    /**
     * Retains only the elements in this set that are contained in the specified collection (optional operation).
     * In other words, removes from this set all of its elements that are not contained in the specified collection.
     * If the specified collection is also a set, this operation effectively modifies this set so that its value is the intersection of the two sets.
     *
     * @param c collection containing elements to be retained in this set
     * @return true if this set changed as a result of the call
     * @throws ClassCastException if the class of an element of this set is incompatible with the specified collection
     * @throws NullPointerException if this set contains a null element or if the specified collection is null
     */
    public boolean retainAll(Collection<?> c) {
        CollectionAdapter<E> coll = (CollectionAdapter<E>) c;   //classcast
        Iterator iter = this.iterator();
        ListIterator itertmp = (ListIterator) coll.iterator();
        boolean change = false;
        while(itertmp.hasNext()) {
            if(itertmp.next() == null) throw new NullPointerException();
        }
        while(iter.hasNext()){
            E tmp = (E) iter.next();
            if(!coll.contains(tmp)) change = this.remove(tmp);
        }
        return change;
    }

    /**
     * Removes from this set all of its elements that are contained in the specified collection (optional operation).
     * If the specified collection is also a set, this operation effectively modifies this set so that its value is the asymmetric set difference of the two sets.
     *
     * @param c collection containing elements to be removed from this set
     * @return true if this set changed as a result of the call
     * @throws ClassCastException if the class of an element of this set is incompatible with the specified collection
     * @throws NullPointerException if this set contains a null element or if the specified collection is null
     */
    public boolean removeAll(Collection<?> c) {
        CollectionAdapter<E> coll = (CollectionAdapter<E>) c;   //classcast
        ListIterator iter = (ListIterator) coll.iterator();
        while(iter.hasNext()) {
            if(iter.next() == null) throw new NullPointerException();
        }
        boolean change = false;
        while(iter.hasPrevious()){
            E tmp = (E) iter.previous();
            if(this.contains(tmp)) change = this.remove(tmp);
        }
        return change;
    }

    /**
     * Removes all of the elements from this set (optional operation).
     * The set will be empty after this call returns.
     */
    public void clear() { a.clear();}

    /**
     * Compares the specified object with this set for equality.
     * Returns true if the specified object is also a set, the two sets have the same size, and every member of the specified set is contained in this set
     * (or equivalently, every member of this set is contained in the specified set).
     * This definition ensures that the equals method works properly across different implementations of the set interface.
     *
     * @param o object to be compared for equality with this set
     * @return true if the specified object is equal to this set
     */
    public boolean equals(Object o) {
        SetAdapter<E> obj = null;
        try{
            obj = (SetAdapter<E>) o;
        }
        catch(ClassCastException cce){
            return false;
        }
        if(obj.size() == this.size()){
            Iterator<E> iter = obj.iterator();
            while(iter.hasNext()){
                if(!this.contains(iter.next())) return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Returns the hash code value for this set.
     * The hash code of a set is defined to be the sum of the hash codes of the elements in the set, where the hash code of a null element is defined to be zero.
     * This ensures that s1.equals(s2) implies that s1.hashCode()==s2.hashCode() for any two sets s1 and s2, as required by the general contract of Object.hashCode().
     *
     * @return the hash code value for this set
     */
    public int hashCode() {
        int sum = 0;
        Iterator<E> iter = this.iterator();
        while(iter.hasNext()){
            sum += iter.next().hashCode();
        }
        return sum;
    }

    /**
     * Creates a Spliterator over the elements in this set.
     *
     * @throws UnsupportedOperationException
     */
    public Spliterator<E> spliterator() {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing zero elements.
     *
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of() {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing one element.
     *
     * @param e1
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of(E e1) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing two elements.
     *
     * @param e1
     * @param e2
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of(E e1, E e2) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing three elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of(E e1, E e2, E e3) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing four elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param e4
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of(E e1, E e2, E e3, E e4) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing five elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param e4
     * @param e5
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing six elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param e4
     * @param e5
     * @param e6
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing seven elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param e4
     * @param e5
     * @param e6
     * @param e7
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing eight elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param e4
     * @param e5
     * @param e6
     * @param e7
     * @param e8
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing nine elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param e4
     * @param e5
     * @param e6
     * @param e7
     * @param e8
     * @param e9
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing ten elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param e4
     * @param e5
     * @param e6
     * @param e7
     * @param e8
     * @param e9
     * @param e10
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable set containing an arbitrary number of elements.
     *
     * @param elements
     * @param <E>
     * @throws UnsupportedOperationException
     */
    public <E> Set<E> of(E... elements) {throw new UnsupportedOperationException();}

    private class SetIterator
            implements ListIterator {

        private Enumeration<E> set;
        private int current;
        private E value;
        private boolean next;
        private boolean previous;

        public SetIterator() {
            set = (Enumeration<E>) a.keys();
            value = null;
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
        public boolean hasNext() {
            if(previous){
                previous();
                current++;
            }
            return set.hasMoreElements();
        }

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
            current++;
            return value = set.nextElement();
        }

        /**
         * Returns true if this list iterator has more elements when traversing the list in the reverse direction.
         * (In other words, returns true if previous() would return an element rather than throwing an exception.)
         *
         * @return true if the list iterator has more elements when traversing the list in the reverse direction
         */
        public boolean hasPrevious() {
            return current != 0;
        }

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
            set = (Enumeration<E>) a.keys();
            for(int i=0; i<current;i++){
                value = set.nextElement();
            }
            previous = true;
            next = false;
            current--;
            return value;
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
            if(next){
                value = this.previous();
                SetAdapter.this.remove(value);
                previous = false;
            }
            if(previous) SetAdapter.this.remove(value);
            next = false;
            previous = false;
        }

        /**
         * Replaces the last element returned by next() or previous() with the specified element
         *
         * @param o
         * @throws UnsupportedOperationException
         */
        public void set(Object o) {
            throw new UnsupportedOperationException();
        }

        /**
         * Inserts the specified element into the list
         *
         * @param o
         * @throws UnsupportedOperationException
         */
        public void add(Object o) {
            throw new UnsupportedOperationException();
        }
    }
}
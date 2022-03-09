import java.util.*;
import java.util.function.UnaryOperator;

/**
 * <b>Homework 2, versione 1
 * <br>Alessandra Pastore 1191000 </b>
 *      <br>
 *      <br>La seguente classe ListAdapter implementa l'interfaccia List.
 *      <br>Viene utilizzato il pattern Object Adapter per convertire l'interfaccia di Vector della Java Micro Edition, CLDC1.1,
 *      in quanto quest'ultima non è compatibile con quella di List della J2SE.
 *      <br>Ho preferito l'utilizzo del pattern Object Adapter, piuttosto che del ClassAdapter in modo da non estendere l'adaptee (Vector), bensì
 *      di mantenerne un istanza attiva e di lavorare con essa.
 *      In caso di mantenimento dell'adapter nel riconoscere nuove sottoclassi dell'adaptee, basterà aggiungere le istanze delle sottoclassi stesse
 *      piuttosto che implementare da capo una nuova classe Adapter.
 *
 */
public class ListAdapter<E>
        implements List<E>
{
    private Vector<E> v = new Vector<>(0);

    /**
     * Returns the number of elements in this list. If this list contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     *
     * @return the number of elements in this list
     */
    public int size() { return v.size(); }

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    public boolean isEmpty() { return v.isEmpty(); }

    /**
     * Returns true if this list contains the specified element.
     * More formally, returns true if and only if this list contains at least one element e such that Objects.equals(o, e).
     *
     * @param o element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     * @throws ClassCastException if the type of the specified element is incompatible with this list
     * @throws NullPointerException if the specified element is null
     */
    public boolean contains(Object o) {
        if(o == null) throw new NullPointerException();
        E obj = (E) o;  //classcast
        return v.contains(obj);
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<E> iterator() { return new AdaptListIterator(); }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     * The returned array will be "safe" in that no references to it are maintained by this list.
     * (In other words, this method must allocate a new array even if this list is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * @return an array containing all of the elements in this list in proper sequence
     */
    public Object[] toArray() {

        Object[] array = new Object[this.size()];
        v.copyInto(array);
        return array;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element);
     * the runtime type of the returned array is that of the specified array.
     *
     * @param a
     * @param <T>
     * @throws UnsupportedOperationException
     */
    public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException(); }

    /**
     * Appends the specified element to the end of this list
     *
     * @param e element to be appended to this list
     * @return true
     * @throws ClassCastException if the type of the specified element is incompatible with this list
     * @throws NullPointerException if the specified element is null
     */
    public boolean add(E e) {
        if(e == null) throw new NullPointerException();
        E el = (E) e;   //classcast
        v.addElement(el);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list, if it is present (optional operation).
     * If this list does not contain the element, it is unchanged.
     * More formally, removes the element with the lowest index i such that Objects.equals(o, get(i)) (if such an element exists).
     * Returns true if this list contained the specified element (or equivalently, if this list changed as a result of the call).
     *
     * @param o element to be removed from this list, if present
     * @return true if this list contained the specified element
     * @throws ClassCastException if the type of the specified element is incompatible with this list
     * @throws NullPointerException if the specified element is null
     */
    public boolean remove(Object o) {
        if(o == null) throw new NullPointerException();
        E obj = (E) o;  //classcast
        return v.remove(obj);
    }

    /**
     * Returns true if this list contains all of the elements of the specified collection.
     *
     * @param c collection to be checked for containment in this list
     * @return true if this list contains all of the elements of the specified collection
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this list
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
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator
     * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
     * (Note that this will occur if the specified collection is this list, and it's nonempty.)
     *
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this list
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
            this.add(tmp);

        }
        return true;
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position (optional operation).
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (increases their indices).
     * The new elements will appear in this list in the order that they are returned by the specified collection's iterator.
     * The behavior of this operation is undefined if the specified collection is modified while the operation is in progress.
     * (Note that this will occur if the specified collection is this list, and it's nonempty.)
     *
     * @param index index at which to insert the first element from the specified collection
     * @param c collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this list
     * @throws NullPointerException if the specified collection contains one or more null elements or if the specified collection is null
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        CollectionAdapter<E> coll = (CollectionAdapter<E>) c;
        ListIterator iter = (ListIterator) coll.iterator();
        while(iter.hasNext()) {
            if(iter.next() == null) throw new NullPointerException();
        }
        iter = (ListIterator) coll.iterator();
        try{
            while(iter.hasNext()){
                E el = (E) iter.next();
                v.insertElementAt(el, index++);
            }
        }
        catch (ArrayIndexOutOfBoundsException aibe){
            IndexOutOfBoundsException iobe = new IndexOutOfBoundsException();
            iobe.initCause(aibe);
            throw iobe;
        }

        return true;
    }

    /**
     * Removes from this list all of its elements that are contained in the specified collection
     *
     * @param c collection containing elements to be removed from this list
     * @return true if this list changed as a result of the call
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this list
     * @throws NullPointerException if the specified collection contains one or more null elements or if the specified collection is null
     */
    public boolean removeAll(Collection<?> c) {
        CollectionAdapter<E> coll = (CollectionAdapter<E>) c;
        ListIterator iter = (ListIterator) coll.iterator();
        boolean change = false;
        while(iter.hasNext()) {
            if(iter.next() == null) throw new NullPointerException();
        }
        while(iter.hasPrevious()){
            E el = (E) iter.previous();
            if(this.contains(el)) {
                change = true;
                while(change) change = this.remove(el);
                change = true;
            }
        }
        return change;
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection (optional operation).
     * In other words, removes from this list all of its elements that are not contained in the specified collection.
     *
     * @param c collection containing elements to be retained in this list
     * @return true if this list changed as a result of the call
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this list
     * @throws NullPointerException if the specified collection contains one or more null elements or if the specified collection is null
     */
    public boolean retainAll(Collection<?> c) {
        ListIterator<E> iter = this.listIterator();
        CollectionAdapter<E> coll = (CollectionAdapter<E>) c;
        ListIterator itertmp = (ListIterator) coll.iterator();
        boolean change = false;
        while(itertmp.hasNext()) {
            if(itertmp.next() == null) throw new NullPointerException();
        }
        while (iter.hasNext()){
            E el = iter.next();
            if(!coll.contains(el)) {
                change = true;
                iter.remove();
            }
        }
        return change;
    }

    /**
     * Replaces each element of this list with the result of applying the operator to that element.
     *
     * @param operator
     * @throws UnsupportedOperationException
     */
    public void replaceAll(UnaryOperator<E> operator) {throw new UnsupportedOperationException();}

    /**
     * Sorts this list according to the order induced by the specified Comparator.
     *
     * @param c
     * @throws UnsupportedOperationException
     */
    public void sort(Comparator<? super E> c) {throw new UnsupportedOperationException();}

    /**
     * Removes all of the elements from this list (optional operation). The list will be empty after this call returns.
     */
    public void clear() { v.removeAllElements(); }

    /**
     * Compares the specified object with this list for equality.
     * Returns true if and only if the specified object is also a list, both lists have the same size, and all corresponding pairs of elements in the two lists are equal.
     * (Two elements e1 and e2 are equal if Objects.equals(e1, e2).) In other words, two lists are defined to be equal if they contain the same elements in the same order.
     * This definition ensures that the equals method works properly across different implementations of the List interface.
     *
     * @param o the object to be compared for equality with this list
     * @return true if the specified object is equal to this list
     */
    public boolean equals(Object o) {
        ListAdapter<E> obj = null;
        try{
            obj = (ListAdapter<E>) o;
        }
        catch (ClassCastException cce){
            return false;
        }
        if(obj.size() == this.size()){
            Iterator<E> iter1 = this.iterator();
            Iterator<E> iter2 = obj.iterator();
            while(iter1.hasNext()){
                if(iter1.next() != iter2.next()) return false;
            }
        }
        else{return false;}
        return true;
    }

    /**
     * Returns the hash code value for this list.
     *
     * @return the hash code value for this list
     */
    public int hashCode() {
        int hashCode = 1;
        Iterator<E> iter = this.iterator();
        while(iter.hasNext()){
            E el = iter.next();
            int tmp = 0;
            if(el != null) tmp = el.hashCode();
            hashCode = 31*hashCode + tmp;
        }
        return hashCode;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E get(int index) {
        try{
            return v.elementAt(index);
        }
        catch(ArrayIndexOutOfBoundsException aibo){
            IndexOutOfBoundsException iobe = new IndexOutOfBoundsException();
            iobe.initCause(aibo);
            throw iobe;
        }
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list
     * @throws NullPointerException if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E set(int index, E element) {
        if(element == null) throw new NullPointerException();
        E el = (E) element;
        E oldEl = this.get(index);  //indexout
        try{
            v.setElementAt(el, index);
        }
        catch(ArrayIndexOutOfBoundsException aibo){
            IndexOutOfBoundsException iobe = new IndexOutOfBoundsException();
            iobe.initCause(aibo);
            throw iobe;
        }
        return oldEl;
    }

    /**
     * Inserts the specified element at the specified position in this list (optional operation).
     * Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices).
     *
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list
     * @throws NullPointerException if the specified element is null
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void add(int index, E element) {
        if(element == null) throw new NullPointerException();
        E el = (E) element;
        try{
            v.insertElementAt(element,index);
        }
        catch(ArrayIndexOutOfBoundsException aibo){
            IndexOutOfBoundsException iobe = new IndexOutOfBoundsException();
            iobe.initCause(aibo);
            throw iobe;
        }
    }

    /**
     * Removes the element at the specified position in this list (optional operation). Shifts any subsequent elements to the left (subtracts one from their indices).
     * Returns the element that was removed from the list.
     *
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E remove(int index) {
        E oldEl = this.get(index);
        try{
            v.removeElementAt(index);
        }
        catch(ArrayIndexOutOfBoundsException aibo){
            IndexOutOfBoundsException iobe = new IndexOutOfBoundsException();
            iobe.initCause(aibo);
            throw iobe;
        }
        return oldEl;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index i such that Objects.equals(o, get(i)), or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this list, or -1 if this list does not contain the element
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list
     * @throws NullPointerException if the specified element is null
     */
    public int indexOf(Object o) {
        if(o == null) throw new NullPointerException();
        E el = (E) o;       //class cast
        return v.indexOf(el);
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index i such that Objects.equals(o, get(i)), or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the last occurrence of the specified element in this list, or -1 if this list does not contain the element
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list
     * @throws NullPointerException if the specified element is null
     */
    public int lastIndexOf(Object o) {
        if(o == null) throw new NullPointerException();
        E el = (E) o;       //class cast
        return v.lastIndexOf(el);
    }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence).
     *
     * @return a list iterator over the elements in this list (in proper sequence)
     */
    public ListIterator<E> listIterator() { return new AdaptListIterator(); }

    /**
     * Returns a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list.
     * The specified index indicates the first element that would be returned by an initial call to next.
     * An initial call to previous would return the element with the specified index minus one.
     *
     * @param index index of the first element to be returned from the list iterator (by a call to next)
     * @return a list iterator over the elements in this list (in proper sequence), starting at the specified position in the list
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public ListIterator<E> listIterator(int index) {
        if(index < 0 || index > this.size()) throw new IndexOutOfBoundsException();
        ListIterator<E> iter = this.listIterator();
        int i = 0;
        while(i<index){
            iter.next();
            i++;
        }
        return iter;
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * (If fromIndex and toIndex are equal, the returned list is empty.)
     * The returned list is backed by this list, so non-structural changes in the returned list are reflected in this list, and vice-versa.
     * The returned list supports all of the optional list operations supported by this list.
     *
     * @param fromIndex low endpoint (inclusive) of the subList
     * @param toIndex high endpoint (exclusive) of the subList
     * @return a view of the specified range within this list
     * @throws IndexOutOfBoundsException for an illegal endpoint index value
     */
    public List<E> subList(int fromIndex, int toIndex) {
        if(fromIndex < 0 || toIndex > this.size() || fromIndex > toIndex) throw new IndexOutOfBoundsException();
        return new MySubList(this, fromIndex, toIndex);
    }

    /**
     * Creates a Spliterator over the elements in this list.
     *
     * @throws UnsupportedOperationException
     */
    public Spliterator<E> spliterator() {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing none elements.
     *
     * @param <E>
     * @return
     */
    public <E> List<E> of() {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing one element.
     *
     * @param e1
     * @param <E>
     * @return
     */
    public <E> List<E> of(E e1) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing two elements.
     *
     * @param e1
     * @param e2
     * @param <E>
     * @return
     */
    public <E> List<E> of(E e1, E e2) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing three elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param <E>
     * @return
     */
    public <E> List<E> of(E e1, E e2, E e3) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing four elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param e4
     * @param <E>
     * @return
     */
    public <E> List<E> of(E e1, E e2, E e3, E e4) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing five elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param e4
     * @param e5
     * @param <E>
     * @return
     */
    public <E> List<E> of(E e1, E e2, E e3, E e4, E e5) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing six elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param e4
     * @param e5
     * @param e6
     * @param <E>
     * @return
     */
    public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing seven elements.
     *
     * @param e1
     * @param e2
     * @param e3
     * @param e4
     * @param e5
     * @param e6
     * @param e7
     * @param <E>
     * @return
     */
    public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing eight elements.
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
     * @return
     */
    public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing nine elements.
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
     * @return
     */
    public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing ten elements.
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
     * @return
     */
    public <E> List<E> of(E e1, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {throw new UnsupportedOperationException();}

    /**
     * Returns an immutable list containing an arbitrary number of elements.
     *
     * @param elements
     * @param <E>
     * @return
     */
    public <E> List<E> of(E... elements) {throw new UnsupportedOperationException();}

    /**
     * Una sublist presa dalla lista originale
     */
    private class MySubList<E>
            extends ListAdapter<E> {

        private ListAdapter parent;
        private final int start;
        private int end;

        public MySubList(ListAdapter parent, int start, int end) {
            this.parent = parent;
            this.start = start;
            this.end = end;
        }

        public int size() { return end - start ; }

        public boolean isEmpty() { return this.size() == 0 ; }

        public boolean contains(Object o) {
            if(o == null) throw new NullPointerException();
            E obj = (E) o;  //classcast
            if(parent.indexOf(obj) != -1) {
                for(int i=start; i<end;++i) {
                    if(parent.get(i).equals(obj)) return true;
                }
            }
            return false;
        }

        public Iterator iterator() { return new AdaptListIterator(); }

        public Object[] toArray() {
            Iterator<E> iter = this.iterator();
            Object[] array = new Object[this.size()];
            int i = 0;
            while(iter.hasNext()){
                array[i++] = iter.next();
             }
            return array;
        }

        public boolean add(E o) {
            this.add(this.size(),o);
            return true;
        }

        public boolean remove(Object o) {
            E el = (E) o;
            if(this.contains(el)) {
                Iterator<E> iter = this.iterator();
                while(iter.hasNext()) {
                    E x = iter.next();
                    if(x.equals(el)) {
                        iter.remove();
                        return true;
                    }
                }
            }
            return false;
        }

        public boolean addAll(Collection<? extends E> c) {
            return this.addAll(this.size(),c);
        }

        public boolean addAll(int index, Collection<? extends E> c) {
            addRangeCheck(index);
            boolean result = parent.addAll(start+index,c);
            if(result) end+=c.size();
            return result;
        }

        public E set(int index, E element) {
            rangeCheck(index);
            return (E) parent.set(start+index,element);
        }

        public E get(int index) {
            rangeCheck(index);
            return (E) parent.get(start+index);
        }

        public void add(int index, E element) {
            addRangeCheck(index);
            parent.add(start+index,element);
            end++;
        }

        public E remove(int index) {
            rangeCheck(index);
            E result = (E) parent.remove(start+index);
            end--;
            return result;
        }

        public void clear() {
            int index = this.size();
            while(index > 0) {
                this.remove(index-1);
                index--;
            }
        }

        public int indexOf(Object o) {
            E obj = (E) o;
            Iterator iter = this.iterator();
            int index = 0;
            while(iter.hasNext()){
                if(iter.next().equals(obj)) return index;
                index++;
            }
            return -1;
        }

        public int lastIndexOf(Object o) {
            E obj = (E) o;
            ListIterator iter = this.listIterator();
            if(this.contains(obj)) {
                int index = this.size()-1;
                while(iter.hasNext()) iter.next();
                while(iter.hasPrevious()){
                    if(iter.previous().equals(obj)) return index;
                    index--;
                }
            }
            return -1;
        }

        public ListIterator<E> listIterator() { return new AdaptListIterator(); }

        public ListIterator<E> listIterator(int index) {
            addRangeCheck(index);
            return new ListIterator() {

                int current = index;
                boolean next = false;
                boolean previous = false;


                public boolean hasNext() { return MySubList.this.size() > current; }

                public E next() {
                    if(!this.hasNext()) throw new NoSuchElementException();
                    next = true;
                    previous = false;
                    current++;
                    return (E) parent.get(start+current-1);
                }

                public boolean hasPrevious() { return current != 0; }

                public E previous() {
                    if(!this.hasPrevious()) throw new NoSuchElementException();
                    previous = true;
                    next = false;
                    --current;
                    return (E) parent.get(start+current);
                }

                public int nextIndex() {
                    return current;
                }

                public int previousIndex() {
                    return current-1;
                }

                public void remove() {
                    if(!next || !previous) throw new IllegalStateException();
                    if(next) parent.remove(start+current-1);
                    if(previous) parent.remove(start+current);
                    next = false;
                    previous = false;
                }

                public void set(Object e) {
                    if(!next || !previous) throw new IllegalStateException();
                    if(e == null) throw new IllegalArgumentException();
                    E el = (E) e;       //CLASSCAST

                    if(next) parent.set(start+current-1, el);
                    if(previous) parent.set(start+current, el);

                    next = false;
                    previous = false;
                }

                public void add(Object e) {
                    if(e == null) throw new IllegalArgumentException();
                    E el = (E) e;       //CLASSCAST
                    parent.add(start+current, el);
                }
            };
        }

        public List<E> subList(int fromIndex, int toIndex) {
            if(fromIndex < 0 || toIndex > this.size() || fromIndex > toIndex) throw new IndexOutOfBoundsException();
            return new MySubList<E>(this, fromIndex, toIndex);
        }

        private void addRangeCheck(int index) {
            if(index > this.size() || index < 0) throw new IndexOutOfBoundsException();
        }

        private void rangeCheck(int index) {
            if(index >= this.size() || index < 0) throw new IndexOutOfBoundsException();
        }
    }

    private class AdaptListIterator
            implements ListIterator {

        private int current;
        private boolean next;
        private boolean previous;

        public AdaptListIterator() {
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
        public boolean hasNext() { return size() > current; }

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
            return (E) get(current++);
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
            return (E) get(--current);
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
            if(next) ListAdapter.this.remove(--current);
            if(previous) ListAdapter.this.remove(current);
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
            if(e == null) throw new IllegalArgumentException();
            E el = (E) e;       //CLASSCAST

            if(next) ListAdapter.this.set(current-1, el);
            if(previous) ListAdapter.this.set(current, el);

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
         * @throws IllegalArgumentException if some aspect of this element prevents it from being added to this list
         */
        public void add(Object e) {
            if(e == null) throw new IllegalArgumentException();
            E el = (E) e;       //CLASSCAST
            ListAdapter.this.add(current,el);
            next = false;
            previous = false;
        }
    }
}
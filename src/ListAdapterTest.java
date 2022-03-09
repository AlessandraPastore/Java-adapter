import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * <b>Suite di Test per ListAdapter.</b>
 * @safe.summary Vengono effettuati diversi test per ogni metodo di ListAdapter per verificarne il corretto funzionamento.
 * @safe.testsuitedesign Ogni metodo è testato attraverso multipli Asserts per verificare tutti i possibili cambiamenti che tale metodo provoca
 * rispetto agli altri metodi e allo stato della List
 */
public class ListAdapterTest {

    ListAdapter<String> tmp = new ListAdapter<>();

    /**
     * Test the size() method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that size() returns 3
     */
    @Test
    public void size() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        assertEquals(3,tmp.size());
    }

    /**
     * Test the size() method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that size() returns 0
     */
    @Test
    public void sizeEmpty(){
        assertEquals(0,tmp.size());
    }

    /**
     * Test the isEmpty() method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that isEmpty() returns true
     */
    @Test
    public void isEmptyTrue() {
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the isEmpty() method
     *
     * @safe.precondition a List of 1 element
     * @safe.postcondition none
     * @safe.testcases test to verify that isEmpty() returns false
     */
    @Test
    public void isEmptyFalse() {
        tmp.add("uno");
        assertFalse(tmp.isEmpty());
    }

    /**
     * Test the contains() method
     *
     * @safe.precondition a List of 1 element
     * @safe.postcondition none
     * @safe.testcases test to verify that contains(el) returns true
     */
    @Test
    public void containsTrue() {
        tmp.add("uno");
        assertTrue(tmp.contains("uno"));
    }

    /**
     * Test the contains() method
     *
     * @safe.precondition a List of 2 duplicate elements
     * @safe.postcondition none
     * @safe.testcases test to verify that contains(el) returns true even after removing one of them
     */
    @Test
    public void containsTrueDuplicate() {
        tmp.add("uno");
        tmp.add("uno");
        assertTrue(tmp.contains("uno"));
        tmp.remove("uno");
        assertTrue(tmp.contains("uno"));
    }

    /**
     * Test the contains() method
     *
     * @safe.precondition a List of 1 element
     * @safe.postcondition none
     * @safe.testcases test to verify that contains(el) returns false if the el was not found
     */
    @Test
    public void containsFalse() {
        tmp.add("uno");
        assertFalse(tmp.contains("due"));
    }

    /**
     * Test the contains() method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that contains(null) throws NullPointerException
     */
    @Test
    public void containsNull() {
        assertThrows(NullPointerException.class, () -> { tmp.contains(null); });
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition a List of 1 element
     * @safe.postcondition an empty List
     * @safe.testcases test to verify that calling iterator() on the List returns an actual iterator
     * that works over the elements of the List
     */
    @Test
    public void listIterator() {
        tmp.add("a");
        ListIterator iter = tmp.listIterator();
        assertEquals("a",iter.next());
        assertFalse(iter.hasNext());
        assertEquals("a",iter.previous());
        assertFalse(iter.hasPrevious());
        iter.remove();
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().remove() on the List throws IllegalStateException if not called after a next() or previosu()
     */
    @Test
    public void iteratorRemove() {
        ListIterator iter = tmp.listIterator();
        assertThrows(IllegalStateException.class, () -> { iter.remove(); });
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().next() on the List throws NoSuchElementException if there's no next element
     */
    @Test
    public void iteratorNext() {
        ListIterator iter = tmp.listIterator();
        assertThrows(NoSuchElementException.class, () -> { iter.next(); });
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().previous() on the List throws NoSuchElementException if there's no previous element
     */
    @Test
    public void iteratorPrevious() {
        ListIterator iter = tmp.listIterator();
        assertThrows(NoSuchElementException.class, () -> { iter.previous(); });
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition a List of 1 element
     * @safe.postcondition a List of 2 element
     * @safe.testcases test to verify that calling iterator().add(el) on the List actually inserts the specified element in the current position
     * of the iterator, shifting the elements after it to the right
     */
    @Test
    public void iteratorAdd() {
        tmp.add("due");
        ListIterator iter = tmp.listIterator();
        iter.add("uno");
        assertTrue(tmp.contains("uno"));
        assertTrue(tmp.contains("due"));
        assertEquals("uno",iter.next());
        assertEquals("due",iter.next());
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().add(null) on the List throws IllegalArgumentException
     */
    @Test
    public void iteratorAddNull() {
        ListIterator iter = tmp.listIterator();
        assertThrows(IllegalArgumentException.class, () -> { iter.add(null); });
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition a List of 1 element
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().set(newEl) after next() or previous() actually sets the new element
     */
    @Test
    public void iteratorSet() {
        ListIterator iter = tmp.listIterator();
        iter.add("uno");
        iter.next();
        iter.set("zero");
        assertTrue(tmp.contains("zero"));
        assertEquals("zero",iter.previous());
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition a List of 1 element
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().set(null) after next() or previous() throws IllegalArgumentException
     */
    @Test
    public void iteratorSetNull() {
        ListIterator iter = tmp.listIterator();
        iter.add("uno");
        iter.next();
        assertThrows(IllegalArgumentException.class, () -> { iter.set(null); });
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition a List of 1 element
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().set(newEl) without calling first next() or previous() throws IllegalStateException
     */
    @Test
    public void iteratorSetState() {
        ListIterator iter = tmp.listIterator();
        iter.add("uno");
        assertThrows(IllegalStateException.class, () -> { iter.set("due"); });
    }

    /**
     * Test the toArray() method
     *
     * @safe.precondition a List of 3 element
     * @safe.postcondition none
     * @safe.testcases test to verify that calling toArray() returns an array with all the elements of the List
     */
    @Test
    public void toArray() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        Object[] array = tmp.toArray();
        assertEquals(tmp.size(),array.length);
        Iterator iter = tmp.iterator();
        assertEquals(iter.next(),array[0]);
        assertEquals(iter.next(),array[1]);
        assertEquals(iter.next(),array[2]);
    }

    /**
     * Test the add(el) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition a List of 1 element
     * @safe.testcases test to verify that calling add(el) returns true and add the specified element at the end of the List
     */
    @Test
    public void add() {
        assertTrue(tmp.add("uno"));
        assertTrue(tmp.contains("uno"));
        assertEquals(1,tmp.size());
    }

    /**
     * Test the add(el) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling add(null) throws NullPointerException
     */
    @Test
    public void addNull() {
        assertThrows(NullPointerException.class, () -> { tmp.add(null); });
    }

    /**
     * Test the remove(el) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling remove(el) returns false if there's no el in the List
     */
    @Test
    public void removeFalse() {
        assertFalse(tmp.remove("uno"));
    }

    /**
     * Test the remove(el) method
     *
     * @safe.precondition a List of 2 elements
     * @safe.postcondition an empty List
     * @safe.testcases test to verify that calling remove(el) returns true and removes only one instance of the element
     */
    @Test
    public void removeTrue() {
        tmp.add("uno");
        tmp.add("uno");
        assertTrue(tmp.remove("uno"));
        assertTrue(tmp.contains("uno"));
        assertTrue(tmp.remove("uno"));
        assertFalse(tmp.contains("uno"));
    }

    /**
     * Test the remove(el) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling remove(null) throws NullPointerException
     */
    @Test
    public void removeNull() {
        assertThrows(NullPointerException.class, () -> { tmp.remove(null); });
    }

    /**
     * Test the containsAll(coll) method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling containsAll(coll) on the List returns true
     * if the coll contains the same elements of List
     */
    @Test
    public void containsAllTrue() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add("due");
        coll.add("tre");
        assertTrue(tmp.containsAll(coll));
    }

    /**
     * Test the containsAll(coll) method
     *
     * @safe.precondition a List of 1 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling containsAll(coll) on the List returns false
     * if the coll do not contains the same elements of List
     */
    @Test
    public void containsAllFalse() {
        tmp.add("uno");
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add("due");
        assertFalse(tmp.containsAll(coll));
    }

    /**
     * Test the containsAll(coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling containsAll(coll) throws NullPointerException if the collection
     * contains null elements
     */
    @Test
    public void containsAllNull() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add(null);
        assertThrows(NullPointerException.class, () ->{ tmp.containsAll(coll); });
    }

    /**
     * Test the containsAll(coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling containsAll(null) throws NullPointerException
     */
    @Test
    public void containsAllNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () ->{ tmp.containsAll(coll); });
    }

    /**
     * Test the addAll(coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition a List of 2 elements
     * @safe.testcases test to verify that calling addAll(coll) actually adds the elements of the collection at the end of the List
     */
    @Test
    public void addAllTrue() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add("due");
        assertTrue(tmp.addAll(coll));
        assertEquals(2,tmp.size());
        assertTrue(tmp.containsAll(coll));
    }

    /**
     * Test the addAll(coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling addAll(coll) throws NullPointerException if the collection
     * contains null elements
     */
    @Test
    public void addAllNull() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add(null);
        assertThrows(NullPointerException.class, () ->{ tmp.addAll(coll); });
    }

    /**
     * Test the addAll(coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling addAll(null) throws NullPointerException
     */
    @Test
    public void addAllNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () ->{ tmp.addAll(coll); });
    }

    /**
     * Test the addAll(index, coll) method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition a List of 6 elements
     * @safe.testcases test to verify that calling addAll(index, coll) inserts all of the elements in the specified collection into this list at the specified index and that
     * shifts the element currently at that position and after that to the right
     */
    @Test
    public void addAllIndex() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("nuovo1");
        coll.add("nuovo2");
        coll.add("nuovo3");
        assertTrue(tmp.addAll(1,coll));
        assertTrue(tmp.contains("nuovo1"));
        assertTrue(tmp.contains("nuovo2"));
        assertTrue(tmp.contains("nuovo3"));
        assertEquals("uno",tmp.get(0));
        assertEquals("due",tmp.get(4));
        assertEquals("tre",tmp.get(5));
    }

    /**
     * Test the addAll(index, coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling addAll(index, coll) throws NullPointerException if the collection contains null elements
     */
    @Test
    public void addAllIndexNull() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add(null);
        assertThrows(NullPointerException.class, () -> { tmp.addAll(0,coll); });
    }

    /**
     * Test the addAll(index, coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling addAll(index, null) throws NullPointerException
     */
    @Test
    public void addAllIndexNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () ->{ tmp.addAll(0,coll); });
    }

    /**
     * Test the addAll(index, coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling addAll(index, coll) throws IndexOutOfBoundsException if the index does not respect the List size
     */
    @Test
    public void addAllIndexOut() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        assertThrows(IndexOutOfBoundsException.class, () -> { tmp.addAll(1,coll); });
    }

    /**
     * Test the removeAll coll) method
     *
     * @safe.precondition a List of 4 elements
     * @safe.postcondition an empty List
     * @safe.testcases test to verify that calling removeAll(coll) actually removes all the elements contained in the collection from the List (even duplicates)
     */
    @Test
    public void removeAllTrue() {
        tmp.add("uno");
        tmp.add("uno"); //elemento doppio
        tmp.add("due");
        tmp.add("tre");
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add("uno");
        coll.add("due");
        coll.add("tre");
        coll.add("tre");
        assertTrue(tmp.removeAll(coll));
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the removeAll coll) method
     *
     * @safe.precondition a List of 1 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling removeAll(coll) returns false if the collection does not contain elements equals to the ones in the List
     */
    @Test
    public void removeAllFalse() {
        tmp.add("tre");
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add("due");
        assertFalse(tmp.removeAll(coll));
        assertFalse(tmp.isEmpty());
    }

    /**
     * Test the removeAll coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling removeAll(coll) throws NullPointerException if the collection contains null elements
     */
    @Test
    public void removeAllNull() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add(null);
        assertThrows(NullPointerException.class, () ->{ tmp.removeAll(coll); });
    }

    /**
     * Test the removeAll coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling removeAll(null) throws NullPointerException
     */
    @Test
    public void removeAllNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () ->{ tmp.removeAll(coll); });
    }

    /**
     * Test the retainAll(coll) method
     *
     * @safe.precondition a List of 4 elements
     * @safe.postcondition an empty List
     * @safe.testcases test to verify that calling retainAll(coll) returns true when changing the List and actually removing
     * the elements that are not present in the coll
     */
    @Test
    public void retainAllTrue() {
        tmp.add("uno");
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        assertTrue(tmp.retainAll(coll));
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the retainAll(coll) method
     *
     * @safe.precondition a List of 4 elements
     * @safe.postcondition a List of 2 elements
     * @safe.testcases test to verify that calling retainAll(coll) returns true when changing the List and actually removing
     * the elements that are not present in the coll
     */
    @Test
    public void retainAllTrue2() {
        tmp.add("uno");
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        assertTrue(tmp.retainAll(coll));
        assertFalse(tmp.isEmpty());
    }

    /**
     * Test the retainAll(coll) method
     *
     * @safe.precondition a List of 2 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling retainAll(coll) returns false when all the elements contained in the collection
     * are also contained in the List
     */
    @Test
    public void retainAllFalse() {
        tmp.add("uno");
        tmp.add("uno");
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        assertFalse(tmp.retainAll(coll));
        assertEquals(2,tmp.size());
    }

    /**
     * Test the retainAll(coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling retainAll(coll) throws NullPointerException when the collection contains null elements
     */
    @Test
    public void retainAllNull() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add(null);
        assertThrows(NullPointerException.class, () ->{ tmp.retainAll(coll); });
    }

    /**
     * Test the retainAll(coll) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that calling retainAll(null) throws NullPointerException
     */
    @Test
    public void retainAllNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () ->{ tmp.retainAll(coll); });
    }

    /**
     * Test the clear() method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition an empty List
     * @safe.testcases test to verify that clear() leaves the List empty
     */
    @Test
    public void clear() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        tmp.clear();
        assertTrue(tmp.isEmpty());
        assertFalse(tmp.contains("uno"));
        assertFalse(tmp.contains("due"));
        assertFalse(tmp.contains("tre"));
    }

    /**
     * Test the clear() method
     *
     * @safe.precondition an empty List
     * @safe.postcondition an empty List
     * @safe.testcases test to verify that clear() leaves the List empty
     */
    @Test
    public void clearEmpty() {
        tmp.clear();
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the equals(list) method
     *
     * @safe.precondition a List of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling equals(list) on the List returns true if they contains the same elements in the same order
     */
    @Test
    public void equalsTrue() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");

        ListAdapter<String> list = new ListAdapter<>();
        list.add("uno");
        list.add("due");
        list.add("tre");
        assertTrue(tmp.equals(list));
    }

    /**
     * Test the equals(list) method
     *
     * @safe.precondition a List of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling equals(list) on the List returns true if they contains the same elements in the same order
     */
    @Test
    public void equalsTrueSame() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");

        ListAdapter<String> list = tmp;
        assertTrue(tmp.equals(list));
    }

    /**
     * Test the equals(list) method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling equals(list) on the List returns false if they do not contains the same elements
     * or if their size is not equal
     */
    @Test
    public void equalsFalse() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");

        ListAdapter<String> list = new ListAdapter<>();
        list.add("uno");
        list.add("due");
        list.add("quattro");
        assertFalse(tmp.equals(list));
        tmp.remove("uno");
        assertFalse(tmp.equals(list));
    }

    /**
     * Test the equals(list) method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling equals(list) on the List returns false if they do not contains the same elements in the same order
     */
    @Test
    public void equalsFalse2() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");

        ListAdapter<String> list = new ListAdapter<>();
        list.add("uno");
        list.add("tre");
        list.add("due");
        assertFalse(tmp.equals(list));
    }

    /**
     * Test the hashCode() method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling hashCode() on the List returns the right Integer
     */
    @Test
    public void hashCodeTest() {
        tmp.add("10");
        tmp.add("15");
        tmp.add("20");
        assertEquals(1586008, tmp.hashCode());
    }

    /**
     * Test the get(index) method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that get(index) returns the correct element
     */
    @Test
    public void get() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        assertEquals("uno",tmp.get(0));
        assertEquals("due",tmp.get(1));
        assertEquals("tre",tmp.get(2));
    }

    /**
     * Test the get(index) method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that get(index) throws IndexOutOfBoundsException when the index does not respect the List size
     */
    @Test
    public void getOut() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        assertThrows(IndexOutOfBoundsException.class, () -> { tmp.get(3); });
    }

    /**
     * Test the get(index) method
     *
     * @safe.precondition an empty List
     * @safe.postcondition none
     * @safe.testcases test to verify that get(index) throws IndexOutOfBoundsException when the index does not respect the List size
     */
    @Test
    public void getOutEmpty() {
        assertThrows(IndexOutOfBoundsException.class, () -> { tmp.get(0); });
    }

    /**
     * Test the set(index, el) method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that set(index, el) return the oldValue at the specified index and that actually
     * modifies the element at that index
     */
    @Test
    public void set() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        assertEquals("uno",tmp.set(0,"zero"));
        assertEquals("zero",tmp.get(0));
    }

    /**
     * Test the set(index, el) method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that set(index, el) throws IndexOutOfBoundsException when the index does not respect the List size
     */
    @Test
    public void setOut() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        assertThrows(IndexOutOfBoundsException.class, () -> { tmp.set(3,"quattro"); });
    }

    /**
     * Test the set(index, el) method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that set(index, null) throws NullPointerException
     */
    @Test
    public void setNull() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        assertThrows(NullPointerException.class, () -> { tmp.set(1,null); });
    }

    /**
     * Test the add(index, el) method
     *
     * @safe.precondition a List of 1 element
     * @safe.postcondition a List of 2 element
     * @safe.testcases test to verify that add(index, el) inserts the element at the specified index shifting the element at the current
     * position and all the elements after that to the right
     */
    @Test
    public void addIndex() {
        tmp.add("uno");
        tmp.add(0,"zero");
        assertEquals("zero",tmp.get(0));
        assertEquals("uno",tmp.get(1));
        assertEquals(2,tmp.size());
    }

    /**
     * Test the add(index, el) method
     *
     * @safe.precondition a List of 3 element
     * @safe.postcondition none
     * @safe.testcases test to verify that add(index, el) throws IndexOutOfBoundsException when the index does not respect the List size
     */
    @Test
    public void addIndexOut() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        assertThrows(IndexOutOfBoundsException.class, () -> { tmp.add(4,"11"); });
    }

    /**
     * Test the add(index, el) method
     *
     * @safe.precondition a List of 3 element
     * @safe.postcondition none
     * @safe.testcases test to verify that add(index, null) throws NullPointerException
     */
    @Test
    public void addIndexNull() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        assertThrows(NullPointerException.class, () -> { tmp.add(1,null); });
    }

    /**
     * Test the remove(index) method
     *
     * @safe.precondition a List of 3 element
     * @safe.postcondition a List of 2 element
     * @safe.testcases test to verify that remove(index) return the previous element at that position
     * and that actually removes that element from the List
     */
    @Test
    public void removeIndex() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        assertEquals("due",tmp.remove(1));
        assertEquals(2,tmp.size());
        assertFalse(tmp.contains("due"));
    }

    /**
     * Test the remove(index) method
     *
     * @safe.precondition a List of 3 element
     * @safe.postcondition none
     * @safe.testcases test to verify that remove(index) throws IndexOutOfBoundsException when the index does not respect the List size
     */
    @Test
    public void removeIndexOut() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        assertThrows(IndexOutOfBoundsException.class, () -> { tmp.remove(3); });
    }

    /**
     * Test the indexOf(el) method
     *
     * @safe.precondition a List of 4 element
     * @safe.postcondition none
     * @safe.testcases test to verify that indexOf(el) returns the correct index inside List and if there are duplicate returns the index of the first occurrence of the element
     */
    @Test
    public void indexOf() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("due");
        tmp.add("tre");
        assertEquals(0,tmp.indexOf("uno"));
        assertEquals(1,tmp.indexOf("due"));
        assertEquals(3,tmp.indexOf("tre"));
        tmp.remove("due");
        assertEquals(2,tmp.indexOf("tre"));
    }

    /**
     * Test the indexOf(el) method
     *
     * @safe.precondition a List of 4 element
     * @safe.postcondition none
     * @safe.testcases test to verify that indexOf(el) returns -1 if the element is not present in the List
     */
    @Test
    public void indexOfNone() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("due");
        tmp.add("tre");
        assertEquals(-1,tmp.indexOf("quattro"));
    }

    /**
     * Test the indexOf(el) method
     *
     * @safe.precondition a List of 4 element
     * @safe.postcondition none
     * @safe.testcases test to verify that indexOf(null) throws NullPointerException
     */
    @Test
    public void indexOfNull() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("due");
        tmp.add("tre");
        assertThrows(NullPointerException.class, () -> { tmp.indexOf(null); });
    }

    /**
     * Test the lastIndexOf(el) method
     *
     * @safe.precondition a List of 4 element
     * @safe.postcondition none
     * @safe.testcases test to verify that indexOf(el) returns the index of the last occurrence of the element searched
     */
    @Test
    public void lastIndexOf() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("due");
        tmp.add("tre");
        assertEquals(2,tmp.lastIndexOf("due"));
    }

    /**
     * Test the lastIndexOf(el) method
     *
     * @safe.precondition a List of 4 element
     * @safe.postcondition none
     * @safe.testcases test to verify that lastIndexOf(el) returns -1 if the element is not present in the List
     */
    @Test
    public void lastIndexOfNone() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("due");
        tmp.add("tre");
        assertEquals(-1,tmp.lastIndexOf("quattro"));
    }

    /**
     * Test the lastIndexOf(el) method
     *
     * @safe.precondition a List of 4 element
     * @safe.postcondition none
     * @safe.testcases test to verify that lastIndexOf(null) throws NullPointerException
     */
    @Test
    public void lastIndexOfNull() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("due");
        tmp.add("tre");
        assertThrows(NullPointerException.class, () -> { tmp.lastIndexOf(null); });
    }

    /**
     * Test the listIterator(index) method
     *
     * @safe.precondition a List of 5 element
     * @safe.postcondition none
     * @safe.testcases test to verify that listIterator(index) return an iterator over the element at that index,
     * in fact a call to next() should return it
     */
    @Test
    public void listIteratorIndex() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        ListIterator iter = tmp.listIterator(2);
        assertEquals("2",iter.next());
    }

    /**
     * Test the listIterator(index) method
     *
     * @safe.precondition a List of 5 element
     * @safe.postcondition none
     * @safe.testcases test to verify that listIterator(index) return an iterator over the element at that index,
     * in fact a call to previous() should return the element at the previous index
     */
    @Test
    public void listIteratorIndexPrev() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        ListIterator iter = tmp.listIterator(2);
        assertEquals("1",iter.previous());
    }

    /**
     * Test the subList(from, to) method for an empty list
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify that subList(x,x) returns an empty List.
     * This test tests partially the next methods: size(), isEmpty(), listIterator().hasNext(), listIterator.hasPrevious(), set(index,el),
     * get(index), toArray(), indexOf(el), lastIndexOf(el)
     */
    @Test
    public void subListMin() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");
        List<String> sublist = tmp.subList(4,4);
        assertEquals(0,sublist.size());
        assertTrue(sublist.isEmpty());
        assertFalse(sublist.contains("4"));
        assertFalse(sublist.listIterator().hasNext());
        assertFalse(sublist.listIterator().hasPrevious());
        assertThrows(IndexOutOfBoundsException.class, () -> { sublist.set(0,"errato"); });
        assertThrows(IndexOutOfBoundsException.class, () -> { sublist.get(0); });

        assertEquals(0,sublist.toArray().length);

        assertEquals(-1,sublist.indexOf("4"));
        assertEquals(-1,sublist.lastIndexOf("4"));

    }

    /**
     * Test the subList(from, to) method
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify that subList(x,y) returns a subList.
     * This test tests partially the next methods: size(), isEmpty(), listIterator().hasNext(), listIterator().next(), listIterator.hasPrevious(), set(index,el),
     * get(index), toArray(), indexOf(el), lastIndexOf(el)
     */
    @Test
    public void subListMedium() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");
        List<String> sublist = tmp.subList(4,8);
        assertEquals(4,sublist.size());
        assertFalse(sublist.isEmpty());
        assertTrue(sublist.contains("4"));
        assertFalse(sublist.contains("8"));

        Object[] array = sublist.toArray();
        int i = 0;
        ListIterator iter = sublist.listIterator();
        assertFalse(sublist.listIterator().hasPrevious());
        while(iter.hasNext()){
            assertEquals(array[i++],iter.next());
        }

        assertThrows(IndexOutOfBoundsException.class, () -> { sublist.set(5,"errato"); });
        assertThrows(IndexOutOfBoundsException.class, () -> { sublist.get(5); });

        assertEquals(0,sublist.indexOf("4"));
        assertEquals(0,sublist.lastIndexOf("4"));

    }

    /**
     * Test the subList(from, to) method of a maximum subList
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify that subList(0,size()) returns a subList.
     * This test tests partially the next methods: size(), isEmpty(), listIterator().hasNext(), listIterator().next(), listIterator.hasPrevious(), set(index,el),
     * get(index), toArray(), indexOf(el), lastIndexOf(el)
     */
    @Test
    public void subListMax() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");
        List<String> sublist = tmp.subList(0,tmp.size());
        assertEquals(10,sublist.size());
        assertFalse(sublist.isEmpty());
        assertTrue(sublist.contains("0"));
        assertTrue(sublist.contains("9"));

        assertFalse(sublist.listIterator().hasPrevious());
        Object[] array = sublist.toArray();
        int i = 0;
        ListIterator iter = sublist.listIterator();
        assertFalse(sublist.listIterator().hasPrevious());
        while(iter.hasNext()){
            assertEquals(array[i++],iter.next());
        }

        assertThrows(IndexOutOfBoundsException.class, () -> { sublist.set(10,"errato"); });
        assertThrows(IndexOutOfBoundsException.class, () -> { sublist.get(10); });

        assertEquals(4,sublist.indexOf("4"));
        assertEquals(4,sublist.lastIndexOf("4"));

    }

    /**
     * Test the subList(from, to) method with add(index, el)
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify the method add(index, el) called on a subList
     */
    @Test
    public void subListAddIndex() {       //add vale uguale anche per le altre sublist non minime, lo testo sulla minima per facilità
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");

        List<String> sublist = tmp.subList(4, 4);
        sublist.add(0, "nuovoEl");

        assertEquals("nuovoEl", sublist.get(0));
        assertEquals("nuovoEl", tmp.get(4));
        assertEquals(1, sublist.size());
        assertEquals(11, tmp.size());
    }

    /**
     * Test the subList(from, to) method with add(el)
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify the method add(el) called on a subList
     */
    @Test
    public void subListAdd() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");

        List<String> sublist = tmp.subList(4, 4);

        assertTrue(sublist.add("nuovoEl"));

        assertEquals("nuovoEl", sublist.get(0));
        assertEquals("nuovoEl", tmp.get(4));
        assertEquals(1, sublist.size());
        assertEquals(11, tmp.size());
    }

    /**
     * Test the subList(from, to) method with remove(el)
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify the method remove(el) called on a subList
     */
    @Test
    public void subListRemove() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");

        List<String> sublist = tmp.subList(0,4);
        assertTrue(sublist.remove("2"));
        assertFalse(tmp.contains("2"));
        assertFalse(sublist.contains("2"));
    }

    /**
     * Test the subList(from, to) method with addAll(coll)
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify the method addAll(coll) called on a subList
     */
    @Test
    public void subListAddAll() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");

        List<String> sublist = tmp.subList(4, 4);

        Collection<String> coll = new CollectionAdapter<>();
        coll.add("nuovo1");
        coll.add("nuovo2");
        coll.add("nuovo3");

        assertTrue(sublist.addAll(coll));

        coll.add(null);
        assertThrows(NullPointerException.class, () -> { sublist.addAll(0,coll); });

        coll.clear();
        sublist.clear();
        assertThrows(IndexOutOfBoundsException.class, () -> { sublist.addAll(1,coll); });
    }

    /**
     * Test the subList(from, to) method with containsAll(coll)
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify the method containsAll(coll) called on a subList
     */
    @Test
    public void sublistContainsAll() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");

        List<String> sublist = tmp.subList(0, 4);
        Collection<String> coll = new CollectionAdapter<>();
        coll.add("1");
        coll.add("2");
        coll.add("3");

        assertTrue(sublist.containsAll(coll));

        coll.add("7");

        assertFalse(sublist.containsAll(coll));
    }

    /**
     * Test the subList(from, to) method with removeAll(coll)
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify the method removeAll(coll) called on a subList
     */
    @Test
    public void subListRemoveAll() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");

        List<String> sublist = tmp.subList(3, 7);

        Collection<String> coll = new CollectionAdapter<>();
        coll.add("4");
        coll.add("6");
        coll.add("8");

        assertTrue(sublist.removeAll(coll));
        assertTrue(tmp.contains("8"));

        coll.add(null);
        assertThrows(NullPointerException.class, () -> { sublist.removeAll(coll); });
    }

    /**
     * Test the subList(from, to) method with indexOf(el) and lastIndexOf(el)
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify the method indexOf(el) and lastIndexOf(el) called on a subList
     */
    @Test
    public void subListIndexOfAndLast() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");   //doppio
        tmp.add("4");   //doppio
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");

        List<String> sublist = tmp.subList(3, 7);

        assertEquals(1,sublist.indexOf("4"));
        assertEquals(2,sublist.lastIndexOf("4"));

    }

    /**
     * Test the subList(from, to) method with listIterator(index)
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify the method listIterator(index) called on a subList
     */
    @Test
    public void subListListIteratorIndex() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");

        List<String> sublist = tmp.subList(3, 7);
        ListIterator iter = sublist.listIterator(1);
        assertEquals("4",iter.next());
        assertEquals("5",iter.next());
        assertEquals("5",iter.previous());

    }

    /**
     * Test the subList(from, to) method with subList(from, to)
     *
     * @safe.precondition a List of 10 element
     * @safe.postcondition none
     * @safe.testcases test to verify the method subList(from, to) called on a subList
     */
    @Test
    public void subListSub() {
        tmp.add("0");
        tmp.add("1");
        tmp.add("2");
        tmp.add("3");
        tmp.add("4");
        tmp.add("5");
        tmp.add("6");
        tmp.add("7");
        tmp.add("8");
        tmp.add("9");

        List<String> sublist = tmp.subList(3, 7);
        sublist.subList(1, 2).clear();
        assertFalse(tmp.contains("4"));
    }
}
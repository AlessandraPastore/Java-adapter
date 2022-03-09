import org.junit.Test;
import java.util.*;
import static org.junit.Assert.*;

/**
 * <b>Suite di Test per SetAdapter.</b>
 * @safe.summary Vengono effettuati diversi test per ogni metodo di SetAdapter per verificarne il corretto funzionamento.
 * @safe.testsuitedesign Ogni metodo è testato attraverso multipli Asserts per verificare tutti i possibili cambiamenti che tale metodo provoca
 * rispetto agli altri metodi e allo stato della List
 */
public class SetAdapterTest {

    Set<String> tmp = new SetAdapter<>();

    /**
     * Test the size() method
     *
     * @safe.precondition a Set of 3 elements
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
     * @safe.precondition an empty Set
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
     * @safe.precondition an empty Set
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
     * @safe.precondition a Set of 1 element
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
     * @safe.precondition a Set of 1 element
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
     * @safe.precondition a Set of 1 element
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
     * @safe.precondition an empty Set
     * @safe.postcondition none
     * @safe.testcases test to verify that contains(null) throws NullPointerException
     */
    @Test
    public void containsNull() {
        assertThrows(NullPointerException.class, () -> { tmp.contains(null); });
    }

    /**
     * Test the iterator() method
     *
     * @safe.precondition a Set of 1 element
     * @safe.postcondition an empty List
     * @safe.testcases test to verify that calling iterator() on the Set returns an actual iterator
     * that works over the elements of the Set
     */
    @Test
    public void iterator() {
        tmp.add("a");
        ListIterator iter = (ListIterator) tmp.iterator();
        assertEquals("a",iter.next());
        assertFalse(iter.hasNext());
        assertEquals("a",iter.previous());
        assertFalse(iter.hasPrevious());
        iter.remove();
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the iterator() method
     *
     * @safe.precondition an empty Set
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().remove() on the Set throws IllegalStateException if not called after a next() or previosu()
     */
    @Test
    public void iteratorRemove() {
        ListIterator iter = (ListIterator) tmp.iterator();
        assertThrows(IllegalStateException.class, () -> { iter.remove(); });
    }

    /**
     * Test the iterator() method
     *
     * @safe.precondition an empty Set
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().next() on the Set throws NoSuchElementException if there's no next element
     */
    @Test
    public void iteratorNext() {
        ListIterator iter = (ListIterator) tmp.iterator();
        assertThrows(NoSuchElementException.class, () -> { iter.next(); });
    }

    /**
     * Test the iterator() method
     *
     * @safe.precondition an empty Set
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().previous() on the Set throws NoSuchElementException if there's no previous element
     */
    @Test
    public void iteratorPrevious() {
        ListIterator iter = (ListIterator) tmp.iterator();
        assertThrows(NoSuchElementException.class, () -> { iter.previous(); });
    }

    /**
     * Test the toArray() method
     *
     * @safe.precondition a Set of 3 element
     * @safe.postcondition none
     * @safe.testcases test to verify that calling toArray() returns an array with all the elements of the Set
     */
    @Test
    public void toArray() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        Object[] array = tmp.toArray();
        assertEquals(tmp.size(),array.length);
        ListIterator iter = (ListIterator) tmp.iterator();
        assertEquals(iter.next(),array[0]);
        assertEquals(iter.next(),array[1]);
        assertEquals(iter.next(),array[2]);
    }

    /**
     * Test the add(el) method
     *
     * @safe.precondition an empty Set
     * @safe.postcondition a List of 1 element
     * @safe.testcases test to verify that calling add(el) returns true and add the specified element at the Set
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
     * @safe.precondition an empty Set
     * @safe.postcondition a List of 1 element
     * @safe.testcases test to verify that calling add(el) returns false if the element is already present
     */
    @Test
    public void addDuplicate() {
        assertTrue(tmp.add("uno"));
        assertFalse(tmp.add("uno"));
    }

    /**
     * Test the add(el) method
     *
     * @safe.precondition an empty Set
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
     * @safe.precondition an empty Set
     * @safe.postcondition none
     * @safe.testcases test to verify that calling remove(el) returns false if there's no el in the Set
     */
    @Test
    public void removeFalse() {
        assertFalse(tmp.remove("uno"));
    }

    /**
     * Test the remove(el) method
     *
     * @safe.precondition a Set of 1 elements
     * @safe.postcondition an empty Set
     * @safe.testcases test to verify that calling remove(el) returns true and removes the element
     */
    @Test
    public void removeTrue() {
        tmp.add("uno");
        assertTrue(tmp.remove("uno"));
        assertFalse(tmp.contains("uno"));
    }

    /**
     * Test the remove(el) method
     *
     * @safe.precondition an empty Set
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
     * @safe.precondition a Set of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling containsAll(coll) on the Set returns true
     * if the coll contains the same elements of Set
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
     * @safe.precondition a Set of 1 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling containsAll(coll) on the Set returns false
     * if the coll do not contains the same elements of Set
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
     * @safe.precondition an empty Set
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
     * @safe.precondition an empty Set
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
     * @safe.precondition an empty Set
     * @safe.postcondition a Set of 2 elements
     * @safe.testcases test to verify that calling addAll(coll) return true if actually adds the elements of the collection in the Set
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
     * @safe.precondition a Set of 2 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling addAll(coll) return false if the elements in the collection where
     * already present in the Set
     */
    @Test
    public void addAllFalse() {
        tmp.add("uno");
        tmp.add("due");
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add("due");
        assertFalse(tmp.addAll(coll));
        assertEquals(2,tmp.size());
    }

    /**
     * Test the addAll(coll) method
     *
     * @safe.precondition an empty Set
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
     * @safe.precondition an empty Set
     * @safe.postcondition none
     * @safe.testcases test to verify that calling addAll(null) throws NullPointerException
     */
    @Test
    public void addAllNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () ->{ tmp.addAll(coll); });
    }

    /**
     * Test the retainAll(coll) method
     *
     * @safe.precondition a Set of 3 elements
     * @safe.postcondition an empty Set
     * @safe.testcases test to verify that calling retainAll(coll) returns true when changing the Set and actually removing
     * the elements that are not present in the coll
     */
    @Test
    public void retainAllTrue() {
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
     * @safe.precondition a Set of 3 elements
     * @safe.postcondition an empty Set
     * @safe.testcases test to verify that calling retainAll(coll) returns true when changing the Set and actually removing
     * the elements that are not present in the coll
     */
    @Test
    public void retainAllTrue2() {
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
     * @safe.precondition a Set of 2 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling retainAll(coll) returns false when all the elements contained in the collection
     * are also contained in the Set
     */
    @Test
    public void retainAllFalse() {
        tmp.add("uno");
        tmp.add("due");
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add("due");
        assertFalse(tmp.retainAll(coll));
    }

    /**
     * Test the retainAll(coll) method
     *
     * @safe.precondition an empty Set
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
     * @safe.precondition an empty Set
     * @safe.postcondition none
     * @safe.testcases test to verify that calling retainAll(null) throws NullPointerException
     */
    @Test
    public void retainAllNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () ->{ tmp.retainAll(coll); });
    }

    /**
     * Test the removeAll coll) method
     *
     * @safe.precondition a List of 3 elements
     * @safe.postcondition an empty List
     * @safe.testcases test to verify that calling removeAll(coll) actually removes all the elements contained in the collection from the List (even duplicates)
     */
    @Test
    public void removeAllTrue() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add("due");
        coll.add("tre");
        assertTrue(tmp.removeAll(coll));
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the removeAll coll) method
     *
     * @safe.precondition a Set of 1 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling removeAll(coll) returns false if the collection does not contain elements equals to the ones in the Set
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
     * @safe.precondition an empty Set
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
     * @safe.precondition an empty Set
     * @safe.postcondition none
     * @safe.testcases test to verify that calling removeAll(null) throws NullPointerException
     */
    @Test
    public void removeAllNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () ->{ tmp.removeAll(coll); });
    }

    /**
     * Test the clear() method
     *
     * @safe.precondition a Set of 3 elements
     * @safe.postcondition an empty Set
     * @safe.testcases test to verify that clear() leaves the Set empty
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
     * @safe.precondition an empty Set
     * @safe.postcondition an empty Set
     * @safe.testcases test to verify that clear() leaves the Set empty
     */
    @Test
    public void clearEmpty() {
        tmp.clear();
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the equals(set) method
     *
     * @safe.precondition a Set of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling equals(set) on the Set returns true if they contains the same elements
     */
    @Test
    public void equalsTrue() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");

        SetAdapter<String> set = new SetAdapter<>();
        set.add("uno");
        set.add("due");
        set.add("tre");
        assertTrue(tmp.equals(set));
    }

    /**
     * Test the equals(set) method
     *
     * @safe.precondition a Set of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling equals(set) on the Set returns false if they do not contains the same elements
     */
    @Test
    public void equalsFalse() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");

        SetAdapter<String> set = new SetAdapter<>();
        set.add("uno");
        set.add("due");
        assertFalse(tmp.equals(set));
        tmp.remove("uno");
        assertFalse(tmp.equals(set));
    }

    /**
     * Test the hashCode() method
     *
     * @safe.precondition a Set of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling hashCode() on the Set returns the right Integer
     */
    @Test
    public void hashCodeTest() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        assertEquals(330897,tmp.hashCode());

        SetAdapter<String> set = new SetAdapter<>();
        set.add("uno");
        set.add("due");
        set.add("tre");
        assertEquals(tmp.hashCode(),set.hashCode());
    }
}
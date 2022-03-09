import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * <b>Suite di Test per CollectionAdapter.</b>
 * @safe.summary Vengono effettuati diversi test per ogni metodo di CollectionAdapter per verificarne il corretto funzionamento.
 * @safe.testsuitedesign Ogni metodo è testato attraverso multipli Asserts per verificare tutti i possibili cambiamenti che tale metodo provoca
 * rispetto agli altri metodi e allo stato della Map
 */
public class CollectionAdapterTest {

    CollectionAdapter<String> tmp = new CollectionAdapter<>();

    /**
     * Test the size() method
     *
     * @safe.precondition a Collection of 3 elements
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
     * @safe.precondition an empty Collection
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
     * @safe.precondition an empty Collection
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
     * @safe.precondition a Collection of 1 element
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
     * @safe.precondition a Collection of 1 element
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
     * @safe.precondition a Collection of 2 duplicate elements
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
     * @safe.precondition a Collection of 1 element
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
     * @safe.precondition a Collection of 1 element
     * @safe.postcondition none
     * @safe.testcases test to verify that contains(null) find the null element
     */
    @Test
    public void containsNull() {
        tmp.add(null);
        assertTrue(tmp.contains(null));
    }

    /**
     * Test the ListIterator() method
     *
     * @safe.precondition a Collection of 1 element
     * @safe.postcondition an empty Collection
     * @safe.testcases test to verify that calling iterator() on the Collection returns an actual iterator
     * that works over the elements of the Collection
     */
    @Test
    public void listIterator() {
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
     * Test the listIterator() method
     *
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().remove() on the Collection throws IllegalStateException if not called after a next() or previosu()
     */
    @Test
    public void iteratorRemove() {
        ListIterator iter = (ListIterator) tmp.iterator();
        assertThrows(IllegalStateException.class, () -> { iter.remove(); });
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().next() on the Collection throws NoSuchElementException if there's no next element
     */
    @Test
    public void iteratorNext() {
        ListIterator iter = (ListIterator) tmp.iterator();
        assertThrows(NoSuchElementException.class, () -> { iter.next(); });
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().previous() on the Collection throws NoSuchElementException if there's no previous element
     */
    @Test
    public void iteratorPrevious() {
        ListIterator iter = (ListIterator) tmp.iterator();
        assertThrows(NoSuchElementException.class, () -> { iter.previous(); });
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition a Collection of 1 element
     * @safe.postcondition a Collection of 2 element
     * @safe.testcases test to verify that calling iterator().add(el) on the Collection actually inserts the specified element in the current position
     * of the iterator, shifting the elements after it to the right
     */
    @Test
    public void iteratorAdd() {
        tmp.add("due");
        ListIterator iter = (ListIterator) tmp.iterator();
        iter.add("uno");
        assertFalse(tmp.isEmpty());
        assertTrue(tmp.contains("uno"));
        assertEquals("uno",iter.next());
        assertEquals("due",iter.next());
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition an empty Collection
     * @safe.postcondition a Collection of 1 element
     * @safe.testcases test to verify that calling iterator().add(null) on the Collection actually inserts a null element
     */
    @Test
    public void iteratorAddNull() {
        ListIterator iter = (ListIterator) tmp.iterator();
        iter.add(null);
        assertTrue(tmp.contains(null));
        assertEquals(null,iter.next());
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition a Collection of 1 element
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().set(newEl) after next() or previous() actually sets the new element
     */
    @Test
    public void iteratorSet() {
        ListIterator iter = (ListIterator) tmp.iterator();
        iter.add("uno");
        iter.next();
        iter.set("due");
        assertFalse(tmp.isEmpty());
        assertTrue(tmp.contains("due"));
        assertEquals("due",iter.previous());
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition a Collection of 1 element
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().set(newEl) without calling first next() or previous() throws IllegalStateException
     */
    @Test
    public void iteratorSetState() {
        ListIterator iter = (ListIterator) tmp.iterator();
        iter.add("uno");
        assertThrows(IllegalStateException.class, () -> { iter.set("due"); });
    }

    /**
     * Test the listIterator() method
     *
     * @safe.precondition a Collection of 1 element
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().set(null) after next() or previous() actually sets that element to null
     */
    @Test
    public void iteratorSetNull() {
        ListIterator iter = (ListIterator) tmp.iterator();
        iter.add("uno");
        iter.next();
        iter.set(null);
        assertEquals(null,iter.previous());
    }

    /**
     * Test the toArray() method
     *
     * @safe.precondition a Collection of 3 element
     * @safe.postcondition none
     * @safe.testcases test to verify that calling toArray() returns an array with all the elements of the Collection
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
     * @safe.precondition an empty Collection
     * @safe.postcondition a Collection of 1 element
     * @safe.testcases test to verify that calling add(el) returns true and add the specified element to the Collection
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
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling add(null) add a null element to the Collection
     */
    @Test
    public void addNull() {
        tmp.add(null);
        assertTrue(tmp.contains(null));
    }

    /**
     * Test the remove(el) method
     *
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling remove(el) returns false if there's no el in the Collection
     */
    @Test
    public void removeFalse() {
        assertFalse(tmp.remove("uno"));
    }

    /**
     * Test the remove(el) method
     *
     * @safe.precondition a Collection of 2 elements
     * @safe.postcondition an empty Collection
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
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling remove(null) actually tries to remove a null element
     */
    @Test
    public void removeNull() {
       assertFalse(tmp.remove(null));
    }

    /**
     * Test the containsAll(coll) method
     *
     * @safe.precondition a Collection of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling containsAll(coll) on the Collection returns true
     * if the coll contains the same elements of Collection
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
     * @safe.precondition a Collection of 1 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling containsAll(coll) on the Collection returns false
     * if the coll do not contains the same elements of Collection
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
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling containsAll(coll) return false even if coll contains null elements
     */
    @Test
    public void containsAllNull() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add(null);
        assertFalse(tmp.containsAll(coll));
    }

    /**
     * Test the containsAll(coll) method
     *
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling containsAll(null) throws NullPointerException
     */
    @Test
    public void containsAllNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () -> { tmp.containsAll(coll);  });
    }

    /**
     * Test the addAll(coll) method
     *
     * @safe.precondition an empty Collection
     * @safe.postcondition a Collection of 2 elements
     * @safe.testcases test to verify that calling addAll(coll) actually adds the elements of the collection at the end of the Collection
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
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling addAll(coll) add all the elements from coll to the Collection
     * even if coll contains null elements
     */
    @Test
    public void addAllNull() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add(null);
       assertTrue(tmp.addAll(coll));
    }

    /**
     * Test the addAll(coll) method
     *
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling addAll(null) throws NullPointerException
     */
    @Test
    public void addAllNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () -> { tmp.addAll(coll);  });
    }

    /**
     * Test the removeAll coll) method
     *
     * @safe.precondition a Collection of 4 elements
     * @safe.postcondition an empty Collection
     * @safe.testcases test to verify that calling removeAll(coll) actually removes all the elements contained in the collection from the Collection (even duplicates)
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
     * @safe.precondition a Collection of 1 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling removeAll(coll) returns false if the collection does not contain elements equals to the ones in the Collection
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
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling removeAll(coll) tries to remove all the elements even if coll contains null elements
     */
    @Test
    public void removeAllNull() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add(null);
        assertFalse(tmp.removeAll(coll));
    }

    /**
     * Test the removeAll coll) method
     *
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling removeAll(null) throws NullPointerException
     */
    @Test
    public void removeAllNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () -> { tmp.removeAll(coll);  });
    }

    /**
     * Test the retainAll(coll) method
     *
     * @safe.precondition a Collection of 4 elements
     * @safe.postcondition an empty Collection
     * @safe.testcases test to verify that calling retainAll(coll) returns true when changing the Collection and actually removing
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
     * @safe.precondition a Collection of 2 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling retainAll(coll) returns false when all the elements contained in the collection
     * are also contained in the Collection
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
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling retainAll(coll) tries to remove elements from the Collection even if coll contains null elements
     */
    @Test
    public void retainAllNull() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add(null);
        assertFalse(tmp.retainAll(coll));
    }

    /**
     * Test the retainAll(coll) method
     *
     * @safe.precondition an empty Collection
     * @safe.postcondition none
     * @safe.testcases test to verify that calling retainAll(null) throws NullPointerException
     */
    @Test
    public void retainAllNull2() {
        CollectionAdapter<String> coll = null;
        assertThrows(NullPointerException.class, () -> { tmp.retainAll(coll);  });
    }

    /**
     * Test the clear() method
     *
     * @safe.precondition a Collection of 3 elements
     * @safe.postcondition an empty Collection
     * @safe.testcases test to verify that clear() leaves the Collection empty
     */
    @Test
    public void clear() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("tre");
        tmp.clear();
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the clear() method
     *
     * @safe.precondition an empty Collection
     * @safe.postcondition an empty Collection
     * @safe.testcases test to verify that clear() leaves the Collection empty
     */
    @Test
    public void clearEmpty() {
        tmp.clear();
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the equals(collection) method
     *
     * @safe.precondition a Collection of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling equals(collection) on the Collection returns true if they contains the same elements in the same order
     */
    @Test
    public void equalsTrue() {
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        tmp.add("a");
        tmp.add("b");
        tmp.add(null);

        coll.add("a");
        coll.add("b");
        coll.add(null);

        assertTrue(tmp.equals(coll));
    }

    /**
     * Test the equals(collection) method
     *
     * @safe.precondition a Collection of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling equals(collection) on the Collection returns false if they do not contains the same elements
     * or if their size is not equal
     */
    @Test
    public void equalsFalse() {
        tmp.add("uno");
        tmp.add("due");
        tmp.add("due");
        tmp.add(null);

        CollectionAdapter<String> coll = new CollectionAdapter<>();
        coll.add("uno");
        coll.add("due");
        coll.add(null);
        assertFalse(tmp.equals(coll));
    }

    /**
     * Test the hashCode() method
     *
     * @safe.precondition a Collection of 3 elements
     * @safe.postcondition none
     * @safe.testcases test to verify that calling hashCode() on the Collection returns the right Integer
     */
    @Test
    public void hashCodeTest() {
        tmp.add("uno");
        tmp.add("due");
        assertEquals(3695487,tmp.hashCode());
    }
}
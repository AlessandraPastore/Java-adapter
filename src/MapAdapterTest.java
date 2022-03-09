import org.junit.Test;
import java.util.*;

import static java.lang.Math.pow;
import static org.junit.Assert.*;

/**
 * <b>Suite di Test per MapAdapter.</b>
 * @safe.summary Vengono effettuati diversi test per ogni metodo di MapAdapter per verificarne il corretto funzionamento.
 * @safe.testsuitedesign Ogni metodo è testato attraverso multipli Asserts per verificare tutti i possibili cambiamenti che tale metodo provoca
 * rispetto agli altri metodi e allo stato della Map
 */
public class MapAdapterTest {
    Map<Integer,String> tmp = new MapAdapter<>();

    /**
     * Test the size() method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that size() returns 0
     */
    @Test
    public void sizeEmpty() {
        assertEquals(0,tmp.size());
    }

    /**
     * Test the size() method
     *
     * @safe.precondition a Map of 2 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that size() returns 2
     */
    @Test
    public void size() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        assertEquals(2, tmp.size());
    }

    /**
     * Test the isEmpty() method
     *
     * @safe.precondition an empty Map
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
     * @safe.precondition a Map of 1 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that isEmpty() returns false
     */
    @Test
    public void isEmptyFalse() {
        tmp.put(1,"uno");
        assertFalse(tmp.isEmpty());
    }

    /**
     * Test the containsKey() method
     *
     * @safe.precondition a Map of 1 entry (key = 1)
     * @safe.postcondition none
     * @safe.testcases test to verify that containsKey(1) returns true
     */
    @Test
    public void containsKeyTrue() {
        tmp.put(1,"uno");
        assertTrue(tmp.containsKey(1));
    }

    /**
     * Test the containsKey() method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that containsKey(*) returns false
     */
    @Test
    public void containsKeyFalse() {
        assertFalse(tmp.containsKey(2));
    }

    /**
     * Test the containsKey() method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that containsKey(null) throws NullPointerException
     */
    @Test
    public void containsKeyNullEx() {
        assertThrows(NullPointerException.class, () -> { tmp.containsKey(null); });
    }

    /**
     * Test the containsValue() method
     *
     * @safe.precondition a Map of 1 entry (value = "uno")
     * @safe.postcondition none
     * @safe.testcases test to verify that containsValue("uno") returns true
     */
    @Test
    public void containsValueTrue() {
        tmp.put(1,"uno");
        assertTrue(tmp.containsValue("uno"));
    }

    /**
     * Test the containsValue() method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that containsValue() returns false
     */
    @Test
    public void containsValueFalse() {
        assertFalse(tmp.containsValue("uno"));
    }

    /**
     * Test the containsValue() method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that containsValue(null) throws NullPointerException
     */
    @Test
    public void containsValueNullEx() {
        assertThrows(NullPointerException.class, () -> { tmp.containsValue(null); });
    }

    /**
     * Test the get(key) method
     *
     * @safe.precondition a Map of 2 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that get(key) returns the correct value when the key is present
     */
    @Test
    public void get() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        assertEquals("uno", tmp.get(1));
    }

    /**
     * Test the get(key) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that get(key) returns Null when the key is absent
     */
    @Test
    public void getNull() {
        assertNull(tmp.get(1));
    }

    /**
     * Test the get(key) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that get(null) throws NullPointerException
     */
    @Test
    public void getNullEx() {
        assertThrows(NullPointerException.class, () -> { tmp.get(null); });
    }

    /**
     * Test the put(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition a Map of 1 entry (key, value)
     * @safe.testcases test to verify that put(key, value) actually increases the size of the Map and that this contains the new entry
     */
    @Test
    public void putFunction() {
        tmp.put(2, "due");
        assertEquals(1,tmp.size());
        assertTrue(tmp.containsKey(2));
        assertTrue(tmp.containsValue("due"));
    }

    /**
     * Test the put(key, value) method
     *
     * @safe.precondition a Map of 1 entry (key, prevValue)
     * @safe.postcondition a Map of 1 entry (key, newValue)
     * @safe.testcases test to verify that put(key, newValue) returns the previous value associated with the key
     */
    @Test
    public void put() {
        tmp.put(2, "tre");
        assertEquals("tre", tmp.put(2, "due"));
    }

    /**
     * Test the put(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition a Map of 1 entry
     * @safe.testcases test to verify that put(key, value) returns Null when the key is absent
     */
    @Test
    public void putNull() {
        assertNull(tmp.put(1, "uno"));
    }

    /**
     * Test the put(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that put(null, value) throws NullPointerException
     */
    @Test
    public void putNullEx() {
        assertThrows(NullPointerException.class, () -> { tmp.put(null,"uno"); });
    }

    /**
     * Test the put(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that put(key, null) throws NullPointerException
     */
    @Test
    public void putNullEx2() {
        assertThrows(NullPointerException.class, () -> { tmp.put(1,null); });
    }

    /**
     * Test the remove(key) method
     *
     * @safe.precondition a Map of 1 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that remove(key) actually removes the entry(key, *) and that the Map decreased of size
     */
    @Test
    public void removeFunction() {
        tmp.put(1, "uno");
        tmp.remove(1);
        assertTrue(tmp.isEmpty());
        assertFalse(tmp.containsKey(1));
    }

    /**
     * Test the remove(key) method
     *
     * @safe.precondition a Map of 1 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that remove(key) returns the values associated with the key
     */
    @Test
    public void remove() {
        tmp.put(1, "uno");
        assertEquals("uno", tmp.remove(1));
    }

    /**
     * Test the remove(key) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that remove(key) returns null when the Map does not contain the key
     */
    @Test
    public void removeEmpty() {
        assertNull(tmp.remove(1));
    }

    /**
     * Test the remove(key) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that remove(null) throws NullPointerException
     */
    @Test
    public void removeNull() {
        assertThrows(NullPointerException.class, () -> { tmp.remove(null); });
    }

    /**
     * Test the putAll(map) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition a Map of 1 entry (the same entry of the second map)
     * @safe.testcases test to verify that putAll(map) actually inserts the entry of the second map in the first
     */
    @Test
    public void putAllFunction() {
        MapAdapter<Integer,String> map = new MapAdapter<>();
        map.put(1,"uno");
        tmp.putAll(map);
        assertTrue(tmp.containsKey(1));
        assertEquals(1,tmp.size());
    }

    /**
     * Test the putAll(map) method
     *
     * @safe.precondition a Map of 1 entry
     * @safe.postcondition a Map of 1 entry (the same entry of the second map)
     * @safe.testcases test to verify that putAll(map) actually inserts the entry of the second map in the first and changes the oldValues if the key inserted corresponds
     */
    @Test
    public void putAll() {
        MapAdapter<Integer,String> map = new MapAdapter<>();
        tmp.put(1,"due");
        map.put(1,"uno");
        tmp.putAll(map);
        assertTrue(tmp.containsKey(1));
        assertEquals(1,tmp.size());
    }

    /**
     * Test the putAll(map) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that putAll(null) throws NullPointerException
     */
    @Test
    public void putAllNull() {
        MapAdapter<Integer,String> store = null;
        assertThrows(NullPointerException.class, () -> { tmp.putAll(store); });
    }

    /**
     * Test the clear() method
     *
     * @safe.precondition a Map of 2 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that clear() leaves the Map empty
     */
    @Test
    public void clear() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.clear();
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the clear() method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that clear() leaves the Map empty
     */
    @Test
    public void clearEmpty() {
        tmp.clear();
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the keySet() method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that keySet() creates a Set that contains all the keys in the Map
     */
    @Test
    public void keySet() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        SetAdapter<Integer> set = (SetAdapter<Integer>) tmp.keySet();
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
    }

    /**
     * Test the keySet() method with clear()
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that calling clear() on the Set actually removes all of the keys from the Set and from the Map
     */
    @Test
    public void keySetClear() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        SetAdapter<Integer> set = (SetAdapter<Integer>) tmp.keySet();
        set.clear();
        assertTrue(tmp.isEmpty());
        assertTrue(set.isEmpty());
    }

    /**
     * Test the keySet() method with remove(key)
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition a Map of 2 entry
     * @safe.testcases test to verify that calling remove(key) on the Set actually remove the key from the Set and from the Map
     */
    @Test
    public void keySetRemove() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        SetAdapter<Integer> set = (SetAdapter<Integer>) tmp.keySet();
        set.remove(1);
        assertFalse(set.contains(1));
        assertFalse(tmp.containsKey(1));
    }

    /**
     * Test the keySet() method with iterator.remove()
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition a Map of 2 entry
     * @safe.testcases test to verify that calling iterator.remove() on the Set actually removes the key from the Set and from the Map
     */
    @Test
    public void keySetRemoveIter() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        SetAdapter<Integer> set = (SetAdapter<Integer>) tmp.keySet();
        Iterator iter = set.iterator();
        int old = (int) iter.next();
        iter.remove();
        assertFalse(set.contains(old));
        assertFalse(tmp.containsKey(old));
    }

    /**
     * Test the keySet() method with removeAll(coll)
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that calling removeAll(coll) on the Set actually removes
     * from the Set and from the Map all of its elements that are contained in the specified collection
     */
    @Test
    public void keySetRemoveAll() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        SetAdapter<Integer> set = (SetAdapter<Integer>) tmp.keySet();
        CollectionAdapter<Integer> coll = new CollectionAdapter<>();
        Iterator iter = set.iterator();
        while(iter.hasNext()){
            coll.add((Integer) iter.next());
        }
        set.removeAll(coll);
        assertTrue(tmp.isEmpty());
        assertTrue(set.isEmpty());
    }

    /**
     * Test the keySet() method with retainAll(coll)
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that calling retainAll(coll) on the Set actually removes
     * from the Set and from the Map all of its elements that are not contained in the specified collection.
     */
    @Test
    public void keySetRetainAll() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        Set<Integer> set = tmp.keySet();
        CollectionAdapter<Integer> coll = new CollectionAdapter<>();
        set.retainAll(coll);
        assertTrue(tmp.isEmpty());
        assertTrue(set.isEmpty());
    }

    /**
     * Test the values() method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that values() creates a Collection that contains all the values in the Map
     */
    @Test
    public void values() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        CollectionAdapter<String> coll = (CollectionAdapter<String>) tmp.values();
        assertTrue(coll.contains("uno"));
        assertTrue(coll.contains("due"));
        assertTrue(coll.contains("tre"));
    }

    /**
     * Test the values() method with clear()
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that calling clear() on the Collection actually removes all of the values from the Collection and from the Map
     */
    @Test
    public void valuesClear() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        CollectionAdapter<String> coll = (CollectionAdapter<String>) tmp.values();
        coll.clear();
        assertTrue(tmp.isEmpty());
        assertTrue(coll.isEmpty());
    }

    /**
     * Test the values() method with remove(key)
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition a Map of 2 entry
     * @safe.testcases test to verify that calling remove(key) on the Collection actually removes the values from the Collection and from the Map
     */
    @Test
    public void valuesRemove() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        CollectionAdapter<String> coll = (CollectionAdapter<String>) tmp.values();
        coll.remove("uno");
        assertFalse(tmp.containsValue("uno"));
        assertFalse(coll.contains("uno"));
    }

    /**
     * Test the values() method with iterator.remove()
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition a Map of 2 entry
     * @safe.testcases test to verify that calling iterator.remove() on the Collection actually removes the value from the Collection and from the Map
     */
    @Test
    public void valuesRemoveIter() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        CollectionAdapter<String> coll = (CollectionAdapter<String>) tmp.values();
        Iterator iter = coll.iterator();
        String old = (String) iter.next();
        iter.remove();
        assertFalse(tmp.containsValue(old));
        assertFalse(coll.contains(old));
    }

    /**
     * Test the values() method with removeAll(coll)
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that calling removeAll(secondColl) on the Collection actually removes
     * from the Collection and from the Map all of its elements that are contained in the specified collection
     */
    @Test
    public void valuesRemoveAll() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        CollectionAdapter<String> coll = (CollectionAdapter<String>) tmp.values();
        CollectionAdapter<String> secondColl = new CollectionAdapter<>();
        Iterator iter = coll.iterator();
        while(iter.hasNext()){
            secondColl.add((String) iter.next());
        }
        coll.removeAll(secondColl);
        assertTrue(tmp.isEmpty());
        assertTrue(coll.isEmpty());
    }

    /**
     * Test the values() method with retainAll(coll)
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that calling retainAll(secondColl) on the Collection actually removes
     * from the Collection and from the Map all of its elements that are not contained in the specified collection.
     */
    @Test
    public void valuesRetainAll() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        CollectionAdapter<String> coll = (CollectionAdapter<String>) tmp.values();
        CollectionAdapter<String> secondColl = new CollectionAdapter<>();
        coll.retainAll(secondColl);
        assertTrue(tmp.isEmpty());
        assertTrue(coll.isEmpty());
    }

    /**
     * Test the entrySet() method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that entrySet() creates a Set that contains all the entry in the Map
     */
    @Test
    public void entrySet() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        SetAdapter<Map.Entry<Integer, String>> entrySet = (SetAdapter<Map.Entry<Integer, String>>) tmp.entrySet();
        Iterator iter = entrySet.iterator();
        while(iter.hasNext()){
            assertTrue(entrySet.contains(iter.next()));
        }
    }

    /**
     * Test the entrySet() method with clear()
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that calling clear() on the Set actually removes all of the entry from the Set and from the Map
     */
    @Test
    public void entrySetClear() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        SetAdapter<Map.Entry<Integer, String>> entrySet = (SetAdapter<Map.Entry<Integer, String>>) tmp.entrySet();
        entrySet.clear();
        assertTrue(tmp.isEmpty());
        assertTrue(entrySet.isEmpty());
    }

    /**
     * Test the entrySet() method with remove(key)
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition a Map of 2 entry
     * @safe.testcases test to verify that calling remove(entry) on the Set actually removes the entry from the Set and from the Map
     */
    @Test
    public void entrySetRemove() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        SetAdapter<Map.Entry<Integer, String>> entrySet = (SetAdapter<Map.Entry<Integer, String>>) tmp.entrySet();
        Map.Entry entry = entrySet.iterator().next();
        entrySet.remove(entry);
        assertFalse(tmp.containsKey(entry.getKey()));
        assertFalse(entrySet.contains(entry));
    }

    /**
     * Test the entrySet() method with iterator.remove()
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition a Map of 2 entry
     * @safe.testcases test to verify that calling iterator.remove() on the Set actually removes the value from the Set and from the Map
     */
    @Test
    public void entrySetRemoveIter() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        SetAdapter<Map.Entry<Integer, String>> entrySet = (SetAdapter<Map.Entry<Integer, String>>) tmp.entrySet();
        Iterator iter = entrySet.iterator();
        Map.Entry entry = (Map.Entry) iter.next();
        iter.remove();
        assertFalse(tmp.containsKey(entry.getKey()));
        assertFalse(entrySet.contains(entry));
    }

    /**
     * Test the entrySet() method with removeAll(coll)
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that calling removeAll(coll) on the Set actually removes
     * from the Set and from the Map all of its elements that are contained in the specified collection
     */
    @Test
    public void entrySetRemoveAll() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        SetAdapter<Map.Entry<Integer, String>> entrySet = (SetAdapter<Map.Entry<Integer, String>>) tmp.entrySet();
        Iterator iter = entrySet.iterator();
        CollectionAdapter<Map.Entry> coll = new CollectionAdapter<>();
        while(iter.hasNext()){
            coll.add((Map.Entry) iter.next());
        }
        entrySet.removeAll(coll);
        assertTrue(tmp.isEmpty());
        assertTrue(entrySet.isEmpty());
    }

    /**
     * Test the entrySet() method with retainAll(coll)
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that calling retainAll(coll) on the Set actually removes
     * from the Set and from the Map all of its elements that are not contained in the specified collection.
     */
    @Test
    public void entrySetRetainAll() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");
        SetAdapter<Map.Entry<Integer, String>> entrySet = (SetAdapter<Map.Entry<Integer, String>>) tmp.entrySet();
        CollectionAdapter<String> coll = new CollectionAdapter<>();
        entrySet.retainAll(coll);
        assertTrue(tmp.isEmpty());
        assertTrue(entrySet.isEmpty());
    }

    /**
     * Test the equals(map) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling equals(map) on the Map returns true if they contains the same entry
     */
    @Test
    public void equalsTrue() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");

        MapAdapter<Integer,String> map = new MapAdapter<>();
        map.put(1,"uno");
        map.put(2,"due");
        map.put(3,"tre");
        assertTrue(tmp.equals(map));
    }

    /**
     * Test the equals(map) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling equals(map) on the Map returns false if they do not contains the same entry
     * or if their size is not equal
     */
    @Test
    public void equalsFalse(){
        tmp.put(1,"uno");
        tmp.put(2,"due");
        tmp.put(3,"tre");

        MapAdapter<Integer,String> map = new MapAdapter<>();
        map.put(1,"uno");
        map.put(2,"due");
        assertFalse(tmp.equals(map));
        map.put(3,"due");
        assertFalse(tmp.equals(map));
    }

    /**
     * Test the hashCode() method
     *
     * @safe.precondition a Map of 2 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling hashCode() on the Map returns the right Integer
     */
    @Test
    public void hashCodeTest() {
        tmp.put(1,"uno");
        tmp.put(2,"due");
        int x = (int) pow(2,99828);
        assertEquals(x+1,tmp.hashCode());
    }

    /**
     * Test the getOrDefault(key, defaultV) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling getOrDefault(key, defaultV) on the Map returns the actual value of the specified key
     * if that is present on the Map
     */
    @Test
    public void getOrDefaultG() {
        tmp.put(1, "uno");
        tmp.put(2, "due");
        tmp.put(3, "tre");
        assertEquals("tre", tmp.getOrDefault(3, "null"));
    }

    /**
     * Test the getOrDefault(key, defaultV) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling getOrDefault(key, defaultV) on the Map returns default Value if the specified key
     * is not present on the Map
     */
    @Test
    public void getOrDefaultD() {
        tmp.put(1, "uno");
        tmp.put(2, "due");
        tmp.put(3, "tre");
        assertEquals("null", tmp.getOrDefault(4, "null"));
    }

    /**
     * Test the getOrDefault(key, defaultV) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling getOrDefault(null, defaultV) on the Map throws NullPointerException
     */
    @Test
    public void getOrDefaultNull() {
        assertThrows(NullPointerException.class, () -> { tmp.getOrDefault(null,"uno"); });
    }

    /**
     * Test the putIfAbsent(key, value) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition a Map of 4 entry
     * @safe.testcases test to verify that calling putIfAbsent(key, value) on the Map actually puts the new entry if not already present the same key
     */
    @Test
    public void putIfAbsentFunction() {
        tmp.put(1, "uno");
        tmp.put(2, "due");
        tmp.put(3, "tre");
        tmp.putIfAbsent(4, "quattro");
        assertTrue(tmp.containsKey(4));
    }

    /**
     * Test the putIfAbsent(key, value) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition a Map of 4 entry
     * @safe.testcases test to verify that calling putIfAbsent(key, value) on the Map returns null if the put was successful
     */
    @Test
    public void putIfAbsent() {
        tmp.put(1, "uno");
        tmp.put(2, "due");
        tmp.put(3, "tre");
        assertEquals(null, tmp.putIfAbsent(4, "quattro"));
    }

    /**
     * Test the putIfAbsent(key, value) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling putIfAbsent(key, value) on the Map returns the value already present associated with the key if present
     * and does not add the new entry
     */
    @Test
    public void putIfAbsentFail() {
        tmp.put(1, "uno");
        tmp.put(2, "due");
        tmp.put(3, "tre");
        assertEquals("uno", tmp.putIfAbsent(1, "cinque"));
        assertFalse(tmp.containsValue("cinque"));
    }

    /**
     * Test the putIfAbsent(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling putIfAbsent(null, value) on the Map throws NullPointerException
     */
    @Test
    public void putIfAbsentNull() {
        assertThrows(NullPointerException.class, () -> { tmp.putIfAbsent(null,"uno"); });
    }

    /**
     * Test the putIfAbsent(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling putIfAbsent(key, null) on the Map throws NullPointerException
     */
    @Test
    public void putIfAbsentNull2() {
        assertThrows(NullPointerException.class, () -> { tmp.putIfAbsent(1,null); });
    }

    /**
     * Test the remove(key, value) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition a Map of 2 entry
     * @safe.testcases test to verify that calling remove(key, value) on the Map returns true if the specified entry was present in the Map
     * and actually removes it
     */
    @Test
    public void removeBoolean() {
        tmp.put(1, "uno");
        tmp.put(2, "due");
        tmp.put(3, "tre");
        assertTrue(tmp.remove(1, "uno"));
        assertFalse(tmp.containsKey(1));
    }

    /**
     * Test the remove(key, value) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling remove(key, value) on the Map returns false if the specified entry was not present in the Map
     */
    @Test
    public void removeBooleanFail() {
        tmp.put(1, "uno");
        tmp.put(2, "due");
        tmp.put(3, "tre");
        assertFalse(tmp.remove(2, "tre"));
        assertTrue(tmp.containsKey(2));
    }

    /**
     * Test the remove(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling remove(key, value) on the Map returns false if there was no entry in the Map
     * and actually removes it
     */
    @Test
    public void removeBooleanEmpty() {
        assertFalse(tmp.remove(1, "uno"));
    }

    /**
     * Test the remove(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling remove(null, value) on the Map throws NullPointerException
     * and actually removes it
     */
    @Test
    public void removeBooleanNull() {
        assertThrows(NullPointerException.class, () -> { tmp.remove(null,"uno"); });
    }

    /**
     * Test the remove(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling remove(key, null) on the Map throws NullPointerException
     * and actually removes it
     */
    @Test
    public void removeBooleanNull2() {
        assertThrows(NullPointerException.class, () -> { tmp.remove(1,null); });
    }

    /**
     * Test the replace(key, oldValue, newValue) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling replace(key, oldValue, newValue) on the Map returns true if the entry (key, oldValue) was present in the Map
     * and actually replaces the oldValue with the newValue
     */
    @Test
    public void replaceOldValue() {
        tmp.put(1, "uno");
        tmp.put(2, "due");
        tmp.put(3, "tre");
        assertTrue(tmp.replace(1, "uno", "due"));
        assertEquals("due", tmp.get(1));
    }

    /**
     * Test the replace(key, oldValue, newValue) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling replace(key, oldValue, newValue) on the Map returns false if the entry (key, oldValue) was not present in the Map
     */
    @Test
    public void replaceOldValueFail() {
        tmp.put(1, "uno");
        tmp.put(2, "due");
        tmp.put(3, "tre");
        assertFalse(tmp.replace(1, "due", "tre"));
        assertTrue(tmp.containsValue("uno"));
    }

    /**
     * Test the replace(key, oldValue, newValue) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling replace(key, oldValue, newValue) on the Map returns false if the entry (key, oldValue) was not present in the Map
     */
    @Test
    public void replaceOldValueEmpty() {
        assertFalse(tmp.replace(1, "due", "uno"));
    }

    /**
     * Test the replace(key, oldValue, newValue) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling replace(null, oldValue, newValue) on the Map throws NullPointerException
     */
    @Test
    public void replaceOldValueNull() {
        assertThrows(NullPointerException.class, () -> { tmp.replace(null,"uno","due"); });
    }

    /**
     * Test the replace(key, oldValue, newValue) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling replace(key, null, newValue) on the Map throws NullPointerException
     */
    @Test
    public void replaceOldValueNull2() {
       assertThrows(NullPointerException.class, () -> { tmp.replace(1,null,"due"); });
    }

    /**
     * Test the replace(key, oldValue, newValue) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling replace(key, oldValue, null) on the Map throws NullPointerException
     */
    @Test
    public void replaceOldValueNull3() {
        assertThrows(NullPointerException.class, () -> { tmp.replace(1,"uno",null); });
    }

    /**
     * Test the replace(key, value) method
     *
     * @safe.precondition a Map of 3 entry
     * @safe.postcondition none
     * @safe.testcases test to verify that calling replace(key, value) on the Map returns the oldValue if the key was present
     * and actually replaces the oldValue with the newValue
     */
    @Test
    public void replace() {
        tmp.put(1, "due");
        tmp.put(2, "due");
        tmp.put(3, "tre");
        assertEquals("due", tmp.replace(1, "uno"));
        assertTrue(tmp.containsValue("uno"));
    }

    /**
     * Test the replace(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling replace(key, value) on the Map returns null if the key was not present
     */
    @Test
    public void replaceEmpty() {
        assertNull(tmp.replace(1, "uno"));
    }

    /**
     * Test the replace(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling replace(null, value) on the Map throws NullPointerException
     */
    @Test
    public void replaceNull() {
        assertThrows(NullPointerException.class, () -> { tmp.replace(null,"uno"); });
    }

    /**
     * Test the replace(key, value) method
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling replace(key, null) on the Map throws NullPointerException
     */
    @Test
    public void replaceNull2() {
        assertThrows(NullPointerException.class, () -> { tmp.replace(1,null); });
    }

    /**
     * Test the MapIterator called by entrySet
     *
     * @safe.precondition a Map of 1 entry
     * @safe.postcondition an empty Map
     * @safe.testcases test to verify that calling iterator() on the Set returned by entrySet() returns an actual iterator
     * that works over the entry of the Map
     */
    @Test
    public void iterator() {
        tmp.put(1,"uno");

        Set<Map.Entry<Integer,String>> set = tmp.entrySet();
        ListIterator iter = (ListIterator) set.iterator();
        Map.Entry entry = (Map.Entry) iter.next();
        assertEquals("uno",entry.getValue());
        assertFalse(iter.hasNext());
        entry = (Map.Entry) iter.previous();
        assertEquals("uno",entry.getValue());
        assertFalse(iter.hasPrevious());
        iter.remove();
        assertTrue(tmp.isEmpty());
    }

    /**
     * Test the MapIterator called by entrySet
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().remove() on the Set returned by entrySet() throws IllegalStateException if not called after a next() or previosu()
     */
    @Test
    public void iteratorRemove() {
        Set<Map.Entry<Integer,String>> set = tmp.entrySet();
        ListIterator iter = (ListIterator) set.iterator();
        assertThrows(IllegalStateException.class, () -> { iter.remove(); });
    }

    /**
     * Test the MapIterator called by entrySet
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().next() on the Set returned by entrySet() throws NoSuchElementException if there's no next element
     */
    @Test
    public void iteratorNext() {
        Set<Map.Entry<Integer,String>> set = tmp.entrySet();
        ListIterator iter = (ListIterator) set.iterator();
        assertThrows(NoSuchElementException.class, () -> { iter.next(); });
    }

    /**
     * Test the MapIterator called by entrySet
     *
     * @safe.precondition an empty Map
     * @safe.postcondition none
     * @safe.testcases test to verify that calling iterator().previous() on the Set returned by entrySet() throws NoSuchElementException if there's no previous element
     */
    @Test
    public void iteratorPrevious() {
        Set<Map.Entry<Integer,String>> set = tmp.entrySet();
        ListIterator iter = (ListIterator) set.iterator();
        assertThrows(NoSuchElementException.class, () -> { iter.previous(); });
    }
}
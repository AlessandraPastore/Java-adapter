import java.util.*;
import static java.lang.Math.pow;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * <b>Homework 2, versione 1
 * <br>Alessandra Pastore 1191000 </b>
 *      <br>
 *      <br>La seguente classe MapAdapter implementa l'interfaccia Map.
 *      <br>Viene utilizzato il pattern Object Adapter per convertire l'interfaccia di HashTable della Java Micro Edition, CLDC1.1,
 *      in quanto quest'ultima non è compatibile con quella di Map della J2SE.
 *      <br>Ho preferito l'utilizzo del pattern Object Adapter, piuttosto che del ClassAdapter in modo da non estendere l'adaptee (HashTable), bensì
 *      di mantenerne un istanza attiva e di lavorare con essa.
 *      In caso di mantenimento dell'adapter nel riconoscere nuove sottoclassi dell'adaptee, basterà aggiungere le istanze delle sottoclassi stesse
 *      piuttosto che implementare da capo una nuova classe Adapter.
 *
 */

public class MapAdapter<K,V>
        implements Map<K,V> {

    private Hashtable<K,V> a = new Hashtable<K,V>();

    /**
     * Returns the number of key-value mappings in this map.
     * If the map contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of key-value mappings in this map
     */
    public int size() { return a.size(); }

    /**
     * Returns {@code true} if this map contains no key-value mappings.
     *
     * @return {@code true} if this map contains no key-value mappings
     */
    public boolean isEmpty() {
        return a.isEmpty();
    }

    /**
     * Returns true if this map contains a mapping for the specified key.
     * More formally, returns true if and only if this map contains a mapping for a key k such that Objects.equals(key, k).
     * (There can be at most one such mapping.)
     *
     * @param key  key whose presence in this map is to be tested
     * @return {@code true} if this map contains a mapping for the specified key
     * @throws ClassCastException  if the key is of an inappropriate type for this map
     * @throws NullPointerException  if the specified key is null
     */
    public boolean containsKey(Object key) {
        if(key == null) throw new NullPointerException();
        K k = (K)key;       //throw ClassCastException
        return a.containsKey(k);
    }

    /**
     * Returns true if this map maps one or more keys to the specified value.
     * More formally, returns true if and only if this map contains at least one mapping to a value v such that Objects.equals(value, v).
     * This operation will probably require time linear in the map size for most implementations of the Map interface.
     *
     * @param value  value whose presence in this map is to be tested
     * @return true if this map maps one or more keys to the specified value
     * @throws ClassCastException  if the value is of an inappropriate type for this map
     * @throws NullPointerException  if the specified value is null
     */
    public boolean containsValue(Object value) {
        V v = (V)value;     //throw ClassCastException
        return a.contains(v);   //throw NullPointerException
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     * More formally, if this map contains a mapping from a key k to a value v such that Objects.equals(key, k),
     * then this method returns v; otherwise it returns null.
     * (There can be at most one such mapping.)
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     * @throws ClassCastException if the key is of an inappropriate type for this map
     * @throws NullPointerException if the specified key is null
     */
    public V get(Object key) {
        if(key == null) throw new NullPointerException();
        K k = (K)key;       //throw ClassCastException
        return a.get(k);    //null if there no k key
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value is replaced by the specified value.
     * (A map m is said to contain a mapping for a key k if and only if m.containsKey(k) would return true.)
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no mapping for key.
     * @throws ClassCastException if the class of the specified key or value prevents it from being stored in this map
     * @throws NullPointerException if the specified key or value is null
     */
    public V put(K key, V value) {
        V v = (V)value;     //throw ClassCastException
        K k = (K)key;       //throw ClassCastException
        return a.put(k, v);   //nullpointer
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * More formally, if this map contains a mapping from key k to value v such that Objects.equals(key, k), that mapping is removed.
     * (The map can contain at most one such mapping.)
     * Returns the value to which this map previously associated the key, or null if the map contained no mapping for the key.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for key.
     * @throws ClassCastException if the key is of an inappropriate type for this map
     * @throws NullPointerException if the specified key is null and this map does not permit null keys
     */
    public V remove(Object key) {
        if(key == null) throw new NullPointerException();
        K k = (K)key;       //throw ClassCastException
        return a.remove(k);
    }

    /**
     * Copies all of the mappings from the specified map to this map.
     * The effect of this call is equivalent to that of calling put(k, v) on this map once for each mapping from key k to value v in the specified map.
     * The behavior of this operation is undefined if the specified map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     * @throws ClassCastException - if the class of a key or value in the specified map prevents it from being stored in this map
     * @throws NullPointerException - if the specified map is null and the specified map contains null keys or values
     */
    public void putAll(Map<? extends K, ? extends V> m) {
        if(m == null) throw new NullPointerException();
        MapAdapter<K,V> map = (MapAdapter<K,V>) m;
        SetAdapter<Entry<K,V>> tmp = (SetAdapter<Entry<K,V>>) map.entrySet();
        Iterator<Map.Entry<K,V>> iter = tmp.iterator();
        while(iter.hasNext()) {
            Map.Entry<K,V> entry = iter.next();
            K key = entry.getKey();
            V value = entry.getValue();
            put(key,value);
        }
    }

    /**
     * Removes all of the mappings from this map.
     * The map will be empty after this call returns.
     */
    public void clear() { a.clear(); }

    /**
     * Returns a Set view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
     * If the map is modified while an iteration over the set is in progress (except through the iterator's own remove operation), the results of the iteration are undefined.
     * The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll, and clear operations.
     * It does not support the add or addAll operations.
     *
     * @return a set view of the keys contained in this map
     */
    public Set<K> keySet() {
        return new KeySet();
    }

    /**
     * Un set con tutte le chiavi della mappa attuale
     */
    private class KeySet extends SetAdapter<K> {
        public int size() { return MapAdapter.this.size(); }
        public boolean isEmpty() { return MapAdapter.this.isEmpty(); }
        public void clear() { MapAdapter.this.clear(); }
        public boolean contains(Object k) { return MapAdapter.this.containsKey(k); }
        public boolean remove(Object o) {
            if(MapAdapter.this.remove(o) == null) return false;
            return true;
        }
        public boolean removeAll(Collection<?> c) {
            return super.removeAll(c);
        }
        public boolean retainAll(Collection<?> c) {
            return super.retainAll(c);
        }
        public Iterator<K> iterator() { return new ListIterator<K>(){
            private ListIterator<Entry<K, V>> i = (ListIterator<Entry<K, V>>) entrySet().iterator();

            public boolean hasNext() { return i.hasNext(); }
            public K next() { return i.next().getKey(); }
            public boolean hasPrevious() { return i.hasPrevious(); }
            public K previous() { return i.previous().getKey(); }
            public int nextIndex() { return i.nextIndex(); }
            public int previousIndex() { return i.nextIndex(); }
            public void remove() { i.remove(); }
            public void set(K k) { throw new UnsupportedOperationException(); }
            public void add(K k) { throw new UnsupportedOperationException(); }
        };
        }
        public boolean add(K k) { throw new UnsupportedOperationException(); }
        public boolean addAll(Collection<? extends K> c) { throw new UnsupportedOperationException(); }
    }

    /**
     * Returns a Collection view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa.
     * If the map is modified while an iteration over the collection is in progress (except through the iterator's own remove operation), the results of the iteration are undefined.
     * The collection supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Collection.remove, removeAll, retainAll and clear operations.
     * It does not support the add or addAll operations.
     *
     * @return a collection view of the values contained in this map
     */
    public Collection<V> values() {
        return new Values();
    }

    /**
     * Una collection con tutti i valori della Map attuale
     */
    private class Values extends CollectionAdapter<V> {
        public int size() { return MapAdapter.this.size(); }
        public boolean isEmpty() { return MapAdapter.this.isEmpty(); }
        public void clear() { MapAdapter.this.clear(); }
        public boolean contains(Object o) { return MapAdapter.this.containsValue(o); }
        public boolean remove(Object o) {
            if(this.contains(o)) {
                Iterator<V> iter = this.iterator();
                while(iter.hasNext()) {
                    V value = iter.next();
                    if(value == o) {
                        iter.remove();
                        return true;
                    }
                }
            }
            return false;
        }
        public boolean removeAll(Collection<?> c) {
            return super.removeAll(c);
        }
        public boolean retainAll(Collection<?> c) {
            return super.retainAll(c);
        }
        public Iterator<V> iterator() { return new ListIterator<V>() {

            private ListIterator<Entry<K, V>> i = (ListIterator<Entry<K, V>>) entrySet().iterator();
            public boolean hasNext() { return i.hasNext(); }
            public V next() { return i.next().getValue(); }
            public boolean hasPrevious() { return i.hasPrevious(); }
            public V previous() { return i.previous().getValue(); }
            public int nextIndex() { return i.nextIndex(); }
            public int previousIndex() { return i.previousIndex(); }
            public void remove() { i.remove(); }
            public void set(V v) { throw new UnsupportedOperationException(); }
            public void add(V v) { throw new UnsupportedOperationException(); }
        };
        }
        public boolean add(V v) { throw new UnsupportedOperationException(); }
        public boolean addAll(Collection<? extends V> c) { throw new UnsupportedOperationException(); }
    }

    /**
     * Returns a Set view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are reflected in the set, and vice-versa.
     * If the map is modified while an iteration over the set is in progress (except through the iterator's own remove operation, or through the setValue operation on a map entry returned by the iterator) the results of the iteration are undefined.
     * The set supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Set.remove, removeAll, retainAll and clear operations.
     * It does not support the add or addAll operations.
     *
     * @return a set view of the mappings contained in this map
     */
    public Set<Map.Entry<K,V>> entrySet() {
        return new EntrySet();
    }

    /**
     * Un set con tutte le entry della Map attuale
     */
    private class EntrySet extends SetAdapter<Map.Entry<K,V>> {

        public int size() { return MapAdapter.this.size(); }
        public void clear() { MapAdapter.this.clear(); }
        public Iterator<Map.Entry<K, V>> iterator() { return new MapIterator(); }
        public boolean contains(Object o) {
            Map.Entry<K,V> entry = (Entry<K, V>) o;
            K key = entry.getKey();
            V value = entry.getValue();
            if(MapAdapter.this.containsKey(key)) {
                if(MapAdapter.this.get(key) == value) return true;
            }
            return false;
        }
        public boolean remove(Object o) {
            Map.Entry<K,V> entry = (Entry<K, V>) o;
            K key = entry.getKey();
            V value = entry.getValue();
            return MapAdapter.this.remove(key, value);
        }
        public boolean removeAll(Collection<?> c) {
            return super.removeAll(c);
        }
        public boolean retainAll(Collection<?> c) {
            return super.retainAll(c);
        }
        public boolean add(Entry<K, V> kvEntry) { throw new UnsupportedOperationException(); }
        public boolean addAll(Collection<? extends Entry<K, V>> c) { throw new UnsupportedOperationException(); }
    }

    /**
     * Compares the specified object with this map for equality.
     * Returns true if the given object is also a map and the two maps represent the same mappings.
     * More formally, two maps m1 and m2 represent the same mappings if m1.entrySet().equals(m2.entrySet()).
     * This ensures that the equals method works properly across different implementations of the Map interface.
     *
     * @param o object to be compared for equality with this map
     * @return true if the specified object is equal to this map
     */
    public boolean equals(Object o) {
        MapAdapter obj = (MapAdapter) o;
        return entrySet().equals(obj.entrySet());
    }

    /**
     * Returns the hash code value for this map.
     * The hash code of a map is defined to be the sum of the hash codes of each entry in the map's entrySet() view.
     * This ensures that m1.equals(m2) implies that m1.hashCode()==m2.hashCode() for any two maps m1 and m2, as required by the general contract of Object.hashCode().
     *
     * @return the hash code value for this map
     */
    public int hashCode() {
        SetAdapter<Map.Entry<K,V>> tmp = (SetAdapter<Entry<K,V>>)this.entrySet();
        Iterator iter = tmp.iterator();
        int sum = 0;
        while(iter.hasNext()) {
            EntryAdapter entry = (EntryAdapter) iter.next();
            int ss = entry.hashCode();
            sum += ss;
        }
        return sum;
    }

    /**
     * Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default mapping of the key
     * @return the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key
     * @throws ClassCastException if the key is of an inappropriate type for this map
     * @throws NullPointerException if the specified key is null
     */
    public V getOrDefault(Object key, V defaultValue) {
        V mapped = this.get(key);
        if(mapped == null) return defaultValue;
        return mapped;
    }

    /**
     * Performs the given action for each entry in this map until all entries have been processed or the action throws an exception.
     *
     * @param action
     * @throws UnsupportedOperationException
     */
    public void forEach(BiConsumer<? super K,? super V> action) { throw new UnsupportedOperationException(); }

    /**
     * Replaces each entry's value with the result of invoking the given function on that entry until all entries have been processed or the function throws an exception.
     *
     * @param function
     * @throws UnsupportedOperationException
     */
    public void replaceAll(BiFunction<? super K,? super V,? extends V> function) { throw new UnsupportedOperationException(); }

    /**
     * If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null, else returns the current value.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or null if there was no mapping for the key.
     * @throws ClassCastException if the key or value is of an inappropriate type for this map
     * @throws NullPointerException if the specified key or value is null
     */
    public V putIfAbsent(K key, V value) {
        if(value == null) throw new NullPointerException();
        if(this.containsKey(key)) return this.get(key); //cast and null
        this.put(key, value);   //cast e null
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to the specified value.
     *
     * @param key key with which the specified value is associated
     * @param value value expected to be associated with the specified key
     * @return true if the value was removed
     * @throws ClassCastException if the key or value is of an inappropriate type for this map
     * @throws NullPointerException if the specified key or value is null
     */
    public boolean remove(Object key, Object value) {
        if(value == null) throw new NullPointerException();
        V v =(V)value;      //throw classcast
        if(this.containsKey(key) && this.get(key).equals(v)) {
            this.remove(key);
            return true;
        }
        return false;
    }

    /**
     * Replaces the entry for the specified key only if currently mapped to the specified value.
     *
     * @param key key with which the specified value is associated
     * @param oldValue value expected to be associated with the specified key
     * @param newValue value to be associated with the specified key
     * @return true if the value was replaced
     * @throws ClassCastException if the class of a specified key or value prevents it from being stored in this map
     * @throws NullPointerException if a specified key or newValue is null
     * @throws NullPointerException - if oldValue is null
     */
    public boolean replace(K key, V oldValue, V newValue) {
        if(oldValue == null || newValue == null) throw new NullPointerException();
        V old = (V)oldValue;        //classcast
        if (this.containsKey(key) && this.get(key).equals(old)) {
            this.put(key, newValue);
            return true;
        }
        return false;
    }

    /**
     * Replaces the entry for the specified key only if it is currently mapped to some value.
     *
     * @param key key with which the specified value is associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with the specified key, or null if there was no mapping for the key.
     * @throws ClassCastException - if the class of the specified key or value prevents it from being stored in this map
     * @throws NullPointerException - if the specified key or value is null, and this map does not permit null keys or values
     */
    public V replace(K key, V value) {
        if(value == null) throw new NullPointerException();
        if (this.containsKey(key)) {
            return this.put(key, value);
        }
        return null;
    }

    /**
     * If the specified key is not already associated with a value (or is mapped to null),
     * attempts to compute its value using the given mapping function and enters it into this map unless null.
     *
     * @param key
     * @param mappingFunction
     * @throws UnsupportedOperationException
     */
    public V computeIfAbsent(K key, Function<? super K,? extends V> mappingFunction) { throw new UnsupportedOperationException(); }

    /**
     * If the value for the specified key is present and non-null, attempts to compute a new mapping given the key and its current mapped value.
     *
     * @param key
     * @param remappingFunction
     * @throws UnsupportedOperationException
     */
    public V computeIfPresent(K key, BiFunction<? super K,? super V,? extends V> remappingFunction) { throw new UnsupportedOperationException(); }

    /**
     * Attempts to compute a mapping for the specified key and its current mapped value (or null if there is no current mapping).
     *
     * @param key
     * @param remappingFunction
     * @throws UnsupportedOperationException
     */
    public V compute(K key, BiFunction<? super K,? super V,? extends V> remappingFunction) { throw new UnsupportedOperationException(); }

    /**
     *If the specified key is not already associated with a value or is associated with null, associates it with the given non-null value.
     *
     * @param key
     * @param value
     * @param remappingFunction
     * @throws UnsupportedOperationException
     */
    public V merge(K key, V value, BiFunction<? super V,? super V,? extends V> remappingFunction) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing zero mappings.
     *
     * @throws UnsupportedOperationException
     */
    public <K,V> Map<K,V> of() { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing a single mapping.
     *
     * @throws UnsupportedOperationException
     */
    public <K,V> Map<K,V> of(K k1, V v1) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing two mappings.
     *
     * @throws UnsupportedOperationException
     */
    public <K,V> Map<K,V> of(K k1, V v1, K k2, V v2) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing three mappings.
     *
     * @throws UnsupportedOperationException
     */
    public <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing four mappings.
     *
     * @throws UnsupportedOperationException
     */
    public <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing five mappings.
     *
     * @throws UnsupportedOperationException
     */
    public <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing six mappings.
     *
     * @throws UnsupportedOperationException
     */
    public <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing seven mappings.
     *
     * @throws UnsupportedOperationException
     */
    public <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing eight mappings.
     *
     * @throws UnsupportedOperationException
     */
    public <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing nine mappings.
     *
     * @throws UnsupportedOperationException
     */
    public <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing ten mappings.
     *
     * @throws UnsupportedOperationException
     */
    public <K,V> Map<K,V> of(K k1, V v1, K k2, V v2, K k3, V v3, K k4, V v4, K k5, V v5, K k6, V v6, K k7, V v7, K k8, V v8, K k9, V v9, K k10, V v10) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable map containing keys and values extracted from the given entries.
     *
     * @param entries
     * @param <K>
     * @param <V>
     * @throws UnsupportedOperationException
     */
    public <K, V> Map<K, V> ofEntries(Entry<? extends K, ? extends V>... entries) { throw new UnsupportedOperationException(); }

    /**
     * Returns an immutable Map.Entry containing the given key and value.
     *
     * @param k
     * @param v
     * @param <K>
     * @param <V>
     * @throws UnsupportedOperationException
     */
    public <K,V> Map.Entry<K,V> entry(K k, V v) { throw new UnsupportedOperationException(); }


    /**
     *  Un iteratore che scorre la mappa attraverso le sue entry (coppie chiave - valore)
     */
    private class MapIterator
            implements ListIterator {

        private Enumeration<K> keys;
        private Enumeration<V> values;
        private int current;
        private K key;
        private V value;
        private boolean next;
        private boolean previous;

        public MapIterator() {
            keys = a.keys();
            values= a.elements();
            key = null;
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
            if(previous) {
                previous();
                current++;
            }
            return (values.hasMoreElements() && keys.hasMoreElements()); }

        /**
         * Returns the next element in the list and advances the cursor position.
         * This method may be called repeatedly to iterate through the list, or intermixed with calls to previous() to go back and forth.
         * (Note that alternating calls to next and previous will return the same element repeatedly.)
         *
         * @return the next element in the list
         * @throws NoSuchElementException if the iteration has no next element
         */
        public Map.Entry<K,V> next() {
            if(!this.hasNext()) throw new NoSuchElementException();
            next = true;
            previous = false;
            key = keys.nextElement();
            value = values.nextElement();
            current++;
            return new EntryAdapter<K,V>(key,value);
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
        public Map.Entry<K,V> previous() {
            if(!this.hasPrevious()) throw new NoSuchElementException();
            keys = a.keys();
            for(int i=0; i<current;i++) {
                key = keys.nextElement();
            }
            values = a.elements();
            for(int i=0; i<current;i++) {
                value = values.nextElement();
            }
            previous = true;
            next = false;
            current--;
            return new EntryAdapter<>(key,value);
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
            return current - 1;
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
            if(next) {
                Entry entry = this.previous();
                MapAdapter.this.remove(entry.getKey());
                previous = false;
            }
            if(previous) MapAdapter.this.remove(key,value);
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

    /**
     * Una entry (coppia chiave - valore )
     */
    private class EntryAdapter<K,V>
            implements Map.Entry<K,V> {

        /**
         * the key represented by this entry
         */
        private K key;

        /**
         * the value represented by this entry
         */
        private V value;

        /**
         * Creates an entry representing a mapping from the specified key to the specified value.
         *
         * @param k the key represented by this entry
         * @param v the value represented by this entry
         */
        public EntryAdapter(K k, V v) {
            key=k;
            value=v;
        }

        /**
         * Returns the key corresponding to this entry
         *
         * @return the key corresponding to this entry
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Returns the value corresponding to this entry.
         *
         * @return the value corresponding to this entry
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Replaces the value corresponding to this entry with the specified value.
         *
         * @param value new value to be stored in this entry
         * @return old value corresponding to the entry
         * @throws ClassCastException if the class of the specified value prevents it from being stored in the backing map
         * @throws NullPointerException if the specified value is null
         */
        public V setValue(V value) {
            if(value == null) throw new NullPointerException();
            V exValue = this.value;
            this.value = (V) value; //classcast
            return exValue;
        }

        /**
         * Compares the specified object with this entry for equality.
         *
         * @param obj object to be compared for equality with this map entry
         * @return true if the specified object is equal to this map entry
         */
        public boolean equals(Object obj) {
            EntryAdapter<K,V> o = null;
            try {
                o = (EntryAdapter<K, V>)obj;
            }
            catch (ClassCastException cce) {
                cce.printStackTrace();
                return false;
            }
            K objKey = o.getKey();
            V objValue = o.getValue();

            if(this.key == objKey && this.value == objValue) return true;
            return false;
        }

        /**
         * Returns the hash code value for this map entry.
         *
         * @return the hash code value for this map entry
         */
        public int hashCode() {
            int hashKey = key.hashCode();
            int hashValue = value.hashCode();
            return (int) pow(hashKey,hashValue);
        }

        /**
         * Returns a comparator that compares Map.Entry in natural order on key.
         *
         * @param <K>
         * @param <V>
         * @throws UnsupportedOperationException
         */
        public <K extends Comparable<? super K>,V> Comparator<Map.Entry<K,V>>	comparingByKey() { throw new UnsupportedOperationException(); }

        /**
         * Returns a comparator that compares Map.Entry by key using the given Comparator.
         *
         * @param cmp
         * @param <K>
         * @param <V>
         * @throws UnsupportedOperationException
         */
        public <K,V> Comparator<Map.Entry<K,V>>	comparingByKey(Comparator<? super K> cmp) { throw new UnsupportedOperationException(); }

        /**
         * Returns a comparator that compares Map.Entry in natural order on value.
         *
         * @param <K>
         * @param <V>
         * @throws UnsupportedOperationException
         */
        public <K,V extends Comparable<? super V>> Comparator<Map.Entry<K,V>> comparingByValue() { throw new UnsupportedOperationException(); }

        /**
         * Returns a comparator that compares Map.Entry by value using the given Comparator.
         *
         * @param cmp
         * @param <K>
         * @param <V>
         * @throws UnsupportedOperationException
         */
        public <K,V> Comparator<Map.Entry<K,V>>	comparingByValue(Comparator<? super V> cmp) { throw new UnsupportedOperationException(); }


    }
}
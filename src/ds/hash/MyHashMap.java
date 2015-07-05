package ds.hash;

import java.util.*;

public class MyHashMap<K,V> implements Map<K,V> {

    public static final int DEFAULT_TABLE_SIZE = 16;
    public static final double LOAD_FACTOR = 0.75;

    private int size;
    private Bucket<K,V>[] table;
    private int threshold;


    @SuppressWarnings({"rawtypes","unchecked"})
    public MyHashMap() {
        table = (Bucket<K,V>[]) new Bucket[DEFAULT_TABLE_SIZE];
        threshold = (int) (DEFAULT_TABLE_SIZE * LOAD_FACTOR);
    }

    @Override
    public int size() {

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {

        int position = findPosition(key);

        if(table[position] == null){
            return false;
        } else {
            Bucket<K,V> iter = findInBucket(key, position);
            return iter != null;
        }

    }

    @Override
    public boolean containsValue(Object value) {
        BucketIterator iterator = new BucketIterator();
        while(iterator.hasNext()){
            Bucket<K,V> iter = iterator.next();
            if(iter.value.equals(value)){
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {

        int position = findPosition(key);

        if(table[position] == null){
            return null;
        } else {
            Bucket<K,V> iter = findInBucket(key, position);
            return iter != null ? iter.value : null;
        }
    }

    @Override
    public V put(K key, V value) {
        if(size >= threshold){
            resize(table.length * 2);
        }
        int hash = Math.abs(key.hashCode());
        int position = hash % table.length;

        if(table[position] == null){
            table[position] = new Bucket<K, V>(key,value);
        } else {
            Bucket iter = table[position];
            // find last node in bucket
            while(iter.next != null){
                iter = iter.next;
            }

            iter.next = new Bucket<K, V>(key,value);
        }
        size++;
        return null;
    }

    private void resize(int newSize){
        Bucket<K,V>[] newTable = new Bucket[newSize];
        reput(newTable);
        table = newTable;
        threshold = (int)(newSize * LOAD_FACTOR);
    }

    private void reput(Bucket<K,V>[] newTable){
        for (int i = 0; i < table.length; i++) {
            Bucket<K,V> iter = table[i];
            if(iter != null){
                table[i] = null;
                do {
                    Bucket<K,V> next = iter.next;
                    int position = findPosition(iter.key);
                    iter.next = table[position];
                    newTable[position] = iter;
                    iter = next;
                } while (iter != null);
            }
        }
    }

    @Override
    public V remove(Object key) {
        int position = findPosition(key);
        Bucket<K,V> iter = table[position];
        Bucket<K,V> prev = table[position];
        if(table[position].next == null){
            table[position] = null;
            size--;
        }else {
            iter = iter.next;
            for(; iter != null; iter = iter.next, prev = prev.next){
                if(iter.key.equals(key) && iter.key.hashCode() == key.hashCode()){
                    prev.next = iter.next;
                    break;
                }
            }
        }
        return iter.value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = null;
        }
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        BucketIterator iter = new BucketIterator();
        while (iter.hasNext()){
            set.add(iter.next().key);
        }
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> set = new ArrayList<V>();
        BucketIterator iter = new BucketIterator();
        while (iter.hasNext()){
            set.add(iter.next().value);
        }
        return set;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    public Iterator getIterator(){
        return new BucketIterator();
    }

    private class BucketIterator implements Iterator<Bucket<K,V>> {

        Bucket<K,V> current;
        int i = 0;

        public BucketIterator() {
            findNextNotNullBucket();
        }

        private void findNextNotNullBucket() {

            for (; i < table.length && table[i] == null; i++) {
            }
            current = i< table.length ? table[i] : null;

        }

        @Override
        public boolean hasNext() {
            return i < table.length && current != null;
        }

        @Override
        public Bucket<K,V> next() {
            Bucket<K, V> iter = current;
            if(current.next != null){
                current = current.next;
            }else{
                i++;
                findNextNotNullBucket();

            }
            return iter;

        }
    }


    private Bucket<K,V> findInBucket(Object key, int position){
        Bucket<K,V> iter = table[position];
        // find last node in bucket
        while(iter.next != null){
            if(iter.key.equals(key)){
                return iter;
            }
            iter = iter.next;
        }

        return null;
    }

    private int findPosition(Object key){
        int hash = Math.abs(key.hashCode());
        return hash % table.length;
    }


    private static class Bucket<K,V> {
        K key;
        V value;
        Bucket<K,V> next;

        public Bucket(K key, V value, Bucket next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Bucket(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}

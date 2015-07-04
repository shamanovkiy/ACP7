package ds.hash;

import java.util.Iterator;

public class TestMyHashMap {
    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap<>();
        map.put(5, "a");
        map.put(9, "b");
        map.put(12, "c");
        map.put(5, "d");

        Iterator iter = map.getIterator();
        Object[] o = new Object[5];
        o[0] = iter.next();
        o[1] = iter.next();
        o[2] = iter.next();
        o[3] = iter.next();
        iter.hasNext();


    }
}

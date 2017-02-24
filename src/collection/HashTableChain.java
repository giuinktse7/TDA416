package collection;

import java.util.Iterator;
import java.util.LinkedList;

public class HashTableChain<K, V> implements  NewHashMap<K, V> {
    
    private LinkedList<Entry<K, V>>[] table;
    private int numKeys;
    private static final int CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 3.0;
    
    public HashTableChain() {
        table = new LinkedList[CAPACITY];
    }

    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) index += table.length;
        
        if (table[index] == null)
            return null;
        
        for (Entry<K, V> entry : table[index])
            if (key.equals(entry.getKey()))
                return entry.getValue();
        
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        if (index < 0) index += table.length;
        
        if (table[index] == null)
            table[index] = new LinkedList<>();
        
        for (Entry<K, V> entry : table[index])
            if (key.equals(entry.getKey())) {
                V oldValue = entry.getValue();
                entry.setValue(value);
                return oldValue;
            }
            
        table[index].addFirst(new Entry<>(key, value));
        ++numKeys;
        rehash();
        return null;
    }

    private void rehash() {
        if ( !((double) (numKeys) / table.length > LOAD_THRESHOLD) )
            return;

        LinkedList<Entry<K, V>>[] oldTable = table;
        table = new LinkedList[2 * oldTable.length - 1];

        numKeys = 0;
        for (LinkedList<Entry<K, V>> list : table)
            list.forEach(entry -> {
                put(entry.getKey(), entry.getValue());
                ++numKeys;
            });
    }

    @Override
    public V remove(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0) index += table.length;
        
        if (table[index] == null)
            return null;
        
        Iterator<Entry<K, V>> iterator = table[index].iterator();
        while (iterator.hasNext()) {
            Entry<K, V> entry = iterator.next();
            if (key.equals(entry.getKey())) {
                iterator.remove();
                --numKeys;
                if (table[index].isEmpty())
                    table[index] = null;
                
                return entry.getValue();
            }
        }
        
        return null;
    }

    @Override
    public int size() {
        return numKeys;
    }
}

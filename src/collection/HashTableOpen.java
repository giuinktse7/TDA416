package collection;

public class HashTableOpen<K, V> implements NewHashMap<K, V> {

    private Entry<K, V>[] table;
    private static final int START_CAPACITY = 101;
    private double LOAD_THRESHOLD = .75;
    private int numKeys, numDeletes;
    private final Entry<K, V> DELETED = new Entry<>(null, null);

    public HashTableOpen() {
        table = new Entry[START_CAPACITY];
    }

    @Override
    public V get(Object key) {
        int index = find(key);

        return table[index] != null ? table[index].getValue() : null;
    }

    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    @Override
    public V put(K key, V value) {
        int index = find(key);

        if (table[index] == null) {
            table[index] = new Entry<>(key, value);
            ++numKeys;
            rehash();
            return null;
        }

        V oldValue = table[index].getValue();
        table[index].setValue(value);
        return oldValue;
    }

    private void rehash() {
        if ( !((double) (numKeys + numDeletes) / table.length > LOAD_THRESHOLD) )
            return;

        Entry<K, V>[] oldTable = table;
        table = new Entry[2 * oldTable.length - 1];

        numKeys = 0;
        numDeletes = 0;
        for (Entry<K, V> entry : oldTable)
            if (entry != null && entry != DELETED)
                put(entry.getKey(), entry.getValue());
    }

    @Override
    public V remove(Object key) {
        int index = find(key);

        if (table[index] == null)
            return null;

        V oldValue = table[index].getValue();
        table[index] = DELETED;
        ++numDeletes;
        --numKeys;
        return oldValue;
    }

    @Override
    public int size() {
        return numKeys;
    }

    private int find(Object key) {
        int index = key.hashCode() % table.length;
        if (index < 0)
            index += table.length;

        while (table[index] != null && !key.equals(table[index].getKey()))
            if (++index >= table.length)
                index = 0;

        return index;
    }
}

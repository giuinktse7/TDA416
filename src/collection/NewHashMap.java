package collection;

public interface NewHashMap<K, V> {
    V get(Object key);
    boolean isEmpty();
    V put(K key, V value);
    V remove(Object key);
    int size();

    public static class Entry<K, V> {
        private K key;
        private V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return this.key; }
        public V getValue() { return this.value; }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }
}

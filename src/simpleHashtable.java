public class simpleHashtable<K, V> {  //LINEAR PROBING (or Open Addressing)
    private storedVal<K, V>[] hashtable;

    @SuppressWarnings("unchecked")
    public simpleHashtable() {
        hashtable = new storedVal[10];
    }

    // stores the key-value pair in the hashtable
    private class storedVal<Key, Val> {
        public K key;
        public V val;

        public storedVal(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // hashing fnc. of our hashtable
    private int hashKey(K key) {
        return Math.abs(key.hashCode()) % hashtable.length;
    }

    private int findKey(K key) {
        int hashKey = hashKey(key);
        if (hashtable[hashKey] != null && hashtable[hashKey].key.equals(key)) {
            return hashKey;
        }

        int stopIdx = hashKey;
        hashKey = (hashKey+1) % hashtable.length;
        while (hashKey != stopIdx) {
            if (hashtable[hashKey] == null) {
                hashKey = (hashKey+1) % hashtable.length;
                continue;
            }
            if (hashtable[hashKey].key.equals(key)) {
                break;
            }
            hashKey = (hashKey+1) % hashtable.length;
        }
        
        if (stopIdx == hashKey) {
            return -1;
        }
        return hashKey;
    }

    // adds key-val pair in hashtable
    public void put(K key, V val) {
        int hashKey = hashKey(key);

        if (hashtable[hashKey] != null) {
            int stopIdx = hashKey;
            hashKey = (hashKey+1) % hashtable.length;

            while (hashtable[hashKey] != null && hashKey != stopIdx) {
                hashKey = (hashKey+1) % hashtable.length;
            }
        }

        if (hashtable[hashKey] != null) {
            System.out.println("Hashtable is full");
        } else {
            hashtable[hashKey] = new storedVal<>(key, val);
        }
    }

    // returns val associated with the key
    public V get(K key) {
        int hashKey = findKey(key);
        if (hashKey == -1) {
            System.out.println("Key not found");
            return null;
        }

        return hashtable[hashKey].val;
    }

    // removes key-val from hashtable
    public V remove(K key) {
        int hashKey = findKey(key);
        if (hashKey == -1) {
            System.out.println("Key not found");
            return null;
        }

        V val = hashtable[hashKey].val;
        hashtable[hashKey] = null;
        return val;
    }

    // prints the hashtable
    public void printHashtable() {
        for (int i  = 0; i < hashtable.length; i++) {
            if (hashtable[i] == null) {
                System.out.println("Empty");
            } else {
                System.out.println("Pos." + i + " => "+ hashtable[i].key + " ~ " + hashtable[i].val);
            }
        }
    }
    public static void main(String[] args) {
        simpleHashtable<String, Integer> table = new simpleHashtable<>();
        table.put("India", 75);
        table.put("USA", 90);
        table.put("UK", 65);
        table.put("Russia", 70);
        table.put("China", 80);

        table.printHashtable();
        // System.out.println(table.get("USA"));
        // System.out.println(table.get("UK"));

        table.remove("Russia");
        table.remove("India");
        System.out.println();
        System.out.println(table.get("China"));
        System.out.println(table.get("Nepal"));
        // table.printHashtable();
    }
}
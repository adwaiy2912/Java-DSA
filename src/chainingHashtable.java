import java.util.ArrayList;
import java.util.LinkedList;

public class chainingHashtable<K, V> {   //generics
    private class tableNode {
        K key;
        V val;

        public tableNode(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private int n;   //nodes
    private int N;   //hashmap
    private LinkedList<tableNode> hashMap[];   //N = hashmap.length

    @SuppressWarnings("unchecked")
    public chainingHashtable() {
        this.N = 5;
        this.hashMap = new LinkedList[5];
        for (int i = 0; i < 5; i++) {
            this.hashMap[i] = new LinkedList<>();
        }
    }

    private int hashKey(K key) {
        return Math.abs(key.hashCode()) % N;
    }

    private int findKey(K key, int idx) {
        LinkedList<tableNode> ll = hashMap[idx];

        for (int i = 0; i < ll.size(); i++) {
            if (ll.get(i).key == key) {
                return i;   //dataidx
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    private void rehashing() {
        LinkedList<tableNode> oldHashMap[] = hashMap;
        N *= 2;
        hashMap = new LinkedList[N];

        for (int i = 0; i<N; i++) {
            hashMap[i] = new LinkedList<>();
        }

        for (int i = 0; i < oldHashMap.length; i++) {
            LinkedList<tableNode> ll = oldHashMap[i];
            for (int j = 0; j < ll.size(); j++) {
                tableNode data = ll.get(j);
                put(data.key, data.val);
            }
        }
    }
    
    public boolean isEmpty() {
        return n==0;
    }

    public void put(K key, V val) {
        int mapidx = hashKey(key);
        int dataidx = findKey(key, mapidx);

        if (dataidx == -1) {    //key does not exists
            hashMap[mapidx].add(new tableNode(key, val));
            n++;
        } else {    //key exists
            tableNode data = hashMap[mapidx].get(dataidx);
            data.val = val;
        }

        double lambda = (double)n/N;
        if (lambda > 2) {
            rehashing();
        }
    }

    public V get(K key) {
        int mapidx = hashKey(key);
        int dataidx = findKey(key, mapidx);

        if (dataidx == -1) {
            System.out.println("Key does not exist");
            return null;
        } else {
            return hashMap[mapidx].get(dataidx).val;
        }
    }

    public V remove(K key) {
        int mapidx = hashKey(key);
        int dataidx = findKey(key, mapidx);

        if (dataidx == -1) {
            System.out.println("Key does not exist");
            return null;
        } else {
            n--;
            tableNode data = hashMap[mapidx].remove(dataidx);
            return data.val;
        }
    }

    public boolean containsKey(K key) {
        int mapidx = hashKey(key);
        int dataidx = findKey(key, mapidx);

        if (dataidx == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<K> keySet() {
        ArrayList<K> keys = new ArrayList<>();

        for (int i = 0; i < hashMap.length; i++) {
            LinkedList<tableNode> ll = hashMap[i];
            for (int j = 0; j < ll.size(); j++) {
                keys.add(ll.get(j).key);
            }
        }
        return keys;
    }

    public void printHashtable() {
        for (int i = 0; i < hashMap.length; i++) {
            LinkedList<tableNode> ll = hashMap[i];
            for (int j = 0; j < ll.size(); j++) {
                tableNode data = ll.get(j);
                System.out.println(data.key + " ~ " + data.val);
            }
        }
    }
    public static void main(String[] args) {
       chainingHashtable<String, Integer> table = new chainingHashtable<>();
       
       table.put("A", 0);
       table.put("D", 5);
       table.put("N", 9);
       table.put("Q", 10);

       table.printHashtable();
       table.remove("D");
       System.out.println();
       table.printHashtable();
    }
}

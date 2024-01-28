public class heap {
    int[] heap;
    int size;

    // constructor
    public heap(int capacity) {
        heap = new int[capacity];
    }

    // checks for if heap is full
    public boolean isFull() {
        return size == heap.length;
    }

    // checks for if heap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    public int getParent(int idx) {
        return (idx-1)/2;
    }

    public int getChild(int idx, boolean left) {
        return 2*idx + (left ? 1 : 2);
    }

    private void fixHeapAbove(int idx) {
        int newVal = heap[idx];
        while (idx > 0 && newVal > heap[getParent(idx)]) {
            heap[idx] = heap[getParent(idx)];
            idx = getParent(idx);
        }
        heap[idx] = newVal;
    }

    private void fixHeapBelow(int idx, int lastIdx) {
        int child;
        while (idx <= lastIdx) {
            int leftChild = getChild(idx, true);
            int rightChild = getChild(idx, false);
            if (leftChild <= lastIdx) {
                if (rightChild > lastIdx) {
                    child = leftChild;
                } else {
                    child = (heap[leftChild] > heap[rightChild] ? leftChild : rightChild);
                }

                if (heap[idx] < heap[child]) {
                    int temp = heap[idx];
                    heap[idx] = heap[child];
                    heap[child] = temp;
                } else {
                    break;
                }
                idx = child;
            } else {
                break;
            }
        }
    }

    //insert val in heap
    public void insert(int val) {
        if(isFull()) {
            System.out.println("Heap is full");
            return;
        }

        heap[size] = val;
        fixHeapAbove(size);
        size++;
    }

    //delete element at index from heap
    public int delete(int idx) {
        if (isEmpty()) {
            System.out.println("Heap is empty");
            return -1;
        }
        
        int parent = getParent(idx);
        int delVal = heap[idx];
        heap[idx] = heap[size-1];

        if (idx == 0 || heap[idx] < heap[parent]) {
            fixHeapBelow(idx, size-1);
        } else {
            fixHeapAbove(idx);
        }
        size--;
        return delVal;
    }

    // return root
    public int peek() {
        if (isEmpty()) {
            System.out.println("Heap is empty");
            return -1;
        }
        return heap[0];
    }

    // printing all values of heap
    public void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + "  ");
        }
        System.out.println();
    }

    // if heap is sorted then it's no longer a heap 
    public void sort() {
        int lastIdx = size-1;
        for (int i = 0; i < lastIdx; i++) {
            int temp = heap[0];
            heap[0] = heap[lastIdx-i];
            heap[lastIdx-i] = temp;

            fixHeapBelow(0, lastIdx-i-1);
        }
    }
    public static void main(String[] args) {
        heap hp = new heap(10);
        hp.insert(80);
        hp.insert(75);
        hp.insert(60);
        hp.insert(68);
        hp.insert(55);
        hp.insert(40);
        hp.insert(52);
        hp.insert(67);

        hp.printHeap();
        hp.sort();
        hp.printHeap();
    }
}

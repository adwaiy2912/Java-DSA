public class arrayQueue {
    String queue[];
    int size;
    int front = -1;
    int back = -1;

    public arrayQueue(int capacity) {
        queue = new String[capacity];
        this.size = capacity;
    }

    // queue is empty
    public boolean isEmpty() {
        return back == -1 && front == -1;
    }

    // add element in queue - enqueue
    public void add(String data) {
        if ((back+1)%size == front) {
            String[] newQueue = new String[2*size];

            int i = 0;
            if (back >= front) {
                for (int j = front; j <= back; j++) {
                    newQueue[i++] = queue[j];
                }
            } else {
                for (int j = front; j < size; j++) {
                    newQueue[i++] = queue[j];
                }
                for (int j = 0; j <= back; j++) {
                    newQueue[i++] = queue[j];
                }
            }

            queue = newQueue;
            front = 0;
            back = size - 1;
            size *= 2;
        }

        if (front == -1) {
            front = 0;
        }
        back = (back + 1) % size;
        queue[back] = data;
    }

    // remove element from queue - dequeue
    public String remove() {
        if (isEmpty()) {
            System.out.println("Underflow, queue is empty");
            return null;
        }
        String top = queue[front];
        if (back == front) {
            back = front = -1;
        } else {
            front = (front+1)%size;
        }

        return top;
    }

    // return top element
    public String peek() {
        if (isEmpty()) {
            System.out.println("Underflow, queue is empty");
            return null;
        }
        return queue[front];
    }

    // print queue elements
    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
         
        if (front <= back) {
            for (int i = front; i < back; i++) {
                System.out.print(queue[i] + " ~ ");
            }
            System.out.println(queue[back]);
        } else {
            for (int i = front; i < queue.length; i++) {
                System.out.println(queue[i] + " ~ ");
            }
            for (int i = 0; i < back; i++) {
                System.out.println(queue[i] + " ~ ");
            }
            System.out.println(queue[back]);
        }
    }
    public static void main(String[] args) {
        arrayQueue queue = new arrayQueue(5);
        queue.add("A");
        queue.add("B");
        queue.add("C");
        queue.add("D");
        queue.add("E");
        queue.add("F");
        queue.add("G");

        queue.printQueue();
    }
}

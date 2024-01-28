public class linkedQueue {
    QNode head;
    QNode tail;
    int size;

    private class QNode {
        String data;
        QNode next;

        public QNode(String data) {
            this.data = data;
            this.next = null;   //not req.
        }

        public String getData() {
            return data;
        }

        public QNode getNext() {
            return next;
        }

        public void setNext(QNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return data;
        }
    }

    // get queue size
    public int getSize() {
        return size;
    }

    // queue is empty
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    // add element - enqueue
    public void add(String data) {
        QNode newNode = new QNode(data);
        size++;

        if (head == null && tail == null) {
            head = tail = newNode;
            return;
        }

        tail.setNext(newNode);
        tail = newNode;
    }

    // remove element - dequeue
    public QNode pop() {
        if (isEmpty()) {
            System.out.println("Underflow, queue empty");
            return null;
        }
        QNode remNode = head;
        head = head.getNext();
        size--;

        if (head == null) {
            tail = null;
        }
        return remNode;
    }

    // return top element
    public QNode peek() {
        if (isEmpty()) {
            System.out.println("Underflow, queue empty");
            return null;
        }
        return head;
    }

    // print queue
    public void printQueue() {
        if(isEmpty()) {
            return;
        }

        QNode curr = head;
        while (curr.getNext() != null) {
            System.out.print(curr.getData() + " ~ ");
            curr = curr.getNext();
        }
        System.out.println(curr.getData());
    }
    public static void main(String[] args) {
        linkedQueue queue = new linkedQueue();

        queue.add("Hello");
        queue.add("beautiful");
        queue.add("world");

        queue.printQueue();
        System.out.println(queue.pop());
        queue.printQueue();
    }
}

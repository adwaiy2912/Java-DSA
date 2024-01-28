public class doublyLinkedList {
    LLNode head;
    LLNode tail;
    private int size;

    private class LLNode {
        String data;
        LLNode next;
        LLNode previous;

        public LLNode(String data) {
            this.data = data;
            this.next = null;   //not req.
            this.previous = null;   //not req.
        }

        public String getData() {
            return data;
        }

        public LLNode getNext() {
            return next;
        }

        public void setNext(LLNode next) {
            this.next = next;
        }

        public LLNode getPrevious() {
            return previous;
        }

        public void setPrevious(LLNode previous) {
            this.previous = previous;
        }
        
        @Override
        public String toString() {
            return data;
        }
    }

    // print LL size
    public int getSize() {
        return size;
    }

    // LL is empty
    public boolean isEmpty() {
        return head == null;    //size == 0
    }

    // adding infront of LL
    public void addToHead(String data) {
        LLNode newNode = new LLNode(data);

        if (isEmpty()) {
            tail = newNode; 
        } else {
            head.setPrevious(newNode);
            newNode.setNext(head);
        }

        head = newNode;
        size++;
    }

    // adding to the last of LL
    public void addToTail(String data) {
        LLNode newNode = new LLNode(data);

        if (tail == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
        }

        tail = newNode;
        size++;
    }

    // printing LL
    public void printList() {
        LLNode currNode = head;

        System.out.print("HEAD(Null)");
        while (currNode != null) {
            System.out.print(" <=> " + currNode);
            currNode = currNode.getNext();
        }
        System.out.println(" <=> NULL(Tail)");
    }

    // delete first node
    public LLNode remFromFront() {
        if (isEmpty()) {
            System.out.println("LL is empty");
            return null;
        }

        size--;
        LLNode remNode = head;

        if (head.getNext() == null) {
            tail = null;
        } else {
            head.getNext().setPrevious(null);
        }

        head = head.getNext();
        remNode.setNext(null);  //setting removed node pointer to null
        return remNode;
    }

    // delete last node
    public LLNode remFromEnd() {
        if (isEmpty()) {    //if(head == null)
            System.out.println("LL is empty");
            return null;
        }

        size--;
        LLNode remNode = tail;

        if (tail.getPrevious() == null) {
            head = null;
        } else {
            tail.getPrevious().setNext(null);
        }

        tail = tail.getPrevious();
        remNode.setPrevious(null);  //setting removed node pointer to null
        return remNode;
    }

    public void addBefore(String add, String before) {
        LLNode addNode = new LLNode(add);
        LLNode curr = head;

        while (curr != null && !curr.getData().equals(before)) {
            curr = curr.getNext();
        }
        if (curr == null) {
            System.out.println("Node not found");
            return;
        }
        LLNode prev = curr.getPrevious();

        size++;
        curr.setPrevious(addNode);
        addNode.setNext(curr);
        
        if (curr == head) {
            head = addNode;
            return;
        }
        addNode.setPrevious(prev);
        prev.setNext(addNode);
    }
    public static void main(String[] args) {
        doublyLinkedList lst = new doublyLinkedList();
        lst.addToHead("A");
        lst.addToTail("B");
        lst.addToTail("C");

        lst.printList();
        lst.addBefore("X", "A");
        lst.addBefore("Y", "A");
        lst.addBefore("Z", "B");
        lst.addBefore("Q", "C");
        lst.addBefore("E", "P");
        lst.printList();

    }
}

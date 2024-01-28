public class linkedList {
    LLNode head;
    private int size;

    private class LLNode {
        String data;
        LLNode next;

        public LLNode(String data) {
            this.data = data;
            this.next = null;   //not req.
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
    public void addFirst(String data) {
        LLNode newNode = new LLNode(data);
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    // adding to the last of LL
    public void addLast(String data) {
        LLNode newNode = new LLNode(data);
        LLNode currNode = head;
        while (currNode.next != null) {
            currNode = currNode.getNext();
        }
        currNode.setNext(newNode);
        size++;
    }

    // printing LL
    public void printList() {
        LLNode currNode = head;

        System.out.print("HEAD -> ");
        while (currNode != null) {
            System.out.print(currNode + " -> ");
            currNode = currNode.getNext();
        }
        System.out.println("NULL");
    }

    // delete first node
    public LLNode deleteFirst() {
        if (isEmpty()) {
            System.out.println("LL is empty");
            return null;
        }

        size--;
        LLNode remNode = head;
        head = head.getNext();
        remNode.setNext(null);  //setting removed node pointer to null
        return remNode;
    }

    // delete last node
    public LLNode deleteLast() {
        if (isEmpty()) {    //if(head == null)
            System.out.println("LL is empty");
            return null;
        }

        size--;
        if(head.getNext() == null) {
            LLNode remNode = head;
            head = null;
            return remNode;
        }

        LLNode secLastNode = head;
        LLNode lastNode = head.getNext();
        while(lastNode.getNext() != null) {
            lastNode = lastNode.getNext();
            secLastNode = secLastNode.getNext();
        }
        secLastNode.setNext(null);
        return lastNode;
    }

    // reverse LL
    public void reverse() {
        if (isEmpty()) {
            System.out.println("LL is empty");
            return;
        }
        if (head.getNext() == null) {
            return;
        }
        
        LLNode prev = head;
        LLNode curr = prev.getNext();
        while (curr != null) {
            LLNode next = curr.getNext();
            curr.setNext(prev);

            prev = curr;
            curr = next;
        }
        head.setNext(null);
        head = prev;
    }

    public void reverseRec(LLNode top) {
        if (top == null || top.getNext() == null) {
            head = top;
            return;
        }

        reverseRec(top.getNext());
        LLNode nextNode = top.getNext();
        nextNode.setNext(top);
        top.setNext(null);
    }

    // delete nth element from end
    public LLNode remNthFromEnd(int n) {
        if (isEmpty()) {
            System.out.println("LL is empty");
            return null;
        } 
        
        LLNode prev = head;
        LLNode curr = prev.getNext();
        if (n > size) {
            System.out.println("Nth element does not exist");
            return null;
        }
        if (size == n) {
            prev.setNext(null);
            head = curr;
            return prev;
        }

        for (int i = 0; i < size-n-1; i++) {
            prev = prev.getNext();
            curr = curr.getNext();
        }
        prev.setNext(curr.getNext());
        curr.setNext(null);
        return curr;
    }

    public boolean isPalindrome() {
        if(isEmpty() || size == 1) {    // (head == null || head.getNext() == null)
            return true;
        }

        LLNode second = head;
        for(int i = 0; i < size/2; i++) {
            second = second.getNext();
        }
        
        //reverse half list
        LLNode prev = null;
        LLNode curr = second.getNext();
        while (curr != null) {
            LLNode next = curr.getNext();
            curr.setNext(prev);
            prev = curr;
            curr = next;
        }

        second = prev;
        LLNode first = head;
        while (first != null && second != null) {
            if (first.getData() != second.getData()) {
                System.out.println(first.getData()+" "+second.getData());
                return false;
            }
            first = first.getNext();
            second = second.getNext();
        }
        return true;
    }

    public boolean hasCycle() {
        LLNode slow = head;
        LLNode fast = head;
        if (head == null) {
            return false;
        }

        while (fast != null && fast.getNext() != null) {
            if (slow == fast) {
                return true;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return false;
    }

    public void removeCycle() {
        if (!hasCycle()) {
            return;
        }

        LLNode slow = head;
        LLNode fast = head;
        while (fast != slow) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        LLNode prev = fast;
        slow = head;
        while (slow != fast) {
            prev = fast;
            slow = slow.getNext();
            fast = fast.getNext();
        }
        prev.setNext(null);
    }

    public void insertSorted(String data) {
        LLNode newNode = new LLNode(data);
        LLNode curr = head;

        size++;
        if (isEmpty() || head.getData().compareTo(data) >= 0) {
            addFirst(data);
            return;
        }

        LLNode prev = head;
        while (curr != null && curr.getData().compareTo(data) < 0) {
            prev = curr;
            curr = curr.getNext();
        }
        prev.setNext(newNode);
        newNode.setNext(curr);
    }
    public static void main(String[] args) {
        linkedList lst = new linkedList();
        lst.addFirst("this");
        lst.addLast("is");
        lst.addLast("a");
        lst.addLast("linked");
        lst.addLast("list");
        
        lst.printList();
        // lst.reverseRec(lst.head);
        lst.printList();
    }
}

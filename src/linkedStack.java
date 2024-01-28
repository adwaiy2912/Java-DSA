public class linkedStack {
    stkNode top;
    int size;

    private class stkNode {
        String data;
        stkNode next;

        public stkNode(String data) {
            this.data = data;
            this.next = null; //not req.
        }

        public stkNode getNext() {
            return next;
        }

        public void setNext(stkNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return data;
        }
    }

    // stack is empty
    public boolean isEmpty() {
        return top == null;
    }

    // return stack size
    public int getSize() {
        return size;
    }
    
    // add element to stack
    public void push(String data) {
        stkNode newNode = new stkNode(data);
        if (isEmpty()) {
            top = newNode;
            return;
        }

        size++;
        newNode.setNext(top);
        top = newNode;
    }

    // remove element from stach
    public stkNode pop() {
        if (isEmpty()) {
            System.out.println("Underflow, stack is empty");
            return null;
        }

        size--;
        stkNode remNode = top;
        top.setNext(top.getNext());
        return remNode;
    }

    // return top element
    public stkNode peek() {
        return top;
    }

    // print all stack elements
    public void printStack() {
        if (isEmpty()) {
            return;
        }
        
        stkNode curr = top;
        System.out.println(curr + "   <- top");
        curr = curr.getNext();
        while (curr != null) {
            System.out.println(curr);
            curr = curr.getNext();
        }
    }
    public static void main(String[] args) {
        linkedStack stk = new linkedStack();

        stk.push("hello");
        stk.push("this");
        stk.push("is");
        stk.push("beautiful");
        stk.push("world");

        stk.printStack();
    }    
}

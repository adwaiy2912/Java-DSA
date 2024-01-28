public class arrayStack {
    String[] stack;
    int top;

    // initialising stack's size
    public arrayStack (int capacaity) {
        stack = new String[capacaity];
    }

    // stack is empty
    public boolean isEmpty() {
        return top == 0;
    }

    // return stack size
    public int size() {
        return top;
    }

    // add element to stack
    public void push(String data) {
        if (top == stack.length) {
            // resize stack array
            String[] newStack = new String[2*stack.length];
            System.arraycopy(stack, 0, newStack, 0, stack.length);
            stack = newStack;
        }

        stack[top++] = data;
    }

    // remove element from stack
    public String pop() {
        if (isEmpty()) {
            System.out.println("Underflow: stack is empty");
            return null;
        }

        String data = stack[--top];
        stack[top] = null;
        return data;
    }

    // return top element
    public String peek() {
        if (isEmpty()) {
            System.out.println("Underflow: stack is empty");
            return null;
        }
        return stack[top-1];
    }

    // print stack
    public void printStack() {
        for (int i = top-1; i >= 0; i--) {
            if (i == top-1) {
                System.out.println(stack[i] + "   <- top");
            } else {
                System.out.println(stack[i]);
            }
        }
    }

    // add element at front/bottom
    public void addAtBottom(String data) {
        if (isEmpty()) {
            stack[top++] = data;
            return;
        }

        String temp = stack[0], temp2;
        stack[0] = data;
        for (int i = 1; i <= top; i++) {
            temp2 = stack[i];
            stack[i] = temp;
            temp = temp2;
        }
        top++;
    }

    // reverse stack
    public void reverse() {
        for (int i = 0; i < top/2; i++) {
            String temp = stack[i];
            stack[i] = stack[top-i-1];
            stack[top-i-1] = temp;
        }
    }
    public static void main(String[] args) {
        arrayStack stk = new arrayStack(10);

        stk.push("hello");
        stk.push("this");
        stk.push("is");
        stk.push("beautiful");
        stk.push("world");

        stk.printStack();
        System.out.println();
        stk.reverse();
        stk.printStack();
    }
}

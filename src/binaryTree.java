import java.util.LinkedList;
import java.util.Queue;

public class binaryTree {
    class treeNode {
        int data;
        treeNode left;
        treeNode right;

        public treeNode(int data) {
            this.data = data;
        }
        
        public int getData() {
            return data;
        }
        public void setData(int data) {
            this.data = data;
        }
        public treeNode getLeft() {
            return left;
        }
        public void setLeft(treeNode left) {
            this.left = left;
        }
        public treeNode getRight() {
            return right;
        }
        public void setRight(treeNode right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "treeNode [data=" + data + ", left=" + left + ", right=" + right + "]";
        }
    }

    private int idx1 = -1;
    public treeNode buildtree(int nodes[]) {
        idx1++;
        if (nodes[idx1] == -1) {
            return null;
        }

        treeNode newNode = new treeNode(nodes[idx1]);
        newNode.left = buildtree(nodes);
        newNode.right = buildtree(nodes);

        return newNode;
    }

    // print -> root, then left sub-tree, then right sub-tree
    public void preOrder(treeNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.getData()+"  ");
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    // print -> left subtree, then root, then right subtree
    public void inOrder(treeNode root) {
        if (root == null) {
            return;
        }

        inOrder(root.getLeft());
        System.out.print(root.getData()+"  ");
        inOrder(root.getRight());
    }
    
    // print -> left subtree, then right subtree, then root
    public void postOrder(treeNode root) {
        if (root == null) {
            return;
        }

        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.getData()+"  ");
    }

    // prints tree nodes level wise
    public void levelOrder(treeNode root) {
        if (root == null) {
            return;
        }

        Queue<treeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while(!q.isEmpty()) {
            treeNode curr = q.remove();
            if (curr == null) {
                System.out.println();
                if(q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                System.out.print(curr.getData()+" ");

                if(curr.getLeft() != null) {
                    q.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    q.add(curr.getRight());
                }
            }
        }
    }

    // returns no. of nodes
    public int countNodes(treeNode root) {
        if (root == null) {
            return 0;
        }

        return countNodes(root.getLeft()) + countNodes(root.getRight()) + 1;
    }

    // returns the sum of all nodes
    public int sumOfNodes(treeNode root) {
        if (root == null) {
            return 0;
        }

        return sumOfNodes(root.getLeft()) + sumOfNodes(root.getRight()) + root.getData();
    }

    // dist. btw root and deepest leaf 
    public int height(treeNode root) {
        if (root == null) {
            return 0;
        }

        return Math.max(height(root.getLeft()), height(root.getRight())) + 1;
    }

    // no. of nodes in the longest path btw any 2 nodes
    public int diameter(treeNode root) {
        if (root == null) {
            return 0;
        }
        int dia1 = diameter(root.getLeft());
        int dia2 = diameter(root.getRight());
        int dia3 = height(root.getLeft()) + height(root.getRight()) + 1;

        return Math.max(dia1, Math.max(dia2, dia3));
    }

    // is tree a subtree of another bigger tree
    public boolean isIdentical(treeNode root, treeNode subRoot) {
        if (root == null && subRoot == null) {
            return true;
        }
        if (root == null || subRoot == null) {
            return false;
        }
        
        if (root.getData() != subRoot.getData()) {
            return false;
        }
        return isIdentical(root.getLeft(), subRoot.getLeft()) 
            && isIdentical(root.getRight(), subRoot.getRight());
    }

    public boolean isSubTree(treeNode root, treeNode subRoot) {
        if (subRoot == null) {
            return true;
        }
        if (root == null) {
            return false;
        }
        if (root.getData() == subRoot.getData()) {
            if(isIdentical(root, subRoot)) {
                return true;
            }
        }

        return isSubTree(root.getLeft(), subRoot) || isSubTree(root.getRight(), subRoot);
    }

    // returns sum of nodes at kth level
    public int sumOfNodesAtKth(treeNode root, int k) {
        if (root == null) {
            return 0;
        }

        Queue<treeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        int sum = 0;
        while(!q.isEmpty() && k >= 1) {
            treeNode curr = q.remove();
            if (curr == null) {
                k--;
                if(q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                if (k == 1) {
                    sum += curr.getData();
                }

                if(curr.getLeft() != null) {
                    q.add(curr.getLeft());
                }
                if (curr.getRight() != null) {
                    q.add(curr.getRight());
                }
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        binaryTree tree = new binaryTree();
        treeNode root = tree.buildtree(nodes);

        System.out.println(tree.sumOfNodesAtKth(root, 4));
    }
}
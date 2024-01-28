public class binarySearchTree {
    private treeNode root;

    private class treeNode {
        int data;
        treeNode left;
        treeNode right;

        public treeNode(int data) {
            this.data = data;
        }

        private void insert(int val) {
            if (val == data) {
                return;
            }

            if (val < data) {
                if (left == null) {
                    left = new treeNode(val);
                } else {
                    left.insert(val);
                }
            }
            else {
                if (right == null) {
                    right = new treeNode(val);
                } else {
                    right.insert(val);
                }
            }
        }

        private void inOrder() {
            if (left != null) {
                left.inOrder();
            }
            System.out.print(data+"  ");
            if (right != null) {
                right.inOrder();
            }
        }

        private treeNode get(int val) {
            if (val == data) {
                return this;
            }

            if (val < data) {
                if (left != null) {
                    return left.get(val);
                }
            }
            else {
                if (right != null) {
                    return right.get(val);
                }
            }
            return null;
        }

        private int getMin() {
            if (left == null) {
                return data;
            }
            return left.getMin();
        }

        private int getMax() {
            if (right == null) {
                return data;
            }
            return right.getMax();
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

    // insert val in BST
    public void insert(int val) {
        treeNode newNode = new treeNode(val);
        if (root == null) {
            root = newNode;
            return;
        }
        root.insert(val);
    }

    // print -> left subtree, then root, then right subtree (sorted print)
    public void inOrder() {
        if (root == null) {
            return;
        }
        root.inOrder();
    }

    // returns node containg val
    public treeNode get(int val) {
        if (root == null) {
            return null;
        }
        return root.get(val);
    }

    // delete node from BST
    public void delete(int val) {
        root = deleteHelper(root, val);
    }

    private treeNode deleteHelper(treeNode subRoot, int val) {
        if (subRoot == null) {
            return subRoot;
        }

        if (val < subRoot.getData()) {
            subRoot.setLeft(deleteHelper(subRoot.getLeft(), val));
        }
        else if (val > subRoot.getData()) {
            subRoot.setRight((deleteHelper(subRoot.getRight(), val)));
        }
        else {
            // CASE1 & 2: node to del has 0 or 1 child
            if (subRoot.getLeft() == null) {
                return subRoot.getRight();
            }
            else if (subRoot.getRight() == null) {
                return subRoot.getLeft();
            }
            // CASE 3: node to del has 2 children
            else {
                subRoot.setData(subRoot.getRight().getMin());
                subRoot.setRight(deleteHelper(subRoot.getRight(), subRoot.getData()));
            }
        }
        return subRoot;
    }

    // returns min val from BST
    public int getMin() {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        return root.getMin();
    }

    // returns max val from BST
    public int getMax() {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        return root.getMax();
    }

    // #Delete function, another way
    // public treeNode del(treeNode node, int val) {
    //     if (node.data < val) {
    //         node.left = del(node.left, val);
    //     }
    //     else if (node.data > val) {
    //         node.right = del(node.right, val);
    //     }
    //     else {
    //         if (node.left == null) {
    //             return node.right;
    //         }
    //         else if (node.right == null) {
    //             return node.left;
    //         }
    //         else {
    //             treeNode temp = node.right;
    //             while (temp.left != null) {
    //                 temp = temp.left;
    //             }
    //             node.data = temp.data;
    //             node.right = del(node.right, temp.data);
    //         }
    //     }
    //     return node;
    // }

    // #All paths from root to leafs
    // public void printRoot2Leaf(treeNode node, ArrayList<Integer> path) {
    //     if (node == null) {
    //         return;
    //     }
    //     path.add(node);

    //     if (node.left == null && node.right == null) {
    //         // printing path arraylist
    //         for (int i = 0; i < path.size(); i++) {
    //             System.out.print(path.get(i)+"->");
    //         }
    //         System.out.println();
    //     } else {
    //         printRoot2Leaf(node.left, path);
    //         printRoot2Leaf(node.right, path);
    //     }
    //     path.remove(path.size()-1);
    // }

    public static void main(String[] args) {
        binarySearchTree bst = new binarySearchTree();
        int nodes[] = {5,1,3,4,2,7,10,15,6,8};
        for (int i : nodes) {
            bst.insert(i);
        }
        bst.inOrder();
    }
}
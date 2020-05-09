package avl;

public class AVL {

    public Node root;

    private int size;

    public int getSize() {
        return size;
    }

    /**
     * find w in the tree. return the node containing w or
     * null if not found
     */
    public Node search(String w) {
        return search(root, w);
    }

    private Node search(Node n, String w) {
        if (n == null) {
            return null;
        }
        if (w.equals(n.word)) {
            return n;
        } else if (w.compareTo(n.word) < 0) {
            return search(n.left, w);
        } else {
            return search(n.right, w);
        }
    }

    /**
     * insert w into the tree as a standard BST, ignoring balance
     */
    public void bstInsert(String w) {
        if (root == null) {
            root = new Node(w);
            size = 1;
            return;
        }
        bstInsert(root, w);
    }

    /* insert w into the tree rooted at n, ignoring balance
     * pre: n is not null */
    private void bstInsert(Node n, String w) {
        // TODO
        Node current = n;
        Node temp = null;
        //null root

        while (current != null) {
            if (w.compareTo(current.word) < 0) {
                temp = current;
                current = current.left;
            } else if (w.compareTo(current.word) > 0) {
                temp = current;
                current = current.right;
            } else
                break;
        }

        if (current != null)
            return;
        else {
            if (w.compareTo(temp.word) > 0) {
                temp.right = new Node(w, temp);
                size++;
                updateHeight(n);
            } else {
                temp.left = new Node(w, temp);
                size++;
                updateHeight(n);
            }
        }
    }

    private void updateHeight(Node n) {
        if (n == null)
            return;
        else {
            n.height = height(n);
            updateHeight(n.parent);
        }
    }

    private int height(Node n) {
        if (n == null)
            return -1;
        return getHeight(n) - 1;
    }

    private int getHeight(Node n) {
        if (n == null)
            return 0;
        else {
            int left = getHeight(n.right);
            int right = getHeight(n.left);
            return 1 + Integer.max(left, right);
        }
    }

    /**
     * insert w into the tree, maintaining AVL balance
     * precondition: the tree is AVL balanced
     */
    public void avlInsert(String w) {
        // TODO
    }

    /* insert w into the tree, maintaining AVL balance
     *  precondition: the tree is AVL balanced and n is not null */
    private void avlInsert(Node n, String w) {
        // TODO
    }

    /**
     * do a left rotation: rotate on the edge from x to its right child.
     * precondition: x has a non-null right child
     */
    public void leftRotate(Node x) {
        // TODO
        Node y = x.right;
        x.right = y.left;
        if (y.left != null)
            y.left.parent = x;

        y.parent = x.parent;
        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else x.parent.right = y;

        y.left = x;
        x.parent = y;
        updateHeight(x);
    }

    /**
     * do a right rotation: rotate on the edge from x to its left child.
     * precondition: y has a non-null left child
     */
    public void rightRotate(Node y) {
        // TODO
        Node x = y.left;
        y.left = x.right;
        if (x.right != null)
            x.right.parent = y;
        x.parent = y.parent;

        if (y.parent == null)
            root = x;
        else if (y == y.parent.right)
            y.parent.right = x;
        else y.parent.left = x;
        x.right = y;
        y.parent = x;
        updateHeight(y);
    }

    /**
     * rebalance a node N after a potentially AVL-violoting insertion.
     * precondition: none of n's descendants violates the AVL property
     */
    public void rebalance(Node n) {
        // TODO
        if (getbalance(n) < -1) {
            if (getbalance(n.left) < 0) {
                //case1
                rightRotate(n);
            } else {
                //case2
                leftRotate(n.left);
                rightRotate(n);
            }
        } else if (getbalance(n) > 1) {
            if (getbalance(n.right) < 0) {
                //case 3
                rightRotate(n.right);
                leftRotate(n);
            } else {
                //case 4
                leftRotate(n);
            }
        }
    }


    private int getbalance(Node n) {
        return height(n.right) - height(n.left);
    }


    /**
     * remove the word w from the tree
     */
    public void remove(String w) {
        remove(root, w);
    }

    /* remove v from the tree rooted at n */
    private void remove(Node n, String w) {
        return; // (enhancement TODO - do the base assignment first)
    }

    /**
     * print a sideways representation of the tree - root at left,
     * right is up, left is down.
     */
    public void printTree() {
        printSubtree(root, 0);
    }

    private void printSubtree(Node n, int level) {
        if (n == null) {
            return;
        }
        printSubtree(n.right, level + 1);
        for (int i = 0; i < level; i++) {
            System.out.print("        ");
        }
        System.out.println(n);
        printSubtree(n.left, level + 1);
    }

    /**
     * inner class representing a node in the tree.
     */
    public class Node {
        public String word;
        public Node parent;
        public Node left;
        public Node right;
        public int height;

        public String toString() {
            return word + "(" + height + ")";
        }

        /**
         * constructor: gives default values to all fields
         */
        public Node() {
        }

        /**
         * constructor: sets only word
         */
        public Node(String w) {
            word = w;
        }

        /**
         * constructor: sets word and parent fields
         */
        public Node(String w, Node p) {
            word = w;
            parent = p;
        }

        /**
         * constructor: sets all fields
         */
        public Node(String w, Node p, Node l, Node r) {
            word = w;
            parent = p;
            left = l;
            right = r;
        }
    }

    //delete later
    public void preorder(Node root) {
        if (root == null)
            return;
        System.out.print(root.word + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.print(root.word + " ");
        inorder(root.right);
    }

    public void postorder(Node root) {
        if (root == null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.word + " ");
    }
}

class testAVL {
    public static void main(String[] args) {
        AVL a = new AVL();
        a.bstInsert("m");
        a.search("m").height = 2;
        a.bstInsert("p");
        a.search("p").height = 1;
        a.bstInsert("r");
        a.search("r").height = 0;
        a.bstInsert("b");
        a.search("b").height = 1;
        a.bstInsert("c");
        a.search("c").height = 0;
        a.bstInsert("a");
        a.search("a").height = 0;
        //String[] str = {"m", "p", "r", "b"};
//        Arrays.sort(str);
//        for (String s: str)
//            System.out.print(s + " ");
//        System.out.println();
        a.rebalance(a.search("p"));
        a.preorder(a.root);
        System.out.println();
        System.out.println();
        a.inorder(a.root);
        System.out.println();System.out.println();
        a.postorder(a.root);
    }


}

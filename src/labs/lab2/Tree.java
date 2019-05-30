package labs.lab2;

public class Tree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void add(int value) {
        if (root == null) {
            root = new Node(value);
            return;
        }
        Node current = root;
        while (true) {
            if (value == current.value) {
                return;
            }
            if (value < current.value) {
                if (current.leftChild == null) {
                    current.leftChild = new Node(value);
                    current.leftChild.parent = current;
                    return;
                }
                current = current.leftChild;
            } else {
                if (current.rightChild == null) {
                    current.rightChild = new Node(value);
                    current.rightChild.parent = current;
                    return;
                }
                current = current.rightChild;
            }
        }

    }

    public void remove(int value) {
        remove(find(value));
    }

    private Node find(int value) {
        Node current = root;
        while (current != null && value != current.value) {
            if (value < current.value) {
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
        }
        return current;
    }

    private void remove(Node node) {
        if (node == null) {
            return;
        }
        if (node.leftChild == null && node.rightChild == null) { //have not children
            if (node.parent.leftChild == node) {
                node.parent.leftChild = null;
            } else if (node.parent.rightChild == node) {
                node.parent.rightChild = null;
            }
        } else if (node.leftChild == null) { //have only right child
            if (node.parent.leftChild == node) {
                node.parent.leftChild = node.rightChild;
            } else if (node.parent.rightChild == node) {
                node.parent.rightChild = node.rightChild;
            }
            node.rightChild.parent = node.parent;
        } else if (node.rightChild == null) { //have only left child
            if (node.parent.leftChild == node) {
                node.parent.leftChild = node.leftChild;
                node.leftChild.parent = node.parent;
            } else if (node.parent.rightChild == node) {
                node.parent.rightChild = node.leftChild;
                node.leftChild.parent = node.parent;
            }
        } else { //have two children
            Node temp = getRightestFromLeftSubTree(node);
            remove(temp);
            temp.leftChild = node.leftChild;
            temp.rightChild = node.rightChild;
            temp.parent = node.parent;
            if (node.parent.leftChild == node) {
                node.parent.leftChild = temp;
            } else if (node.parent.rightChild == node) {
                node.parent.rightChild = temp;
            }
            node.rightChild.parent = temp;
            if (node.leftChild != null) {
                node.leftChild.parent = temp;
            }
        }
        node.parent = null;
        node.leftChild = null;
        node.rightChild = null;
    }

    private Node getRightestFromLeftSubTree(Node node) {
        Node current = node.leftChild;
        while (current.rightChild != null) {
            current = current.rightChild;
        }
        return current;
    }

    public class Node {
        private Node parent;
        private int value;
        private Node leftChild;
        private Node rightChild;

        private Node(int value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public int getValue() {
            return value;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }
    }
}

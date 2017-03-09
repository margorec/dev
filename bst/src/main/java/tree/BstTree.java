package tree;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BstTree<T extends Comparable> {
    private Node root = null;

    public Node add(T value) {
        Node<T> newNode = new Node<>(null, null, null, value);

        Node<T> y = null;
        Node<T> x = root;

        while (x != null) {
            y = x;
            if (newNode.getValue().compareTo(x.getValue()) < 0) {
                x = x.getLeftSon();
            } else {
                x = x.getRightSon();
            }
        }

        newNode.setParent(y);

        if (y == null) {
            root = newNode;
        } else {
            if (newNode.getValue().compareTo(y.getValue()) < 0) {
                y.setLeftSon(newNode);
            } else {
                y.setRightSon(newNode);
            }
        }

        return newNode;
    }

    public boolean contains(T value) {
        return find(value) != null;
    }

    public Node find(T value) {
        Node x = root;
        while (x != null) {
            if (x.getValue().compareTo(value) > 0) {
                x = x.getLeftSon();
            } else if (x.getValue().compareTo(value) == 0) {
                return x;
            } else {
                x = x.getRightSon();
            }
        }
        return null;
    }

    public Node delete(T value) {
        Node deleteNode = find(value);
        if (deleteNode == null) {
            return null;
        }

        Node y;
        if ((deleteNode.getLeftSon() == null) || deleteNode.getRightSon() == null) {
            y = deleteNode;
        } else {
            y = getSuccessor(deleteNode);
        }
        Node x;

        if (y.getLeftSon() != null) {
            x = y.getLeftSon();
        } else {
            x = y.getRightSon();
        }

        if (x != null) {
            x.setParent(y.getParent());
        }

        if (y.getParent() == null) {
            this.root = x;
        } else {
            if (y == y.getParent().getLeftSon()) {
                y.getParent().setLeftSon(x);
            } else {
                y.getParent().setRightSon(x);
            }
        }

        if (y != deleteNode) {
            deleteNode.setValue(y.getValue());
        }

        return y;
    }

    private Node getSuccessor(Node node) {
        if (node.getRightSon() != null) {
            return getMinNode(node.getRightSon());
        }

        Node tmpNode = node.getParent();
        while (tmpNode != null && tmpNode.getLeftSon() != node) {
            Node x = tmpNode;
            tmpNode = tmpNode.getParent();
        }
        return tmpNode;
    }

    public Node getMinNode(Node startNode) {
        Node x = startNode;
        while (x.getLeftSon() != null) {
            x = x.getLeftSon();
        }
        return x;
    }

    public Node getRoot() {
        return root;
    }
}

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
            if(newNode.getValue().compareTo(y.getValue()) < 0) {
                y.setLeftSon(newNode);
            } else {
                y.setRightSon(newNode);
            }
        }

        return newNode;
    }



    public boolean contains(T value) {
        if (root == null) {
            return false;
        }

        Node x = root;
        while(x != null) {
            if (value.compareTo())
        }


    }

    public Node delete(Integer value) {
        return null;
    }

    public Node getRoot() {
        return root;
    }
}

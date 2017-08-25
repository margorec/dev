package com.gorecki.tree;

import org.apache.commons.lang3.StringUtils;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class BSTTree<T extends Comparable> {
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
        if (deleteNode.getLeftSon() == null || deleteNode.getRightSon() == null) {
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

    public int getDepth(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(getDepth(node.getLeftSon()), getDepth(node.getRightSon()));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        getNodes().forEach(n -> sb.append(n.getValue()).append(" "));
        return sb.toString();
    }

    void visitInOrder(Node node, List<Node> nodes) {
        if (node.getLeftSon() != null) {
            visitInOrder(node.getLeftSon(), nodes);
        }

        nodes.add(node);

        if (node.getRightSon() != null) {
            visitInOrder(node.getRightSon(), nodes);
        }
    }

    void visitPreOrder(Node node, List<Node> nodes) {
        nodes.add(node);

        if (node.getLeftSon() != null) {
            visitPreOrder(node.getLeftSon(), nodes);
        }

        if (node.getRightSon() != null) {
            visitPreOrder(node.getRightSon(), nodes);
        }
    }


    public List<Node> getNodes() {
        List<Node> nodes = new LinkedList<>();
        if (root != null) {
            visitPreOrder(root, nodes);
        }
        return nodes;
    }

    public String getJs() {
        StringBuilder sb = new StringBuilder();
        List<String> nodeNames = new LinkedList<>();
        nodeNames.add("config");

        getNodes().forEach(node -> {
            nodeNames.add(node.jsName());
            sb.append(node.serializeToJs()).append(", ");
        });

        sb.append("treeConfig=").append(StringUtils.join(Arrays.asList(nodeNames), ", ")).append(";");

        return sb.toString();
    }

    public boolean addAll(final Collection<T> toAdd) {
        try {
            toAdd.stream().forEachOrdered(n -> add(n));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

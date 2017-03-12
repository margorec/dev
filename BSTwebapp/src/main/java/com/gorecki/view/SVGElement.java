package com.gorecki.view;


import com.gorecki.tree.Node;

public class SVGElement {

    public static String createNode(Node node) {
        StringBuilder sb = new StringBuilder();
        sb.append("<text x=\"")
                .append((int)node.getValue() + 15)
                .append("\" y=\"")
                .append((int)node.getValue() + 15)
                .append("\" font-family=\"sans-serif\" font-size=\"20px\" fill=\"black\">")
                .append(node.getValue())
                .append("</text>");
        return sb.toString();
    }

}

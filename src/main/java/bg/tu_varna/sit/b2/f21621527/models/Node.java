package bg.tu_varna.sit.b2.f21621527.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Node {
    private String id;
    private String tag;
    private String text;
    private Map<String, String> attributes;
    private Node parent;

    private final List<Node> children = new ArrayList<>();

    public Node(String id, String tag) {
        this.id=id;
        this.tag=tag;
    }

    public Node(String tag) {
        this.tag=tag;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Node> getChildren() {
        return new ArrayList<>(children);
    }

    public boolean addChild(Node child) {
        child.setParent(this);
        return children.add(child);
    }

    public boolean removeChild(Node child) {
        return children.remove(child);
    }

    @Override
    public String toString() {
        children.forEach(System.out::println);
        return "<" + tag + " id=" + id + " " + attributes + "> Text: " + text;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}

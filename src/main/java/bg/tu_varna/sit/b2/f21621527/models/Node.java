package bg.tu_varna.sit.b2.f21621527.models;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String id;
    private final String key;
    private String value;

    private final List<Node> children = new ArrayList<>();

    public Node(String id, String key, String value) {
        this.id = id;
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Node> getChildren() {
        return new ArrayList<>(children);
    }

    public boolean addChild(Node child) {
        return children.add(child);
    }

    public boolean removeChild(Node child) {
        return children.remove(child);
    }
}

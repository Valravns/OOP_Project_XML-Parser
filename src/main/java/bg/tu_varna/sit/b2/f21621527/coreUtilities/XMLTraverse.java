package bg.tu_varna.sit.b2.f21621527.coreUtilities;

import bg.tu_varna.sit.b2.f21621527.models.Node;

public class XMLTraverse {
    public static Node findNode(Node node, String id) {
        if (node == null) {
            return null;
        }

        if (id.equals(node.getId())) {
            return node;
        }

        for (Node child : node.getChildren()) {
            Node result = findNode(child, id);
            if (result != null) {
                return result;
            }
        }

        return null;
    }
}

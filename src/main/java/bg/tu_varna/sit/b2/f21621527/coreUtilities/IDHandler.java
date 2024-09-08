package bg.tu_varna.sit.b2.f21621527.coreUtilities;

import bg.tu_varna.sit.b2.f21621527.models.Node;
import java.util.HashSet;
import java.util.Set;

public class IDHandler {
    private Set<String> existingIds = new HashSet<>();
    private int newIdCounter = 1;

    public void uniqueIdCheck(Node node) {
        assignID(node);
    }

    private void assignID(Node node) {
        if (node == null) {
            return;
        }

        String id = node.getId();
        if (id == null || id.isEmpty()) {
            id = generateId();
            node.setId(id);
        } else if (existingIds.contains(id)) {
            id = changeID(id);
            node.setId(id);
        }
        existingIds.add(id);

        for (Node child : node.getChildren()) {
            assignID(child);
        }
    }

    private String generateId() {
        String newID;
        do {
            newID = "new_" + this.newIdCounter++;
        } while (existingIds.contains(newID));
        return newID;
    }

    private String changeID(String id) {
        int suffix = 1;
        String uniqueID = id + "_" + suffix;
        while (existingIds.contains(uniqueID)) {
            suffix++;
            uniqueID = id + "_" + suffix;
        }
        return uniqueID;
    }
}

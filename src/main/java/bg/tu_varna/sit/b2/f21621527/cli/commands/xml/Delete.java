package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.coreUtilities.XMLTraverse;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.models.Node;

import java.util.List;
import java.util.Map;

public class Delete implements ExecutableCommand {
    private final List<String> args;
    private boolean found = false;
    private boolean keyExists = false;

    public Delete(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        //System.out.println("Delete " + args.get(0) + " " + args.get(1));
        Node root = AppData.getInstance().getOpenedDocument().getParent();
        deleteKey(root, args.get(0), args.get(1));

        if (!found) {
            System.out.println("Element with id " + args.get(0) + " was not found.");
        } else if (!keyExists) {
            System.out.println("Element with id " + args.get(0) + " was found, but the key " + args.get(1) + " doesn't exist");
        } else {
            System.out.println("Key - " + args.get(1) + " was successfully deleted from element with id - " + args.get(0));
        }
    }

    private void deleteKey(Node node, String id, String key) {
        Node searchNode = XMLTraverse.findNode(node, id);
        if(searchNode == null) {
            return;
        }
        found = true;
        Map<String, String> attributes = searchNode.getAttributes();
        if (attributes.containsKey(key)) {
            keyExists = true;
            attributes.remove(key);
        }
    }
}

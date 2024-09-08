package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.coreUtilities.XMLTraverse;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.models.Node;

import java.util.List;
import java.util.Map;

public class Set implements ExecutableCommand {
    private final List<String> args;
    private boolean found = false;

    public Set(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        //System.out.println("Set " + args.get(0) + " " + args.get(1) + " " + args.get(2));
        Node root = AppData.getInstance().getOpenedDocument().getParent();
        String result = changeValue(root, args.get(0), args.get(1), args.get(2));

        if (!found) {
            System.out.println("Element with id " + args.get(0) + " was not found");
        } else if (result == null) {
            System.out.println("Element with id " + args.get(0) + " was found, but the key " + args.get(1) + " does not exist");
        } else {
            System.out.println("Element with id " + args.get(0) + " was found, key - " + args.get(1) + " new value - " + result);
        }
    }

    private String changeValue(Node node, String id, String key, String value) {
        Node searchNode = XMLTraverse.findNode(node, id);
        if(searchNode == null) {
            return null;
        }
        found = true;
        Map<String, String> attributes = searchNode.getAttributes();
        if (attributes != null && attributes.containsKey(key)) {
            searchNode.getAttributes().put(key, value);
            return value;
        } else {
            return null;
        }
    }
}

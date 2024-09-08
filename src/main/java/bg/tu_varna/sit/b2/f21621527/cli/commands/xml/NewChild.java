package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.coreUtilities.XMLTraverse;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.models.Node;

import java.util.List;

public class NewChild implements ExecutableCommand {
    private final List<String> args;
    private boolean found = false;

    public NewChild(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        //System.out.println("NewChild " + args.get(0));
        Node root = AppData.getInstance().getOpenedDocument().getParent();
        createChild(root, args.get(0), args.get(1));

        if (!found) {
            System.out.println("Element with id " + args.get(0) + " was not found.");
        } else {
            System.out.println("Element with id " + args.get(0) + " was found, and the child was created");
        }
    }

    public void createChild(Node node, String id, String name) {
        Node searchNode = XMLTraverse.findNode(node, id);
        if(searchNode == null) {
            return;
        }
        found = true;
        Node createdChild = new Node("350",name);
        searchNode.addChild(createdChild);
    }
}

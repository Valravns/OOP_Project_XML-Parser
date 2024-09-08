package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.coreUtilities.XMLTraverse;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.models.Node;

import java.util.List;

public class Child implements ExecutableCommand {
    private final List<String> args;
    private boolean found = false;

    public Child(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        //System.out.println("child " + args.get(0) + " " + args.get(1));
        Node root = AppData.getInstance().getOpenedDocument().getParent();
        Node result = findChild(root, args.get(0), Integer.parseInt(args.get(1)));

        if (!found) {
            System.out.println("Element with id " + args.get(0) + " was not found");
        } else if (result == null) {
            System.out.println("Element with id " + args.get(0) + " doesn't have a n-th:" + args.get(1) + " child");
        } else {
            System.out.println("Element with id " + args.get(0) + " has a n-th:" + args.get(1) + " child - " + "<" + result.getTag() + ">");
        }

    }

    private Node findChild(Node node, String id, int n) {
        Node searchNode = XMLTraverse.findNode(node, id);
        if(searchNode == null) {
            return null;
        }
        found = true;
        List<Node> searchChildren = searchNode.getChildren();
        if(n>0 && n<=searchChildren.size()) {
            return searchChildren.get(n - 1);
        } else {
            return null;
        }
    }
}

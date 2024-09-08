package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.coreUtilities.XMLTraverse;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.models.Node;

import java.util.List;
import java.util.Map;

public class Children implements ExecutableCommand {
    private final List<String> args;

    public Children(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        //System.out.println("Children " + args.get(0));
        Node root = AppData.getInstance().getOpenedDocument().getParent();
        Node resultNode = XMLTraverse.findNode(root, args.get(0));

        printChildrenAttributes(resultNode);
    }

    private void printChildrenAttributes(Node node) {
        if (node == null) {
            System.out.println("Element with this id was not found.");
            return;
        }

        List<Node> searchChildren = node.getChildren();
        if (searchChildren.isEmpty()) {
            System.out.println("Element with id " + node.getId() + " has no children.");
            return;
        }

        for(Node child: searchChildren) {
            System.out.print("<" + child.getTag());
            Map<String, String> attributes = child.getAttributes();
            System.out.print(" id=" + child.getId());
            if(!attributes.isEmpty()) {
                for(Map.Entry<String, String> attribute : attributes.entrySet()) {
                    System.out.print(" " + attribute.getKey() + "=" + attribute.getValue());
                }
            }
            System.out.print(">" + "\n");
        }

    }

}

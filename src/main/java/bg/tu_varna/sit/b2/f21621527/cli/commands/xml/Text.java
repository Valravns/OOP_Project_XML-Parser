package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.coreUtilities.XMLTraverse;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.models.Node;

import java.util.List;

public class Text implements ExecutableCommand {
    private final List<String> args;
    private boolean found = false;

    public Text(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        Node root = AppData.getInstance().getOpenedDocument().getParent();
        String result = findText(root, args.get(0));

        if (!found) {
            System.out.println("Element with id " + args.get(0) + " was not found");
        } else if (result == null) {
            System.out.println("Element with id " + args.get(0) + " was found, but there is no text");
        } else {
            System.out.println("Element with id " + args.get(0) + " was found, text: " + result);
        }
    }
    private String findText(Node node, String id) {
        Node searchNode = XMLTraverse.findNode(node, id);
        if(searchNode == null) {
            return null;
        }
        found = true;
        return searchNode.getText();
    }
}

package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.models.Node;

import java.util.Map;


public class Print implements ExecutableCommand {
    @Override
    public void execute() {
        Node root = AppData.getInstance().getOpenedDocument().getParent();
        printXML(root, 0);
    }
    private void printXML(Node node, int indent) {
        if (node == null) {
            return;
        }
        printIndent(indent);
        System.out.print("<" + node.getTag() + " id=\"" + node.getId() + "\"");

        if (node.getAttributes() != null) {
            for (Map.Entry<String, String> attribute : node.getAttributes().entrySet()) {
                String k = attribute.getKey();
                String v = attribute.getValue();
                System.out.print(" " + k + "=\"" + v + "\"");
            }
        }

        System.out.print(">");

        if (node.getText() != null) {
            System.out.print(node.getText());
        }

        if (node.getChildren().isEmpty() && node.getText() == null) {
            System.out.println("</" + node.getTag() + ">");
            return;
        }

        if (!node.getChildren().isEmpty()) {
            System.out.println();
            for (Node child : node.getChildren()) {
                printXML(child, indent + 1);
            }
            printIndent(indent);
        }
        System.out.println("</" + node.getTag() + ">");

    }
    private void printIndent(int indentLevel) {

        for (int i = 0; i < indentLevel; i++) {
            System.out.print("   ");
        }
    }
}

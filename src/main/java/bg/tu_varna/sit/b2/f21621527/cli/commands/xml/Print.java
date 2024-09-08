package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.models.Node;

import java.util.Map;


public class Print implements ExecutableCommand {
    @Override
    public void execute() {
        Node root = AppData.getInstance().getOpenedDocument().getParent();
        System.out.println(printXML(root, 0));
    }

    public String printXML(Node node, int indent) {
        StringBuilder XMLBuilder = new StringBuilder();
        buildXML(node, indent, XMLBuilder);
        return XMLBuilder.toString();
    }

    private void buildXML(Node node, int indent, StringBuilder XMLBuilder) {
        if (node == null) {
            return;
        }

        appendIndent(indent, XMLBuilder);
        XMLBuilder.append("<").append(node.getTag()).append(" id=\"").append(node.getId()).append("\"");

        if (node.getAttributes() != null) {
            for (Map.Entry<String, String> attribute : node.getAttributes().entrySet()) {
                String k = attribute.getKey();
                String v = attribute.getValue();
                XMLBuilder.append(" ").append(k).append("=\"").append(v).append("\"");
            }
        }

        XMLBuilder.append(">");

        if (node.getText() != null) {
            XMLBuilder.append(node.getText());
        }

        if (node.getChildren().isEmpty() && node.getText() == null) {
            XMLBuilder.append("</").append(node.getTag()).append(">\n");
            return;
        }

        if (!node.getChildren().isEmpty()) {
            XMLBuilder.append("\n");
            for (Node child : node.getChildren()) {
                buildXML(child, indent + 1, XMLBuilder);
            }
            appendIndent(indent, XMLBuilder);
        }

        XMLBuilder.append("</").append(node.getTag()).append(">\n");
    }

    private void appendIndent(int indentLevel, StringBuilder XMLBuilder) {
        for (int i = 0; i < indentLevel; i++) {
            XMLBuilder.append("   ");
        }
    }
}

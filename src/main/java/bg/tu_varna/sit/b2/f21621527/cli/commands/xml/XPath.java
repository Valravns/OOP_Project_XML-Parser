package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.coreUtilities.XMLTraverse;
import bg.tu_varna.sit.b2.f21621527.models.Node;

import java.util.ArrayList;
import java.util.List;

public class XPath implements ExecutableCommand {
    private final List<String> args;

    public XPath(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        //System.out.println("XPath " + args.get(0) + " " + args.get(1));
        Node root = AppData.getInstance().getOpenedDocument().getParent();

        List<Node> resultXPath = performXPath(root, args.get(0), args.get(1));
        if (resultXPath.isEmpty()) {
            System.out.println("No elements found.");
        } else {
            for (Node result : resultXPath) {
                System.out.println(result.toString());
            }
        }
    }

    private  List<Node> performXPath(Node node, String id, String xpath) {
        Node searchNode = XMLTraverse.findNode(node, id);
        if(searchNode == null) {
            return null;
        }
        return nodeXPath(searchNode, xpath);
    }

    private List<Node> nodeXPath(Node node, String xpath) {
        List<Node> result = new ArrayList<>();

        if(xpath.contains("/")) {
            String[] xpathContent = xpath.split("/");
            Node iNode = node;

            for (String content : xpathContent) {
                if(content.isEmpty()) {
                    continue;
                }
                if (content.contains("[")) {
                    int pos = Integer.parseInt(content.substring(content.indexOf("[") + 1, content.indexOf("]")));
                    String tag = content.substring(0, content.indexOf("["));
                    iNode = getChildPosition(iNode, tag, pos);
                } else {
                    iNode = getChildName(iNode, content);
                }
                if (iNode == null) {
                    return result;
                }
            }
            result.add(iNode);
        }

        else if(xpath.contains("@")) {
            String attributeName = xpath.substring(xpath.indexOf("@") + 1);
            if (node.getAttributes() != null && node.getAttributes().containsKey(attributeName)) {
                System.out.println("Attribute " + attributeName + ": " + node.getAttributes().get(attributeName));
            }
        }

        else if(xpath.contains("=")) {
            String[] xpathContent = xpath.split("=");
            String attributeName = xpathContent[0].trim();
            String attributeValue = xpathContent[1].replace("\"", "").trim();

            for (Node child : node.getChildren()) {
                if (attributeValue.equals(child.getAttributes().get(attributeName))) {
                    result.add(child);
                }
            }
        }

        return result;
    }

    private Node getChildPosition(Node node, String name, int pos) {
        int count = 0;
        for (Node child : node.getChildren()) {
            if (child.getTag().equals(name)) {
                if (count == pos) {
                    return child;
                }
                count++;
            }
        }
        return null;
    }

    private Node getChildName(Node node, String name) {
        for (Node child : node.getChildren()) {
            if (child.getTag().equals(name)) {
                return child;
            }
        }
        return null;
    }

}

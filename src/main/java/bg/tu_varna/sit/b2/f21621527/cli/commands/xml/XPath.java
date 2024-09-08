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
        if (resultXPath == null || resultXPath.isEmpty()) {
            System.out.println("No elements found.");
        } else {
            for (Node result : resultXPath) {
                System.out.println(result.toString());
                System.out.println();
            }
        }
    }

    private  List<Node> performXPath(Node node, String id, String xpath) {
        Node searchNode = XMLTraverse.findNode(node, id);
        if(searchNode == null) {
            return null;
        }

        Node parentNode = searchNode.getParent();
        if (parentNode == null) {
            return null;
        }
        return nodeXPath(parentNode, searchNode, xpath);
    }

    private List<Node> nodeXPath(Node parent, Node node, String xpath) {
        List<Node> result = new ArrayList<>();

        if(xpath.contains("/") && !xpath.contains("=") && xpath.contains("[") && xpath.contains("]")) {
            String[] xpathContent = xpath.split("/");
            Node iNode = parent;

            for (String content : xpathContent) {
                if(content.isEmpty()) {
                    continue;
                }
                int pos = Integer.parseInt(content.substring(content.indexOf("[") + 1, content.indexOf("]")));
                String tag = content.substring(0, content.indexOf("["));
                List<Node> foundNodes = slashFindXPath(parent, node, xpath);
                if(pos > foundNodes.size()) return null;
                iNode = foundNodes.get(pos);
                if (iNode == null) {
                    return result;
                }
            }
            result.add(iNode);
            return result;
        }

        else if(xpath.contains("@")) {
            String attributeName = xpath.substring(1);
            for (Node child : parent.getChildren()) {
                if (child.getAttributes() != null && child.getAttributes().containsKey(attributeName)) {
                    result.add(child);
                }
            }
        }

        else if(xpath.contains("=")) {
            String[] xpathContent = xpath.split("=");
            String attrName = xpathContent[0].trim();
            String attrValue = xpathContent[1].replace("\"", "").trim();

            for (Node child : parent.getChildren()) {
                for(Node secondChild : child.getChildren()) {
                    if (attrValue.equals(secondChild.getText())) {
                        result.add(child);
                    }
                }
            }
        }
        if (xpath.contains("/")) {
            return slashFindXPath(parent, node, xpath);
        }
        return result;
    }
    private List<Node> slashFindXPath(Node parent, Node node, String xpath) {
        List<Node> result = new ArrayList<>();
        String tag = xpath.split("/")[1].trim();

        if (tag.contains("[")) {
            tag = tag.substring(0, tag.indexOf("[")).trim();
        }

        for (Node child : parent.getChildren()) {
            if (child.getTag().equals(node.getTag())) {
                for (Node personChild : child.getChildren()) {
                    if (personChild.getTag().equals(tag)) {
                        result.add(personChild);
                    }
                }
            }
        }
        return result;
    }

}

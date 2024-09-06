package bg.tu_varna.sit.b2.f21621527.coreUtilities;

import bg.tu_varna.sit.b2.f21621527.contracts.Parser;
import bg.tu_varna.sit.b2.f21621527.contracts.Validator;
import bg.tu_varna.sit.b2.f21621527.models.Node;
import bg.tu_varna.sit.b2.f21621527.models.XMLDocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class XMLParser implements Parser {
    private static final XMLParser instance = new XMLParser();
    private final Validator validator = XMLValidator.getInstance();
    //private final String xmlRegex = "<([a-zA-Z0-9_:-]+)(\\s+[a-zA-Z0-9_:-]+=\"[^\"]*\")*\\s*>[^<]*</\\1>|<([a-zA-Z0-9_:-]+)(\\s+[a-zA-Z0-9_:-]+=\"[^\"]*\")*\\s*/?>";
    private XMLParser() {}

    public static XMLParser getInstance() {
        return instance;
    }

    @Override
    public XMLDocument parse(File file) {
        Node root = null;

        if (!validator.validate(file)) {
            throw new RuntimeException(String.format("Error validating XML file: %s", file.getAbsolutePath()));
        }

        Stack<Node> nodeStack = new Stack<>();
        String id = "", key = "", value = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                int startIndex = line.indexOf("<");
                int endIndex;
                if(line.contains("/>"))
                    endIndex = line.indexOf("/>");
                else
                    endIndex = line.indexOf(">");

                if (startIndex >= 0 && endIndex > startIndex) {
                    String tag = line.substring(startIndex + 1, endIndex).trim();

                    Map<String, String> tagAttributes = new HashMap<>();
                    if (tag.contains(" ")) {
                        String[] parts = tag.split(" ");
                        tag = parts[0];
                        for (int i = 1; i < parts.length; i++) {
                            if (parts[i].startsWith("id=")) {
                                id = parts[i].substring(4, parts[i].length() - 1);
                            } else {
                                String[] atr = parts[i].split("=");
                                String k = atr[0].replace("\"", "");
                                String v = atr[1].replace("\"", "");
                                tagAttributes.put(k,v);
                            }
                        }
                    }

                    Node node = new Node(id,tag);
                    node.setAttributes(tagAttributes);

                    if (nodeStack.isEmpty()) {
                        root = node;
                    } else if(!tag.startsWith("/")){
                        nodeStack.peek().addChild(node);
                    }

                    if (tag.startsWith("/")) {
                        node = nodeStack.pop();

                    } else {
                        nodeStack.push(node);
                    }

                    if(line.contains("/" + tag)) {
                        node.setText(line.substring(endIndex+1, line.indexOf("</" + tag)));
                        node = nodeStack.pop();
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error parsing XML file: %s", file.getAbsolutePath()));
        }

        return new XMLDocument(root);
    }
}

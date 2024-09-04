package bg.tu_varna.sit.b2.f21621527.coreUtilities;

import bg.tu_varna.sit.b2.f21621527.contracts.Parser;
import bg.tu_varna.sit.b2.f21621527.contracts.Validator;
import bg.tu_varna.sit.b2.f21621527.models.Node;
import bg.tu_varna.sit.b2.f21621527.models.XMLDocument;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

                int startIndex = line.indexOf('<');
                int endIndex = line.indexOf('>');

                if (startIndex >= 0 && endIndex > startIndex) {
                    String tag = line.substring(startIndex + 1, endIndex).trim();

                    // Handle attributes (like id)
                    id = null;
                    if (tag.contains(" ")) {
                        String[] parts = tag.split(" ");
                        tag = parts[0];
                        for (int i = 1; i < parts.length; i++) {
                            if (parts[i].startsWith("id=")) {
                                id = parts[i].substring(4, parts[i].length() - 1); // Extract id value
                            }
                        }
                    }

                    // Check if it's a closing tag
                    if (tag.startsWith("/")) {
                        tag = tag.substring(1);
                        Node node = nodeStack.pop();

                        // Check for root assignment
                        if (nodeStack.isEmpty()) {
                            root = node;
                        } else {
                            nodeStack.peek().addChild(node);
                        }
                    } else if (line.endsWith("/>")) { // Self-closing tag
                        Node node = new Node(tag, id, value);
                        if (!nodeStack.isEmpty()) {
                            nodeStack.peek().addChild(node);
                        }
                    } else { // Opening tag
                        Node node = new Node(tag, id, value);
                        nodeStack.push(node);
                    }
                } else if (!nodeStack.isEmpty()) {
                    // Handle text content between tags
                    Node current = nodeStack.peek();
                    current.setValue(current.getValue() + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Error parsing XML file: %s", file.getAbsolutePath()));
        }

        return null;
    }
}

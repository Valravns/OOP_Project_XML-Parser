package bg.tu_varna.sit.b2.f21621527.coreUtilities;

import bg.tu_varna.sit.b2.f21621527.contracts.Validator;

import java.io.*;
import java.util.Stack;

public class XMLValidator implements Validator {
    private static final XMLValidator instance = new XMLValidator();

    private XMLValidator() {}

    public static XMLValidator getInstance() {
        return instance;
    }

    @Override
    public boolean validate(File file) {
        Stack<String> tagStack = new Stack<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (!line.contains("<") || !line.contains(">")) {
                    continue;
                }

                while (line.contains("<") && line.contains(">")) {
                    int startIndex = line.indexOf('<');
                    int endIndex = line.indexOf('>');

                    if (line.contains(" id=")) {
                        endIndex = line.indexOf(" id=");
                    }

                    if (startIndex >= 0 && endIndex > startIndex) {
                        String tag = line.substring(startIndex + 1, endIndex);

                        if (tag.startsWith("/")) {
                            tag = tag.substring(1);
                            if (tagStack.isEmpty() || !tagStack.peek().equals(tag)) {
                                throw new RuntimeException("Mismatched closing tag: " + tag);
                            }
                            tagStack.pop();
                        } else if (!tag.endsWith("/")) {
                            tagStack.push(tag);
                        }
                        line = line.substring(endIndex + 1);
                        if(line.contains("/" + tag)) {
                            tagStack.pop();
                        }
                    } else {
                        break;
                    }
                }
            }
            if (!tagStack.isEmpty()) {
                throw new RuntimeException("Unclosed tags: " + tagStack);
            }

            System.out.println("XML is well-formed!");
            return true;

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return false;
        } catch (RuntimeException e) {
            System.err.println("XML is not valid: " + e.getMessage());
            return false;
        }
    }
}

package bg.tu_varna.sit.b2.f21621527.coreUtilities;

import bg.tu_varna.sit.b2.f21621527.contracts.Validator;
import bg.tu_varna.sit.b2.f21621527.exceptions.files.XMLFileNotFoundException;
import bg.tu_varna.sit.b2.f21621527.exceptions.utilities.InvalidXMLFileException;
import bg.tu_varna.sit.b2.f21621527.exceptions.utilities.MismatchedClosingTagException;
import bg.tu_varna.sit.b2.f21621527.exceptions.utilities.UnclosedTagsException;

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
                                throw new MismatchedClosingTagException("Mismatched closing tag: " + tag);
                            }
                            tagStack.pop();
                        } else if (!tag.endsWith("/")) {
                            tagStack.push(tag);
                        }
                        line = line.substring(endIndex + 1);
                        if(line.contains("/" + tag)) {
                            try {
                                int closeStartIndex = line.indexOf("</");
                                int closeFirstEndIndex = line.indexOf('>');
                                int closeSecondEndIndex = line.indexOf('>', closeFirstEndIndex + 1);
                                tag = line.substring(closeStartIndex + 2, closeSecondEndIndex);
                                if (tagStack.isEmpty() || !tagStack.peek().equals(tag)) {
                                    throw new MismatchedClosingTagException("Mismatched closing tag: " + tag);
                                }
                                tagStack.pop();
                            } catch(IndexOutOfBoundsException e) {
                                throw new MismatchedClosingTagException("Mismatched closing tag: " + tag);
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
            if (!tagStack.isEmpty()) {
                throw new UnclosedTagsException("Unclosed tags: " + tagStack);
            }

            System.out.println("XML is well-formed!");
            return true;

        } catch (IOException e) {
            throw new XMLFileNotFoundException();
        } catch (RuntimeException e) {
            throw new InvalidXMLFileException("XML is not valid: " + e.getMessage());
        }
    }
}

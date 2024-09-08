package bg.tu_varna.sit.b2.f21621527.exceptions.utilities;

import bg.tu_varna.sit.b2.f21621527.exceptions.XMLParserException;

public class InvalidParsingException extends XMLParserException {
    public InvalidParsingException() {
        super("Error with parsing the XML file");
    }
}

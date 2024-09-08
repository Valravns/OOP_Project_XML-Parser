package bg.tu_varna.sit.b2.f21621527.exceptions.files;

import bg.tu_varna.sit.b2.f21621527.exceptions.XMLParserException;

public class XMLFileNotFoundException extends XMLParserException {

    public XMLFileNotFoundException() {
        super("Error with reading the file (The file was not found)");
    }
}

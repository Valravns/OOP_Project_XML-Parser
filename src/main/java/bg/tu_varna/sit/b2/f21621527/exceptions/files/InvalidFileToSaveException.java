package bg.tu_varna.sit.b2.f21621527.exceptions.files;

import bg.tu_varna.sit.b2.f21621527.exceptions.XMLParserException;

public class InvalidFileToSaveException extends XMLParserException {
    public InvalidFileToSaveException() {
        super("File name does not include .xml");
    }
}

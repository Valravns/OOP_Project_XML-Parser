package bg.tu_varna.sit.b2.f21621527.exceptions.files;

import bg.tu_varna.sit.b2.f21621527.exceptions.XMLParserException;

public class FileNotSavedException extends XMLParserException {
    public FileNotSavedException() {
        super("File was not saved successfully");
    }
}

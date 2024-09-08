package bg.tu_varna.sit.b2.f21621527.exceptions.files;

import bg.tu_varna.sit.b2.f21621527.exceptions.XMLParserException;

public class FileAlreadyOpenedException extends XMLParserException {
    public FileAlreadyOpenedException() {
        super("File is already opened");
    }
}

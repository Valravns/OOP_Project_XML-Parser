package bg.tu_varna.sit.b2.f21621527.exceptions.commands;

import bg.tu_varna.sit.b2.f21621527.exceptions.XMLParserException;

public class InvalidArgumentsException extends XMLParserException {
    public InvalidArgumentsException() {
        super("Invalid number of arguments");
    }
}

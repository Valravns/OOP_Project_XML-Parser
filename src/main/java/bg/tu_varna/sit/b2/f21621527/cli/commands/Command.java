package bg.tu_varna.sit.b2.f21621527.cli.commands;

public enum Command {
    OPEN ("open <file>", "Opens <file> and reads its contents."),
    CLOSE ("close", "Closes the current file."),
    SAVE ("save", "Saves the contents in the current file."),
    SAVEAS ("saveas <file>", "Saves the contents in <file>"),
    HELP ("help", "Shows all possible commands."),
    EXIT ("exit", "Exits the application."),

    PRINT ("print", "Prints the contents of the XML file."),
    SELECT ("select <id> <key>", "Prints the content of an attribute with <id> and <key>."),
    SET ("set <id> <key> <value>", "Sets the value of an attribute with <id> and <key>."),
    CHILDREN ("children <id>", "Prints a list of the children by a given <id>."),
    CHILD ("child <id> <n>", "Prints the <n>-th child of an element."),
    TEXT ("text <id>", "Prints the content of the element with <id>."),
    DELETE ("delete <id> <key>", "Deletes an attribute with <id> and <key>."),
    NEWCHILD ("newchild <id>", "Adds a new child of an element with <id>."),
    XPATH ("xpath <id> <XPath>", "Runs the XPath 2.0 queries to an element with <id>.");

    private final String syntax;
    private final String description;

    Command(String syntax, String description) {
        this.syntax = syntax;
        this.description = description;
    }

    public String getSyntax() {
        return syntax;
    }

    public String getDescription() {
        return description;
    }

    public static Command getCommand(String syntax) {
        for (Command command : values()) {
            String commandType = command.getSyntax().split(" ")[0];
            if (commandType.equalsIgnoreCase(syntax)) {
                return command;
            }
        }
        throw new RuntimeException("No such command: " + syntax);
    }


    @Override
    public String toString() {
        return String.format("%-25s%s", syntax.toUpperCase(), description);
    }
}

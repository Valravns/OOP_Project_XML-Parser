package bg.tu_varna.sit.b2.f21621527.cli.commands.factory;

import bg.tu_varna.sit.b2.f21621527.cli.commands.Command;
import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.cli.commands.utility.*;
import bg.tu_varna.sit.b2.f21621527.cli.commands.xml.*;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.exceptions.commands.InvalidArgumentsException;
import bg.tu_varna.sit.b2.f21621527.exceptions.files.FileAlreadyOpenedException;
import bg.tu_varna.sit.b2.f21621527.exceptions.files.FileNotOpenedException;

import java.util.List;

public class CommandFactory {
    private static CommandFactory instance;

    private CommandFactory() {}

    public static CommandFactory getInstance() {
        if (instance == null) {
            instance = new CommandFactory();
        }
        return instance;
    }

    public ExecutableCommand createCommand(Command command, List<String> args) {
        ExecutableCommand executableCommand = null;

        switch (command) {
            case OPEN -> {
                if (args.size() != 1) {
                    throw new InvalidArgumentsException();
                }

                if (AppData.getInstance().isFileOpened()) {
                    throw new FileAlreadyOpenedException();
                }

                executableCommand = new Open(args);
            }

            case CLOSE -> {
                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file to be closed");
                }

                executableCommand = new Close();
            }

            case SAVE -> {
                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file to be saved");
                }

                executableCommand = new Save(new Print());
            }

            case SAVEAS -> {
                if (args.size() != 1) {
                    throw new InvalidArgumentsException();
                }

                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file to be saved");
                }

                executableCommand = new SaveAs(args);
            }

            case HELP -> {
                executableCommand = new Help();
            }

            case EXIT -> {
                executableCommand = new Exit();
            }

            case PRINT -> {
                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file to be printed");
                }

                executableCommand = new Print();
            }

            case SELECT -> {
                if (args.size() != 2) {
                    throw new InvalidArgumentsException();
                }

                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file to be selected");
                }

                executableCommand = new Select(args);
            }

            case SET -> {
                if (args.size() != 3) {
                    throw new InvalidArgumentsException();
                }

                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file for a node's value of an attribute to be changed");
                }

                executableCommand = new Set(args);
            }

            case CHILDREN -> {
                if (args.size() != 1) {
                    throw new InvalidArgumentsException();
                }

                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file to be searched for children");
                }

                executableCommand = new Children(args);
            }

            case CHILD -> {
                if (args.size() != 2) {
                    throw new InvalidArgumentsException();
                }

                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file to be searched for a child");
                }

                executableCommand = new Child(args);
            }

            case TEXT -> {
                if (args.size() != 1) {
                    throw new InvalidArgumentsException();
                }

                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file to be searched for a node's text");
                }

                executableCommand = new Text(args);
            }

            case DELETE -> {
                if (args.size() != 2) {
                    throw new InvalidArgumentsException();
                }

                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file for a node's attribute to be deleted");
                }

                executableCommand = new Delete(args);
            }

            case NEWCHILD -> {
                if (args.size() != 2) {
                    throw new InvalidArgumentsException();
                }

                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file to be added a new child");
                }

                executableCommand = new NewChild(args);
            }

            case XPATH -> {
                if (args.size() != 2) {
                    throw new InvalidArgumentsException();
                }

                if (!AppData.getInstance().isFileOpened()) {
                    throw new FileNotOpenedException("No file to be performed XPath on");
                }

                executableCommand = new XPath(args);
            }
        }

        return executableCommand;
    }
}

package bg.tu_varna.sit.b2.f21621527.cli.commands.factory;

import bg.tu_varna.sit.b2.f21621527.cli.commands.Command;
import bg.tu_varna.sit.b2.f21621527.cli.commands.utility.*;
import bg.tu_varna.sit.b2.f21621527.cli.commands.xml.*;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

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
                    throw new RuntimeException("invalid number of args");
                }

                executableCommand = new Open(args);
            }

            case CLOSE -> {
                executableCommand = new Close();
            }

            case SAVE -> {
                executableCommand = new Save();
            }

            case SAVEAS -> {
                if (args.size() != 1) {
                    throw new RuntimeException("invalid number of args");
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
                executableCommand = new Print();
            }

            case SELECT -> {
                if (args.size() != 2) {
                    throw new RuntimeException("invalid number of args");
                }

                executableCommand = new Select(args);
            }

            case SET -> {
                if (args.size() != 3) {
                    throw new RuntimeException("invalid number of args");
                }

                executableCommand = new Set(args);
            }

            case CHILDREN -> {
                if (args.size() != 1) {
                    throw new RuntimeException("invalid number of args");
                }

                executableCommand = new Children(args);
            }

            case CHILD -> {
                if (args.size() != 2) {
                    throw new RuntimeException("invalid number of args");
                }
                executableCommand = new Child(args);
            }

            case TEXT -> {
                if (args.size() != 1) {
                    throw new RuntimeException("invalid number of args");
                }

                executableCommand = new Text(args);
            }

            case DELETE -> {
                if (args.size() != 2) {
                    throw new RuntimeException("invalid number of args");
                }

                executableCommand = new Delete(args);
            }

            case NEWCHILD -> {
                if (args.size() != 1) {
                    throw new RuntimeException("invalid number of args");
                }

                executableCommand = new NewChild(args);
            }

            case XPATH -> {
                if (args.size() != 2) {
                    throw new RuntimeException("invalid number of args");
                }

                executableCommand = new XPath(args);
            }
        }

        return executableCommand;
    }
}

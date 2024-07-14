package bg.tu_varna.sit.b2.f21621527.cli.commands.utility;

import bg.tu_varna.sit.b2.f21621527.cli.commands.Command;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

public class Help implements ExecutableCommand {
    @Override
    public void execute() {
        for (Command command : Command.values()) {
            System.out.println(command);
        }
    }
}

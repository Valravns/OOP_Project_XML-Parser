package bg.tu_varna.sit.b2.f21621527.cli.commands.utility;

import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

public class Exit implements ExecutableCommand {
    @Override
    public void execute() {
        System.out.println("Exiting program");
        System.exit(0);
    }
}

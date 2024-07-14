package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

public class Print implements ExecutableCommand {
    @Override
    public void execute() {
        System.out.println("Print");
    }
}

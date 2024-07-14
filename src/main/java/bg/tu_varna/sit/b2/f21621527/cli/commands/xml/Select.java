package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

import java.util.List;

public class Select implements ExecutableCommand {
    private final List<String> args;

    public Select(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        System.out.println("Select " + args.get(0) + " " + args.get(1));
    }
}

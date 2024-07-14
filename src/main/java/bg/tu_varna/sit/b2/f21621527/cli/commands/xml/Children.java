package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

import java.util.List;

public class Children implements ExecutableCommand {
    private final List<String> args;

    public Children(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        System.out.println("Children " + args.get(0));
    }
}

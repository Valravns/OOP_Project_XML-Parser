package bg.tu_varna.sit.b2.f21621527.cli.commands.utility;

import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

import java.util.List;

public class Open implements ExecutableCommand {
    private final List<String> args;

    public Open(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        System.out.println("Opening " + args.get(0));
    }
}

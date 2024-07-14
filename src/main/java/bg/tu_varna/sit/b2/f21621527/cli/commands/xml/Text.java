package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

import java.util.List;

public class Text implements ExecutableCommand {
    private final List<String> args;

    public Text(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        System.out.println("Text " + args.get(0));
    }
}

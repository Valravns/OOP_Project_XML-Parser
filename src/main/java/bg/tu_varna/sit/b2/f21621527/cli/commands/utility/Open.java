package bg.tu_varna.sit.b2.f21621527.cli.commands.utility;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

import java.io.File;
import java.util.List;

public class Open implements ExecutableCommand {
    private final List<String> args;

    public Open(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        File file = new File(args.get(0));
        AppData.getInstance().load(file);
        System.out.println("Opening " + args.get(0));
    }
}

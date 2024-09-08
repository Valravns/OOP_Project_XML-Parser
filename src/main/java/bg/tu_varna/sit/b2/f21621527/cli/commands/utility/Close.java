package bg.tu_varna.sit.b2.f21621527.cli.commands.utility;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

public class Close implements ExecutableCommand {
    @Override
    public void execute() {
        System.out.println("Closing file");
        AppData.getInstance().closeFile();
        AppData.getInstance().resetDocument();
    }
}
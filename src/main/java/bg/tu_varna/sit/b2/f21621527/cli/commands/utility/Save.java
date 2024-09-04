package bg.tu_varna.sit.b2.f21621527.cli.commands.utility;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

public class Save implements ExecutableCommand {
    @Override
    public void execute() {
        AppData.getInstance().unload();
        System.out.println("Saving");
    }
}

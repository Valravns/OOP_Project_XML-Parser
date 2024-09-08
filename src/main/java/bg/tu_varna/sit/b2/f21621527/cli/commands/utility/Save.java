package bg.tu_varna.sit.b2.f21621527.cli.commands.utility;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.cli.commands.xml.Print;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.models.Node;

public class Save implements ExecutableCommand {
    private final Print printOperation;

    public Save(Print printOperation) {
        this.printOperation = printOperation;
    }

    @Override
    public void execute() {
        AppData.getInstance().unload();
        System.out.println("Saved");
    }

}

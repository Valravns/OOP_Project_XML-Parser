package bg.tu_varna.sit.b2.f21621527.cli.commands.utility;

import bg.tu_varna.sit.b2.f21621527.cli.commands.config.AppData;
import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;
import bg.tu_varna.sit.b2.f21621527.exceptions.files.InvalidFileToSaveException;
import bg.tu_varna.sit.b2.f21621527.models.Node;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class SaveAs implements ExecutableCommand {
    private final List<String> args;

    public SaveAs(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
        System.out.println("Saving as " + args.get(0));
        if(!args.get(0).contains(".xml")) {
            throw new InvalidFileToSaveException();
        }
        File fileToSave = new File(
                args.get(0).contains(File.separator) ?
                        args.get(0) :
                        Paths.get(AppData.getInstance().getOpenedFile().getPath()).toAbsolutePath().getParent().toString().concat(File.separator).concat(args.get(0))
        );

        AppData.getInstance().save(fileToSave);
        AppData.getInstance().setOpenedFile(fileToSave);
    }

}

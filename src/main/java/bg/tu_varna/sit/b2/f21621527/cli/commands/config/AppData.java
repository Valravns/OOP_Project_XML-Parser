package bg.tu_varna.sit.b2.f21621527.cli.commands.config;

import bg.tu_varna.sit.b2.f21621527.coreUtilities.XMLValidator;
import bg.tu_varna.sit.b2.f21621527.contracts.AppDataManagement;
import bg.tu_varna.sit.b2.f21621527.contracts.Validator;

import java.io.File;

public class AppData implements AppDataManagement {
    private static AppData instance;
    private File openedFile;

    private AppData() {}

    public static AppData getInstance() {
        if (instance == null) {
            instance = new AppData();
        }
        return instance;
    }

    public boolean isFileOpened() {
        return openedFile != null;
    }

    public File getOpenedFile() {
        return openedFile;
    }

    @Override
    public void load(File file) {
        //...chetem
        openedFile = file;
    }

    @Override
    public void unload() {
        //prashtame v openedFile
        //openedFile = null;
    }
}

package bg.tu_varna.sit.b2.f21621527.cli.commands.config;

import bg.tu_varna.sit.b2.f21621527.cli.commands.xml.Print;
import bg.tu_varna.sit.b2.f21621527.contracts.AppDataManagement;
import bg.tu_varna.sit.b2.f21621527.coreUtilities.XMLParser;
import bg.tu_varna.sit.b2.f21621527.exceptions.files.FileNotSavedException;
import bg.tu_varna.sit.b2.f21621527.models.XMLDocument;

import java.io.*;

public class AppData implements AppDataManagement {
    private static AppData instance;
    private File openedFile;
    private XMLDocument openedDocument;

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

    public void setOpenedFile(File openedFile) {
        this.openedFile = openedFile;
    }

    public void closeFile() {
        this.openedFile = null;
    }

    public void resetDocument() {
        this.openedDocument = null;
    }

    @Override
    public void load(File file) {
        openedFile = file;
        openedDocument = XMLParser.getInstance().parse(file);
    }

    @Override
    public void unload() {
        save(openedFile);
    }

    public void save(File file) {
        Print printOp = new Print();
        String documentContents = printOp.printXML(openedDocument.getParent(), 0);
        try {
            PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            writer.println(documentContents);
            writer.close();
        } catch (IOException e) {
            throw new FileNotSavedException();
        }
    }

    public XMLDocument getOpenedDocument() {
        return openedDocument;
    }
}
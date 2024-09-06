package bg.tu_varna.sit.b2.f21621527;

import bg.tu_varna.sit.b2.f21621527.cli.CLI;
import bg.tu_varna.sit.b2.f21621527.coreUtilities.XMLValidator;

import java.io.File;

public class XmlParserApplication {
    private static final CLI cli = CLI.getInstance();

    public static void main(String[] args) {
        System.out.println("XML Parser project.");
        //boolean isValid = XMLValidator.getInstance().validate(new File("F:\\University\\XMLParser - OOP1 Project\\src\\main\\java\\bg\\tu_varna\\sit\\b2\\f21621527\\files\\temp.xml"));
        //System.out.println(isValid);
        cli.run();
    }
}

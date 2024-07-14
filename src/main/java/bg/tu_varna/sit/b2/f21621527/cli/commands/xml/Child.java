package bg.tu_varna.sit.b2.f21621527.cli.commands.xml;

import bg.tu_varna.sit.b2.f21621527.contracts.ExecutableCommand;

import java.util.List;

public class Child implements ExecutableCommand {
    private final List<String> args;

    public Child(List<String> args) {
        this.args = args;
    }

    @Override
    public void execute() {
//        try {
//            Integer neshto = Integer.parseInt(args.get(0));
//        } catch (NumberFormatException e) {
//            System.out.println("ne e chislo");
//        }

        System.out.println("child " + args.get(0) + " " + args.get(1));
    }
}

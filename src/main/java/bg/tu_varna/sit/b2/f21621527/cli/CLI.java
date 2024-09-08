package bg.tu_varna.sit.b2.f21621527.cli;

import bg.tu_varna.sit.b2.f21621527.cli.commands.Command;
import bg.tu_varna.sit.b2.f21621527.cli.commands.factory.CommandFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private final CommandFactory commandFactory = CommandFactory.getInstance();
    private static CLI instance;
    private Command command;
    private List<String> args = new ArrayList<>();

    private CLI() {}

    public static CLI getInstance() {
        if (instance == null) {
            instance = new CLI();
        }
        return instance;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        do {
            reset();
            System.out.println();
            System.out.print("* ");

            String rawCommand = scanner.nextLine();

            System.out.println();

            try {
                processCommand(rawCommand);

                commandFactory.createCommand(command, args).execute();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    private void processCommand(String rawCommand) {
        this.command = Command.getCommand(rawCommand.split(" ")[0]);
        List<String> tempArgs = Arrays.stream(rawCommand.split(" ")).toList();
        if (tempArgs.size() == 1) {
            return;
        }
        this.args = tempArgs.subList(1, tempArgs.size());
    }

    private void reset() {
        this.command = null;
        this.args = new ArrayList<>();
    }
}

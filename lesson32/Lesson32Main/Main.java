

import jdbc.NetworkDao;
import service.NetworksService;
import ui.ConsoleController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        ConsoleController consoleController = new ConsoleController(new Scanner(System.in));
        NetworkDao networkDao = new NetworkDao();
        NetworksService networksService = new NetworksService(consoleController, networkDao);
        networksService.process();
    }
}
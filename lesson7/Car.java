package lesson7;

public class Car {

    public void start() {
        startElectricity();
        startCommand();
        startFuelSystem();
    }

    private void startElectricity() {
        System.out.println("Electricity");
    }

    private void startCommand() {
        System.out.println("Command");
    }

    private void startFuelSystem() {
        System.out.println("Fuel System");
    }
}
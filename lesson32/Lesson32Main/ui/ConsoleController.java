package ui;


import model.*;
import service.UserAction;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleController {

    private final Scanner scanner;

    public ConsoleController(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getUserChoice() {
        System.out.println("Enter: ");
        for (var action : UserAction.values()) {
            System.out.println(action.getCode() + " " + action.getDescription());
        }

        int code = scanner.nextInt();
        scanner.nextLine();
        return code;
    }

    public <T extends Model> T selectOf(List<T> models, String type) {
        System.out.println("Select " + type + " by id:");

        while (true) {
            models.forEach(System.out::println);

            int modelId = scanner.nextInt();
            scanner.nextLine();

            T selectedModel = null;
            for (var model : models) {
                if (model.getId() == modelId) {
                    selectedModel = model;
                    break;
                }
            }

            if (selectedModel == null)
                System.out.println("id was incorrect; try again");
            else
                return selectedModel;
        }
    }

    public Device readNewDevice() {

        String name = enterStringField("device", "name");
        String ipAddress;
        do {
            ipAddress = enterStringField("device", "IP address");
            if (!IpValidator.isValidIpv4(ipAddress)) {
                printError("Invalid IP address format. Please enter a valid IPv4 address (like 192.168.1.1)");
            }
        } while (!IpValidator.isValidIpv4(ipAddress));
        String macAddress = enterStringField("device", "mac");
        String status = enterStringField("device", "status");
        String type = enterStringField("device", "type");
        return new Device(name, ipAddress, macAddress, status, type);
    }

    public DeviceConnection readNewConnection() {
        String type = enterStringField("connection", "type");
        String status = enterStringField("connection", "status");
        return new DeviceConnection(type, status);
    }

    public Network readNetwork() {
        return updateFields(new Network());
    }

    public Network updateFields(Network network) {
        System.out.println("Enter network name: ");
        String name = scanner.next();
        scanner.nextLine();

        System.out.println("Enter network description: ");
        String description = scanner.nextLine();

        network.setName(name);
        network.setDescription(description);
        return network;
    }

    public void print(Network network) {
        System.out.println(network.toString());
    }

    public void print(Device device) {
        System.out.println(device.toString());
    }

    public void print(DeviceConnection deviceConnection) {
        System.out.println(deviceConnection.toString());
    }

    public void printError(String message) {
        System.out.println(message);
    }

    public <T> void printList(List<T> items, String title) {
        System.out.println("=== " + title + " ===");
        if (items.isEmpty()) {
            System.out.println("No items found");
        } else {
            items.forEach(System.out::println);
        }
    }

    public String enterStringField(String entityName, String fieldName) {
        return enterString("Enter " + entityName + " " + fieldName + ":");
    }

    public String enterString(String request) {
        System.out.println(request);
        return scanner.nextLine();
    }

    public void printNetworkDevicesAndConnections(Map<Device, List<DeviceConnection>> networkMap, Network network) {

        if (networkMap.isEmpty()) {
            System.out.println("No devices in this network");
            return;
        }

        for (Device device : networkMap.keySet()) {
            List<DeviceConnection> connections = networkMap.get(device);

            if (connections.isEmpty()) {
                System.out.println("No connections");
            } else {
                System.out.println("Connections:");
                for (DeviceConnection conn : connections) {
                    if (conn.getDeviceFromId() == device.getId()) {
                        System.out.println("Connects to device ID: " + conn.getDeviceToId());
                    } else {
                        System.out.println("Connected from device ID: " + conn.getDeviceFromId());
                    }
                }
            }
        }
    }

    public void printAllNetworksWithDevices(List<Network> networks,
                                            Map<Network, List<Device>> networksWithDevices) {

        if (networks.isEmpty()) {
            System.out.println("No networks found");
            return;
        }

        for (Network network : networks) {
            System.out.println("Network: " + network.getName() +
                    " (ID: " + network.getId() + ")");
            System.out.println("Description: " + network.getDescription());

            List<Device> devices = networksWithDevices.get(network);

            if (devices == null || devices.isEmpty()) {
                System.out.println("  No devices in this network");
            } else {
                System.out.println("  Devices (" + devices.size() + "):");
                for (Device device : devices) {
                    System.out.println("  - " + device.getName() +
                            " (ID: " + device.getId() +
                            ", Type: " + device.getType() +
                            ", Status: " + device.getStatus() + ")");
                }
            }
        }
    }
    public void printNetworkReports(List<NetworkReport> reports) {
        if (reports.isEmpty()) {
            System.out.println("No networks available for reporting");
            return;
        }

        for (NetworkReport report : reports) {
            Network network = report.getNetwork();
            System.out.println("\nNetwork: " + network.getName());
            System.out.println("Description: " + network.getDescription());
            System.out.println("Total devices: " + report.getTotalDevices());
            System.out.println("Active devices: " + report.getActiveDevices());
            System.out.println("Inactive devices: " + report.getClosedDevices());

            System.out.println("Devices by type:");
            report.getDevicesByType().forEach((type, count) ->
                    System.out.println("  - " + type + ": " + count)
            );
        }

        int totalNetworks = reports.size();
        int totalDevices = reports.stream().mapToInt(NetworkReport::getTotalDevices).sum();
        int totalActive = reports.stream().mapToInt(NetworkReport::getActiveDevices).sum();

        System.out.println("Total networks: " + totalNetworks);
        System.out.println("Total devices: " + totalDevices);
        System.out.println("Total active devices: " + totalActive);
        System.out.println("Total closed devices: " + (totalDevices - totalActive));
    }
}

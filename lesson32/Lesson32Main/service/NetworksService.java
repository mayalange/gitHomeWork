package service;


import jdbc.NetworkDao;
import model.Device;
import model.DeviceConnection;
import model.Network;
import model.NetworkReport;
import ui.ConsoleController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NetworksService {
    private final ConsoleController consoleController;
    private final NetworkDao networkDao;

    public NetworksService(ConsoleController consoleController, NetworkDao networkDao) {
        this.consoleController = consoleController;
        this.networkDao = networkDao;
    }

    public void process() {
        while (true) {
            int code = consoleController.getUserChoice();
            var userAction = UserAction.valueOf(code);
            if (userAction.isPresent()) {
                var action = userAction.get();

                if (action == UserAction.EXIT)
                    break;

                try {
                    processUserChoice(action);
                } catch (SQLException e) {
                    consoleController.printError(e.getMessage());
                }
            } else {
                consoleController.printError("Code was incorrect; try again");
            }
        }
    }

    public void processUserChoice(UserAction action) throws SQLException {
        switch (action) {
            case ADD_NETWORK -> {
                var network = consoleController.readNetwork();
                network = networkDao.save(network);
                consoleController.print(network);
            }
            case ADD_DEVICE -> {
                var networks = networkDao.getAllNetworks();
                if (networks.isEmpty()) {
                    consoleController.printError("Error; add networks first");
                    return;
                }

                var device = consoleController.readNewDevice();
                var networkToSet = consoleController.selectOf(networks, "network");
                device.setNetworkId(networkToSet.getId());
                Device updatedDevice = networkDao.save(device);
                consoleController.print(updatedDevice);
            }
            case ADD_CONNECTION -> {
                var devices = networkDao.getAllDevices();
                if (devices.size() < 2) {
                    consoleController.printError("Error; add devices first");
                    return;
                }

                var connection = consoleController.readNewConnection();
                var deviceFrom = consoleController.selectOf(devices, "device from");

                var remainedDevices = devices.stream()
                        .filter(device -> !device.equals(deviceFrom))
                        .toList();

                Device deviceTo = consoleController.selectOf(remainedDevices, "device to");

                connection.setDeviceFromId(deviceFrom.getId());
                connection.setDeviceToId(deviceTo.getId());
                DeviceConnection updatedConnection = networkDao.save(connection);
                consoleController.print(updatedConnection);
            }
            case REMOVE_CONNECTION -> {
                var allConnections = networkDao.getAllConnections();
                if (allConnections.isEmpty()) {
                    consoleController.printError("Connections not found");
                    return;
                }
                var selectedConnection = consoleController.selectOf(allConnections, "connection");
                networkDao.remove(selectedConnection);
            }
            case REMOVE_NETWORK -> {
                var allNetworks = networkDao.getAllNetworks();
                if (allNetworks.isEmpty()) {
                    consoleController.printError("Networks not found");
                    return;
                }
                var selectedNetwork = consoleController.selectOf(allNetworks, "network");
                var devicesOfNetwork = networkDao.getDevicesOf(selectedNetwork);
                if (!devicesOfNetwork.isEmpty()) {
                    consoleController.printError("Network has related devices and cannot be removed!");
                    return;
                }
                networkDao.remove(selectedNetwork);
            }
            case EDIT_NETWORK -> {
                var allNetworks = networkDao.getAllNetworks();
                if (allNetworks.isEmpty()) {
                    consoleController.printError("Networks not found");
                    return;
                }
                var selectedNetwork = consoleController.selectOf(allNetworks, "network");
                selectedNetwork = consoleController.updateFields(selectedNetwork);
                selectedNetwork = networkDao.update(selectedNetwork);
                consoleController.print(selectedNetwork);
            }
            case FIND_DEVICES_BY_STATUS -> {
                String status = consoleController.enterString("Enter status to search:");
                List<Device> devices = networkDao.findDevicesByStatus(status);
                consoleController.printList(devices, "Devices with status " + status);
            }
            case SHOW_NETWORK_DEVICES_AND_CONNECTIONS -> {
                var allNetworks = networkDao.getAllNetworks();
                if (allNetworks.isEmpty()) {
                    consoleController.printError("No networks available");
                    return;
                }

                Network selectedNetwork = consoleController.selectOf(allNetworks, "network");
                Map<Device, List<DeviceConnection>> networkMap = networkDao.getDevicesWithConnections(selectedNetwork);
                consoleController.printNetworkDevicesAndConnections(networkMap, selectedNetwork);
            }
            case SHOW_ALL_NETWORKS_WITH_DEVICES -> {
                Map<Network, List<Device>> networksWithDevices = networkDao.getAllNetworksWithDevices();
                consoleController.printAllNetworksWithDevices(
                        new ArrayList<>(networksWithDevices.keySet()),
                        networksWithDevices
                );
            }
            case GENERATE_REPORTS -> {
                List<NetworkReport> reports = networkDao.generateNetworkReports();
                consoleController.printNetworkReports(reports);
            }
            default -> throw new RuntimeException("Unexpected behaviour");
        }
    }
}

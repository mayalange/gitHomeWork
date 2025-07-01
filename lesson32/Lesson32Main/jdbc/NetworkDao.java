package jdbc;

import model.*;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

public class NetworkDao {

    public NetworkDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/lesson_networks_db", "admin", "admin");
    }

    public Network save(Network networkToSave) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement("insert into networks.network (name, description) values (?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, networkToSave.getName());
                statement.setString(2, networkToSave.getDescription());
                statement.execute();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                networkToSave.setId(generatedKeys.getLong("id"));
                networkToSave.setCreatedAt(generatedKeys.getTimestamp("created_at"));
                return networkToSave;
            }
        }
    }

    public List<Network> getAllNetworks() throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("Select * from networks.network");
                List<Network> networks = new ArrayList<>();

                while (resultSet.next()) {
                    networks.add(parseTo(resultSet, new Network()));
                }

                return networks;
            }
        }
    }

    public Network parseTo(ResultSet resultSet, Network network) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Date createdAt = resultSet.getTimestamp("created_at");

        network.setId(id);
        network.setName(name);
        network.setDescription(description);
        network.setCreatedAt(createdAt);
        return network;
    }

    public List<DeviceConnection> getAllConnections() throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("Select * from networks.connection");
                List<DeviceConnection> deviceConnections = new ArrayList<>();

                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String type = resultSet.getString("type");
                    String status = resultSet.getString("status");
                    long deviceFromId = resultSet.getLong("device_from_id");
                    long deviceToId = resultSet.getLong("device_to_id");
                    Date createdAt = resultSet.getTimestamp("created_at");
                    deviceConnections.add(new DeviceConnection(id, deviceFromId, deviceToId, type, status, createdAt));
                }

                return deviceConnections;
            }
        }
    }

    public void remove(DeviceConnection deviceConnection) throws SQLException {
        remove(deviceConnection, "connection");
    }

    public void remove(Network network) throws SQLException {
        remove(network, "network");
    }

    public void remove(Model model, String table) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement("delete from networks." + table + " where id = ?")) {
                statement.setLong(1, model.getId());
                statement.execute();
            }
        }
    }

    public List<Device> getDevicesOf(Network network) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement("select * from networks.device where network_id = ?")) {
                statement.setLong(1, network.getId());
                ResultSet resultSet = statement.executeQuery();
                return parseDevicesFrom(resultSet);
            }
        }
    }

    public List<Device> getAllDevices() throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("Select * from networks.device");
                List<Device> devices = new ArrayList<>();

                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String name = resultSet.getString("name");
                    String ipAddress = resultSet.getString("ip_address");
                    String macAddress = resultSet.getString("mac_address");
                    String type = resultSet.getString("type");
                    String status = resultSet.getString("status");
                    long networkId = resultSet.getLong("network_id");
                    Date createdAt = resultSet.getTimestamp("created_at");
                    devices.add(new Device(id, networkId, name, ipAddress, macAddress, type, status, createdAt));
                }

                return devices;
            }
        }
    }

    public List<Device> parseDevicesFrom(ResultSet resultSet) throws SQLException {
        List<Device> devices = new ArrayList<>();

        while (resultSet.next()) {
            long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            String ipAddress = resultSet.getString("ip_address");
            String macAddress = resultSet.getString("mac_address");
            String type = resultSet.getString("type");
            String status = resultSet.getString("status");
            long networkId = resultSet.getLong("network_id");
            Date createdAt = resultSet.getTimestamp("created_at");
            devices.add(new Device(id, networkId, name, ipAddress, macAddress, type, status, createdAt));
        }

        return devices;
    }

    public Device save(Device deviceToSave) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement("insert into networks.device (name, ip_address,mac_address,type,status, network_id) values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, deviceToSave.getName());
                statement.setString(2, deviceToSave.getIpAddress());
                statement.setString(3, deviceToSave.getMacAddress());
                statement.setString(4, deviceToSave.getType());
                statement.setString(5, deviceToSave.getStatus());
                statement.setLong(6, deviceToSave.getNetworkId());
                statement.execute();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                deviceToSave.setId(generatedKeys.getLong("id"));
                deviceToSave.setCreatedAt(generatedKeys.getTimestamp("created_at"));
                return deviceToSave;
            }
        }
    }

    public DeviceConnection save(DeviceConnection connectionToSave) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement("insert into networks.connection (type,status, device_from_id, device_to_id) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, connectionToSave.getType());
                statement.setString(2, connectionToSave.getStatus());
                statement.setLong(3, connectionToSave.getDeviceFromId());
                statement.setLong(4, connectionToSave.getDeviceToId());
                statement.execute();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                connectionToSave.setId(generatedKeys.getLong("id"));
                connectionToSave.setCreatedAt(generatedKeys.getTimestamp("created_at"));
                return connectionToSave;
            }
        }
    }

    public Network update(Network networkToUpdate) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement("update networks.network set name=?,description=? where id = ? returning id, name, description, created_at")) {
                statement.setString(1, networkToUpdate.getName());
                statement.setString(2, networkToUpdate.getDescription());
                statement.setLong(3, networkToUpdate.getId());
                statement.execute();

                ResultSet resultSet = statement.getResultSet();
                resultSet.next();
                return parseTo(resultSet, networkToUpdate);
            }
        }
    }

    public List<Device> findDevicesByStatus(String status) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement(
                    "SELECT * FROM networks.device WHERE status = ?")) {
                statement.setString(1, status);
                return parseDevicesFrom(statement.executeQuery());
            }
        }
    }
    public Map<Device, List<DeviceConnection>> getDevicesWithConnections(Network network) throws SQLException {
        Map<Device, List<DeviceConnection>> result = new HashMap<>();

        List<Device> devices = getDevicesOf(network);

        List<DeviceConnection> allConnections = getAllConnections();

        for (Device device : devices) {
            List<DeviceConnection> deviceConnections = allConnections.stream()
                    .filter(connection -> connection.getDeviceFromId() == device.getId() ||
                            connection.getDeviceToId() == device.getId())
                    .collect(Collectors.toList());

            result.put(device, deviceConnections);
        }

        return result;
    }
    public Map<Network, List<Device>> getAllNetworksWithDevices() throws SQLException {
        Map<Network, List<Device>> result = new HashMap<>();

        List<Network> networks = getAllNetworks();
        for (Network network : networks) {
            List<Device> devices = getDevicesOf(network);
            result.put(network, devices);
        }

        return result;
    }

    public List<NetworkReport> generateNetworkReports() throws SQLException {
        List<NetworkReport> reports = new ArrayList<>();
        List<Network> networks = getAllNetworks();

        for (Network network : networks) {
            List<Device> devices = getDevicesOf(network);

            int activeDevices = (int) devices.stream()
                    .filter(d -> "ACTIVE".equals(d.getStatus()))
                    .count();

            Map<String, Integer> devicesByType = devices.stream()
                    .collect(Collectors.groupingBy(
                            Device::getType,
                            Collectors.summingInt(e -> 1)
                    ));

            reports.add(new NetworkReport(
                    network,
                    devices.size(),
                    activeDevices,
                    devices.size() - activeDevices,
                    devicesByType
            ));
        }

        return reports;
    }
}
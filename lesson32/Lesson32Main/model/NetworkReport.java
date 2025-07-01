package model;

import java.util.Map;

public class NetworkReport {
    private final Network network;
    private final int totalDevices;
    private final int activeDevices;
    private final int closedDevices;
    private final Map<String, Integer> devicesByType;

    public NetworkReport(Network network, int totalDevices,
                         int activeDevices, int closedDevices,
                         Map<String, Integer> devicesByType) {
        this.network = network;
        this.totalDevices = totalDevices;
        this.activeDevices = activeDevices;
        this.closedDevices = closedDevices;
        this.devicesByType = devicesByType;
    }

    public Network getNetwork() {
        return network;
    }

    public int getTotalDevices() {
        return totalDevices;
    }

    public int getActiveDevices() {
        return activeDevices;
    }

    public int getClosedDevices() {
        return closedDevices;
    }

    public Map<String, Integer> getDevicesByType() {
        return devicesByType;
    }
}

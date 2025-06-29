package service;

import java.util.Arrays;
import java.util.Optional;

public enum UserAction {
    ADD_NETWORK(1, "to add new network"),
    ADD_DEVICE(2, "to add new device"),
    ADD_CONNECTION(3, "to add new connection"),
    REMOVE_NETWORK(4, "to remove any network"),
    REMOVE_DEVICE(5, "to remove any device"),
    REMOVE_CONNECTION(6, "to remove any connection"),
    EDIT_NETWORK(7, "to edit any connection"),
    FIND_DEVICES_BY_STATUS(8, "to find devices by status"),
    SHOW_NETWORK_DEVICES_AND_CONNECTIONS(9, "to show all devices and connections"),
    SHOW_ALL_NETWORKS_WITH_DEVICES(10, "to show all networks with their devices"),
    GENERATE_REPORTS(11, "to generate network reports"),
    EXIT(12, "to exit");

    private final int code;
    private final String description;

    UserAction(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Optional<UserAction> valueOf(int code) {
        return Arrays.stream(values())
                .filter(action -> action.getCode() == code)
                .findAny();
    }
}

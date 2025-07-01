package ui;

import java.util.regex.Pattern;

public class IpValidator {
    private static final String IPV4_PATTERN =
            "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}" +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final Pattern pattern = Pattern.compile(IPV4_PATTERN);

    public static boolean isValidIpv4(String ip) {
        if (ip == null || ip.isEmpty()) {
            return false;
        }
        return pattern.matcher(ip).matches();
    }
}
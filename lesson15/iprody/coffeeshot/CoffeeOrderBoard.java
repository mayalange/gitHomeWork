import java.util.LinkedHashMap;
import java.util.Map;

public class CoffeeOrderBoard {
    Map<Long, Order> orders = new LinkedHashMap();
    long orderId = 1;

    public void add(String userName) {
        orders.put(orderId, new Order(orderId, userName));
        orderId++;
    }

    public Order deliver() {

        Long firstOrderNumber = orders.keySet().iterator().next();

        return orders.remove(firstOrderNumber);
    }

    public Order deliver(long orderId) {

        return orders.remove(orderId);
    }

    public void draw() {
        System.out.println("Coffee Order Board:\n" + toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Order order : orders.values()) {
            sb.append("orderId=").append(order.getOrderId())
                    .append(", userName='").append(order.getUserName()).append("'\n");
        }
        return sb.toString();
    }
}
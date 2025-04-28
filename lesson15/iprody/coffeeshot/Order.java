public class Order {
    private long orderId;
    private String userName;

    public Order(long orderId, String userName) {
        this.orderId = orderId;
        this.userName = userName;
    }

    public long getOrderId() {
        return orderId;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public String toString() {
        return "orderId=" + orderId + ", userName='" + userName + "\'";
    }
}
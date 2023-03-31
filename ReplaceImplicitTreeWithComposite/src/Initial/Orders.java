package Initial;

import java.util.ArrayList;

public class Orders {
    private ArrayList<Order> orders;

    public Orders() {
        this.orders = new ArrayList<>();
    }

    public void add(Order order) {
        this.orders.add(order);
    }

    public int orderCount() {
        return this.orders.size();
    }

    public Order order(int insertionIndex) {
        return this.orders.get(insertionIndex);
    }
}

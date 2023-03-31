package Initial;

import MyWork.Order;

import java.util.ArrayList;

public class Orders {
    private ArrayList<MyWork.Order> orders;

    public Orders() {
        this.orders = new ArrayList<>();
    }

    public void add(MyWork.Order order) {
        this.orders.add(order);
    }

    public int orderCount() {
        return this.orders.size();
    }

    public Order order(int insertionIndex) {
        return this.orders.get(insertionIndex);
    }
}

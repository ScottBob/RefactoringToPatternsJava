package MyWork;

import java.util.ArrayList;

public class Order {
    private int id;
    private ArrayList<Product> products;

    public Order(int id) {
        this.products = new ArrayList<>();
        this.id = id;
    }

    public int orderId() {
        return this.id;
    }

    public void add(Product product) {
        this.products.add(product);
    }

    public int productCount() {
        return this.products.size();
    }

    public Product Product(int insertionIndex) {
        return this.products.get(insertionIndex);
    }
}

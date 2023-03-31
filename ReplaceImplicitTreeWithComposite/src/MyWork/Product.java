package MyWork;

public class Product {
    private int id;
    private String name;
    private ProductSize size;
    private String price;

    public Product(int id, String name, ProductSize size, String price)
    {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductSize getSize() {
        return size;
    }

    public String getPrice() {
        return price;
    }
}

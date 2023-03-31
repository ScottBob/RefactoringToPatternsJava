package Initial;

import MyWork.Order;
import MyWork.Orders;
import MyWork.OrdersWriter;
import MyWork.Product;
import MyWork.ProductSize;
import MyWork.TagNode;
import org.junit.Assert;
import org.junit.Test;

public class OrderTests {
    @Test
    public void test_products_render_properly_with_order_writer() {
        MyWork.Product orange = new MyWork.Product(1, "Orange", MyWork.ProductSize.Medium, ".80");
        MyWork.Product apple = new MyWork.Product(2, "Apple", MyWork.ProductSize.Large, ".30");
        MyWork.Product pear = new MyWork.Product(3, "Pear", MyWork.ProductSize.Small, ".95");
        MyWork.Product iPhone = new MyWork.Product(4, "iPhone", MyWork.ProductSize.ExtraLarge, "1049.99");
        MyWork.Product macBook = new Product(5, "MacBook Air", ProductSize.NotApplicable, "2499.99");
        MyWork.Order fruit = new MyWork.Order(1);
        fruit.add(orange);
        fruit.add(apple);
        fruit.add(pear);
        MyWork.Order tech = new Order(2);
        tech.add(iPhone);
        tech.add(macBook);
        MyWork.Orders orders = new Orders();
        orders.add(fruit);
        orders.add(tech);
        MyWork.OrdersWriter ordersWriter = new OrdersWriter(orders);
        String expected = "<orders><order id='1'><product id='1' color='red' size='medium'><price currency='USD'>.80</price>Orange</product><product id='2' color='red' size='NOT APPLICABLE'><price currency='USD'>.30</price>Apple</product><product id='3' color='red' size='NOT APPLICABLE'><price currency='USD'>.95</price>Pear</product></order><order id='2'><product id='4' color='red' size='NOT APPLICABLE'><price currency='USD'>1049.99</price>iPhone</product><product id='5' color='red'><price currency='USD'>2499.99</price>MacBook Air</product></order></orders>";
        Assert.assertEquals("price XML", expected, ordersWriter.getContents());
    }
}

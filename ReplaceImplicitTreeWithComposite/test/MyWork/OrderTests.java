package MyWork;

import org.junit.Assert;
import org.junit.Test;

public class OrderTests {
    @Test
    public void test_products_render_properly_with_order_writer() {
        Product orange = new Product(1, "Orange", ProductSize.Medium, ".80");
        Product apple = new Product(2, "Apple", ProductSize.Large, ".30");
        Product pear = new Product(3, "Pear", ProductSize.Small, ".95");
        Product iPhone = new Product(4, "iPhone", ProductSize.ExtraLarge, "1049.99");
        Product macBook = new Product(5, "MacBook Air", ProductSize.NotApplicable, "2499.99");
        Order fruit = new Order(1);
        fruit.add(orange);
        fruit.add(apple);
        fruit.add(pear);
        Order tech = new Order(2);
        tech.add(iPhone);
        tech.add(macBook);
        Orders orders = new Orders();
        orders.add(fruit);
        orders.add(tech);
        OrdersWriter ordersWriter = new OrdersWriter(orders);
        String expected = "<orders><order id='1'><product id='1' color='red' size='medium'>Orange<price currency='USD'>.80</price></product><product id='2' color='red' size='NOT APPLICABLE'>Apple<price currency='USD'>.30</price></product><product id='3' color='red' size='NOT APPLICABLE'>Pear<price currency='USD'>.95</price></product></order><order id='2'><product id='4' color='red' size='NOT APPLICABLE'>iPhone<price currency='USD'>1049.99</price></product><product id='5' color='red'>MacBook Air<price currency='USD'>2499.99</price></product></order></orders>";
//        String expected = "<orders><order id='1'><product id='1' color='red' size='medium'><price currency='USD'>.80</price>Orange</product><product id='2' color='red' size='NOT APPLICABLE'><price currency='USD'>.30</price>Apple</product><product id='3' color='red' size='NOT APPLICABLE'><price currency='USD'>.95</price>Pear</product></order><order id='2'><product id='4' color='red' size='NOT APPLICABLE'><price currency='USD'>1049.99</price>iPhone</product><product id='5' color='red'><price currency='USD'>2499.99</price>MacBook Air</product></order></orders>";
        Assert.assertEquals("price XML", expected, ordersWriter.getContents());
    }
    @Test
    public void test_products_render_properly_with_order_writer_and_tag_builder() {
        Product orange = new Product(1, "Orange", ProductSize.Medium, ".80");
        Product apple = new Product(2, "Apple", ProductSize.Large, ".30");
        Product pear = new Product(3, "Pear", ProductSize.Small, ".95");
        Product iPhone = new Product(4, "iPhone", ProductSize.ExtraLarge, "1049.99");
        Product macBook = new Product(5, "MacBook Air", ProductSize.NotApplicable, "2499.99");
        Order fruit = new Order(1);
        fruit.add(orange);
        fruit.add(apple);
        fruit.add(pear);
        Order tech = new Order(2);
        tech.add(iPhone);
        tech.add(macBook);
        Orders orders = new Orders();
        orders.add(fruit);
        orders.add(tech);
        OrdersWriter ordersWriter = new OrdersWriter(orders);
        String expected = "<orders><order id='1'><product id='1' color='red' size='medium'>Orange<price currency='USD'>.80</price></product><product id='2' color='red' size='NOT APPLICABLE'>Apple<price currency='USD'>.30</price></product><product id='3' color='red' size='NOT APPLICABLE'>Pear<price currency='USD'>.95</price></product></order><order id='2'><product id='4' color='red' size='NOT APPLICABLE'>iPhone<price currency='USD'>1049.99</price></product><product id='5' color='red'>MacBook Air<price currency='USD'>2499.99</price></product></order></orders>";
//                "<orders><order id='1'><product id='1' color='red' size='medium'><price currency='USD'>.80</price>Orange</product><product id='2' color='red' size='NOT APPLICABLE'><price currency='USD'>.30</price>Apple</product><product id='3' color='red' size='NOT APPLICABLE'><price currency='USD'>.95</price>Pear</product></order><order id='2'><product id='4' color='red' size='NOT APPLICABLE'><price currency='USD'>1049.99</price>iPhone</product><product id='5' color='red'><price currency='USD'>2499.99</price>MacBook Air</product></order></orders>";
        Assert.assertEquals("price XML", expected, ordersWriter.getTagContents());
    }
}

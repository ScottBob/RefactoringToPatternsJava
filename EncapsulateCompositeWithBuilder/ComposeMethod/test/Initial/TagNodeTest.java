package Initial;

import junit.framework.TestCase;

public class TagNodeTest extends TestCase {

    public void testTagNode() {
        TagNode orders = new TagNode("orders");
        TagNode order = new TagNode("order");
            order.addAttribute("number", "123");
            orders.add(order);
                TagNode item = new TagNode("item");
                item.addAttribute("number", "x1786");
                item.addValue("carDoor");
                order.add(item);
        String xml = orders.toString();
        assertEquals("<orders><order number='123'><item number='x1786'>carDoor</item></order></orders>", xml);
    }
}
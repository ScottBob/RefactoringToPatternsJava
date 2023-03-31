package MyWork;

import org.junit.Assert;
import org.junit.Test;

public class TagTests {
    public static final String SAMPLE_PRICE = "8.95";
    @Test
    public void test_simple_tag_with_one_attribute_and_value() {
        TagNode priceTag = new TagNode("price");
        priceTag.addAttribute("currency", "USD");
        priceTag.addValue(SAMPLE_PRICE);
        String expected = "<price currency=" + "'" + "USD" + "'>" + SAMPLE_PRICE + "</price>";
        Assert.assertEquals("price XML", expected, priceTag.toString());
    }

    @Test
    public void test_composite_tag_one_child() {
        TagNode productTag = new TagNode("product");
        productTag.addValue(new TagNode("price"));
        String expected = "<product>" + "<price>" + "</price>" + "</product>";
        Assert.assertEquals("price XML", expected, productTag.toString());
    }
}

package MyWork;

import org.junit.Assert;
import org.junit.Test;

public class TagBuilderTest {
    @Test
    public void test_build_one_node() {
        String expected = "<flavors/>";
        String actual = new TagBuilder("flavors").toXml();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_build_one_child() {
        String expected = "<flavors>" +
                             "<flavor/>" +
                          "</flavors>";
        TagBuilder builder = new TagBuilder("flavors");
        builder.addChild("flavor");
        String actual = builder.toXml();
        Assert.assertEquals(expected, actual);
    }
}

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
}

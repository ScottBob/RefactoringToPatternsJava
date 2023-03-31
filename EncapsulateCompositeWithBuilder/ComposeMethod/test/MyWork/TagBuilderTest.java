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

    @Test
    public void test_build_children_of_children() {
        String expected = "<flavors>" +
                "<flavor>" +
                "<requirements>" +
                "<requirement/>" +
                "</requirements>" +
                "</flavor>" +
                "</flavors>";
        TagBuilder builder = new TagBuilder("flavors");
        builder.addChild("flavor");
        builder.addChild("requirements");
        builder.addChild("requirement");
        String actual = builder.toXml();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_parents() {
        TagNode root = new TagNode("root");
        Assert.assertNull(root.getParent());

        TagNode childNode = new TagNode("child");
        root.add(childNode);
        Assert.assertEquals(root, childNode.getParent());
        Assert.assertEquals("root", childNode.getParent().getName());
    }

    @Test
    public void test_build_sibling() {
        String expected = "<flavors>" +
                "<flavor1/>" +
                "<flavor2/>" +
                "</flavors>";
        TagBuilder builder = new TagBuilder("flavors");
        builder.addChild("flavor1");
        builder.addSibling("flavor2");
        String actual = builder.toXml();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_repeating_children_and_grandchildren() {
        String expected = "<flavors>" +
                "<flavor>" +
                "<requirements>" +
                "<requirement/>" +
                "</requirements>" +
                "</flavor>" +
                "<flavor>" +
                "<requirements>" +
                "<requirement/>" +
                "</requirements>" +
                "</flavor>" +
                "</flavors>";
        TagBuilder builder = new TagBuilder("flavors");
        for (int i = 0; i < 2; ++i) {
            builder.addToParent("flavors", "flavor");
            builder.addChild("requirements");
            builder.addChild("requirement");
        }
        String actual = builder.toXml();
        Assert.assertEquals(expected, actual);
    }
}

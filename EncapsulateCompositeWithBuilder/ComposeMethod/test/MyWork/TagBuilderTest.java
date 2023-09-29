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

    @Test
    public void test_parent_name_not_found() {
        TagBuilder builder = new TagBuilder("flavors");
        try {
            for (int i = 0; i < 2; ++i) {
                builder.addToParent("favors", "flavor"); // Misspelled "flavors"
                builder.addChild("requirements");
                builder.addChild("requirement");
            }
            Assert.fail("Should not allow adding to parent that doesn't exist");
        } catch (RuntimeException runtimeException) {
            String expectedErrorMessage = "missing parent tag: favors";
            Assert.assertEquals(expectedErrorMessage, runtimeException.getMessage());
        }
    }

    @Test
    public void test_attributes_and_values() {
        String expected = "<flavor name='Test-Driven Development'>" +
                "<requirements>" +
                "<requirement type='hardware'>" +
                "1 computer for every 2 participants" +
                "</requirement>" +
                "<requirement type='software'>" +
                "IDE" +
                "</requirement>" +
                "</requirements>" +
                "</flavor>";
        TagBuilder builder = new TagBuilder("flavor");
        builder.addAttribute("name", "Test-Driven Development");
        builder.addChild("requirements");
        builder.addToParent("requirements","requirement");
        builder.addAttribute("type", "hardware");
        builder.addValue("1 computer for every 2 participants");
        builder.addToParent("requirements","requirement");
        builder.addAttribute("type", "software");
        builder.addValue("IDE");
        Assert.assertEquals(expected, builder.toXml());
    }

    @Test
    public void test_build_one_json_node() {
        String expected = "{\n" +
                "    \"flavors\": \"\"\n" +
                "}\n";
        String actual = new TagBuilder("flavors").toJson();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_build_one_json_child() {
        String expected = "{\n    \"flavors\": {\n" +
                "        \"flavor\": \"\"\n" +
                "    }\n}\n";
        TagBuilder builder = new TagBuilder("flavors");
        builder.addChild("flavor");
        String actual = builder.toJson();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_build_json_children_of_children() {
        String expected = "{\n    \"flavors\": {\n" +
                "        \"flavor\": {\n" +
                "            \"requirements\": {\n" +
                "                \"requirement\": \"\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n}\n";
        TagBuilder builder = new TagBuilder("flavors");
        builder.addChild("flavor");
        builder.addChild("requirements");
        builder.addChild("requirement");
        String actual = builder.toJson();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_json_build_sibling() {
        String expected = "{\n    \"flavors\": {\n" +
                "        \"flavor1\": \"\",\n" +
                "        \"flavor2\": \"\"\n" +
                "    }\n}\n";
        TagBuilder builder = new TagBuilder("flavors");
        builder.addChild("flavor1");
        builder.addSibling("flavor2");
        String actual = builder.toJson();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_repeating_json_children_and_grandchildren_introducing_a_json_array() {
        String expected = "{\n" +
                "    \"flavors\": {\n" +
                "        \"flavor\": [\n" +
                "            {\n" +
                "                \"requirements\": {\n" +
                "                    \"requirement\": \"\"\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"requirements\": {\n" +
                "                    \"requirement\": \"\"\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}\n";
        TagBuilder builder = new TagBuilder("flavors");
        for (int i = 0; i < 2; ++i) {
            builder.addToParent("flavors", "flavor");
            builder.addChild("requirements");
            builder.addChild("requirement");
        }
        String actual = builder.toJson();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void test_json_attributes_and_values() {
        String expected = "{\n" +
                "    \"flavor\": {\n" +
                "        \"requirements\": {\n" +
                "            \"requirement\": [\n" +
                "                {\n" +
                "                    \"_type\": \"hardware\",\n" +
                "                    \"__text\": \"1 computer for every 2 participants\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"_type\": \"software\",\n" +
                "                    \"__text\": \"IDE\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        \"_name\": \"Test-Driven Development\"\n" +
                "    }\n" +
                "}\n";
        TagBuilder builder = new TagBuilder("flavor");
        builder.addAttribute("name", "Test-Driven Development");
        builder.addChild("requirements");
        builder.addToParent("requirements","requirement");
        builder.addAttribute("type", "hardware");
        builder.addValue("1 computer for every 2 participants");
        builder.addToParent("requirements","requirement");
        builder.addAttribute("type", "software");
        builder.addValue("IDE");
        Assert.assertEquals(expected, builder.toJson());
    }
}

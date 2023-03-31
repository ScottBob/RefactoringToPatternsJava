package Initial;

import java.util.ArrayList;
import java.util.List;

public class TagNode {
    private StringBuilder attributes;
    private List<TagNode> children = new ArrayList<>();
    private String name;
    private String value = "";

    public TagNode(String name) {
        this.name = name;
        this.attributes = new StringBuilder();
    }

    public void add(TagNode tagNode) {
        this.children.add(tagNode);
    }

    public void addAttribute(String attribute, String value) {
        this.attributes.append(" ");
        this.attributes.append(attribute);
        this.attributes.append("='");
        this.attributes.append(value);
        this.attributes.append("'");
    }

    public void addValue(String value) {
        this.value = value;
    }

    public String toString() {
        String result = "<" + this.name + this.attributes + ">";

        for (TagNode tagNode : this.children) {
            result += tagNode.toString();
        }

        result += this.value + "</" + this.name + ">";
        return result;
    }
}

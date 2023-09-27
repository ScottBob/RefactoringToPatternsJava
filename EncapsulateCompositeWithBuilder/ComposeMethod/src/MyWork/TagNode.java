package MyWork;

import java.util.ArrayList;
import java.util.List;

public class TagNode {
    private TagNode parent;
    private StringBuilder attributes;
    private List<TagNode> children = new ArrayList<>();
    private String name;
    private String value = "";

    public TagNode(String name) {
        this.name = name;
        this.attributes = new StringBuilder();
    }

    private void setParent(TagNode parent) {
        this.parent = parent;
    }

    public TagNode getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    public void add(TagNode childNode) {
        childNode.setParent(this);
        this.children.add(childNode);
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
        String result;
        if (this.children.isEmpty()) {
            if (this.value != null && !this.value.isEmpty()) {
                result =  "<" + this.name + this.attributes + ">";
                result += this.value + "</" + this.name + ">";
            } else {
                result = "<" + this.name + this.attributes + "/>";
            }
        } else {
            result = "<" + this.name + this.attributes + ">" + this.value;

            for (TagNode tagNode : this.children) {
                result += tagNode.toString();
            }

            result += "</" + this.name + ">";
        }
        return result;
    }

    public String toJson() {
        return toJson(0);
    }

    public String toJson(int indentLevel) {
        String result;
        String indent = new String(new char[indentLevel * 4]).replace("\0", " ");
        final String OPEN = "{\n";
        final String CLOSE = "}\n";

        if (this.children.isEmpty()) {
            result = OPEN + indent + identifier(this.name, "") + indent + CLOSE;
        } else {
            result = OPEN + indent + identifier(this.name);
            for (TagNode tagNode : this.children) {
                result += tagNode.toJson(indentLevel + 1);
            }
            result += indent + CLOSE;
        }
        return result;
    }

    private String identifier(String name) {
        return "    \"" + name + "\": ";
    }
    private String identifier(String name, String value) {
        return "    \"" + name + "\": \"" + value + "\"\n";
    }
}

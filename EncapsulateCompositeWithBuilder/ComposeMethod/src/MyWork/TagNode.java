package MyWork;

import java.util.ArrayList;
import java.util.List;

public class TagNode {
    private final String OPEN = "{\n";
    private final String CLOSE = "}\n";
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
            if (!this.value.isEmpty()) {
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
        return OPEN + toJson(1) + "\n" + CLOSE;
    }

    private String toJson(int indentLevel) {
        String indent = getIndent(indentLevel);
        StringBuilder line = new StringBuilder();
        line.append(indent);
        line.append("\"").append(name).append("\": ");
        if (!children.isEmpty()) {
            List<String> members = new ArrayList<>();
            line.append("{\n");
            for (TagNode child : children) {
                members.add(child.toJson(indentLevel + 1));
            }
            line.append(String.join(",\n", members));
            line.append("\n").append(indent).append("}");
        } else {
            line.append("\"\"");
        }
        return line.toString();
    }

    private static String getIndent(int indentLevel) {
        String format = "%" + indentLevel * 4 + "s";
        String indent = indentLevel == 0 ? "" : String.format(format, "");
        return indent;
    }

}

package MyWork;

import java.util.*;
import java.util.List;

public class TagNode {
    private final String OPEN = "{\n";
    private final String CLOSE = "}\n";
    private TagNode parent;
    private Map<String, String> attributes;
    private List<TagNode> children = new ArrayList<>();
    private String name;
    private String value = "";

    public TagNode(String name) {
        this.name = name;
        this.attributes = new LinkedHashMap<>();
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
        attributes.put(attribute, value);
    }

    public String attributesInXmlFormat() {
        ArrayList<String> lines = new ArrayList<>();
        for (String key : attributes.keySet()) {
            StringBuilder line = new StringBuilder();
            line.append(" ");
            line.append(key);
            line.append("='");
            line.append(attributes.get(key));
            line.append("'");
            lines.add(line.toString());
        }
        return String.join(", ", lines);
    }

    public void addValue(String value) {
        this.value = value;
    }

    public String toString() {
        String result;
        if (this.children.isEmpty()) {
            if (!this.value.isEmpty()) {
                result =  "<" + this.name + attributesInXmlFormat() + ">";
                result += this.value + "</" + this.name + ">";
            } else {
                result = "<" + this.name + attributesInXmlFormat() + "/>";
            }
        } else {
            result = "<" + this.name + attributesInXmlFormat() + ">" + this.value;

            for (TagNode tagNode : this.children) {
                result += tagNode.toString();
            }

            result += "</" + this.name + ">";
        }
        return result;
    }

    public String toJson() {
        return OPEN + toJson(1, false) + "\n" + CLOSE;
    }

    private String toJson(int indentLevel, boolean isArrayChild) {
        String indent = getIndent(indentLevel);
        StringBuilder line = new StringBuilder();
        line.append(indent);
        if (!isArrayChild) {
            line.append("\"").append(name).append("\": ");
        }
        if (!children.isEmpty()) {
            List<String> members = new ArrayList<>();
            line.append(OPEN);
            if (isChildArray()) {
                line.append(indent).append("    \"").append(children.get(0).name).append("\": [\n");
                for (TagNode child : children) {
                    members.add(child.toJson(indentLevel + 2, true));
                }
                line.append(String.join(",\n", members));
                line.append("\n").append(indent).append("    ]");
            } else {
                for (TagNode child : children) {
                    members.add(child.toJson(indentLevel + 1, false));
                }
                line.append(String.join(",\n", members));
            }
            line.append(getJsonAttributesAndValues(indentLevel));
            line.append("\n").append(indent).append("}");
        } else {
            if (attributes.isEmpty()) {
                line.append("\"\"");
            } else {
                line.append("{");
                line.append(getJsonAttributesAndValues(indentLevel));
                line.append("\n").append(getIndent(indentLevel)).append("}");
            }
        }
        return line.toString();
    }

    private String getJsonAttributesAndValues(int indentLevel) {
        StringBuilder line = new StringBuilder();
        String indent;
        // The value is always last and is named "__text"
        //  otherwise it's treated just like any attribute
        if (!value.isEmpty()) {
            attributes.put("_text", value);
        }
        if (!attributes.isEmpty()) {
            indent = getIndent(indentLevel + 1);
            if (!children.isEmpty()) {
                line.append(",");
            }
            List<String> members = new ArrayList<>();
            for (String key : attributes.keySet()) {
                StringBuffer attLine = new StringBuffer();
                attLine.append("\n").append(indent);
                attLine.append("\"_").append(key).append("\": ");
                attLine.append("\"").append(attributes.get(key)).append("\"");
                members.add(attLine.toString());
            }
            line.append(String.join(",", members));
        }
        return line.toString();
    }

    private static String getIndent(int indentLevel) {
        String format = "%" + indentLevel * 4 + "s";
        return (indentLevel == 0) ? "" : String.format(format, "");
    }

    private boolean isChildArray() {
        String lastName = "";
        for (TagNode node : children) {
            if (lastName.isEmpty()) {
                lastName = node.name;
                continue;
            }
            if (lastName.equals(node.name)) {
                return true;
            }
        }
        return false;
    }
}

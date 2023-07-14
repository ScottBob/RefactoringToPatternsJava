package Initial;

public class XMLBuilder extends OutputBuilder {
    private String xmlString;
    public XMLBuilder() {

    }
    public XMLBuilder(String workshops) {
        xmlString += workshops;
    }

    public void addBelowParent(String workshop) {
        xmlString += workshop;
    }

    public void addAttribute(String id, String value) {
        xmlString += "," + id + "=" + value;
    }

    @Override
    public String toString() {
        return "XMLBuilder{" +
                "xmlString='" + xmlString + '\'' +
                '}';
    }
}

package MyWork;

public class HandlerResponse {
    StringBuffer xml;
    String stylesheet;
    public HandlerResponse(StringBuffer stringBuffer, String stylesheet) {
        xml = stringBuffer;
        this.stylesheet = stylesheet;
    }

    @Override
    public String toString() {
        return "HandlerResponse{" +
                "xml=" + xml +
                ", stylesheet='" + stylesheet + '\'' +
                '}';
    }
}

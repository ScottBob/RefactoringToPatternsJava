package MyWork;

import java.util.Iterator;

public class AllWorkshopsHandler {
    private CatalogApp catalogApp;
    public static final String ALL_WORKSHOPS_STYLESHEET = "All workshops stylesheet";
    private PrettyPrinter prettyPrinter = new PrettyPrinter();

    public AllWorkshopsHandler(CatalogApp catalogApp) {
        this.catalogApp = catalogApp;
    }

    public HandlerResponse execute() {
        return new HandlerResponse(
                new StringBuffer(prettyPrint(allWorkshopsData())),
                ALL_WORKSHOPS_STYLESHEET);
    }

    private String prettyPrint(String buffer) {
        return prettyPrinter.format(buffer);
    }

    private String allWorkshopsData() {
        XMLBuilder allWorkshopsXml = new XMLBuilder("workshops");
        WorkshopRepository repository =
                catalogApp.getWorkshopManager().getWorkshopRepository();
        Iterator ids = repository.keyIterator();
        while (ids.hasNext()) {
            String id = (String)ids.next();
            Workshop workshop = repository.getWorkshop(id);
            allWorkshopsXml.addBelowParent("workshop");
            allWorkshopsXml.addAttribute("id", workshop.getID());
            allWorkshopsXml.addAttribute("name", workshop.getName());
            allWorkshopsXml.addAttribute("status", workshop.getStatus());
            allWorkshopsXml.addAttribute("duration",
                    workshop.getDurationAsString());
        }
        return allWorkshopsXml.toString();
    }
}

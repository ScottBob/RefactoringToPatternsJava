package MyWork;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CatalogApp {
    public static final String NEW_WORKSHOP = "New workshop";
    public static final String ALL_WORKSHOPS = "All workshops";
    public static final String ALL_WORKSHOPS_STYLESHEET = "All workshops stylesheet";
    WorkshopManager workshopManager = new WorkshopManager(new WorkshopRepository());

    private HandlerResponse executeActionAndGetResponse(String actionName, Map parameters) {
        if (actionName.equals(NEW_WORKSHOP)) {
            String nextWorkshopID = workshopManager.getNextWorkshopID();
            StringBuffer newWorkshopContents =
                    workshopManager.createNewFileFromTemplate(
                            nextWorkshopID,
                            workshopManager.getWorkshopDir(),
                            workshopManager.getWorkshopTemplate()
                    );
            workshopManager.addWorkshop(newWorkshopContents);
            parameters.put("id",nextWorkshopID);
            return executeActionAndGetResponse(ALL_WORKSHOPS, parameters);
        } else if (actionName.equals(ALL_WORKSHOPS)) {
            XMLBuilder allWorkshopsXml = new XMLBuilder("workshops");
            WorkshopRepository repository =
                    workshopManager.getWorkshopRepository();
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
            String formattedXml = getFormattedData(allWorkshopsXml.toString());
            return new HandlerResponse(
                    new StringBuffer(formattedXml),
                    ALL_WORKSHOPS_STYLESHEET
            );
        } // ...many more "else if" statements
        return new HandlerResponse(new StringBuffer(workshopManager.toString()), "General Style");
    }

    private String getFormattedData(String xmlString) {
        return xmlString;
    }

    public HandlerResponse runTheExecuteActionAndGetResponseFunctionForTesting(String actionName) {
        HashMap<String, String> params = new HashMap<>();
        return executeActionAndGetResponse(actionName, params);
    }
}

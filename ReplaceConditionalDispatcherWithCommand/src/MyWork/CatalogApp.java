package MyWork;

import java.util.HashMap;
import java.util.Map;

public class CatalogApp {
    public static final String NEW_WORKSHOP = "New workshop";
    public static final String ALL_WORKSHOPS = "All workshops";
    WorkshopManager workshopManager = new WorkshopManager(new WorkshopRepository());

    HandlerResponse executeActionAndGetResponse(String actionName, Map parameters) {
        if (actionName.equals(NEW_WORKSHOP)) {
            return new NewWorkshopHandler(this).execute(parameters);
        } else if (actionName.equals(ALL_WORKSHOPS)) {
            return new AllWorkshopsHandler(this).execute(parameters);
        } // ...many more "else if" statements
        return new HandlerResponse(new StringBuffer(workshopManager.toString()), "General Style");
    }

    public HandlerResponse runTheExecuteActionAndGetResponseFunctionForTesting(String actionName) {
        HashMap<String, String> params = new HashMap<>();
        return executeActionAndGetResponse(actionName, params);
    }

    public WorkshopManager getWorkshopManager() {
        return workshopManager;
    }
}

package MyWork;

import java.util.HashMap;
import java.util.Map;

public class CatalogApp {
    public static final String NEW_WORKSHOP = "New workshop";
    public static final String ALL_WORKSHOPS = "All workshops";
    WorkshopManager workshopManager = new WorkshopManager(new WorkshopRepository());
    private Map<String, Handler> handlers;

    public CatalogApp() {
        createHandlers();
    }

    private void createHandlers() {
        handlers = new HashMap<>();
        handlers.put(NEW_WORKSHOP, new NewWorkshopHandler(this));
        handlers.put(ALL_WORKSHOPS, new AllWorkshopsHandler(this));
    }

    HandlerResponse executeActionAndGetResponse(String handlerName, Map parameters) {
        Handler handler = lookupHandlerBy(handlerName);
        return handler.execute(parameters);
    }

    private Handler lookupHandlerBy(String handlerName) {
        return handlers.get(handlerName);
    }

    public HandlerResponse runTheExecuteActionAndGetResponseFunctionForTesting(String actionName) {
        HashMap<String, String> params = new HashMap<>();
        return executeActionAndGetResponse(actionName, params);
    }

    public WorkshopManager getWorkshopManager() {
        return workshopManager;
    }
}

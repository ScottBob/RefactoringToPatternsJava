package MyWork;

import java.util.Map;

import static MyWork.CatalogApp.ALL_WORKSHOPS;

public class NewWorkshopHandler {
    private CatalogApp catalogApp;

    public NewWorkshopHandler(CatalogApp catalogApp) {
        this.catalogApp = catalogApp;
    }

    public HandlerResponse getNewWorkshopResponse(Map parameters) {
        String nextWorkshopID = workshopManager().getNextWorkshopID();
        StringBuffer newWorkshopContents =
                workshopManager().createNewFileFromTemplate(
                        nextWorkshopID,
                        workshopManager().getWorkshopDir(),
                        workshopManager().getWorkshopTemplate()
                );
        workshopManager().addWorkshop(newWorkshopContents);
        parameters.put("id",nextWorkshopID);
        return catalogApp.executeActionAndGetResponse(ALL_WORKSHOPS, parameters);
    }

    private WorkshopManager workshopManager() {
        return catalogApp.getWorkshopManager();
    }
}

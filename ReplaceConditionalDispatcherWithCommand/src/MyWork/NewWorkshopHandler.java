package MyWork;

import java.util.Map;

import static MyWork.CatalogApp.ALL_WORKSHOPS;

public class NewWorkshopHandler extends Handler {
    public NewWorkshopHandler(CatalogApp catalogApp) {
        super(catalogApp);
    }

    public HandlerResponse execute(Map parameters) {
        createNewWorkshop(parameters);
        return catalogApp.executeActionAndGetResponse(ALL_WORKSHOPS, parameters);
    }

    private void createNewWorkshop(Map parameters) {
        String nextWorkshopID = workshopManager().getNextWorkshopID();
        workshopManager().addWorkshop(newWorkshopContents(nextWorkshopID));
        parameters.put("id",nextWorkshopID);
    }

    private StringBuffer newWorkshopContents(String nextWorkshopID) {
        StringBuffer newWorkshopContents =
                workshopManager().createNewFileFromTemplate(
                        nextWorkshopID,
                        workshopManager().getWorkshopDir(),
                        workshopManager().getWorkshopTemplate()
                );
        return newWorkshopContents;
    }

    private WorkshopManager workshopManager() {
        return catalogApp.getWorkshopManager();
    }
}

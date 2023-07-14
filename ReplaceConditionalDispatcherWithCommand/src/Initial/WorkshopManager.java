package Initial;

public class WorkshopManager {
    private final WorkshopRepository workshopRepository;

    public WorkshopManager(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    public String getNextWorkshopID() {
        return "" + workshopRepository.size() + 1;
    }

    public String getWorkshopDir() {
        return "";
    }

    public String getWorkshopTemplate() {
        return "";
    }

    public StringBuffer createNewFileFromTemplate(String nextWorkshopID, String workshopDir, String workshopTemplate) {
        Workshop workshop = new Workshop(nextWorkshopID, workshopDir, workshopTemplate);
        return new StringBuffer(workshop.toString());
    }

    public void addWorkshop(StringBuffer newWorkshopContents) {
        Workshop workshop = new Workshop(getNextWorkshopID(), newWorkshopContents.toString(), "new");
        workshopRepository.addWorkshop(newWorkshopContents.toString(), "new");
    }

    public WorkshopRepository getWorkshopRepository() {
        return workshopRepository;
    }

    @Override
    public String toString() {
        return "WorkshopManager{" +
                "workshopRepository=" + workshopRepository.toString() +
                '}';
    }
}
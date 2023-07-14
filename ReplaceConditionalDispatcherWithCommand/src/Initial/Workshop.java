package Initial;

public class Workshop {
    private String id;
    private String name;
    private String status;

    public Workshop(String id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getDurationAsString() {
        return "1";
    }

    @Override
    public String toString() {
        return "Workshop{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
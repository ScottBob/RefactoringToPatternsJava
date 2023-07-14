package Initial;

import java.util.HashMap;
import java.util.Iterator;

public class WorkshopRepository extends HashMap<String, Workshop> {
    public String addWorkshop(String name, String status) {
        String id = "" +  this.size() + 1;
        put(id, new Workshop(id, name, status));
        return id;
    }

    public Workshop getWorkshop(String id) {
        return get(id);
    }

    public Iterator keyIterator() {
       return this.keySet().iterator();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("WorkshopRespository{");
        Iterator it = this.keyIterator();
        while (it.hasNext()) {
            String id = (String)it.next();
            builder.append(get(id).toString());
        }
        builder.append("}");
        return builder.toString();
    }
}

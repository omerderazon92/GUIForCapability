package Target;

import java.util.ArrayList;
import java.util.List;

public class Target {
    List<String> filter = new ArrayList<String>();
    String connector;

    public Target() {
    }

    public Target(List<String> filter, String connector) {
        this.filter = filter;
        this.connector = connector;
    }

    public List<String> getFilter() {
        return filter;
    }

    public void setFilter(List<String> filter) {
        this.filter = filter;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }
}

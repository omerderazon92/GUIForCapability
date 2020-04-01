import Target.Target;

import java.util.ArrayList;
import java.util.List;

public class CommandsManager {
    private String cluster, stepping, regressions;
    private int budget;
    private List<Target> targets;

    public CommandsManager(String cluster, String stepping, String regressions, int budget, List<Target> targets) {
        this.cluster = cluster;
        this.stepping = stepping;
        this.regressions = regressions;
        this.budget = budget;
        this.targets = targets;
    }

    public String generateCommand() {
        removeRedundantTargets();
        StringBuilder targetsStringBuilder = createTargetForm();
        return "--cluster " + cluster +
                " --stepping " + stepping +
                " --regressions " + "\"" + regressions + "\"" +
                " --budget " + budget +
                targetsStringBuilder;
    }

    private void removeRedundantTargets() {
        List<Target> potentialForRemoval = new ArrayList<Target>();
        for (Target target : targets) {
            int numberOfEmptyFilters = 0;
            for (String filter : target.getFilters()) {
                if (filter.equals("Filter"))
                    numberOfEmptyFilters += 1;
                if (numberOfEmptyFilters == target.getFilters().size()) {
                    potentialForRemoval.add(target);
                }
            }
        }
        for (Target forRemoval : potentialForRemoval) {
            targets.remove(forRemoval);
        }
    }

    private StringBuilder createTargetForm() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Target target : targets) {
            stringBuilder.append(" --targets ").append("\"");
            for (String filter : target.getFilters()) {
                if (filter.equals("Filter")) {
                    continue;
                }
                stringBuilder.append("#filter=").append(filter);
            }
            stringBuilder.append("#connector=")
                    .append(target.getConnector())
                    .append("\"");
        }
        return stringBuilder;
    }
}

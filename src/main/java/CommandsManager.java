import Target.Target;

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

    public void generateCommand() {
        StringBuilder targetsStringBuilder = createTargetForm();
        String commandStringBuilder = "--cluster " + cluster +
                " --stepping " + stepping +
                " --regressions " + regressions +
                " --budget " + budget +
                targetsStringBuilder;
        System.out.println(commandStringBuilder);
    }

    private StringBuilder createTargetForm() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Target target : targets) {
            stringBuilder.append(" --targets ");
            for (String filter : target.getFilter()) {
                stringBuilder.append("#filter=").append(filter);
            }
            stringBuilder.append("#connector=").append(target.getConnector());
        }
        return stringBuilder;
    }
}

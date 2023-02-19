package jupgo.jupgoserver.domain.tree;

import java.util.Map;

public class ExperienceManager {
    private static final Map<Integer, Integer> LEVEL_TABLE = Map.of(
            1, 11_000,
            2, 14_500,
            3, 19_500,
            4, 24_000,
            5, 31_000
    );

    public static void addExperience(Tree tree, int experience) {
        int experienceToConvert = 0;
        int level = tree.getLevel();
        int percentage = tree.getPercentage();
        int percentageToAdd = convertExperienceToPercentage(level, experience);
        percentage += percentageToAdd;

        while (percentage >= 100) {
            experienceToConvert = convertPercentageToExperience(level, percentage - 100);
            percentage = 0;
            level += 1;
            if (level == 6) {
                break;
            }
            percentageToAdd = convertExperienceToPercentage(level, experienceToConvert);
            percentage += percentageToAdd;
        }

        tree.setLevel(level);
        tree.setPercentage(percentage);
    }

    private static int convertPercentageToExperience(int level, int percentage) {
        return percentage * LEVEL_TABLE.get(level) / 100;
    }

    private static int convertExperienceToPercentage(int beforeLevel, int experience) {
        return experience * 100 / LEVEL_TABLE.get(beforeLevel);
    }
}

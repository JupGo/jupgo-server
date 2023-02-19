package jupgo.jupgoserver.domain.tree;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ExperienceManagerTest {

    @Test
    void shouldReturnCorrectPercentageWhenInputLevelAndExperience() {
        Tree tree = new Tree();
        printStatus(tree);
        ExperienceManager.addExperience(tree, 50000);
        printStatus(tree);
    }

    private static void printStatus(Tree tree) {
        System.out.println("Level : " + tree.getLevel());
        System.out.println("Percentage : " + tree.getPercentage());
    }
}
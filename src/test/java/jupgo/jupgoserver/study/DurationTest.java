package jupgo.jupgoserver.study;

import java.time.Duration;
import org.junit.jupiter.api.Test;

public class DurationTest {

    @Test
    void durationTest() {
        String[] timeIndexes = "2:24:07".split(":");
        String durationFormat =
                "PT" + timeIndexes[0] + "H" + timeIndexes[1] + "M" + timeIndexes[2] + "S";
        Duration duration = Duration.parse(durationFormat);
        System.out.println(duration);
        System.out.println(duration.getSeconds());
    }
}

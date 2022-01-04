import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class RadialGradientGeneratorTest {
    RadialGradientGenerator generator = new RadialGradientGenerator(400, 400, 255, 160, 200);

    @Test
    void print() {
//        generator.fillBlueGradientValues();
//        generator.blueGradientValues.forEach((k, v) -> System.out.println(k + " " + v));
        int num = 9;
        int interval = num - num % 3;
        System.out.println(interval);
    }

    @Test
    void printMap() {
        Map<Integer, Integer> test = new HashMap<>();
        test.put(1, 2);
        test.put(2, 4);

        System.out.println(test.get(3));
    }

}
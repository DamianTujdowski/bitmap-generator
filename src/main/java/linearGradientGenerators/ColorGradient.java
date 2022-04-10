package linearGradientGenerators;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.DoubleStream;

public class ColorGradient {

    private Map<Integer, Integer> gradientValues;
    private final int startColor;
    private final int endColor;

    public ColorGradient(int startColor, int endColor) {
        this.gradientValues = new HashMap<>();
        this.startColor = startColor;
        this.endColor = endColor;
    }

    //TODO test logic in DoubleStream - what if startColor is smaller then endColor
    public void fillGradientValues(int dimension) {
        double interval = computeInterval(dimension);
        final int[] tempColor = {startColor};
        int stepsLimit = computeStepsLimit(dimension);

        if (startColor - endColor > 0) {
            DoubleStream.iterate(0, n -> n + interval)
                    .limit(stepsLimit)
                    .forEach(num ->
                            gradientValues.put((int) Math.round(num), tempColor[0]++)
                    );
        } else{
            DoubleStream.iterate(0, n -> n + interval)
                    .limit(stepsLimit)
                    .forEach(num ->
                            gradientValues.put((int) Math.round(num), tempColor[0]--)
                    );
        }
    }

    private int computeStepsLimit(int dimension) {
        int gradientSteps = Math.abs(startColor - endColor);
        return gradientSteps < dimension ? gradientSteps + 1 : dimension;
    }

    public double computeInterval(int dimension) {
        int gradientSteps = Math.abs(startColor - endColor);
        double interval = (double) dimension / (gradientSteps + 1);
        return gradientSteps > dimension ? 1 : interval;
    }

    public int getColorValue(int key) {
        if (gradientValues.containsKey(key)) {
            return gradientValues.get(key);
        } else {
            return gradientValues.get(++key);
        }
    }
}

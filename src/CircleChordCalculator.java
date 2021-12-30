public class CircleChordCalculator {

    int radius;

    public CircleChordCalculator(int radius) {
        this.radius = radius;
    }

    public int countChordLength(int distanceFromCenter) {
        return (int) (2 * Math.sqrt(
                Math.pow(radius, 2) - Math.pow(distanceFromCenter, 2)
        ));
    }
}

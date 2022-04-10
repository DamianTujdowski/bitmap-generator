package linearGradientGenerators;

import java.awt.*;

public class RandomColorGenerator {

    //TODO implement method
    public Color generateStartColor(){
        return new Color(12, 12, 12);
    }

    //TODO implement method end color depends on start color
    public Color generateEndColor(Color color) {
        return new Color(200,200,200);
    }
}

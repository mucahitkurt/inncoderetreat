package io.mucahit.coderetreat.gol.noloopnoconditionals;

import java.awt.*;

/**
 * @author mucahitkurt
 * @since 12.07.2018
 */
public class AlivePoint extends HabitatPoint {

    public AlivePoint(Point point) {
        this.x = point.x;
        this.y = point.y;
        nextGeneration.put(3, this);
        nextGeneration.put(4, this);
    }

    @Override
    public int livenessConstant() {
        return 1;
    }
}

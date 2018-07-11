package io.mucahit.coderetreat.gol.noloop;

import java.awt.*;

/**
 * @author mucahitkurt
 * @since 12.07.2018
 */
public class DeadPoint extends HabitatPoint {

    public DeadPoint(Point point) {
        this.x = point.x;
        this.y = point.y;
        this.nextGeneration.put(3, new AlivePoint(point));
    }
}

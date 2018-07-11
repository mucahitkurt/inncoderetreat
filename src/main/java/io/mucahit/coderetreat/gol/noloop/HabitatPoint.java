package io.mucahit.coderetreat.gol.noloop;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mucahitkurt
 * @since 12.07.2018
 */
public abstract class HabitatPoint extends Point {
    protected int aliveNeighbours;

    protected Map<Integer, HabitatPoint> nextGeneration = new HashMap<>();

    public int livenessConstant() {
        return 0;
    }

    public HabitatPoint nextGeneration() {
        return nextGeneration.get(aliveNeighbours);
    }
}

package io.mucahit.coderetreat.gol.noprimitives;

/**
 * @author mkurt
 * @since 11.07.2018 13:05
 */
public class Coordinate {
    private int value;

    public Coordinate(int value) {
        this.value = value;
    }

    public void forward() {
        value++;
    }

    public boolean isBefore(Coordinate coordinate) {
        return this.value < coordinate.value;
    }

    public int value() {
        return value;
    }
}

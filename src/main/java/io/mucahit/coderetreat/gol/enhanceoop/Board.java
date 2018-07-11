package io.mucahit.coderetreat.gol.enhanceoop;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mucahitkurt
 * @since 9.07.2018
 */
public class Board {

    private Set<Point> grid = new HashSet<>();

    public void place(Point point) {
        grid.add(point);
    }

    public void tick() {
        var newGenerationGrid = new HashSet<Point>();
        for (var point : getPossiblePoints()) {
            if (isAliveAtNextGeneration(getNeighbourCount(point), point)) {
                newGenerationGrid.add(new Point(point.x, point.y));
            }
        }
        this.grid = newGenerationGrid;
    }

    private boolean isAliveAtNextGeneration(int countOfNeighbour, Point point) {

        if (countOfNeighbour == 3) {
            return true;
        }
        return countOfNeighbour == 4 && isAlive(point);
    }

    private Set<Point> getPossiblePoints() {
        var possiblePoints = new HashSet<Point>();
        for (Point point : grid) {
            for (var x = -1; x <= 1; x++) {
                for (var y = -1; y <= 1; y++) {
                    possiblePoints.add(new Point(point.x + x, point.y + y));
                }
            }

        }
        return possiblePoints;
    }

    private int getNeighbourCount(Point point) {
        var count = 0;
        for (var x = -1; x <= 1; x++) {
            for (var y = -1; y <= 1; y++) {
                if (isAlive(point.x + x, point.y + y)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isAlive(int x, int y) {
        return isAlive(new Point(x, y));
    }

    private boolean isAlive(Point point) {
        return grid.contains(point);
    }

    public int getLivingCellsCount() {
        return grid.size();
    }

    public Set<Point> getLivingCells() {
        return grid;
    }
}

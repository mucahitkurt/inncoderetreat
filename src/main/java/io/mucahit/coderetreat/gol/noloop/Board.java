package io.mucahit.coderetreat.gol.noloop;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mucahitkurt
 * @since 11.07.2018
 */
public class Board {
    private Habitat habitat;

    public Board(Habitat habitat) {
        this.habitat = habitat;
    }

    public void advance() {

        Habitat nextGeneration = new Habitat();
        advanceForMembers(new ArrayList<>(habitat.getMembers()), nextGeneration);
        nextGeneration.trim();
        this.habitat = nextGeneration;
    }

    private void advanceForMembers(List<Point> members, Habitat nextGeneration) {

        if (members.isEmpty()) {
            return;
        }

        advanceForNeighbours(new ArrayList<>(habitat.getMemberWithNeighbours(members.get(0)).getMembers()), nextGeneration);
        members.remove(0);
        advanceForMembers(members, nextGeneration);
    }

    private void advanceForNeighbours(List<Point> neighbours, Habitat nextGeneration) {

        if (neighbours.isEmpty()) {
            return;
        }

        final HabitatPoint habitatPoint = habitat.livenessAwarePoint(neighbours.get(0));
        nextGeneration.place(habitatPoint.nextGeneration());
        neighbours.remove(0);
        advanceForNeighbours(neighbours, nextGeneration);
    }

    public Habitat getHabitat() {
        return habitat;
    }
}

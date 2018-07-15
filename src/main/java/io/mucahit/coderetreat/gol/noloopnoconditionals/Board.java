package io.mucahit.coderetreat.gol.noloopnoconditionals;


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
        final Point remove;
        try {
            remove = members.remove(0);
        } catch (Exception e) {
            return;
        }
        advanceForNeighbours(new ArrayList<>(habitat.getMemberWithNeighbours(remove).getMembers()), nextGeneration);
        advanceForMembers(members, nextGeneration);
    }

    private void advanceForNeighbours(List<Point> neighbours, Habitat nextGeneration) {

        final Point remove;
        try {
            remove = neighbours.remove(0);
        } catch (Exception e) {
            return;
        }
        final HabitatPoint habitatPoint = habitat.livenessAwarePoint(remove);
        nextGeneration.place(habitatPoint.nextGeneration());
        advanceForNeighbours(neighbours, nextGeneration);
    }

    public Habitat getHabitat() {
        return habitat;
    }
}

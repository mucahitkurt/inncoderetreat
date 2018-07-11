package io.mucahit.coderetreat.gol.noconditional;


import java.awt.*;

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

        for (Point point : habitat.getMembers()) {
            for (Point point1 : habitat.getMemberWithNeighbours(point).getMembers()) {
                final HabitatPoint habitatPoint = habitat.livenessAwarePoint(point1);
                nextGeneration.place(habitatPoint.nextGeneration());
            }
        }
        nextGeneration.trim();
        this.habitat = nextGeneration;
    }

    public Habitat getHabitat() {
        return habitat;
    }
}

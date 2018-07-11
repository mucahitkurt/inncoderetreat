package io.mucahit.coderetreat.gol.immutable;


import java.awt.*;

/**
 * @author mkurt
 * @since 11.07.2018 19:00
 */
public class Board {

    private final Habitat habitat;

    public Board(final Habitat habitat) {
        this.habitat = habitat;
    }

    public Board advance() {
        Habitat newGenerationHabitat = new Habitat();

        for (Point point : habitat.getMembers()) {
            for (Point neighbour : habitat.getMemberWithNeigbours(point).getMembers()) {
                if (isAliveAtNextGeneration(habitat, neighbour)) {
                    newGenerationHabitat = newGenerationHabitat.place(neighbour);
                }
            }
        }

        return new Board(newGenerationHabitat);
    }

    private boolean isAliveAtNextGeneration(Habitat habitat, Point neighbour) {
        if (habitat.livingMembersCount(neighbour) == 3) {
            return true;
        }
        return habitat.livingMembersCount(neighbour) == 4 && habitat.isAlive(neighbour);
    }

    public Habitat getHabitat() {
        return habitat;
    }
}

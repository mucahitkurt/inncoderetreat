package io.mucahit.coderetreat.gol.noprimitives;

import java.awt.*;

/**
 * @author mkurt
 * @since 11.07.2018 12:05
 */
public class Board {

    private Habitat habitat = new Habitat();

    public void place(Point point) {
        habitat.place(point);
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void advance() {
        Habitat newGenerationHabitat = new Habitat();

        for (Point point : habitat.getMembers()) {
            for (Point neigbour : habitat.getMemberWithNeigbours(point).getMembers()) {
                if (habitat.livingMembersCount(neigbour) == 3) {
                    newGenerationHabitat.place(neigbour);
                } else if (habitat.livingMembersCount(neigbour) == 4 && habitat.isAlive(neigbour)) {
                    newGenerationHabitat.place(neigbour);
                }
            }
        }

        this.habitat = newGenerationHabitat;
    }
}

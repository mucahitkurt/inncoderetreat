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
            if (habitat.getMemberWithNeigbours(point).liveMembersCount() == 3) {
                habitat.place(point);
            } else if (habitat.getMemberWithNeigbours(point).liveMembersCount() == 4) {
                
            }
        }

    }
}

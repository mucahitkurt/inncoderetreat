package io.mucahit.coderetreat.gol.noprimitives;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mkurt
 * @since 11.07.2018 12:26
 */
class HabitatTest {

    @Test
    void place() {

        Habitat habitat = new Habitat();
        assertEquals(0, habitat.getMembersCount());
        habitat.place(new Point(0, 1));
        assertEquals(1, habitat.getMembersCount());
        habitat.place(new Point(0, 1));
        assertEquals(1, habitat.getMembersCount());
        habitat.place(new Point(0, 2));
        assertEquals(2, habitat.getMembersCount());
    }

    @Test
    void getMemberWithNeighbours() {

        Habitat habitat = new Habitat();
        final Point point = new Point(0, 0);
        habitat.place(point);
        assertEquals(9, habitat.getMemberWithNeigbours(point).getMembersCount());
        final Point point2 = new Point(1, 2);
        habitat.place(point2);
        assertEquals(9, habitat.getMemberWithNeigbours(point2).getMembersCount());
    }

    @Test
    public void getLivingCellsCountInASubHabitat() {

        Habitat habitat = new Habitat();
        final Point point = new Point(0, 1);
        habitat.place(point);
        assertEquals(1, habitat.livingMembersCount(new Point(0,1)));

        habitat.place(new Point(0,2));
        assertEquals(2, habitat.livingMembersCount(new Point(0,1)));

        habitat.place(new Point(0,0));
        assertEquals(3, habitat.livingMembersCount(new Point(0,1)));
    }


}
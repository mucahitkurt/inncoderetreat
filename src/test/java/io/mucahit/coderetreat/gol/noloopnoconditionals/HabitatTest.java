package io.mucahit.coderetreat.gol.noloopnoconditionals;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author mucahitkurt
 * @since 11.07.2018
 */
class HabitatTest {

    @Test
    void getMemberWithNeighbours() {
        Habitat habitat = new Habitat();
        habitat.place(new Point(0, 1));

        assertEquals(9, habitat.getMemberWithNeighbours(new Point(0, 0)).getMembersCount());
    }

    @Test
    public void livenessAwarePoint() {

        Habitat habitat = new Habitat();
        habitat.place(new Point(0,0));

        HabitatPoint habitatPoint = habitat.livenessAwarePoint(new Point(0, 0));
        assertEquals(1, habitatPoint.aliveNeighbours);
        assertTrue( AlivePoint.class.isInstance(habitatPoint));

        habitat.place(new Point(0,1));
        habitatPoint = habitat.livenessAwarePoint(new Point(0, 0));
        assertEquals(2, habitatPoint.aliveNeighbours);
        assertTrue(AlivePoint.class.isInstance(habitatPoint));

        habitatPoint = habitat.livenessAwarePoint(new Point(2, 2));
        assertEquals(0, habitatPoint.aliveNeighbours);
        assertTrue(DeadPoint.class.isInstance(habitatPoint));
    }

}
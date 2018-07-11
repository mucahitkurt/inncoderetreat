package io.mucahit.coderetreat.gol.noloop;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
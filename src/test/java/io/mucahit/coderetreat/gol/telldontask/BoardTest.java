package io.mucahit.coderetreat.gol.telldontask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author mkurt
 * @since 13.07.2018 12:27
 */
public class BoardTest {

    private Board board;

    @BeforeEach
    public void beforeEach() {
        board = new Board();
    }

    @Test
    public void emptyBoardAdvanceToEmptyBoard() {
        //use string pattern as board!!
        Set<String> newGeneration = new HashSet<>();
        String pattern = "ooo";

        board.advance(pattern, newGeneration);
        Assertions.assertTrue(newGeneration.isEmpty());
    }

    @Test
    public void block() {
        Set<String> newGeneration = new HashSet<>();
        String pattern = "**\n**";

        board.advance(pattern, newGeneration);
        assertEquals(4, newGeneration.size());
    }

    @Test
    public void blink() {
        Set<String> newGeneration = new HashSet<>();
        String pattern = "ooo\n***\nooo";

        board.advance(pattern, newGeneration);
        assertEquals(3, newGeneration.size());

        assertTrue(newGeneration.contains("1~0"));
        assertTrue(newGeneration.contains("1~1"));
        assertTrue(newGeneration.contains("1~2"));
    }
}

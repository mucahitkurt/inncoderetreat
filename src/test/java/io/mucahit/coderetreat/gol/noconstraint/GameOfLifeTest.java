package io.mucahit.coderetreat.gol.noconstraint;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mucahitkurt
 * @since 8.07.2018
 */
public class GameOfLifeTest {

    @Test
    void nextGenerationForBlock() {
        final String[] board = {
                "XX",
                "XX",
        };

        final GameOfLife gameOfLife = new GameOfLife(board);
        assertArrayEquals(board, gameOfLife.nextGeneration());
        assertArrayEquals(board, gameOfLife.nextGeneration());
        assertArrayEquals(board, gameOfLife.nextGeneration());
    }

    @Test
    void produceNextGenerationForBlinker() {
        final var board = new String[]{
                "OOO",
                "XXX",
                "OOO"
        };

        final var gameOfLife = new GameOfLife(board);

        assertArrayEquals(new String[]{
                "OXO",
                "OXO",
                "OXO"
        }, gameOfLife.nextGeneration());

        assertArrayEquals(new String[]{
                "OOO",
                "XXX",
                "OOO"
        }, gameOfLife.nextGeneration());

    }

    @Test
    void countOfLiveNeighbours() {
        var row = "OXOX";
        final GameOfLife gameOfLife = new GameOfLife(new String[]{});

        Assertions.assertAll(
                () -> assertEquals(1, gameOfLife.countOfLiveNeighbours(row, 0, GameOfLife.Direction.ABOVE)),
                () -> assertEquals(1, gameOfLife.countOfLiveNeighbours(row, 1, GameOfLife.Direction.ABOVE)),
                () -> assertEquals(2, gameOfLife.countOfLiveNeighbours(row, 2, GameOfLife.Direction.ABOVE)),
                () -> assertEquals(1, gameOfLife.countOfLiveNeighbours(row, 3, GameOfLife.Direction.ABOVE)));

        Assertions.assertAll(
                () -> assertEquals(1, gameOfLife.countOfLiveNeighbours(row, 0, GameOfLife.Direction.BELOW)),
                () -> assertEquals(1, gameOfLife.countOfLiveNeighbours(row, 1, GameOfLife.Direction.BELOW)),
                () -> assertEquals(2, gameOfLife.countOfLiveNeighbours(row, 2, GameOfLife.Direction.BELOW)),
                () -> assertEquals(1, gameOfLife.countOfLiveNeighbours(row, 3, GameOfLife.Direction.BELOW)));

        Assertions.assertAll(
                () -> assertEquals(1, gameOfLife.countOfLiveNeighbours(row, 0, GameOfLife.Direction.THESAMELINE)),
                () -> assertEquals(0, gameOfLife.countOfLiveNeighbours(row, 1, GameOfLife.Direction.THESAMELINE)),
                () -> assertEquals(2, gameOfLife.countOfLiveNeighbours(row, 2, GameOfLife.Direction.THESAMELINE)),
                () -> assertEquals(0, gameOfLife.countOfLiveNeighbours(row, 3, GameOfLife.Direction.THESAMELINE)));
    }

    @Test
    void print() {
        final var board = new String[]{
                "OOO",
                "XXX",
                "OOO"
        };

        final GameOfLife gameOfLife = new GameOfLife(board);
        System.out.println(Arrays.toString(board));
        System.out.println(Arrays.toString(gameOfLife.nextGeneration()));
        System.out.println(Arrays.toString(gameOfLife.nextGeneration()));
        System.out.println(Arrays.toString(gameOfLife.nextGeneration()));
        System.out.println(Arrays.toString(gameOfLife.nextGeneration()));
        System.out.println(Arrays.toString(gameOfLife.nextGeneration()));
    }
}

package io.mucahit.coderetreat.gol.enhanceoop;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mucahitkurt
 * @since 9.07.2018
 */
public class GameOfLifeTest {

    @Test
    void oneLivingCellResultAsEmptyGrid() {
        var board = new Board();
        board.place(new Point(0, 0));
        board.tick();
        assertEquals(0, board.getLivingCellsCount());
    }

    @Test
    void blockPattern() {
        final var board = generateBoard("**\n**");
        var beforeCells = board.getLivingCells();
        board.tick();
        final Set<Point> afterCells = board.getLivingCells();

        assertEquals(beforeCells, afterCells);
    }

    @Test
    void blinkerPattern() {
        final Board board = generateBoard("ooo\n***\nooo");
        board.tick();
        assertEquals(generateBoard("o*o\no*o\no*o").getLivingCells(), board.getLivingCells());
        board.tick();
        assertEquals(generateBoard("ooo\n***\nooo").getLivingCells(), board.getLivingCells());
    }

    @Test
    void beaconPattern() {
        final Board board = generateBoard("**oo\n**oo\noo**\noo**");
        board.tick();
        assertEquals(generateBoard("**oo\n*ooo\nooo*\noo**").getLivingCells(), board.getLivingCells());
        board.tick();
        assertEquals(generateBoard("**oo\n**oo\noo**\noo**").getLivingCells(), board.getLivingCells());
    }

    private Board generateBoard(String pattern) {
        var board = new Board();
        var x = 0;
        var y = 0;

        for (var i = 0; i < pattern.toCharArray().length; i++) {
            if (i == '*') {
                board.place(new Point(x, y));
                x++;
            } else if (i == '\n') {
                x = 0;
                y++;
            } else {
                x++;
            }
        }

        return board;
    }
}

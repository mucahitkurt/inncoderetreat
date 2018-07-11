package io.mucahit.coderetreat.gol.immutable;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

/**
 * @author mkurt
 * @since 11.07.2018 18:59
 */
public class BoardTest {

    @Test
    public void oneLivingPointAdvanceToEmptyBoard() {
        Board board = generateBoard("o*o");
        Board newBoard = board.advance();
        assertNotSame(board, newBoard);
        assertNotSame(board.getHabitat(), newBoard.getHabitat());
        assertEquals(0, newBoard.getHabitat().getMembersCount());
    }

    @Test
    public void blinker() {

        Board board = generateBoard("ooo\n***\nooo");

        assertEquals(3, board.getHabitat().getMembersCount());
        board = board.advance();
        assertEquals(3, board.getHabitat().getMembersCount());
        board = board.advance();
        assertEquals(3, board.getHabitat().getMembersCount());
    }

    @Test
    public void beacon() {
        Board board = generateBoard("**oo\n**oo\noo**\noo**");
        final Board boardNext = generateBoard("**oo\n*ooo\nooo*\noo**");
        assertEquals(8, board.getHabitat().getMembersCount());
        board = board.advance();
        assertEquals(board.getHabitat().getMembers(), boardNext.getHabitat().getMembers());
        assertEquals(6, board.getHabitat().getMembersCount());
        board = board.advance();
        assertEquals(8, board.getHabitat().getMembersCount());
    }

    private Board generateBoard(String pattern) {

        var x = 0;
        var y = 0;

        Habitat habitat = new Habitat();

        for (char c : pattern.toCharArray()) {
            if (c == '*') {
                habitat = habitat.place(new Point(x, y));
                x++;
            } else if (c == '\n') {
                x = 0;
                y++;
            } else {
                x++;
            }
        }

        return new Board(habitat);
    }
}

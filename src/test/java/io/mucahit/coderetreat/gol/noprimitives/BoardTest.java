package io.mucahit.coderetreat.gol.noprimitives;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import io.mucahit.coderetreat.gol.noprimitives.Board;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author mkurt
 * @since 11.07.2018 12:04
 */
public class BoardTest {

    @Test
    public void oneLivingPointAdvanceToEmptyBoard() {
        Board board = generateBoard("o*o");
        board.advance();
        assertEquals(0, board.getHabitat().getMembersCount());
    }

    @Test
    public void blinker() {

        final Board board = generateBoard("ooo\n***\nooo");

        assertEquals(3, board.getHabitat().getMembersCount());
        board.advance();
        assertEquals(3, board.getHabitat().getMembersCount());
        board.advance();
        assertEquals(3, board.getHabitat().getMembersCount());
    }

    @Test
    public void beacon() {
        final Board board = generateBoard("**oo\n**oo\noo**\noo**");
        final Board boardNext = generateBoard("**oo\n*ooo\nooo*\noo**");
        assertEquals(8, board.getHabitat().getMembersCount());
        board.advance();
        assertEquals(board.getHabitat().getMembers(), boardNext.getHabitat().getMembers());
        assertEquals(6, board.getHabitat().getMembersCount());
        board.advance();
        assertEquals(8, board.getHabitat().getMembersCount());
    }

    private Board generateBoard(String pattern) {

        var x = 0;
        var y = 0;

        Board board = new Board();

        for (char c : pattern.toCharArray()) {
            if (c == '*') {
                board.place(new Point(x, y));
                x++;
            } else if (c == '\n') {
                x = 0;
                y++;
            } else {
                x++;
            }
        }

        return board;
    }
}

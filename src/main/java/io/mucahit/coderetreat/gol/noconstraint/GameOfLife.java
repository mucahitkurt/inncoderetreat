package io.mucahit.coderetreat.gol.noconstraint;

/**
 * @author mucahitkurt
 * @since 8.07.2018
 */
public class GameOfLife {

    private String[] board;
    private static final char DEAD = 'O';
    private static final char ALIVE = 'X';

    public GameOfLife(String[] board) {
        this.board = board;
    }

    public String[] nextGeneration() {
        var newGeneration = new String[this.board.length];

        for (int row = 0; row < this.board.length; row++) {
            var newGenerationRow = new char[this.board[row].length()];
            for (int col = 0; col < this.board[row].toCharArray().length; col++) {
                var totalLiveNeighbours = this.board[row].charAt(col) == ALIVE ? 1 : 0;
                if (row != 0) {
                    totalLiveNeighbours += countOfLiveNeighbours(this.board[row - 1], col, Direction.ABOVE);
                }
                totalLiveNeighbours += countOfLiveNeighbours(this.board[row], col, Direction.THESAMELINE);
                if (row != this.board.length - 1) {
                    totalLiveNeighbours += countOfLiveNeighbours(this.board[row + 1], col, Direction.BELOW);
                }

                if (totalLiveNeighbours == 3) {
                    newGenerationRow[col] = ALIVE;
                } else if (totalLiveNeighbours == 4) {
                    newGenerationRow[col] = this.board[row].charAt(col);
                } else {
                    newGenerationRow[col] = DEAD;
                }
            }
            newGeneration[row] = new String(newGenerationRow);
        }

        this.board = newGeneration;
        return this.board;
    }

    int countOfLiveNeighbours(String s, int col, Direction direction) {

        var liveNeighbours = 0;
        if (direction == Direction.ABOVE || direction == Direction.BELOW) {
            liveNeighbours = s.toCharArray()[col] == ALIVE ? 1 : 0;
        }
        if (col != 0) {
            liveNeighbours += s.toCharArray()[col - 1] == ALIVE ? 1 : 0;
        }
        if (col != s.toCharArray().length - 1) {
            liveNeighbours += s.toCharArray()[col + 1] == ALIVE ? 1 : 0;
        }
        return liveNeighbours;
    }

    enum Direction {
        ABOVE, BELOW, THESAMELINE,
    }
}

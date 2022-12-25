import java.util.*;

public class MineSweeper {

    private int[][] fieldVisible = new int[10][10];
    private int[][] fieldHidden = new int[10][10];

    public static void main(String[] args)
    {
        MineSweeper M = new MineSweeper();
        M.startGame();
    }
}
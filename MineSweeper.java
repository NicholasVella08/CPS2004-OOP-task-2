import java.util.*;

public class MineSweeper {

    private int[][] fieldVisible = new int[10][10];
    private int[][] fieldHidden = new int[10][10];

    public static void main(String[] args)
    {
        MineSweeper M = new MineSweeper();
        M.startGame();
    }

    public void startGame()
    {
        System.out.println("\n\n================Welcome to Minesweeper ! ================\n");
        setupField(1);

        boolean flag = true;
        while(flag)
        {
            displayVisible();
            flag = playMove();
            if(checkWin())
            {
                displayHidden();
                System.out.println("\n================You WON!!!================");
                break;
            }
        }
    }

}
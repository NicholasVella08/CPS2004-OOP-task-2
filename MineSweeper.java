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

    public void setupField(int diff)
    {
        int var=0;
        while(var!=10)
        {
            Random random = new Random();
            int i = random.nextInt(10);
            int j = random.nextInt(10);
            //System.out.println("i: " + i + " j: " + j);
            fieldHidden[i][j] = 100;
            var++;
        }
        buildHidden();
    }

    public void buildHidden()
    {
        for(int i=0; i<10; i++)
        {
            for(int j=0; j<10; j++)
            {
                int cnt=0;
                if(fieldHidden[i][j]!=100)
                {

                    if(i!=0)
                    {
                        if(fieldHidden[i-1][j]==100) cnt++;
                        if(j!=0)
                        {
                            if(fieldHidden[i-1][j-1]==100) cnt++;
                        }

                    }
                    if(i!=9)
                    {
                        if(fieldHidden[i+1][j]==100) cnt++;
                        if(j!=9)
                        {
                            if(fieldHidden[i+1][j+1]==100) cnt++;
                        }
                    }
                    if(j!=0)
                    {
                        if(fieldHidden[i][j-1]==100) cnt++;
                        if(i!=9)
                        {
                            if(fieldHidden[i+1][j-1]==100) cnt++;
                        }
                    }
                    if(j!=9)
                    {
                        if(fieldHidden[i][j+1]==100) cnt++;
                        if(i!=0)
                        {
                            if(fieldHidden[i-1][j+1]==100) cnt++;
                        }
                    }

                    fieldHidden[i][j] = cnt;
                }
            }
        }

    }

    public void displayVisible()
    {
        System.out.print("\t ");
        for(int i=0; i<10; i++)
        {
            System.out.print(" " + i + "  ");
        }
        
    }

}
import java.util.*;

public class MineSweeper {

    private int[][] fieldVisible = new int[16][16];
    private int[][] fieldHidden = new int[16][16];

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
        while(var!=40)
        {
            Random random = new Random();
            int i = random.nextInt(16);
            int j = random.nextInt(16);
            //System.out.println("i: " + i + " j: " + j);
            fieldHidden[i][j] = 256;
            var++;
        }
        buildHidden();
    }

    public void buildHidden()
    {
        for(int i=0; i<16; i++)
        {
            for(int j=0; j<16; j++)
            {
                int cnt=0;
                if(fieldHidden[i][j]!=256)
                {

                    if(i!=0)
                    {
                        if(fieldHidden[i-1][j]==256) cnt++;
                        if(j!=0)
                        {
                            if(fieldHidden[i-1][j-1]==256) cnt++;
                        }

                    }
                    if(i!=15)
                    {
                        if(fieldHidden[i+1][j]==256) cnt++;
                        if(j!=15)
                        {
                            if(fieldHidden[i+1][j+1]==256) cnt++;
                        }
                    }
                    if(j!=0)
                    {
                        if(fieldHidden[i][j-1]==256) cnt++;
                        if(i!=15)
                        {
                            if(fieldHidden[i+1][j-1]==256) cnt++;
                        }
                    }
                    if(j!=15)
                    {
                        if(fieldHidden[i][j+1]==256) cnt++;
                        if(i!=0)
                        {
                            if(fieldHidden[i-1][j+1]==256) cnt++;
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
        for(int i=0; i<16; i++)
        {
            System.out.print(" " + i + "  ");
        }
        System.out.print("\n");
        for(int i=0; i<16; i++)
        {
            System.out.print(i + "\t| ");
            for(int j=0; j<16; j++)
            {
                if(fieldVisible[i][j]==0)
                {
                    System.out.print("?");
                }
//                else if(fieldVisible[i][j]==50)
                else if(fieldVisible[i][j]==128)
                {
                    System.out.print(" ");
                }
                else
                {
                    System.out.print(fieldVisible[i][j]);
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }

    public boolean playMove()
    {
        Scanner sc= new Scanner(System.in);
        System.out.print("\nEnter Row Number: ");
        int i= sc.nextInt();
        System.out.print("Enter Column Number: ");
        int j= sc.nextInt();

        if(i<0 || i>15 || j<0 || j>15 || fieldVisible[i][j]!=0)
        {
            System.out.print("\nIncorrect Input!!");
            return true;
        }

        if(fieldHidden[i][j]==256)
        {
            displayHidden();
            System.out.print("Oops! You stepped on a mine!\n============GAME OVER============");
            return false;
        }
        else if(fieldHidden[i][j]==0)
        {
            fixVisible(i, j);
        }
        else
        {
            fixNeighbours(i, j);
        }

        return true;
    }


    public void fixVisible(int i, int j)
    {
        fieldVisible[i][j] = 128;
        if(i!=0)
        {
            fieldVisible[i-1][j] = fieldHidden[i-1][j];
            if(fieldVisible[i-1][j]==0) fieldVisible[i-1][j] = 128;
            if(j!=0)
            {
                fieldVisible[i-1][j-1] = fieldHidden[i-1][j-1];
                if(fieldVisible[i-1][j-1]==0) fieldVisible[i-1][j-1]=128;

            }
        }
        if(i!=15)
        {
            fieldVisible[i+1][j]=fieldHidden[i+1][j];
            if(fieldVisible[i+1][j]==0) fieldVisible[i+1][j]=128;
            if(j!=9)
            {
                fieldVisible[i+1][j+1]= fieldHidden[i+1][j+1];
                if(fieldVisible[i+1][j+1]==0) fieldVisible[i+1][j+1] = 128;
            }
        }
        if(j!=0)
        {
            fieldVisible[i][j-1]=fieldHidden[i][j-1];
            if(fieldVisible[i][j-1]==0) fieldVisible[i][j-1] = 128;
            if(i!=15)
            {
                fieldVisible[i+1][j-1]=fieldHidden[i+1][j-1];
                if(fieldVisible[i+1][j-1]==0) fieldVisible[i+1][j-1] = 128;
            }
        }
        if(j!=15)
        {
            fieldVisible[i][j+1]=fieldHidden[i][j+1];
            if(fieldVisible[i][j+1]==0) fieldVisible[i][j+1] = 128;
            if(i!=0)
            {
                fieldVisible[i-1][j+1]=fieldHidden[i-1][j+1];
                if(fieldVisible[i-1][j+1]==0) fieldVisible[i-1][j+1] = 128;
            }
        }
    }

    public void fixNeighbours(int i, int j)
    {
        Random random = new Random();
        int x = random.nextInt()%4;

        fieldVisible[i][j] = fieldHidden[i][j];

        if(x==0)
        {
            if(i!=0)
            {
                if(fieldHidden[i-1][j]!=256)
                {
                    fieldVisible[i-1][j] = fieldHidden[i-1][j];
                    if(fieldVisible[i-1][j]==0)  fieldVisible[i-1][j] = 128;
                }
            }
            if(j!=0)
            {
                if(fieldHidden[i][j-1]!=256)
                {
                    fieldVisible[i][j-1] = fieldHidden[i][j-1];
                    if(fieldVisible[i][j-1]==0)  fieldVisible[i][j-1] = 128;
                }

            }
            if(i!=0 && j!=0)
            {
                if(fieldHidden[i-1][j-1]!=256)
                {
                    fieldVisible[i-1][j-1] = fieldHidden[i-1][j-1];
                    if(fieldVisible[i-1][j-1]==0)  fieldVisible[i-1][j-1] = 128;
                }

            }
        }
        else if(x==1)
        {
            if(i!=0)
            {
                if(fieldHidden[i-1][j]!=256)
                {
                    fieldVisible[i-1][j] = fieldHidden[i-1][j];
                    if(fieldVisible[i-1][j]==0)  fieldVisible[i-1][j] = 128;
                }
            }
            if(j!=15)
            {
                if(fieldHidden[i][j+1]!=256)
                {
                    fieldVisible[i][j+1] = fieldHidden[i][j+1];
                    if(fieldVisible[i][j+1]==0)  fieldVisible[i][j+1] = 128;
                }

            }
            if(i!=0 && j!=15)
            {
                if(fieldHidden[i-1][j+1]!=256)
                {
                    fieldVisible[i-1][j+1] = fieldHidden[i-1][j+1];
                    if(fieldVisible[i-1][j+1]==0)  fieldVisible[i-1][j+1] = 128;
                }
            }
        }
        else if(x==2)
        {
            if(i!=15)
            {
                if(fieldHidden[i+1][j]!=256)
                {
                    fieldVisible[i+1][j] = fieldHidden[i+1][j];
                    if(fieldVisible[i+1][j]==0)  fieldVisible[i+1][j] = 128;
                }
            }
            if(j!=15)
            {
                if(fieldHidden[i][j+1]!=256)
                {
                    fieldVisible[i][j+1] = fieldHidden[i][j+1];
                    if(fieldVisible[i][j+1]==0)  fieldVisible[i][j+1] = 128;
                }

            }
            if(i!=9 && j!=15)
            {
                if(fieldHidden[i+1][j+1]!=256)
                {
                    fieldVisible[i+1][j+1] = fieldHidden[i+1][j+1];
                    if(fieldVisible[i+1][j+1]==0)  fieldVisible[i+1][j+1] = 128;
                }
            }
        }
        else
        {
            if(i!=15)
            {
                if(fieldHidden[i+1][j]!=256)
                {
                    fieldVisible[i+1][j] = fieldHidden[i+1][j];
                    if(fieldVisible[i+1][j]==0)  fieldVisible[i+1][j] = 128;
                }
            }
            if(j!=0)
            {
                if(fieldHidden[i][j-1]!=256)
                {
                    fieldVisible[i][j-1] = fieldHidden[i][j-1];
                    if(fieldVisible[i][j-1]==0)  fieldVisible[i][j-1] = 128;
                }

            }
            if(i!=15 && j!=0)
            {
                if(fieldHidden[i+1][j-1]!=256)
                {
                    fieldVisible[i+1][j-1] = fieldHidden[i+1][j-1];
                    if(fieldVisible[i+1][j-1]==0)  fieldVisible[i+1][j-1] = 128;
                }
            }
        }
    }

    public boolean checkWin()
    {
        for(int i=0; i<16; i++)
        {
            for(int j=0; j<16; j++)
            {
                if(fieldVisible[i][j]==0)
                {
                    if(fieldHidden[i][j]!=256)
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void displayHidden()
    {
        System.out.print("\t ");
        for(int i=0; i<16; i++)
        {
            System.out.print(" " + i + "  ");
        }
        System.out.print("\n");
        for(int i=0; i<16; i++)
        {
            System.out.print(i + "\t| ");
            for(int j=0; j<16; j++)
            {
                if(fieldHidden[i][j]==0)
                {
                    System.out.print(" ");
                }
                else if(fieldHidden[i][j]==256)
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print(fieldHidden[i][j]);
                }
                System.out.print(" | ");
            }
            System.out.print("\n");
        }
    }

}
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

// initializeField()
    public void  setupField(int difficulty) {

        for (int counter = 0; counter < 40; counter++) {
            Random randomGenerator = new Random();
            int rowIndex = randomGenerator.nextInt(16);
            int colIndex = randomGenerator.nextInt(16);
            fieldHidden[rowIndex][colIndex] = 256;
        }

        buildHidden();
    }


    public void buildHidden()
    {
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                int count = 0;
                if (fieldHidden[row][col] != 256) {
                    if (row > 0) {
                        if (fieldHidden[row - 1][col] == 256) count++;
                        if (col > 0) {
                            if (fieldHidden[row - 1][col - 1] == 256) count++;
                        }
                    }
                    if (row != 15) {
                        if (fieldHidden[row + 1][col] == 256) count++;
                        if (col != 15) {
                            if (fieldHidden[row + 1][col + 1] == 256) count++;
                        }
                    }
                    if (col > 0) {
                        if (fieldHidden[row][col - 1] == 256) count++;
                        if (row != 15) {
                            if (fieldHidden[row + 1][col - 1] == 256) count++;
                        }
                    }
                    if (col != 15) {
                        if (fieldHidden[row][col + 1] == 256) count++;
                        if (row > 0) {
                            if (fieldHidden[row - 1][col + 1] == 256) count++;
                        }
                    }
                    fieldHidden[row][col] = count;
                }
            }
        }


    }

    public void displayVisible()
    {
        System.out.print("\t ");
        int n = 0;
        while (n < 16) {
            System.out.print(" " + n + "  ");
            n++;
        }
        System.out.print("\n");
        int i = 0;
        while (i < 16) {
            System.out.print(i + "\t| ");
            int j = 0;
            while (j < 16) {
                if (fieldVisible[i][j] == 0) {
                    System.out.print("?");
                }

                else if (fieldVisible[i][j] == 128) {
                    System.out.print(" ");
                }
                else {
                    System.out.print(fieldVisible[i][j]);
                }
                System.out.print(" | ");
                j++;
            }
            System.out.print("\n");
            i++;
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
        int row = 0;
        while (row < 16) {
            int col = 0;
            while (col < 16) {
                if (fieldVisible[row][col] == 0) {
                    if (fieldHidden[row][col] != 256) {
                        return false;
                    }
                }
                col++;
            }
            row++;
        }
        return true;

    }

    public void displayHidden()
    {
        System.out.print("\t ");
        int row = 0;
        do {
            System.out.print(" " + row + "  ");
            row++;
        } while (row < 16);
        System.out.print("\n");
        row = 0;
        do {
            System.out.print(row + "\t| ");
            int col = 0;
            do {
                if (fieldHidden[row][col] == 0) {
                    System.out.print(" ");
                }
                else if (fieldHidden[row][col] == 256) {
                    System.out.print("X");
                }
                else {
                    System.out.print(fieldHidden[row][col]);
                }
                System.out.print(" | ");
                col++;
            } while (col < 16);
            System.out.print("\n");
            row++;
        } while (row < 16);

    }

}
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Launcher {

    int width = 46;
    int height = 81;
    Random randomizer;
    UI gui;
    public Launcher(long seed) throws InterruptedException {
        gui = new UI(width,height,9);
        randomizer = new Random(seed);
        int[][] currentGrid = generateGrid(width,height);
        gui.setState(currentGrid);
        playLife(currentGrid);
    }

    private void playLife(int[][] currentGrid) throws InterruptedException {
        int[][] nextGrid = new int[height][width];
        for(int i=0;i<nextGrid.length;i++){
            Arrays.fill(nextGrid[i],0);
        }
        for (int i = 0; i < currentGrid.length; i++) {
            for (int j = 0; j < currentGrid[i].length; j++) {
                int amountOfNeighbors = countNeighbors(currentGrid,i,j);
          //      System.out.println((i+1)+" "+ (j+1) +" // "+amountOfNeighbors);
                if (currentGrid[i][j]==0){
                    if (amountOfNeighbors==3){nextGrid[i][j]=1;}
                }
                else {
                    if (amountOfNeighbors==2 || amountOfNeighbors==3){
                        nextGrid[i][j]=1;
                    }
                }
            }
        }
        Thread.sleep(100);
        gui.setState(nextGrid);
        playLife(nextGrid);
    }

    private int countNeighbors(int[][] currentGrid, int i, int j) {
        int left = i - 1 > 0 ? i - 1 : 0;
        int right = i + 1 < height - 1 ? i + 1 : height - 1;
        int up = j - 1 > 0 ? j - 1 : 0;
        int down = j + 1 < width - 1 ? j + 1 : width - 1;

        int neighbors = 0;
        for (int a = left; a <= right; a++) {
            for (int b = up; b <= down; b++) {
            if(currentGrid[a][b]==1){neighbors++;}
            }
        }
        if(currentGrid[i][j]==1){
            return neighbors-1;
        }
        return neighbors;
    }



    private int[][] generateGrid(int width, int height) {
        int[][] grid = new int[height][width];

        for (int i = 0; i< grid.length; i++){
            for(int j = 0; j< grid[i].length; j++){
                grid[i][j]= randomizer.nextInt(2);
            }
        }
        return grid;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("enter seed: ");
        Scanner scanner= new Scanner(System.in);
        long seed = scanner.nextLong();
        Launcher launcher = new Launcher(seed);
        int[] broo = new int[10];
           }

}

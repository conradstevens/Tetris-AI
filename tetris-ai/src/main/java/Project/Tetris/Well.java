package Project.Tetris;

import java.awt.*;
import java.io.Serializable;

public class Well implements Serializable {
    Color[][] well = new Color[12][24];

    public Well() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 23; j++) {
                if (i == 0 || i == 11 || j == 22) {
                    well[i][j] = Color.GRAY;        // Drawing Perimeter
                } else {
                    well[i][j] = Color.BLACK;       // Drawing Walls
                }
            }
        }
    }

    Color getpoint(int x, int y) {
        return well[x][y];
    }

    void setPoint(int x, int y, Color c) {
        well[x][y] = c;
    }

    private int getColumHeight(int colum) {
        int height = 0;
        Color c = Color.BLACK;
        while (c.equals(Color.BLACK)) {
            c = getpoint(colum, height);
            height++;
        }
        return 23 - height;
    }

    public int getMaxHeight() {
        int maxHeight = 0;
        for (int x = 1; x < 11; x++) {
            if (getColumHeight(x) > maxHeight) {
                maxHeight = getColumHeight(x);
            }
        }
        return maxHeight;
    }

    public int getRoughness() {
        int roughness = 0;
        for (int x = 1; x < 10; x++) {
            roughness += Math.abs(getColumHeight(x) - getColumHeight(x + 1));
        }
        return roughness;
    }

    public int getNumHoles() {
        int numHoles = 0;
        for(int x = 1; x <= 10; x++){
            for (int y = 22; y > 21 - this.getColumHeight(x); y--) {
                if (this.getpoint(x, y).equals(Color.black)) {
                    numHoles ++;
                }
                else if (this.getpoint(x, y).equals(Color.red)) {
                    numHoles += 0;
                }
            }
        }
        return numHoles;
    }

    public int getHoleDepth() {
        int holeDepth = 0;
        for(int x = 1; x <= 10; x++){
            int columHoleDepth = 0;
            boolean counting = false;
            for (int y = 22; y > 21 - this.getColumHeight(x); y--) {
                if (this.getpoint(x, y).equals(Color.black)) {
                    counting = true;
                }
                else if (this.getpoint(x, y).equals(Color.red) && counting) {
                    holeDepth ++;
                }
            }
            counting = false;
        }
        return holeDepth;
    }

    public int getBreaks() {
        boolean gap;
        int numClears = 0;

        for (int j = 21; j > 0; j--) {
            gap = false;
            for (int i = 1; i < 11; i++) {
                if (this.getpoint(i,j).equals(Color.BLACK)) {
                    gap = true;
                }
            }
            if (!gap) {
                numClears += 1;
            }
        }
        return numClears;
    }

    public void prinwell(){
        for(int x = 1; x <= 10; x++){
            for (int y = 0; y <= 22; y++) {
                try {
                    if (this.getpoint(x, y).equals(Color.black)) {
                        System.out.print("0");
                    }
                    else if (this.getpoint(x, y).equals(Color.red)) {
                        System.out.print("♦");
                    }
                } catch (NullPointerException e){
                    System.out.print("?");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

}


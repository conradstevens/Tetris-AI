package Project.Tetris;

import org.apache.commons.lang3.SerializationUtils;

import java.awt.*;
import java.io.Serializable;

public class BackEndTetris implements Serializable {

    public Pice piece = new Pice();
    public Well gameWell = new Well();
    public long score;

    // Collision test for the dropping piece
    private boolean collidesAt(int x, int y, int rotation) {
        for (Point p : piece.Tetraminos[piece.piceType][rotation]) {
            if (!(gameWell.getpoint(p.x + x, p.y + y).equals(Color.BLACK))) {
                return true;
            }
        }
        return false;
    }

    // Rotate the piece clockwise or counterclockwise
    public void rotate(int i) {
        int newRotation = (piece.rotation + i) % 4; // Modular Arethmatic
        if (newRotation < 0) {
            newRotation = 3;
        }

        if (!collidesAt(piece.position.x, piece.position.y, newRotation)) {
            piece.rotation = newRotation;
        }
    }

    // Move the piece left or right
    public void move(int i) {
        if (!collidesAt(piece.position.x + i, piece.position.y, piece.rotation)) {
            piece.position.x += i;
        }
    }

    // Drops the piece one line or fixes it to the well if it can't drop
    public void lower() {
        if (!collidesAt(piece.position.x, piece.position.y + 1, piece.rotation) &&
                !collidesAt(piece.position.x, piece.position.y, piece.rotation)) {
            piece.position.y += 1;
        } else {
            fixToWell();
            piece.newPiece();
        }
    }

    public void drop() {
        while (!collidesAt(piece.position.x, piece.position.y + 1, piece.rotation) &&
                !collidesAt(piece.position.x, piece.position.y, piece.rotation)) {
            piece.position.y += 1;
        }
        fixToWell();
    }

    // Make the dropping piece part of the well, so it is available for
    // collision detection.
    public void fixToWell() {
        for (Point p : piece.Tetraminos[piece.piceType][piece.rotation]) {
            gameWell.setPoint(piece.position.x + p.x, piece.position.y + p.y, piece.tetraminoColors[piece.piceType]);
        }
        clearRows();
    }

    private void deleteRow(int row) {
        for (int j = row-1; j > 0; j--) {
            for (int i = 1; i < 11; i++) {
                gameWell.setPoint(i,j+1, gameWell.getpoint(i,j));
            }
        }
    }

    // Clear completed rows from the field and award score according to
    // the number of simultaneously cleared rows.
    private void clearRows() {
        boolean gap;
        int numClears = 0;

        for (int j = 21; j > 0; j--) {
            gap = false;
            for (int i = 1; i < 11; i++) {
                if (gameWell.getpoint(i,j).equals(Color.BLACK)) {
                    gap = true;
                    break;
                }
            }
            if (!gap) {
                deleteRow(j);
                j += 1;
                numClears += 1;
            }
        }
        score += numClears;
    }

    public boolean checkOver(){
        return collidesAt(piece.position.x, piece.position.y, piece.rotation);
    }

    // Clones the Backend Game
    public BackEndTetris cloneTetris(){
        return  SerializationUtils.clone(this);
    }

}

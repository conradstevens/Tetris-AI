package Project.Tetris;

import Project.AI.Machine;

import javax.swing.*;
import java.awt.*;

public class Tetris extends JPanel {

	public BackEndTetris backEnd = new BackEndTetris();

	// Creates a border around the well and initializes the dropping piece
	public void init() {
		backEnd = new BackEndTetris();
		backEnd.piece.newPiece();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Paint the well
		g.fillRect(0, 0, 26*12, 26*23);
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 23; j++) {
				g.setColor(backEnd.gameWell.well[i][j]);
				g.fillRect(26*i, 26*j, 25, 25);
			}
		}
		g.setColor(Color.BLACK);
		g.fillRect(0, 26*23, 26*12, 100); // Change 100 to make bigger

		drawScore(g);
		drawPiece(g);
	}

	// Draw the falling piece
	private void drawPiece(Graphics g) {		
		g.setColor(backEnd.piece.tetraminoColors[backEnd.piece.piceType]);
		for (Point p : backEnd.piece.Tetraminos[backEnd.piece.piceType][backEnd.piece.rotation]) {
			g.fillRect((p.x + backEnd.piece.position.x) * 26,
					   (p.y + backEnd.piece.position.y) * 26,
					   25, 25);
		}
	}

	// Makes numbers
	private double reduceDeciaml(double num) {
		num = num * 1000;
		num = (int) num;
		return num/1000;
	}

	private void drawScore(Graphics g) {
		g.setColor(Color.WHITE);

		g.drawString("Score: " + backEnd.score + "                    AggHeight: " +
				reduceDeciaml(Machine.printAggregateHeight) + "         Roughness " +
				reduceDeciaml(Machine.printRoughness),
				0, 26*24 - 6);

		g.drawString("NumHoles: " +
				reduceDeciaml(Machine.printNumHoles) + "         Breake: " +
				reduceDeciaml(Machine.printHoleDepth) + "     MaxHeightDiff: " + reduceDeciaml(Machine.printMaxDiff),
				0, 26*24 + 12);

		g.drawString("-------------------------------------------------------------------------------",
				0, 26*24 + 24);

		g.drawString("AggHiehgt: " + backEnd.gameWell.getAggregateHeight() +
                "                    Roughness: " + backEnd.gameWell.getRoughness() +
                "     	  NumHoles: " + backEnd.gameWell.getNumHoles()
                ,0,26*24 +36 );


		g.drawString("HoleDepth: " + backEnd.gameWell.getHoleDepth() +
                     "               CheckBreak: " + (int) Machine.printBreake + "      HeightDiff: " +
				backEnd.gameWell.getHightDiff(), 0, 26*24 + 60);
	}
}

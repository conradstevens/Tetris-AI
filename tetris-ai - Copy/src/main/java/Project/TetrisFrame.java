package Project;

import Project.AI.Genome;
import Project.AI.Machine;
import Project.AI.MasterAI;
import Project.Tetris.Tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.SwingUtilities.invokeLater;

public class TetrisFrame {
    private final Tetris game = new Tetris();

    // Make the falling piece drop every second
    public void startPlay() {
        new Thread() {
            @Override
            public void run() {
                game.init();
                repaint();
                //noinspection InfiniteLoopStatement
                while (true) {
                    try {
                        Thread.sleep(500);
                        game.backEnd.lower();
                    } catch (InterruptedException e) {
                    }

                    if (game.backEnd.checkOver()) {
                        game.init();
                    }
                    repaint();
                }
            }
        }.start();
    }

    // Make the falling piece drop every second And Have Project.AI make moves
    public void RunAiMachineDefault(Genome genome) {
        final Machine machine = new Machine(game, genome);
        new Thread() {
            @Override
            public void run() {
                game.init();
                repaint();

                while (true) {
                    try {
                        Thread.sleep(80);
                        game.backEnd.lower();
                    } catch (InterruptedException e) { System.out.println("Oops");}

                    if (game.backEnd.checkOver()) {
                        game.init();
                    }
                    machine.runMashine();
                    repaint();
                }
            }
        }.start();
    }

    // Have a Genome play an invisile game and return it's score
    public int scilentAIRun(Genome genome){
        Machine invisMachine = new Machine(game, genome);
        boolean gameRunning = true;
        game.init();

        while (gameRunning) {
            game.backEnd.lower();
            if (game.backEnd.checkOver()) {
                gameRunning = false;
            }
            invisMachine.runMashine();
        }
        System.out.println(game.backEnd.score);
        return game.backEnd.score;
    }

    public void makeFrame() {
        JFrame f = makeFameBox();
        f.add(game);

        // Keyboard controls
        f.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
            }

            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        game.backEnd.rotate(-1);
                        break;
                    case KeyEvent.VK_DOWN:
                        game.backEnd.rotate(+1);
                        break;
                    case KeyEvent.VK_LEFT:
                        game.backEnd.move(-1);
                        break;
                    case KeyEvent.VK_RIGHT:
                        game.backEnd.move(+1);
                        break;
                    case KeyEvent.VK_SPACE:
                        game.backEnd.drop();
                        break;
                }
                repaint();
            }
            public void keyReleased(KeyEvent e) { }
        } );
    }

    public JFrame makeFameBox(){
        JFrame f = new JFrame("Project/Project.Tetris");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(12 * 26 + 15, 27 * 24 + 95); // Change 95 to make bigger
        f.setVisible(true);
        return f;
    }

    void repaint() {
        invokeLater(() -> game.repaint());
    }
}

package Project;

import Project.AI.Machine;
import Project.Tetris.Tetris;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static javax.swing.SwingUtilities.invokeLater;

public class TetrisFrame {
    private final Tetris game = new Tetris();

    public void startPlay() {
        // Make the falling piece drop every second
        new Thread() {
            @Override
            public void run() {
                game.init();
                repaint();

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

    public void startAi() {
        // Make the falling piece drop every second And Have Project.AI make moves
        final Machine machine = new Machine(game);
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

    public void makeFrame(){
        JFrame f = new JFrame("Project/Project.Tetris");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(12 * 26 + 15, 27 * 24 + 95); // Change 95 to make bigger
        f.setVisible(true);

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

            public void keyReleased(KeyEvent e) {
            }
        });
    }

    void repaint() {
        invokeLater(() -> game.repaint());
    }
}

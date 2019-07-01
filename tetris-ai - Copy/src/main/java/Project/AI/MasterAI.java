package Project.AI;
import Project.Tetris.Tetris;
import Project.TetrisFrame;

import javax.swing.*;
import java.util.HashMap;
import java.lang.Math;

import static javax.swing.SwingUtilities.invokeLater;

public class MasterAI {

    private Competitors competitors;
    private Genome      genRepTet;
    private int         numMutants;
    private double      perMutate;
    private JFrame      frame;
    private Tetris      visGame;

    //Establishes the classes variables
    public MasterAI(int numMutants, double perMutate) {
        this.perMutate  = perMutate;
        this.genRepTet  = new Genome(Math.random(), Math.random(), Math.random(), Math.random(), Math.random());
        TetrisFrame tf  = new TetrisFrame();
        frame           = tf.makeFameBox();

        runAIOptimizationLoop();
    }

    // Runs the AI optimization loop
    private void runAIOptimizationLoop() {
        //noinspection InfiniteLoopStatement // An in infitire loop Running the AI.
        while (true) {
            runCompetitors();
            //runVisualTetris();
        }
    }

    //Runs the tetris visuals representative of the current generation
    private void runVisualTetris() {
        visGame = new Tetris();
        visGame.init();

        frame.add(visGame);
        frame.invalidate();
        frame.validate();
        repaint();

        Machine machine = new Machine(visGame, genRepTet);

        while (!visGame.backEnd.checkOver()) {
            try {
                Thread.sleep(10);
                visGame.backEnd.lower();

            } catch (InterruptedException e) { e.printStackTrace(); }
            machine.runMashine();
            repaint();
        }
    }

    // Runs the Competitors in the background and updates teh genome to be the most effective mutant
    private void runCompetitors() {
        competitors = new Competitors(genRepTet, numMutants, perMutate);
        competitors.runCompetitors();
        genRepTet = competitors.breed();
    }



    void repaint() {
        invokeLater(() -> visGame.repaint());
    }
}

package Project.AI;
import Project.AI.Tournament.Competitors;
import Project.AI.Tournament.RoundRobin;
import Project.Tetris.Tetris;
import Project.TetrisFrame;

import javax.swing.*;
import java.lang.Math;

import static javax.swing.SwingUtilities.invokeLater;

public class MasterAI {

    private Genome  genRepTet;
    private JFrame  frame;
    private Tetris  visGame;
    private int     numMutants;
    private int     generation  = 0;
    private int     bestScore   = 0;
    private double  perMutate;
    private double  numRuns;

    //Establishes the classes variables
    public MasterAI(int numMutants, double numRuns, double perMutate) {
        this.numMutants = numMutants;
        this.perMutate  = perMutate;
        this.numRuns    = numRuns;
        this.genRepTet  = new Genome(Math.random(), Math.random(), Math.random(), Math.random(), Math.random(), Math.random());
        TetrisFrame tf  = new TetrisFrame();
        frame           = tf.makeFameBox();
    }

    // Runs the AI optimization loop
    void runAIOptimizationLoop() {
        //noinspection InfiniteLoopStatement // An in infitire loop Running the AI.
        while (true) {
            System.out.println("\nRunning Generation: " + generation + " Best Score: " + bestScore); generation += 1;
            System.out.println(genRepTet);
            runVisualTetris();
            System.out.println("\nCreating next generation\n" +
                    "------------------------------------------------------------------------------------------------");
            //runCompetitors();
            runTournament();

            if (visGame.backEnd.score > bestScore) {
                bestScore = visGame.backEnd.score;
            }
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
                Thread.sleep(1);
                visGame.backEnd.lower();

            } catch (InterruptedException e) { e.printStackTrace(); }
            machine.runMashine();
            repaint();
        }
    }

    // Runs the Competitors in the background and updates the genome to be the most effective mutant
    private void runCompetitors() {
        Competitors competitors = new Competitors(genRepTet, numMutants, numRuns, perMutate);
        competitors.runCompetitors();
        genRepTet = competitors.breed();
    }

    // Runs the back propagation tournament to get the next generation
    private void  runTournament(){
        RoundRobin  tourney     = new RoundRobin(genRepTet, numMutants, numRuns, perMutate);
        this.genRepTet = tourney.runTournament();
    }


    void repaint() {
        invokeLater(() -> visGame.repaint());
    }
}

package Project.AI;

import Project.AI.AIData.GenomeWriter;
import Project.Tetris.Tetris;
import Project.TetrisFrame;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

class RunGeneration {

    private Genome      genome;
    private TetrisFrame tetrisFrame;
    private JFrame      frame;
    private Tetris      game;
    private long        sleepTime;
    private boolean     visual;

    RunGeneration(boolean visual, long sleepTime) {
        this.visual = visual;
        this.sleepTime = sleepTime;
        this.tetrisFrame = new TetrisFrame();

        TetrisFrame tf = new TetrisFrame();
        if (visual) {
            frame = tf.makeFameBox();
        }
    }

    long run(Genome genome, int generation) {
        this.genome = genome;
        long score;
        System.out.println("\nRunning Generation: " + generation + "\n" + "Genome: \n" + genome);
        writeGenerationData();

        if (this.visual) {
            runVisualTetris();
            score = game.backEnd.score;
        } else {
            score = tetrisFrame.scilentAIRun(genome);
        }
        writeGenerationScore(score);
        return score;
    }

    //Runs the tetris visuals representative of the current generation
    private void runVisualTetris() {
        game = new Tetris();
        game.init();
        frame.add(game);
        frame.invalidate();
        frame.validate();
        repaint();

        Machine machine = new Machine(game, genome);

        while (!game.backEnd.checkOver()) {
            try {
                Thread.sleep(sleepTime);
                game.backEnd.lower();

            } catch (InterruptedException e) { e.printStackTrace(); }
            machine.runMashine();
            repaint();
        }
    }

    // Write Generation to GenomeGenerationData
    private void writeGenerationData() {
        GenomeWriter genomeWriter = new GenomeWriter("/C://Users//conra//Desktop//tetris-ai//src//main//java//Project//AI//GenomeGenerationData/");
        genomeWriter.writeGenome(genome);
        genomeWriter.closeWriter();
    }

    // Writes the generations score
    private void writeGenerationScore(long score) {
        GenomeWriter genomeWriter = new GenomeWriter("/C://Users//conra//Desktop//tetris-ai//src//main//java//Project//AI//GenomeGenerationData/");
        genomeWriter.writeScore(score);
        genomeWriter.closeWriter();
    }

    private void repaint() {
        invokeLater(() -> game.repaint());
    }
}

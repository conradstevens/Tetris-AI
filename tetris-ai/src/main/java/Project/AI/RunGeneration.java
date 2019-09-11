package Project.AI;

import Project.AI.AIData.GenomeWriter;
import Project.Tetris.Tetris;
import Project.TetrisFrame;

import javax.swing.*;

import static javax.swing.SwingUtilities.invokeLater;

class RunGeneration {

    private Genome      genome;
    private TetrisFrame tetrisFrame;
    private long        sleepTime;
    private boolean     visual;

    RunGeneration(boolean visual, long sleepTime) {
        this.visual         = visual;
        this.sleepTime      = sleepTime;
        this.tetrisFrame    = new TetrisFrame();
    }

    long run(Genome genome, int generation) {
        this.genome = genome;
        long score;
        System.out.println("\nRunning Generation: " + generation + "\n" + "Genome: \n" + genome);
        writeGenerationData(); // Writes data into data base

        if (this.visual) {
            score = tetrisFrame.visualAiRun(genome, this.sleepTime);
        } else {
            score = tetrisFrame.scilentAIRun(genome);
        }
        writeGenerationScore(score);
        return score;
    }

    // Write Generation to GenomeGenerationData
    private void writeGenerationData() {
        GenomeWriter genomeWriter = new GenomeWriter("/C://Users//conra//Desktop//tetris-ai//src//main//java//Project//AI//AIData//GenomeGenerationData/");
        genomeWriter.writeGenome(genome);
        genomeWriter.closeWriter();
    }

    // Writes the generations score
    private void writeGenerationScore(long score) {
        GenomeWriter genomeWriter = new GenomeWriter("/C://Users//conra//Desktop//tetris-ai//src//main//java//Project//AI//AIData//GenomeGenerationData/");
        genomeWriter.writeScore(score);
        genomeWriter.closeWriter();
    }

}

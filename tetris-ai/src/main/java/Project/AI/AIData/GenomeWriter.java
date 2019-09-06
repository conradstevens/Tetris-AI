package Project.AI.AIData;
import Project.AI.Genome;

import java.io.*;

public class GenomeWriter {

    private BufferedWriter writer;

    // Oppens the text document where the data is stored
    public GenomeWriter(String path) {
        try {
            this.writer = new BufferedWriter(new FileWriter(path, true));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Writes the genome data in the data base
    public void writeGenome(Genome genome) {
        try {
            String genomeData = ( "\n"+
                    genome.genome[0] + ";" +
                    genome.genome[1] + ";" +
                    genome.genome[2] + ";" +
                    genome.genome[3] + ";" +
                    genome.genome[4] + ";" +
                    genome.genome[5] + ";" );

            this.writer.append(genomeData);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Writes the score of the genome
    public void writeScore(long score) {
        try {
        String genomeScore = score + "";
        this.writer.append(genomeScore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Closes the writer
    public void closeWriter() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package Project.AI.Tournament;

import Project.AI.Genome;
import Project.AI.AIData.GenomeWriter;
import Project.TetrisFrame;

import java.util.ArrayList;

public class Competitors {
    ArrayList<Genome> compList = new ArrayList<Genome>();
    GenomeWriter genomeWriter;
    private double numRuns;

    // Creates dictionary of mutants, with score set to zero
    public Competitors(Genome centralGenome, int numCompetitors, double numRuns, double permutation) {
        this.genomeWriter = new GenomeWriter("C://Users//conra//Desktop//tetris-ai//src//main//java//Project//AI/GenomeData");
        this.numRuns = numRuns;
        for (int index = 0; index < numCompetitors; index ++) {
            compList.add(centralGenome.mutate(permutation));
        }
        compList.add(centralGenome);
        compList.add(centralGenome);
    }

    // Runs all the compSet and determines their score
    public void runCompetitors(){
        TetrisFrame scilentScoreFrame = new TetrisFrame();
        int gameNum = 1;
        for (Genome genome : compList){
            genomeWriter.writeGenome(genome);
            for (int j = 0; j < numRuns; j++) {
                genome.sumSocre += scilentScoreFrame.scilentAIRun(genome);
            }
            genomeWriter.writeScore(genome.sumSocre);
            System.out.print(gameNum + ", ");
            gameNum += 1;
        }
        System.out.println();
    }

    // breeds the 2 genomes, prioritizing the genomes quaitly based on score
    public Genome breed(){
        Genome  parent1 = getParent();
        Genome  parent2 = getParent();
        return parent1.breedWith(parent2);
    }

    // Once the scores of each genome have been determined the best genomes (the parents) can be chosen
    // Just a helper for breed
    private Genome getParent(){
        Genome  parent      = new Genome();
        int     parentScore = 0;

        for (Genome genome : compList){
            if (genome.sumSocre >= parentScore) {
                parentScore    = genome.sumSocre;
                parent         = genome;
            }
        }
        compList.remove(parent);
        return parent;
    }
}

package Project.AI;

import Project.TetrisFrame;

import java.util.ArrayList;
import java.util.HashMap;

class Competitors {
    private ArrayList<Genome> compList = new ArrayList<Genome>();
    private double numRuns;

    // Creates dictionary of mutants, with score set to zero
    Competitors(Genome centralGenome, int numCompetitors, double numRuns, double permutation) {
        this.numRuns = numRuns;
        for (int index = 0; index < numCompetitors; index ++) {
            compList.add(centralGenome.mutate(permutation));
        }
        compList.add(centralGenome);
    }

    // Runs all the compSet and determines their score
    void runCompetitors(){
        TetrisFrame scilentScoreFrame = new TetrisFrame();
        int gameNum = 1;
        for (Genome genome : compList){
            System.out.print(gameNum + ", ");
            for (int j = 0; j < numRuns; j++) {
                genome.sumSocre += scilentScoreFrame.scilentAIRun(genome);
            }
            gameNum += 1;
        }
    }

    // breeds the 2 genomes, prioritizing the genomes quaitly based on score
    Genome breed(){
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

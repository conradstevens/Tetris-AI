package Project.AI;

import Project.TetrisFrame;

import java.util.HashMap;

class Competitors {
    private HashMap<Genome, Integer> compSet;

    // Creates dictionary of mutants, with score set to zero
    Competitors(Genome centralGenome, int numCompetitors, double permutation) {
        compSet = new HashMap<>();
        for (int index = 0; index < numCompetitors - 1; index ++){
            Genome mutant = centralGenome.mutate(permutation);
            compSet.put(mutant, 0);
        }
        compSet.put(centralGenome, 0);
    }

    // Runs all the compSet and determines their score
    void runCompetitors(){
        TetrisFrame scilentScoreFrame = new TetrisFrame();
        for (Genome genome : compSet.keySet()){
            compSet.put(genome, scilentScoreFrame.scilentAIRun(genome));
        }
    }

    //
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

        for (Genome genome : compSet.keySet()){
            if (compSet.get(genome) >= parentScore) {
                parentScore    = compSet.get(genome);
                parent         = genome;
            }
        }
        compSet.remove(parent);
        return parent;
    }
}

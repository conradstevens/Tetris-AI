package Project.AI;

import Project.TetrisFrame;

import java.util.HashMap;

class Competitors {
    private HashMap<Genome, Integer> competitors;

    // Creates dictionary of mutants, with score set to zero
    Competitors(Genome centralGenome, int numCompetitors, double permutation) {
        for (int index = 0; index < numCompetitors - 1; index ++){
            Genome mutant = centralGenome.mutate(permutation);
            //noinspection ConstantConditions
            competitors.put(mutant, 0);
        }
        competitors.put(centralGenome, 0);
    }

    // Runs all the competitors and determines their score
    void runCompetitors(){
        TetrisFrame scilentScoreFrame = new TetrisFrame();
        for (Genome genome : competitors.keySet()){
            competitors.put(genome, scilentScoreFrame.scilentAIRun(genome));
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

        for (Genome genome : competitors.keySet()){
            if (competitors.get(genome) >= parentScore) {
                parentScore    = competitors.get(genome);
                parent         = genome;
            }
        }
        competitors.remove(parent);
        parent = (Genome) competitors.keySet().toArray()[0];
        competitors.remove(parent);
        return parent;
    }
}

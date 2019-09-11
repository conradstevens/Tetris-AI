package Project.AI.Tournament;
import Project.AI.Genome;
import Project.AI.Mutation;
import java.util.ArrayList;

public class RoundRobin extends Competitors {

    private ArrayList<TournNode>    round;
    private ArrayList<Genome>       allGens;

    //Round Robin
    public RoundRobin(Genome genRepTet, int numMutants, double numRuns, Mutation mutator){
        super(genRepTet, numMutants, numRuns, mutator);  // numNodes must be a natural log of 2
    }

    // Runs the entire tournament
    public Genome runTournament(Boolean useBest) {
        System.out.println("Runing Round: ");
        this.allGens = this.compList;

        while(compList.size() > 1) {
            makeRound();
            runRound();
        }
        super.genomeWriter.closeWriter();
        return getGenRep(useBest);
    }

    // Creates a round from a list of genomes
    private void makeRound() {
        this.round = new ArrayList<TournNode>();
        runCompetitors();
        for (int i = 0; i < this.compList.size(); i++) {
            if (i % 2 == 0) {
                TournNode node = new TournNode(this.compList.get(i), this.compList.get(i + 1));
                round.add(node);
            }
        }
    }

    // Runs the round
    private void runRound() {
        this.compList = new ArrayList<Genome>();
        for (TournNode node : this.round) {
            Genome genomeChild = node.runNode();
            this.allGens.add(genomeChild);
            this.compList.add(genomeChild);
        }
    }

    // Returns the leader of the genome based on what (useBest) is set to
    private Genome getGenRep(boolean useBest) {
        Genome returnGenome = new Genome();
        if (useBest) {
            long bestScore = 0;
            for (Genome genome : this.allGens) {
                if (genome.sumSocre > bestScore) {
                    returnGenome    = genome;
                    bestScore       = genome.sumSocre;
                }
            }
        } else {
            return compList.get(0);
        }
        return returnGenome;
    }
}

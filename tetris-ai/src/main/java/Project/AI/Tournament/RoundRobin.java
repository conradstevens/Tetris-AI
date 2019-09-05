package Project.AI.Tournament;
import Project.AI.Genome;
import org.w3c.dom.ranges.Range;
import java.util.ArrayList;

public class RoundRobin extends Competitors{

    private ArrayList<TournNode>    round;

    //Round Robin
    public RoundRobin(Genome genRepTet, int numMutants, double numRuns, double perMutate) {
        super(genRepTet, numMutants, numRuns, perMutate);  // numNodes must be a natural log of 2
    }

    // Runs the entire tournament
    public Genome runTournament() {
        System.out.println("Runing Round: ");
        while(compList.size() > 1) {
            makeRound();
            runRound();
        }
        return compList.get(0);
    }

    // Creates a round from a list of genomes
    private void makeRound() {
        round = new ArrayList<TournNode>();
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
            this.compList.add(genomeChild);
        }
    }
}

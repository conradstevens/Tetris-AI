package Project.AI.Tournament;

import Project.AI.Genome;

public class TournNode {

    private Genome genome1;
    private Genome genome2;

    // A node in the round robin tournament
    TournNode(Genome genome1, Genome genome2) {
        this.genome1 = genome1;
        this.genome2 = genome2;
    }

    Genome runNode() {
    return genome1.breedWith(genome2);
    }
}

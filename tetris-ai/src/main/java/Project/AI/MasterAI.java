package Project.AI;
import Project.AI.Tournament.RoundRobin;
import java.lang.Math;


public class MasterAI {
    private Genome          genRepTet;
    private RunGeneration   genRunner;
    private Mutation        mutator;
    private Boolean         useBest;
    private long            roundScore;
    private int             numMutants;
    private double          numRuns;
    private int             generation  = 0;
    private long            bestScore   = 0;

    // Establishes the classes variables
    MasterAI(int numMutants, double numRuns, Mutation mutator, long sleepTime, Boolean showRep, Boolean useBest) {
        this.numMutants = numMutants;
        this.numRuns    = numRuns;
        this.useBest    = useBest;

        this.mutator    = mutator;
        this.genRepTet  = new Genome(Math.random(), Math.random(), Math.random(), Math.random(), Math.random(), Math.random());
        this.genRunner  = new RunGeneration(showRep, sleepTime);
    }

    // Runs the AI optimization loop
    void runAIOptimizationLoop() {
        //noinspection InfiniteLoopStatement // An in infitire loop Running the AI.
        while (true) {
            long score = genRunner.run(this.genRepTet, generation);

            this.mutator.score  = score;
            this.roundScore     = score;
            generation += 1;
            if (score > bestScore) {
                bestScore = score;
            }
            writeGenMsg();
            runTournament();
        }
    }

    // Runs the back propagation tournament to get the next generation
    private void  runTournament() {
        RoundRobin tourney  = new RoundRobin(genRepTet, numMutants, numRuns, mutator);
        this.genRepTet      = tourney.runTournament(this.useBest);
    }

    // Writes the details of the generation
    private void writeGenMsg() {
        System.out.println("\nGeneration Score: " + this.roundScore);
        System.out.println("Best Score: " + bestScore);
        System.out.println("\n------------------------------------------------------------------------------------------------\n" +
                "\nMutation level: " + this.mutator.getMutationAmount());
    }
}

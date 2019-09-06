package Project.AI;
import Project.AI.Tournament.Competitors;
import Project.AI.Tournament.RoundRobin;
import java.lang.Math;


public class MasterAI {
    private Genome          genRepTet;
    private RunGeneration   genRunner;
    private int             numMutants;
    private int             generation  = 0;
    private long            bestScore   = 0;
    private double          perMutate;
    private double          numRuns;

    // Establishes the classes variables
    MasterAI(int numMutants, double numRuns, double perMutate, long sleepTime, Boolean showRep) {
        this.numMutants = numMutants;
        this.perMutate  = perMutate;
        this.numRuns    = numRuns;

        this.genRepTet  = new Genome(Math.random(), Math.random(), Math.random(), Math.random(), Math.random(), Math.random());
        this.genRunner  = new RunGeneration(showRep, sleepTime);
    }

    // Runs the AI optimization loop
    void runAIOptimizationLoop() {
        //noinspection InfiniteLoopStatement // An in infitire loop Running the AI.
        while (true) {
            long score = genRunner.run(genRepTet, generation);

            generation += 1;
            if (score > bestScore) {
                bestScore = score;
            }
            writeGenMsg();
            runTournament();
        }
    }

    // Runs the back propagation tournament to get the next generation
    private void  runTournament(){
        RoundRobin  tourney     = new RoundRobin(genRepTet, numMutants, numRuns, perMutate);
        this.genRepTet = tourney.runTournament();
    }

    // Runs the Competitors in the background and updates the genome to be the most effective mutant
    private void runCompetitors() {
        Competitors competitors = new Competitors(genRepTet, numMutants, numRuns, perMutate);
        competitors.runCompetitors();
        genRepTet = competitors.breed();
    }

    // Writes the details of the generation
    private void writeGenMsg() {
        System.out.println("\nBest Score: " + bestScore);
        System.out.println("Creating next generation\n" +
                "------------------------------------------------------------------------------------------------\n");
    }
}

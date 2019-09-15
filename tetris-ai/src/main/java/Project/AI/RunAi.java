package Project.AI;

@SuppressWarnings("ConstantConditions")
public class RunAi {

    public static void main(String[] args) {
        // ***************************************************************************************************
        // ***************************************************************************************************
        // ***                                                                                             ***
        // ***                       ---   ENTER THE AI INFORMATION HERE  ---                              ***
        // ***
                                    int     tournametSize      = 63;     // Must be of form (2^n - 1), n N
                                    double  numRunsPerGenome   = 1;     // Only needed if pices are Random
                                    double  horzCompression    = 0.1;   // How fast will mutations decrease in size
                                    double  startMutation      = 1.2;   // How much mutations to start
                                    long    sleepTime          = 1;

                                    boolean useBest            = true;  // Uses the best genome in the backPropagation
                                                                        // not the result of the backPropagation
                                    boolean runVisual          = false;
        // ***                                                                                             ***
        // ***                                                                                             ***
        // ***************************************************************************************************
        // ***************************************************************************************************

        Mutation mutator    = new Mutation(horzCompression, startMutation);
        MasterAI masterAI   = new MasterAI(tournametSize, numRunsPerGenome, mutator, sleepTime, runVisual, useBest);
        masterAI.runAIOptimizationLoop();
    }
}

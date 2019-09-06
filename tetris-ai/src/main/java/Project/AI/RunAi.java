package Project.AI;

public class RunAi {

    public static void main(String[] args) {
        // ***************************************************************************************************
        // ***************************************************************************************************
        // ***                                                                                             ***
        // ***                       ---   ENTER THE AI INFORMATION HERE  ---                              ***
        // ***
                                    int     tournametSize      = 126;  // Must be of form (2^n - 2), n N
                                    double  numRunsPerGenome   = 1;    // Only needed if pices are Random
                                    double  percentMutation    = 0.5;  // Must be between [0,1]
                                    long    sleepTime          = 1;
                                    boolean runVisual          = false;
        // ***                                                                                             ***
        // ***                                                                                             ***
        // ***************************************************************************************************
        // ***************************************************************************************************

        MasterAI masterAI = new MasterAI(tournametSize, numRunsPerGenome, percentMutation, sleepTime, runVisual);
        masterAI.runAIOptimizationLoop();
    }
}

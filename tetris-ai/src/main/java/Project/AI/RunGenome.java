package Project.AI;

public class RunGenome {


    // Takes in a user input Genome and runs the Tetris AI with this genome
    public static void main(String[] args) {

        // ***************************************************************************************************
        // ***************************************************************************************************
        // ***                                                                                             ***
        // ***                   ---   ENTER THE GENOME INFORMATION HERE  ---                              ***
        // ***                                                                                             ***
                                            double aggHeight    = 1;
                                            double roughness    = 1;
                                            double numHoles     = 1;
                                            double holeDepth    = 1;
                                            double breake       = 1;
                                            double hightDiff    = 1;

                                            long   sleepTime    = 30;
        // ***                                                                                             ***
        // ***                                                                                             ***
        // ***************************************************************************************************
        // ***************************************************************************************************

        Genome runningGenome    = new Genome(aggHeight,  roughness, numHoles, holeDepth, breake, hightDiff);
        RunGeneration genRun    = new RunGeneration(true, sleepTime);

        genRun.run(runningGenome, 1);
    }
}

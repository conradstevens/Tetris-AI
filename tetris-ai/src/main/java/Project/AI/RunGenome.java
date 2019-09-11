package Project.AI;

public class RunGenome {


    // Takes in a user input Genome and runs the Tetris AI with this genome
    public static void main(String[] args) {

        // ***************************************************************************************************
        // ***************************************************************************************************
        // ***                                                                                             ***
        // ***                   ---   ENTER THE GENOME INFORMATION HERE  ---                              ***
        // ***                                                                                             ***
                                            double aggHeight    = 0.4892611682771852;
                                            double roughness    = 0.5081974962380555;
                                            double numHoles     = 0.4982826320756096;
                                            double holeDepth    = 0.13136056990618095;
                                            double breake       = 0.4464740257527826;
                                            double hightDiff    = 0.193593847028373;

                                            long   sleepTime    = 2;

        // Score: 102

        // ***                                                                                             ***
        // ***                                                                                             ***
        // ***************************************************************************************************
        // ***************************************************************************************************

        Genome runningGenome    = new Genome(aggHeight,  roughness, numHoles, holeDepth, breake, hightDiff);
        RunGeneration genRun    = new RunGeneration(false, sleepTime);

        genRun.run(runningGenome, 1);
    }
}

package Project.AI;

public class RunGenome {


    // Takes in a user input Genome and runs the Tetris AI with this genome
    public static void main(String[] args) {

        // ***************************************************************************************************
        // ***************************************************************************************************
        // ***                                                                                             ***
        // ***                   ---   ENTER THE GENOME INFORMATION HERE  ---                              ***
        // ***                                                                                             ***
                                            double aggHeight    = 0.400116655;
                                            double roughness    = 0.365804379;
                                            double numHoles     = 0.656085579;
                                            double holeDepth    = 0.184972517;
                                            double breake       = 0.466003722;
                                            double hightDiff    = 0.155792267;

                                            long   sleepTime    = 10;


        //        0.400116655	0.365804379	0.656085579	0.184972517	0.466003722	0.155792267	247


        // ***                                                                                             ***
        // ***                                                                                             ***
        // ***************************************************************************************************
        // ***************************************************************************************************

        Genome runningGenome    = new Genome(aggHeight,  roughness, numHoles, holeDepth, breake, hightDiff);
        RunGeneration genRun    = new RunGeneration(true, sleepTime);

        genRun.run(runningGenome, 1);
    }
}

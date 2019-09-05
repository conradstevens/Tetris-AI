package Project.AI;

import Project.TetrisFrame;

public class RunAi {

    public static void main(String[] args) {
        MasterAI masterAI = new MasterAI(126, 1, 0.5); // First number should be of form (2^x - 2)
        masterAI.runAIOptimizationLoop();
    }
}

package Project.AI;

import Project.TetrisFrame;

public class RunAi {

    public static void main(String[] args) {
        MasterAI masterAI = new MasterAI(40, 1, 0.50);
        masterAI.runAIOptimizationLoop();
    }
}

package Project.AI;

import Project.TetrisFrame;

public class RunAi {

    public static void main(String[] args) {
        //TetrisFrame tetrisFrame = new TetrisFrame();
        //tetrisFrame.makeFrame();
        //tetrisFrame.startAi();
        MasterAI masterAI = new MasterAI(10, 0.03);
    }
}

package Project.AI;

import Project.Tetris.BackEndTetris;
import Project.Tetris.Tetris;

public class Machine {

    // Genes
    public static double printAggregateHeight;
    public static double printRoughness;
    public static double printNumHoles;
    public static double printHoleDepth;
    public static double printBreake;
    public static double printMaxDiff;

    private Tetris game;
    private int[] bestMove = {5, 0};
    public Genome machineGenome;

    public Machine(Tetris game, Genome machineGenome){
        this.game = game;
        this.machineGenome = machineGenome;
    }

    // Runs Mashine
    public void runMashine(){

        if (game.backEnd.piece.position.y == 0) {
            bestMove = bestMove();
        }
        rotatePice(bestMove[1], game.backEnd);
        movePiceTo(bestMove[0], game.backEnd);
    }

    private int[] bestMove() {
        //Returns the position and rotation of the best Project.Project.Tetris move
        int[] bestMove = {2, 0};
        double bestFitness = -100;

        for(int x = 0; x <= 11; x++) {      //Goint the extra piece to ensure every shape is accounted for.
            for(int r = 0; r <= 3; r++) {
                double testFitness = getFitnessOfMove(x, r);
                if (testFitness > bestFitness) {
                    bestFitness = testFitness;
                    bestMove [0] = x;
                    bestMove [1] = r;
                }
            }
        }
        // System.out.println(bestMove[0] + " " + bestMove[1]);
        return bestMove;
    }

    // Returns Fitness Score of move ie. proximity to move will bring well fitness to mashinegenome
    private double getFitnessOfMove(int x, int r) {
        BackEndTetris testWell = this.game.backEnd.cloneTetris();
        long oldScore = this.game.backEnd.score;

        dropPice(x, r, testWell);
        long scorDiff = testWell.score - oldScore;
        printBreake = scorDiff;
        return machineGenome.returnFitness(testWell.gameWell, scorDiff);
    }

    // Moves piece One Step
    private void movePiceTo(int x, BackEndTetris bet){
        if (bet.piece.position.x > x){
            bet.move(-1);
        }
        else if (game.backEnd.piece.position.x < x){

            bet.move(+1);
        }
    }

    // Rotates piece one step
    private void rotatePice(int r, BackEndTetris bet){
        int doesNotFit = 0;
        while (((bet.piece.rotation % 4) != (r % 4)) && doesNotFit < 4) {
            bet.rotate(1);
            doesNotFit += 1;
        }
    }

    // Places piece
    private void dropPice(int x, int r, BackEndTetris bet){
        int trys = 0;
        while (trys != 5 && bet.piece.position.x != x){
            trys ++;

            movePiceTo(x, bet);
            rotatePice(r, bet);
        }
        bet.drop();
    }
}

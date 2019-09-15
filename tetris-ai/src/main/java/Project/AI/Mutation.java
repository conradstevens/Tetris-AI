package Project.AI;
import java.lang.reflect.Array;
import java.math.*;
import java.util.Arrays;

public class Mutation {
    double horizComp;
    double startVal;
    long score;

    Mutation(double horizComp, double startVal) {
        this.horizComp  = horizComp;
        this.startVal   = startVal;
    }

    // Mutates the genome with respect to the peramiters
    double[] mutateGenome(double[] parentGenome){
        double[] mutatedGenome = new double[6];
        double[] mutations = getMutationVector();
        for (int i=0; i < 6; i++) {
            mutatedGenome[i] = parentGenome[i] + mutations[i];
            if (mutatedGenome[i] > 1){mutatedGenome[i] = 1;}
            if (mutatedGenome[i] < 0){mutatedGenome[i] = 0;}
        }
        return mutatedGenome;
    }

    double getMutationAmount(){
        double denom = Math.pow((1+score), horizComp);
        return startVal/denom;
    }

    // Returns a vector where each index coresponds with how much a gene will mutate
    private double[] getMutationVector() {
        double[] startV = new double[]{
                0,
                Math.random(),
                Math.random(),
                Math.random(),
                Math.random(),
                Math.random(),
                1};
        Arrays.sort(startV);

        double mutAmt = getMutationAmount();
        return new double[]{
                (startV[0] - startV[1]) * mutAmt * plusMinus(),
                (startV[1] - startV[2]) * mutAmt * plusMinus(),
                (startV[2] - startV[3]) * mutAmt * plusMinus(),
                (startV[3] - startV[4]) * mutAmt * plusMinus(),
                (startV[4] - startV[5]) * mutAmt * plusMinus(),
                (startV[5] - startV[6]) * mutAmt * plusMinus(),
        };
    }

    // Returns either 1 or -1 on a 50/50 basis
    private double plusMinus() {
        if (Math.random() > 0.5) {
            return -1;
        }
        return 1;
    }
}

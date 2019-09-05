package Project.AI;

import Project.Tetris.Well;

import java.lang.Math;
import java.util.Arrays;

public class Genome {
    // Genome
    private double[] genome = new double[6];
    public int sumSocre    = 0;

    public Genome(){}

    Genome(double aggHeight, double roughness, double numHoles, double holeDepth, double breake, double hightDiff){
        this.genome[0]  = aggHeight;
        this.genome[1]  = roughness;
        this.genome[2]  = numHoles;
        this.genome[3]  = holeDepth;
        this.genome[4]  = breake;
        this.genome[5]  = hightDiff;

        setCallingNums();
    }

    // returns the percent similarity between ideal well and the given well
    double returnFitness(Well well, long scoorDiff) {
        double score = 0;
        score -= genome[0] * well.getAggregateHeight();
        score -= genome[1] * well.getRoughness();
        score -= genome[2] * well.getNumHoles();
        score -= genome[3] * well.getHoleDepth();
        score -= genome[5] * well.getHightDiff();
        score += genome[4] * scoorDiff;

        return score;
    }

    @Override
    public String toString() {
        return ("Height: " + this.genome[0] + "    roughness: " + this.genome[1] + "     numHoles: " + this.genome[2] +
                "     holeDepth: " + this.genome[3] + "     break: " + this.genome[4] + "     maxHeightDiff: " + this.genome[5]);
    }

    // Mutates the genome so every gene is varied by a max of perDif
    public Genome mutate(double percDif){
        Genome mutant = new Genome();
        for (int i = 0; i < genome.length; i++){
            double mutation = (Math.random() - 0.5) * 2 * percDif;
            mutant.genome[i] = this.genome[i] + mutation;

            if (mutant.genome[i] > 1) {mutant.genome[i] = 1;}
            if (mutant.genome[i] < 0) {mutant.genome[i] = 0;}
        }
        //System.out.println(mutant);
        return mutant;
    }

    // Returns the genome that is a product of this and partner breeding
    public Genome breedWith(Genome partner) {
        Genome baybeGene    = new Genome();
        double[] zeroGenome = {0,0,0,0,0};

        double sumsumScore  = this.sumSocre + partner.sumSocre;
        if (sumsumScore == 0) {
            sumsumScore         = 1;
            this.sumSocre       = 1;
            partner.sumSocre    = 1;
        }

        for (int i = 0; i < genome.length; i++) {
            baybeGene.genome[i] = (this.genome[i]*this.sumSocre/sumsumScore +
                    partner.genome[i]*partner.sumSocre/sumsumScore) / 2;
        }

        baybeGene.makeGenomeOfRadious1(baybeGene.genome);
        return baybeGene;
    }

    // Technical Functions----------------------------------------------------------------------------------------------

    // Changes Genome proportionally to have radious 1
    private void makeGenomeOfRadious1(double[] genome) {
        double genomeRadious = getRadious(genome);
        for(int i = 0; i < genome.length; i++) {
            genome[i] = genome[i] * (1/genomeRadious);
        }
    }

    // Gets the radious of the genome treated as a vector
    private double getRadious(double[] genome) {
        double genomeRadious = 0;
        for (double gene : genome) {
            genomeRadious += gene*gene;
        }
        return Math.sqrt(genomeRadious);
    }

    // Returns subtraction fo v1 and v2. Remark both V1 and V2 have to be of same length
    private double[] subtractVector(double[] v1, double[] v2) {
        double [] differenceVector = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            differenceVector[i] = v1[i] - v2[i];
        }
        return differenceVector;
    }

    // Sets the calling numbers, whould only be used for printing
    private void setCallingNums() {
        Machine.printAggregateHeight    = this.genome[0];
        Machine.printHoleDepth          = this.genome[1];
        Machine.printNumHoles           = this.genome[2];
        Machine.printRoughness          = this.genome[3];
        Machine.printMaxDiff            = this.genome[5];
    }

    // LEGACY ----------------------------------------------------------------------------------------------------------

//    double returnFitness(Well well, long scorDiff) {
//        double[] wellGenome = {well.getAggregateHeight(), well.getRoughness(), well.getNumHoles(),
//        well.getHoleDepth(), scorDiff, well.getHightDiff()};
//        makeGenomeOfRadious1(wellGenome);
//
//        double[] difVector = subtractVector(wellGenome, genome);
//        return getRadious(difVector);
//    }

//    double returnFitness(Well well, long scorDiff) {
//        double[] wellGenome = {well.getAggregateHeight(), well.getRoughness(), well.getNumHoles(), well.getHoleDepth(), scorDiff, well.getHightDiff()};
//        makeGenomeOfRadious1(wellGenome);
//
//        double[] difVector = subtractVector(wellGenome, genome);
//        return getRadious(difVector);
//    }

}

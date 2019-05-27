package Project.AI;

import Project.Tetris.Well;

public class Genome {
    // Genes
    public double height     = 0.3;
    public double roughness  = 0.9;
    public double numHoles   = 0.9;
    public double holeDepth  = 0.1;
    public double breake     = 0.7;

    // Genome
    double[] genome = {height, roughness, numHoles, holeDepth, breake};

    Genome(double height, double roughness, double numHoles, double holeDepth, double breake){
        this.height             = height;
        this.roughness          = roughness;
        this.numHoles           = numHoles;
        this.holeDepth          = holeDepth;
        this.breake             = breake;

        Machine.printHeight     = height;
        Machine.printHoleDepth  = holeDepth;
        Machine.printNumHoles   = numHoles;
        Machine.printRoughness  = roughness;
    }

    // returns the percent similarity between ideal well and the given well
    double returnFitness(Well well, long scorDiff) {
        double[] wellGenome = {well.getMaxHeight(), well.getRoughness(), well.getNumHoles(), well.getHoleDepth(), scorDiff};
        makeGenomeOfRadious1(wellGenome);

        double[] difVector = subtractVector(wellGenome, genome);
        return getRadious(difVector);
    }

    // Helper Functions-------------------------------------------------------------------------------------------------
    private double getRadious(double[] genome) {
        double genomeRadious = 0;
        for (double gene : genome) {
            genomeRadious += gene*gene;
        }
    return Math.sqrt(genomeRadious);
    }

    // Shortens Genome to have radious 1
    private void makeGenomeOfRadious1(double[] genome) {
        double genomeRadious = getRadious(genome);
        for(int i = 0; i < genome.length; i++) {
            genome[i] = genome[i] * (1/genomeRadious);
        }
    }

    // returns subtraction fo v1 and v2. Remark both V1 and V2 have to be of same length
    private double[] subtractVector(double[] v1, double[] v2) {
        double [] differenceVector = new double[v1.length];
        for (int i = 0; i < v1.length; i++) {
            differenceVector[i] = v1[i] - v2[i];
        }
        return differenceVector;
    }
}

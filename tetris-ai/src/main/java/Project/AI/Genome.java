package Project.AI;

import Project.Tetris.Well;
import org.apache.commons.lang3.SerializationUtils;

import java.lang.Math;

public class Genome {
    // Genes
    public double height;
    public double roughness;
    public double numHoles;
    public double holeDepth;
    public double breake;

    // Genome
    private double[] genome = {height, roughness, numHoles, holeDepth, breake};

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

    Genome(){}

    // returns the percent similarity between ideal well and the given well
    double returnFitness(Well well, long scorDiff) {
        double[] wellGenome = {well.getMaxHeight(), well.getRoughness(), well.getNumHoles(), well.getHoleDepth(), scorDiff};
        makeGenomeOfRadious1(wellGenome);

        double[] difVector = subtractVector(wellGenome, genome);
        return getRadious(difVector);
    }

    // Mutates the genome so every gene is varied by a max of perDif
    Genome mutate(double percDif){
        Genome mutant = new Genome();
        for (int i = 0; i < genome.length; i++){
            double mutation = (Math.random() - 0.5) * 2 * percDif;
            mutant.genome[i] = this.genome[i] + mutation;

            if (mutant.genome[i] > 1) {mutant.genome[i] = 1;}
            if (mutant.genome[i] < 0) {mutant.genome[i] = 0;}
        }
        mutant.setGenomeList();
        System.out.println(mutant);
        return mutant;
    }

    // Returns the genome that is a product of this and partner breeding
    Genome breedWith(Genome partner){
        Genome baybeGene = new Genome();
        for (int i = 0; i < genome.length; i++){
            baybeGene.genome[i] = (this.genome[i] + partner.genome[i]) / 2;
        }
        baybeGene.setGenomeList();
        return baybeGene;
    }

    // Result of bad design, genome should be a hash map
    private void setGenomeList() {
        height      = genome[0];
        roughness   = genome[1];
        numHoles    = genome[2];
        holeDepth   = genome[3];
        breake      = genome[4];
    }

    @Override
    public String toString() {
        return ("Height: " + this.height + "    roughness: " + this.roughness + "     numHoles: " + this.numHoles +
        "     holeDepth: " + this.holeDepth + "     break: " + this.breake);
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

package ru.dorogin.biogarden.gameplay.dna;

import com.badlogic.gdx.graphics.Color;
import lombok.Getter;
import ru.dorogin.biogarden.gameplay.dna.commands.*;

import java.util.Random;

import static ru.dorogin.biogarden.GlobalVars.*;


public class DNA {
    private final byte[] genSequence;
    private int currentPosition = 0;

    @Getter
    private final int reproductionEnergy;
    @Getter
    private final float percentOfEnergyForChildren;
    @Getter
    private final int maxAge;

    public DNA(byte[] genSequence, int reproductionEnergy, float percentOfEnergyForChildren, int maxAge) {
        this.genSequence = genSequence;
        this.reproductionEnergy = reproductionEnergy;
        this.percentOfEnergyForChildren = percentOfEnergyForChildren;
        this.maxAge = maxAge;
    }

    public DNA(int length) {
        genSequence = new byte[length];
        for(int i = 0; i < length; i++) {
            genSequence[i] = (byte) new Random().nextInt(MAX_COMMAND_VALUE);
        }
        reproductionEnergy = MIN_REPRODUCTION_ENERGY;
        percentOfEnergyForChildren = BASE_PERCENT_OF_ENERGY_FOR_CHILDREN;
        maxAge = BASE_MAX_AGE;

    }

    public Command getNextCommand() {
        int code = getNextCode() % 13;
        switch (code) {
            case 0: return new MoveCommand(this);
            case 1: return new JumpCommand(this);
            case 2: return new CheckByMeatCommand(this);
            case 3: return new CheckByGrassCommand(this);
            case 4: return new CheckByAnimalCommand(this);
            case 5: return new PlantEatCommand(this);
            case 6: return new MeatEatCommand(this);
            case 7: return new NopCommand();
            case 8: return new ReproductionCommand(this);
            case 9: return new RelativeCheckCommand(this);
            case 10: return new SpeciesCheckCommand(this);
            case 11: return new DifferentCheckCommand(this);
            case 12: return new AtackCommand(this);
            default: return null;
        }
    }

    public byte getNextCode() {
        byte code = genSequence[currentPosition];
        shiftRight(1);
        return code;
    }

    public void shiftRight(int amount) {
        currentPosition = (currentPosition + amount) % genSequence.length;
    }

    public Color getDnaColor() {
        int[] rgb = new int[3];
        int flowColor = 0;
        for(byte gen : genSequence) {
            rgb[flowColor++ % 3] += gen;
        }
        int rgb888 = Color.rgb888(rgb[0]%256, rgb[1]%256, rgb[2]%256);
        return new Color(rgb888);
    }

    public DNA getDnaCopyWithMutate() {
        byte[] childGenSequence = new byte[genSequence.length];
        Random random = new Random();
        for(int i = 0; i < genSequence.length; i++) {
            if(random.nextFloat() < MUTATE_PROBABILITY) {
                childGenSequence[i] = (byte) random.nextInt(100);
            } else {
                childGenSequence[i] = genSequence[i];
            }
        }
        return new DNA(childGenSequence, reproductionEnergy, percentOfEnergyForChildren, maxAge);
    }

    public Relationship calcRelationship(DNA himDna) {
        int countDiff = calcDnaDiff(himDna);
        return Relationship.getRelationshipByDifference(countDiff);
    }

    private int calcDnaDiff(DNA himDna) {
        int countDiff = 0;
        int minCountGen = Math.min(genSequence.length, himDna.genSequence.length);
        int maxCountGen = Math.max(genSequence.length, himDna.genSequence.length);
        for(int i = 0; i < minCountGen; i++) {
            if(genSequence[i] != himDna.genSequence[i]) {
                countDiff++;
            }
        }
        countDiff += maxCountGen - minCountGen;
        return countDiff;
    }
}
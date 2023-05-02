package ru.dorogin.biogarden.gameplay.dna;

import com.badlogic.gdx.graphics.Color;
import lombok.Getter;
import ru.dorogin.biogarden.gameplay.dna.commands.*;

import java.util.Random;

public class DNA {
    private static final float mutateProbability = 0.0005f;
    private static final int AMOUNT_COMMANDS = 9;
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
            genSequence[i] = (byte) new Random().nextInt(100);
        }
        reproductionEnergy = 1500;
        percentOfEnergyForChildren = 0.5f;
        maxAge = 1000;

    }


    public Command getNextCommand() {
        int code = getNextCode() % AMOUNT_COMMANDS;
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
            if(random.nextFloat() < mutateProbability) {
                childGenSequence[i] = (byte) random.nextInt(100);
            } else {
                childGenSequence[i] = genSequence[i];
            }
        }
        return new DNA(childGenSequence, reproductionEnergy, percentOfEnergyForChildren, maxAge);
    }
}
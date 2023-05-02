package ru.dorogin.biogarden.gameplay.dna;

import lombok.Getter;
import ru.dorogin.biogarden.gameplay.dna.commands.*;

import java.util.Random;

public class DNA {
    private static final int AMOUNT_COMMANDS = 6;
    private final byte[] sequence;
    private int currentPosition = 0;

    @Getter
    private final int reproductionEnergy;
    @Getter
    private final float percentOfEnergyForChildren;

    public DNA(byte[] sequence, int reproductionEnergy, float percentOfEnergyForChildren) {
        this.sequence = sequence;
        this.reproductionEnergy = reproductionEnergy;
        this.percentOfEnergyForChildren = percentOfEnergyForChildren;
    }

    public DNA(int length) {
        sequence = new byte[length];
        for(int i = 0; i < length; i++) {
            sequence[i] = (byte) new Random().nextInt(100);
        }
        reproductionEnergy = 1500;
        percentOfEnergyForChildren = 0.5f;
    }


    public Command getNextCommand() {
        int code = getNextCode() % AMOUNT_COMMANDS;
        switch (code) {
            case 0: return new MoveCommand(this);
            case 1: return new JumpCommand(this);
            case 2: return new CheckByGrassCommand(this);
            case 3: return new CheckByAnimalCommand(this);
            case 4: return new NopCommand();
            case 5: return new ReproductionCommand(this);
            default: return null;
        }
    }

    public byte getNextCode() {
        byte code = sequence[currentPosition];
        shiftRight(1);
        return code;
    }

    public void shiftRight(int amount) {
        currentPosition = (currentPosition + amount) % sequence.length;
    }

    public void shiftLeft(int amount) {
        currentPosition = (currentPosition - amount + sequence.length) % sequence.length;
    }
}
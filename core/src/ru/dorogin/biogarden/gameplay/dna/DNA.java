package ru.dorogin.biogarden.gameplay.dna;

import ru.dorogin.biogarden.gameplay.dna.commands.*;

import java.util.Random;

public class DNA {
    private static int AMOUNT_COMMANDS = 6;
    private byte[] sequence;
    private int currentPosition = 0;

    public DNA(byte[] sequence) {
        this.sequence = sequence;
    }


    public DNA(int length) {
        sequence = new byte[length];
        for(int i = 0; i < length; i++) {
            sequence[i] = (byte) new Random().nextInt(100);
        }
    }


    public Command getNextCommand() {
        int code = getNextCode() % AMOUNT_COMMANDS;
        switch (code) {
            case 0: return new MoveCommand(this);
            case 1: return new JumpCommand(this);
            case 2: return new CheckByGrassCommand(this);
            case 3: return new CheckByAnimalCommand(this);
            case 5: return new NopCommand();
            default: return null;
        }
//        return new MoveCommand(this);
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
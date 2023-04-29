package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.EntityContainer;

public class JumpCommand implements Command {
    private final byte shift;
    public JumpCommand(DNA dna) {
        shift = dna.getNextCode();
    }

    @Override
    public void process(Animal animal, EntityContainer entityContainer) {
        animal.getDna().shiftRight(shift);
    }

    @Override
    public int energyCost() {
        return 0;
    }

    @Override
    public boolean isTerminateCommand() {
        return false;
    }
}

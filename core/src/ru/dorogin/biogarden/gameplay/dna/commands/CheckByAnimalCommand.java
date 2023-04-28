package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.EntityContainer;

public class CheckByAnimalCommand implements Command {
    @Override
    public void process(Animal animal, EntityContainer entityContainer) {

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

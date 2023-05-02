package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.EntityContainer;

public class NopCommand implements Command {
    @Override
    public void process(Animal animal, EntityContainer entityContainer) {
    }

    @Override
    public boolean isTerminateCommand() {
        return true;
    }
}

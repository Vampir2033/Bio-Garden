package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.EntityContainer;

public interface Command {
    void process(Animal animal, EntityContainer entityContainer);

    boolean isTerminateCommand();
}

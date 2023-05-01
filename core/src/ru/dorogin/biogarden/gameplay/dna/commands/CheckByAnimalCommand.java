package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Animal;

public class CheckByAnimalCommand extends CheckNearCommand {

    public CheckByAnimalCommand(DNA dna) {
        super(dna, Animal.class);
    }
}

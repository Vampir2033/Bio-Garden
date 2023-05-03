package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.dna.Relationship;

public class DifferentCheckCommand extends CheckNearAnimal {
    public DifferentCheckCommand(DNA dna) {
        super(dna, Relationship.DIFFERENT);
    }
}

package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.dna.Relationship;

public class SpeciesCheckCommand extends CheckNearAnimal {
    public SpeciesCheckCommand(DNA dna) {
        super(dna, Relationship.ONE_SPECIES);
    }
}

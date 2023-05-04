package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.dna.Relationship;

public class RelativeCheckCommand extends CheckNearAnimal {
    public RelativeCheckCommand(DNA dna) {
        super(dna, Relationship.RELATIVES);
    }
}

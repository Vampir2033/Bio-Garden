package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Meat;

public class CheckByMeatCommand extends CheckNearCommand {

    public CheckByMeatCommand(DNA dna) {
        super(dna, Meat.class);
    }
}

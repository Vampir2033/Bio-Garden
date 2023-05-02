package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Meat;

public class MeatEatCommand extends EatCommand{
    public MeatEatCommand(DNA dna) {
        super(dna, Meat.class, 500);
    }
}

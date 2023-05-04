package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Meat;

import static ru.dorogin.biogarden.GlobalVars.MEAT_ENERGY;

public class MeatEatCommand extends EatCommand{
    public MeatEatCommand(DNA dna) {
        super(dna, Meat.class, MEAT_ENERGY);
    }
}

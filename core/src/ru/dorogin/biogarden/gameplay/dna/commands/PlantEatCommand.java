package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Grass;

import static ru.dorogin.biogarden.GlobalVars.PLANT_ENERGY;

public class PlantEatCommand extends EatCommand{
    public PlantEatCommand(DNA dna) {
        super(dna, Grass.class, PLANT_ENERGY);
    }
}

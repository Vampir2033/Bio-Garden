package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Grass;

public class CheckByGrassCommand extends CheckNearCommand {

    public CheckByGrassCommand(DNA dna) {
        super(dna, Grass.class);
    }
}

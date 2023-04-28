package ru.dorogin.biogarden.gameplay.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import ru.dorogin.biogarden.gameplay.EntityContainer;
import ru.dorogin.biogarden.gameplay.dna.commands.Command;
import ru.dorogin.biogarden.gameplay.dna.DNA;

public class Animal extends Entity {
    private static final int BASE_ENERGY = 1000;
    private static final int TACT_ENERGY = 1;

    private final DNA dna;
    private int energy;

    public Animal(int x, int y, Sprite sprite, DNA dna) {
        super(x, y, sprite);
        this.dna = dna;
        energy = BASE_ENERGY;
    }

    @Override
    public void update(EntityContainer entityContainer) {
        energy -= TACT_ENERGY;
        while (true) {
            Command command = dna.getNextCommand();
            energy -= command.energyCost();
            if(energy >= 0) {
                command.process(this, entityContainer);
            }
            if(command.isTerminateCommand()) {
                return;
            }
        }
    }

    @Override
    public boolean isAlive() {
        return energy > 0;
    }

    public void eatGrass() {
        energy += 300;
    }
}

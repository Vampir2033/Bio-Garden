package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.EntityContainer;
import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.entities.Entity;

import static ru.dorogin.biogarden.GlobalVars.ATACK_COST;
import static ru.dorogin.biogarden.GlobalVars.ATACK_SIZE;

public class AtackCommand implements Command {
    private final Direction direction;

    public AtackCommand(DNA dna) {
        direction = Direction.getDirectionByNumber(dna.getNextCode());
    }

    @Override
    public void process(Animal animal, EntityContainer entityContainer) {
        if(animal.getEnergy() >= ATACK_COST) {
            Entity neighbor = entityContainer.getNeighborEntity(animal, direction);
            if(neighbor != null && neighbor.getClass() == Animal.class) {
                Animal neighborAnimal = (Animal) neighbor;
                animal.subEnergy(ATACK_COST);
                neighborAnimal.subEnergy(ATACK_SIZE);
            }
        }
    }

    @Override
    public boolean isTerminateCommand() {
        return true;
    }
}

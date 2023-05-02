package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.EntityContainer;
import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.entities.Entity;

public abstract class EatCommand implements Command {
    private final Direction direction;
    private final Class<? extends Entity> entityType;
    private final int foodEnergy;

    public EatCommand(DNA dna, Class<? extends Entity> entityType, int foodEnergy) {
        direction = Direction.getDirectionByNumber(dna.getNextCode());
        this.entityType = entityType;
        this.foodEnergy = foodEnergy;
    }

    @Override
    public void process(Animal animal, EntityContainer entityContainer) {
        int toX = animal.x + direction.x;
        int toY = animal.y + direction.y;
        Entity entity = entityContainer.getEntity(toX, toY);
        if(entity != null) {
            if(entity.getClass() == entityType) {
                entityContainer.removeEntity(toX, toY);
                animal.addEnergy(foodEnergy);
            }
        }
    }

    @Override
    public boolean isTerminateCommand() {
        return false;
    }
}

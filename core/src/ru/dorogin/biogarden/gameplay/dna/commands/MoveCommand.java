package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.entities.Entity;
import ru.dorogin.biogarden.gameplay.EntityContainer;
import ru.dorogin.biogarden.gameplay.dna.DNA;

public class MoveCommand implements Command {
    private static final int MOVE_ENERGY = 10;
    private final Direction direction;

    public MoveCommand(DNA dna) {
        direction = Direction.values()[dna.getNextCode() % 4];
    }

    @Override
    public void process(Animal animal, EntityContainer entityContainer) {
        int toX = animal.x + direction.x;
        int toY = animal.y + direction.y;
        if(animal.getEnergy() >= MOVE_ENERGY) {
            Entity toCellEntity = entityContainer.getEntity(toX, toY);
            if(toCellEntity == null) {
                animal.subEnergy(MOVE_ENERGY);
                entityContainer.moveEntity(animal, toX, toY);
            }
//            else if(toCellEntity.getClass() == Grass.class) {
//                animal.eatGrass();
//                entityContainer.removeEntity(toX, toY);
//                entityContainer.moveEntity(animal, toX, toY);
//            } else if(toCellEntity.getClass() == Meat.class) {
//                animal.eatMeat();
//                entityContainer.removeEntity(toX, toY);
//                entityContainer.moveEntity(animal, toX, toY);
//            }
        }
    }

    @Override
    public boolean isTerminateCommand() {
        return true;
    }
}

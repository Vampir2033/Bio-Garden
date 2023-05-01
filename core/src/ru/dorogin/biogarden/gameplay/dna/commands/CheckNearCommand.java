package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.EntityContainer;
import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.entities.Entity;
import ru.dorogin.biogarden.gameplay.entities.Grass;

public abstract class CheckNearCommand implements Command {
    private final Direction direction;
    private final byte shiftIfFind;
    private final byte shiftIfNotFind;
    private final Class entityClassForCheck;

    public CheckNearCommand(DNA dna, Class entityClassForCheck) {
        direction = Direction.values()[dna.getNextCode() % 4];
        shiftIfFind = dna.getNextCode();
        shiftIfNotFind = dna.getNextCode();
        this.entityClassForCheck = entityClassForCheck;
    }

    @Override
    public void process(Animal animal, EntityContainer entityContainer) {
        int toX = animal.x + direction.x;
        int toY = animal.y + direction.y;
        int finalShift;
        if(entityContainer.checkByOutside(toX, toY)) {
            Entity entity = entityContainer.getEntity(toX, toY);
            if(entity == null) {
                finalShift = shiftIfNotFind;
            } else if(entity.getClass() == entityClassForCheck) {
                finalShift = shiftIfFind;
            } else {
                finalShift = shiftIfNotFind;
            }
        } else {
            finalShift = shiftIfNotFind;
        }
        animal.getDna().shiftRight(finalShift);
    }

    @Override
    public int energyCost() {
        return 0;
    }

    @Override
    public boolean isTerminateCommand() {
        return false;
    }
}

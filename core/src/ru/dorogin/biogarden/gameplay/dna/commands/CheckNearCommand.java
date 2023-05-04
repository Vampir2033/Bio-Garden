package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.EntityContainer;
import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.entities.Entity;

public abstract class CheckNearCommand implements Command {
    private final Direction direction;
    private final byte shiftIfFind;
    private final byte shiftIfNotFind;
    private final Class<? extends Entity> entityClassForCheck;

    public CheckNearCommand(DNA dna, Class<? extends Entity> entityClassForCheck) {
        direction = Direction.getDirectionByNumber(dna.getNextCode());
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
    public boolean isTerminateCommand() {
        return false;
    }
}

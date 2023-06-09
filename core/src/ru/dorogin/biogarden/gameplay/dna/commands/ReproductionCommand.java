package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.EntityContainer;
import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.entities.Entity;

import static ru.dorogin.biogarden.GlobalVars.BASE_REPRODUCTION_ENERGY;


public class ReproductionCommand implements Command {
    private final Direction reproductionSide;
    private final byte shiftIfCantReproduct;

    public ReproductionCommand(DNA dna) {
        reproductionSide = Direction.getDirectionByNumber(dna.getNextCode());
        shiftIfCantReproduct = dna.getNextCode();
    }

    @Override
    public void process(Animal animal, EntityContainer entityContainer) {
        if(animal.getEnergy() + BASE_REPRODUCTION_ENERGY >= animal.getDna().getReproductionEnergy()) {
            int toX = animal.x + reproductionSide.x;
            int toY = animal.y + reproductionSide.y;
            int finalShift;
            if(entityContainer.checkByOutside(toX, toY)) {
                Entity entity = entityContainer.getEntity(toX, toY);
                if(entity == null) {
                    int energyForChildren = (int) (animal.getEnergy() * animal.getDna().getPercentOfEnergyForChildren());
                    animal.subEnergy(energyForChildren + BASE_REPRODUCTION_ENERGY);
                    entityContainer.addEntity(new Animal(toX, toY, animal.getDna().getDnaCopyWithMutate(), energyForChildren));
                    finalShift = 0;
                } else {
                    finalShift = shiftIfCantReproduct;
                }
            } else {
                finalShift = shiftIfCantReproduct;
            }
            animal.getDna().shiftRight(finalShift);
        }
    }

    @Override
    public boolean isTerminateCommand() {
        return false;
    }

}

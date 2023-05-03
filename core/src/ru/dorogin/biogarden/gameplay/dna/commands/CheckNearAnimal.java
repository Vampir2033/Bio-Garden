package ru.dorogin.biogarden.gameplay.dna.commands;

import ru.dorogin.biogarden.gameplay.EntityContainer;
import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.dna.Relationship;
import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.entities.Entity;

public class CheckNearAnimal implements Command {

    private final Direction direction;
    private final int shiftIfCheckPass;
    private final Relationship checkingRelationShip;

    public CheckNearAnimal(DNA dna, Relationship checkingRelationShip) {
        direction = Direction.getDirectionByNumber(dna.getNextCode());
        this.shiftIfCheckPass = dna.getNextCode();
        this.checkingRelationShip = checkingRelationShip;
    }

    @Override
    public void process(Animal animal, EntityContainer entityContainer) {
        Entity nearEntity = entityContainer.getNeighborEntity(animal, direction);
        if(nearEntity != null && nearEntity.getClass() == Animal.class) {
            Animal nearAnimal = (Animal) nearEntity;
            Relationship relationship = animal.getDna().calcRelationship(nearAnimal.getDna());
            if(relationship == checkingRelationShip) {
                animal.getDna().shiftRight(shiftIfCheckPass);
            }
        }
    }

    @Override
    public boolean isTerminateCommand() {
        return false;
    }
}

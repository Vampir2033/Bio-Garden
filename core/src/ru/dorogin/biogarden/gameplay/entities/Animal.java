package ru.dorogin.biogarden.gameplay.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import lombok.Getter;
import lombok.Setter;
import ru.dorogin.biogarden.gameplay.EntityContainer;
import ru.dorogin.biogarden.gameplay.dna.commands.Command;
import ru.dorogin.biogarden.gameplay.dna.DNA;

import static ru.dorogin.biogarden.GlobalVars.MAX_NON_TERMINATE_COMMANDS;
import static ru.dorogin.biogarden.GlobalVars.ANIMAL_TACT_ENERGY;

public class Animal extends Entity {

    @Getter
    private final DNA dna;
    @Getter @Setter
    private int energy;
    private int age;

    public Animal(int x, int y, DNA dna, int energy) {
        super(x, y, new Sprite(generateSquare(dna.getDnaColor())));
        this.dna = dna;
        this.energy = energy;
        age = 0;
    }

    @Override
    public void update(EntityContainer entityContainer) {
        age++;
        energy -= ANIMAL_TACT_ENERGY;
        if(energy >= 0) {
            for(int i = 0; i < MAX_NON_TERMINATE_COMMANDS; i++){
                Command command = dna.getNextCommand();
                command.process(this, entityContainer);
                if(command.isTerminateCommand()) {
                    break;
                }
            }
        }
    }

    @Override
    public boolean isAlive() {
        return energy > 0 && age <= dna.getMaxAge();
    }

    public void subEnergy(int ammEnergy) {
        energy -= ammEnergy;
    }

    public void addEnergy(int ammEnergy) {
        energy += ammEnergy;
    }

    private static Texture generateSquare(Color color) {
        Pixmap squarePixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        squarePixmap.setColor(color);
        squarePixmap.fill();
        return new Texture(squarePixmap);
    }
}

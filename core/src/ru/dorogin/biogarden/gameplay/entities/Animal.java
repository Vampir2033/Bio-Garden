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

public class Animal extends Entity {
    private static final int TACT_ENERGY = 1;
    private static final int MAX_NON_TERMINATE_COMMANDS = 20;

    @Getter
    private final DNA dna;
    @Getter @Setter
    private int energy;

    public Animal(int x, int y, DNA dna, int energy) {
        super(x, y, new Sprite(generateSquare(dna.getDnaColor())));
        this.dna = dna;
        this.energy = energy;
    }

    @Override
    public void update(EntityContainer entityContainer) {
        energy -= TACT_ENERGY;
        for(int i = 0; i < MAX_NON_TERMINATE_COMMANDS; i++){
            Command command = dna.getNextCommand();
            energy -= command.energyCost();
            if(energy >= 0) {
                command.process(this, entityContainer);
            }
            if(command.isTerminateCommand()) {
                break;
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

    private static Texture generateSquare(Color color) {
        Pixmap squarePixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        squarePixmap.setColor(color);
        squarePixmap.fill();
        return new Texture(squarePixmap);
    }
}

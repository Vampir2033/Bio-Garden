package ru.dorogin.biogarden.gameplay.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ru.dorogin.biogarden.gameplay.EntityContainer;
import ru.dorogin.biogarden.gameplay.dna.commands.Direction;

import java.util.*;

import static ru.dorogin.biogarden.GlobalVars.*;


public class Grass extends Entity {

    private static final Texture grassTexture;

    static {
        grassTexture = generateCircleTexture();
    }

    public Grass(int x, int y) {
        super(x, y, new Sprite(grassTexture));
    }



    @Override
    public void update(EntityContainer entityContainer) {
        Random random = new Random();
        if(random.nextFloat() < PLANT_REPRODUCTION_PROBABILITY) {
            List<Direction> freeDirections = new ArrayList<>(Direction.values().length);
            for(Direction direction : Direction.values()) {
                if(entityContainer.getNeighborEntity(this, direction) == null) {
                    freeDirections.add(direction);
                }
            }
            if(!freeDirections.isEmpty()) {
                Direction direction = freeDirections.get(new Random().nextInt(freeDirections.size()));
                Grass grass = new Grass(x + direction.x, y + direction.y);
                entityContainer.addEntity(grass);
            }
        }
    }

    @Override
    public boolean isAlive() {
        return true;
    }

    private static Texture generateCircleTexture() {
        Pixmap pixmap = new Pixmap(GRASS_TEXTURE_QUALITY, GRASS_TEXTURE_QUALITY, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fillCircle(GRASS_TEXTURE_QUALITY / 2, GRASS_TEXTURE_QUALITY / 2, GRASS_TEXTURE_QUALITY / 2);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

}

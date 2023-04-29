package ru.dorogin.biogarden.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import ru.dorogin.biogarden.gameplay.dna.DNA;
import ru.dorogin.biogarden.gameplay.entities.Animal;
import ru.dorogin.biogarden.gameplay.entities.Entity;
import ru.dorogin.biogarden.gameplay.entities.Grass;

import java.util.Random;

import static com.badlogic.gdx.graphics.Color.*;

public class Gameplay {
    private long lastUpdateTime;
    private final static float fps = 15;
    private final static float updateInterval = 1.0f / fps; // интервал обновления в секундах
    private final EntityContainer entityContainer;
    private final static int COUNT_ENTITIES = 50;
    private final static int COUNT_GRASS = 200;

    public Gameplay(int width, int height) {
        entityContainer = new EntityContainer(width, height);
        Color[] colors = {BROWN, RED, WHITE, BLUE, YELLOW, GREEN, BLACK};
//        Color[] colors = {RED};
//        for (Color color : colors) {
//            try {
//                Animal animal = generateAnimal(color);
//                entityContainer.addEntity(animal);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        for (long count = COUNT_ENTITIES; count > 0; count--) {
            try {
                Random rnd = new Random();
                Animal animal = generateAnimal(new Color(
                        Color.rgb888(rnd.nextInt(256)
                                , rnd.nextInt(256)
                                , rnd.nextInt(256))));
                entityContainer.addEntity(animal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        for (long count = COUNT_GRASS; count > 0; count--) {
            try {
                Grass grass = generateGrass();
                entityContainer.addEntity(grass);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        long timeSinceLastUpdate = TimeUtils.nanoTime() - lastUpdateTime;

        if (timeSinceLastUpdate >= updateInterval * 1000000000L) {
            // прошло достаточно времени для обновления
            lastUpdateTime = TimeUtils.nanoTime();

            // обновляем каждую сущность
            for (Entity entity : entityContainer.getAllEntities()) {
                entity.update(entityContainer);
                if(!entity.isAlive()) {
                    entityContainer.removeEntity(entity.x, entity.y);
                }
            }
        }
    }

    public Array<Entity> getEntities() {
        return entityContainer.getAllEntities();
    }

    private Texture generateSquare(Color color) {
        Pixmap squarePixmap = new Pixmap(1, 1, Pixmap.Format.RGB888);
        squarePixmap.setColor(color);
        squarePixmap.fill();
        return new Texture(squarePixmap);
    }

    private Animal generateAnimal(Color color) {
        int x = new Random().nextInt(entityContainer.getWidth());
        int y = new Random().nextInt(entityContainer.getHeight());
        Texture texture = generateSquare(color);
        Sprite sprite = new Sprite(texture);
        DNA dna = new DNA(100);
        return new Animal(x, y, sprite, dna);
    }

    private Grass generateGrass() {
        int x = new Random().nextInt(entityContainer.getWidth());
        int y = new Random().nextInt(entityContainer.getHeight());
        return new Grass(x, y);
    }
}

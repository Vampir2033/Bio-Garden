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
import ru.dorogin.biogarden.gameplay.entities.Meat;

import java.util.Random;

public class Gameplay {
    private long lastUpdateTime;
    private final static float fps = 50;
    private final static float updateInterval = 1.0f / fps; // интервал обновления в секундах
    private final EntityContainer entityContainer;
    private final static int COUNT_ENTITIES = 100;
    private final static int COUNT_GRASS = 500;

    private final static float PLANT_GENERATE_CHANCE = 0.3f;
    private final static int MAX_COUNT_PLANT = 20;

    public Gameplay(int width, int height) {
        entityContainer = new EntityContainer(width, height);
//        Color[] colors = {BROWN, RED, WHITE, BLUE, YELLOW, GREEN, BLACK};
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
                Animal animal = generateAnimal();
                entityContainer.addEntity(animal);
            } catch (Exception ignored) {}
        }


        for (long count = COUNT_GRASS; count > 0; count--) {
            try {
                Grass grass = generateGrass();
                entityContainer.addEntity(grass);
            } catch (Exception ignored) {}
        }

//        Animal animal = new Animal(5, 5, new Sprite(generateSquare(RED))
//                , new DNA(new byte[]{
//                        0,1,0,1 // Начальное
//                ,2,0,4,8       // Анализ на растение сверху
//                ,0,2,0,2        // Ошибка - идём влево
//                ,0,0            // Растение найдено - идём вверх
//                ,1,2
//                ,0,2            // Растение не найдено - идём вниз
//                }));
//        entityContainer.addEntity(animal);

//        Animal animal = new Animal(5, 5, new Sprite(generateSquare(RED))
//                , new DNA(new byte[]{
//                        0,1,0,1 // Начальное
//                ,3,0,4,8       // Анализ на животное сверху
//                ,0,2,0,2        // Ошибка - идём влево
//                ,0,2            // Животное найдено - идём вниз
//                ,1,2
//                ,0,0            // Животное не найдено - идём вверх
//                }));
//        entityContainer.addEntity(animal);
//        Grass grass = new Grass(7, 6);
//        entityContainer.addEntity(grass);

//        Animal animal1 = new Animal(7,6,new Sprite(generateSquare(WHITE)), new DNA(new byte[]{5}));
//        entityContainer.addEntity(animal1);
    }

    public void update() {
        long timeSinceLastUpdate = TimeUtils.nanoTime() - lastUpdateTime;

        if (timeSinceLastUpdate >= updateInterval * 1000000000L) {
            // прошло достаточно времени для обновления
            lastUpdateTime = TimeUtils.nanoTime();

            // Обновляем растения на карте
            updateGrassOnScreen();

            // обновляем каждую сущность
            for (Entity entity : entityContainer.getAllEntities()) {
                entity.update(entityContainer);
                if(!entity.isAlive()) {
                    entityContainer.removeEntity(entity.x, entity.y);
                    entityContainer.addEntity(new Meat(entity.x, entity.y));
                }
            }
        }
    }

    public Array<Entity> getEntities() {
        return entityContainer.getAllEntities();
    }

    private Animal generateAnimal() {
        int x = new Random().nextInt(entityContainer.getWidth());
        int y = new Random().nextInt(entityContainer.getHeight());
        DNA dna = new DNA(1000);
        return new Animal(x, y, dna, 1000);
    }

    private Grass generateGrass() {
        int x = new Random().nextInt(entityContainer.getWidth());
        int y = new Random().nextInt(entityContainer.getHeight());
        return new Grass(x, y);
    }

    private void updateGrassOnScreen() {
        if(new Random().nextFloat() < PLANT_GENERATE_CHANCE) {
            for(int i = 0; i < MAX_COUNT_PLANT; i++) {
                try {
                    entityContainer.addEntity(generateGrass());
                } catch (Exception ignored) {

                }
            }
        }
    }
}

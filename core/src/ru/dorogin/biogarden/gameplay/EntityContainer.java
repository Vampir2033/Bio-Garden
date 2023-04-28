package ru.dorogin.biogarden.gameplay;

import com.badlogic.gdx.utils.Array;
import lombok.Getter;
import ru.dorogin.biogarden.gameplay.entities.Entity;

public class EntityContainer {

    @Getter
    private final int width;
    @Getter
    private final int height;
    private final Entity[][] entities;

    public EntityContainer(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new Entity[width][height];
    }

    public void addEntity(Entity entity) {
        if (!isCellOccupied(entity.x, entity.y)) {
                entities[entity.x][entity.y] = entity;
        } else {
            throw new IllegalArgumentException("Attempt to add an entity to an invalid location");
        }
    }

    public void removeEntity(int x, int y) {
        entities[x][y] = null;
    }

    public Entity getEntity(int x, int y) {
        return entities[x][y];
    }

    public boolean isCellOccupied(int x, int y) {
        return entities[x][y] != null;
    }

    public void moveEntity(Entity entity, int toX, int toY) {
        if (isCellOccupied(toX, toY)) {
            throw new IllegalArgumentException("Attempt to move entity into invalid location");
        } else {
            removeEntity(entity.x, entity.y);
            entity.x = toX;
            entity.y = toY;
            addEntity(entity);
        }
    }

    public Array<Entity> getAllEntities() {
        Array<Entity> entityList = new Array<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (entities[i][j] != null) {
                    entityList.add(entities[i][j]);
                }
            }
        }
        return entityList;
    }

    public boolean checkByOutside(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }
}

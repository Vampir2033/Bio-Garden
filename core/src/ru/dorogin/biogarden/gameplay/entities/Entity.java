package ru.dorogin.biogarden.gameplay.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import ru.dorogin.biogarden.gameplay.EntityContainer;

public abstract class Entity {
    public int x;
    public int y;
    public Sprite sprite;

    public Entity(int x, int y, Sprite sprite) {
        this.x = x;
        this.y = y;
        this.sprite = sprite;
        sprite.setSize(1, 1);
    }

    public abstract void update(EntityContainer entityContainer);

    public abstract boolean isAlive();
}

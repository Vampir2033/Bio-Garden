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
        sprite.setSize(0.9f, 0.9f);
    }

    public abstract void update(EntityContainer entityContainer);

    public abstract boolean isAlive();
}

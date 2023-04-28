package ru.dorogin.biogarden.gameplay.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ru.dorogin.biogarden.gameplay.EntityContainer;

public class Grass extends Entity {

    private static final Texture grassTexture;

    static {
        grassTexture = createCircleTexture();
    }

    public Grass(int x, int y) {
        super(x, y, new Sprite(grassTexture));
    }



    @Override
    public void update(EntityContainer entityContainer) {

    }

    @Override
    public boolean isAlive() {
        return true;
    }

    private static Texture createCircleTexture() {
        // Создаем новый объект Pixmap с заданными размерами
        int width = 64;
        int height = 64;
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);

        // Задаем цвет круга (в данном случае красный)
        Color color = Color.GREEN;

        // Рисуем круг на Pixmap
        pixmap.setColor(color);
        pixmap.fillCircle(width / 2, height / 2, width / 2);

        // Создаем новую текстуру из Pixmap
        return new Texture(pixmap);
    }
}

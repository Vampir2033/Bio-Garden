package ru.dorogin.biogarden.gameplay.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import ru.dorogin.biogarden.gameplay.EntityContainer;

import static ru.dorogin.biogarden.GlobalVars.MEAT_TEXTURE_QUALITY;

public class Meat extends Entity{

    private static final Texture meatTexture;

    static {
        meatTexture = generateCircleTexture();
    }

    public Meat(int x, int y) {
        super(x, y, new Sprite(meatTexture));
    }



    @Override
    public void update(EntityContainer entityContainer) {

    }

    @Override
    public boolean isAlive() {
        return true;
    }

    private static Texture generateCircleTexture() {
        Pixmap pixmap = new Pixmap(MEAT_TEXTURE_QUALITY, MEAT_TEXTURE_QUALITY, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fillCircle(MEAT_TEXTURE_QUALITY / 2, MEAT_TEXTURE_QUALITY / 2, MEAT_TEXTURE_QUALITY / 2);
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }
}

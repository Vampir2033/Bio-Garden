package ru.dorogin.biogarden.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.dorogin.biogarden.BioGardenGame;
import ru.dorogin.biogarden.gameplay.entities.Entity;
import ru.dorogin.biogarden.gameplay.Gameplay;

import static ru.dorogin.biogarden.GlobalVars.*;

public class GameFieldScreen implements Screen {


    private final BioGardenGame game;
    private final OrthographicCamera camera;
    private final ShapeRenderer shapeRenderer;
    private final Gameplay gameplay;

    public GameFieldScreen(BioGardenGame game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, GRID_WIDTH, GRID_HEIGHT);
        shapeRenderer = new ShapeRenderer();
        gameplay = new Gameplay(GRID_WIDTH, GRID_HEIGHT);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameplay.update();
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        for(Entity entity : gameplay.getEntities()) {
            entity.sprite.setX(entity.x + 0.05f);
            entity.sprite.setY(entity.y + 0.05f);
            entity.sprite.draw(game.batch);
        }
        game.batch.end();
        shapeRenderer.setProjectionMatrix(camera.combined);
        drawGrid();
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    private void drawGrid() {
        shapeRenderer.setColor(GRID_COLOR);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < GRID_HEIGHT; i++) {
            shapeRenderer.line(0, i, GRID_WIDTH, i);
        }
        for (int i = 0; i < GRID_WIDTH; i++) {
            shapeRenderer.line(i, 0, i, GRID_HEIGHT);
        }
        shapeRenderer.end();
    }
}

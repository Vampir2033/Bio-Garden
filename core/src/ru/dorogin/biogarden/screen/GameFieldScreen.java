package ru.dorogin.biogarden.screen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.dorogin.biogarden.BioGardenGame;
import ru.dorogin.biogarden.gameplay.entities.Entity;
import ru.dorogin.biogarden.gameplay.Gameplay;

public class GameFieldScreen implements Screen {

    private static final int CELL_SIZE = 1;
    private static final int GRID_WIDTH = 27*1 * CELL_SIZE;
    private static final int GRID_HEIGHT = 15*1 * CELL_SIZE;

    private final BioGardenGame game;
    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private Gameplay gameplay;

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
        drawGrid(GRID_HEIGHT, GRID_WIDTH, CELL_SIZE, Color.GRAY);
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

    private void drawGrid(int rows, int cols, float cellSize, Color color) {
        shapeRenderer.setColor(color);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        for (int i = 0; i < rows; i++) {
            shapeRenderer.line(0, i * cellSize, cols * cellSize, i * cellSize);
        }
        for (int i = 0; i < cols; i++) {
            shapeRenderer.line(i * cellSize, 0, i * cellSize, rows * cellSize);
        }
        shapeRenderer.end();
    }
}

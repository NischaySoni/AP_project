package io.github.ap;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class SlingShot extends ApplicationAdapter implements InputProcessor {
    private ShapeRenderer shapeRenderer;
    private OrthographicCamera camera;

    private Vector2 startPoint1, endPoint1;
    private Vector2 startPoint2, endPoint2;
    private boolean isDragging1 = false, isDragging2 = false;

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Set initial positions for the lines
        startPoint1 = new Vector2(Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 2f);
        endPoint1 = new Vector2(startPoint1);
        startPoint2 = new Vector2(2 * Gdx.graphics.getWidth() / 3f, Gdx.graphics.getHeight() / 2f);
        endPoint2 = new Vector2(startPoint2);

        // Set input processor
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        Gdx.gl.glLineWidth(10);

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Draw the lines
        if (isDragging1) {
            shapeRenderer.line(startPoint1, endPoint1);
        }
        if (isDragging2) {
            shapeRenderer.line(startPoint2, endPoint2);
        }

        shapeRenderer.end();
        Gdx.gl.glLineWidth(1);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == 0) {
            Vector2 mousePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);

            // Check proximity to start points to determine which line to drag
            if (mousePos.dst(startPoint1) < 20) {
                isDragging1 = true;
                endPoint1.set(mousePos);
            } else if (mousePos.dst(startPoint2) < 20) {
                isDragging2 = true;
                endPoint2.set(mousePos);
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 mousePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);

        if (isDragging1) {
            endPoint1.set(mousePos);
        }
        if (isDragging2) {
            endPoint2.set(mousePos);
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (button == 0) {
            isDragging1 = false;
            isDragging2 = false;
        }
        return true;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    // Unused InputProcessor methods
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }
    @Override
    public boolean keyTyped(char character) {
        return false;
    }
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }
    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}

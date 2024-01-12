package com.tutorial.game.camera;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tutorial.game.Utils;

import static com.badlogic.gdx.Gdx.gl;

public class Orthographic implements ApplicationListener {

    private OrthographicCamera camera;

    private float VIEWPORT_WIDTH = 100f;
    private float VIEWPORT_HEIGHT = 100f;

    private SpriteBatch batch;
    private Texture texture;

    @Override
    public void create() {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(VIEWPORT_WIDTH,VIEWPORT_HEIGHT * height / width);
        camera.position.set(0,0,0);
        camera.update();
        texture = new Texture(Utils.getInternalPath("block_texture.png"));
        batch = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) {
       camera.viewportWidth = VIEWPORT_WIDTH;
       camera.viewportHeight = VIEWPORT_HEIGHT * height / width;
       camera.update();
    }

    @Override
    public void render() {
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(texture,0,0);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
      texture.dispose();
      batch.dispose();
    }
}

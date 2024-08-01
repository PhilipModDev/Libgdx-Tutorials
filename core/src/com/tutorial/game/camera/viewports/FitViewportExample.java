package com.tutorial.game.camera.viewports;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.tutorial.game.Utils;

import static com.badlogic.gdx.Gdx.gl;

public class FitViewportExample extends ApplicationAdapter {

    private PerspectiveCamera camera;
    private FitViewport fitViewport;
    private SpriteBatch spriteBatch;
    private Texture texture;

    @Override
    public void create() {
        //Creates the camera and the viewport plus texture.
        texture = new Texture(Utils.getInternalPath("smoke_signals_by_hellsescapeartist-d4ohgaz.jpg"));
        camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        fitViewport = new FitViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(),camera);
        spriteBatch = new SpriteBatch();
    }


    @Override
    public void resize(int width, int height) {
        //Make sure you update the viewport by resize.
        fitViewport.update(width, height);
    }

    @Override
    public void render() {
         gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
         gl.glClearColor(0.1f,0.1f,0.1f,1.0f);
         camera.update();
         spriteBatch.begin();
         spriteBatch.draw(texture,0,0,camera.viewportWidth ,camera.viewportHeight);
         spriteBatch.end();
    }

    @Override
    public void dispose() {
        //Disposes the resources.
        spriteBatch.dispose();
        texture.dispose();
    }
}

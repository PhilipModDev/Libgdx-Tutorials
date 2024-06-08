package com.tutorial.game.bitmapFonts;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import static com.badlogic.gdx.Gdx.gl32;


public class ExampleFonts extends ApplicationAdapter {

    private BitmapFont font;
    private SpriteBatch batch;
    private PerspectiveCamera camera;
    private FirstPersonCameraController controller;

    @Override
    public void create() {
        camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(0,0,0);
        camera.near = 0.1f;
        camera.far = 1000f;
        camera.update();
        controller = new FirstPersonCameraController(camera);
        Gdx.input.setInputProcessor(controller);
        controller.update();

        batch = new SpriteBatch();

        font = new BitmapFont(Gdx.files.internal("fonts/my_font.fnt"));
        font.getData().scale(3f);
    }


    @Override
    public void resize(int width, int height) {
      camera.viewportWidth = width;
      camera.viewportHeight = height;
      camera.update();
      controller.update();
    }

    @Override
    public void render() {

        controller.update();
        camera.update();
       gl32.glClear(GL32.GL_COLOR_BUFFER_BIT);
        gl32.glClearColor(0.0f,0.0f,0.0f,0.0f);


       batch.begin();
       font.draw(batch,"Hello World.",250.0f,800.0f/2.0f);
       batch.end();
    }

    @Override
    public void dispose() {
        font.dispose();
        batch.dispose();
    }
}

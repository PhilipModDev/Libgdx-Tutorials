package com.tutorial.game.Animation.TwoD;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.*;

import static com.badlogic.gdx.Gdx.gl;

public class AnimationSprite2d extends ApplicationAdapter {

     private TextureAtlas atlas;
     private Animation<TextureAtlas.AtlasRegion> fireAnimation;
     private Sprite fire;
     private SpriteBatch batch;
     private OrthographicCamera camera;

    @Override
    public void create() {

        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        batch = new SpriteBatch();

        atlas = new TextureAtlas(Gdx.files.internal("atlas/fire.atlas"));

        fireAnimation = new Animation<>(0.10f,atlas.findRegions("fire"));
        fireAnimation.setPlayMode(Animation.PlayMode.LOOP);

        fire = new Sprite(fireAnimation.getKeyFrame(0));

        fire.scale(20);
        fire.setPosition(10,10);
    }


    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    float stateTime = 0f;
    @Override
    public void render() {
        camera.update();
        gl.glClear(GL32.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();

        TextureRegion region = fireAnimation.getKeyFrame(stateTime,true);
        fire.setRegion(region);

        batch.setProjectionMatrix(camera.projection);
        batch.begin();
        fire.draw(batch);
        batch.end();
    }


    @Override
    public void dispose() {
        batch.dispose();
    }
}

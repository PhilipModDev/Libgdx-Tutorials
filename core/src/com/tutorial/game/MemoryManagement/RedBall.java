package com.tutorial.game.MemoryManagement;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Pool;

public class RedBall implements Disposable, Pool.Poolable {

    private Sprite sprite;
    private final Texture texture;
    public boolean alive;
    public Vector2 direction = new Vector2();
    private static final float SPEED = 0.1f;

    public RedBall(float size, float x, float y){
        this.texture = new Texture("red_ball.png");
        sprite = new Sprite(texture);
        sprite.setSize(size,size);
        sprite.setOrigin(sprite.getWidth()/2.0f,sprite.getHeight()/2.0f);
        sprite.setPosition(x, y);
    }

    public void init(float x, float y,float size){
        alive = true;
        sprite.setSize(size,size);
        sprite.setOrigin(sprite.getWidth()/2.0f,sprite.getHeight()/2.0f);
        sprite.setPosition(x, y);
    }

    public void setPosition(float x, float y){
        sprite.setPosition(x - sprite.getOriginX(), y - sprite.getOriginY());
    }

    public void update(){
        if (sprite.getX() >= ExampleObjectPool.getInstance().camera.viewportWidth - 12) {
            reset();
            return;
        }
        sprite.translate(direction.x * SPEED,direction.y * SPEED);
    }


    public void draw(Batch batch){
        sprite.draw(batch);
    }

    @Override
    public void dispose() {
        this.texture.dispose();
    }

    @Override
    public void reset() {
        alive = false;
        sprite.setSize(1,1);
        sprite.setOrigin(sprite.getWidth()/2.0f,sprite.getHeight()/2.0f);
    }
}

package com.tutorial.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class TutorialGame extends ApplicationAdapter {

	public Texture sandTexture;
	public SpriteBatch spriteBatch;
    public Sprite textureSprite;

	@Override
	public void create () {
		spriteBatch = new SpriteBatch();
        sandTexture = new Texture(Gdx.files.internal("sand.png"));
		textureSprite = new Sprite(sandTexture);
		textureSprite.setBounds(0,0,200,200);
	}

	@Override
	public void render () {
     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

	 spriteBatch.begin();
	 textureSprite.draw(spriteBatch);
	 spriteBatch.end();
	}
	
	@Override
	public void dispose () {
       sandTexture.dispose();
	   spriteBatch.dispose();
	}

	@Override
	public void resize(int width, int height) {
       spriteBatch.getProjectionMatrix().setToOrtho2D(0,0,width,height);
	}
}

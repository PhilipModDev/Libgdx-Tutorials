package com.tutorial.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public class TutorialGame extends ApplicationAdapter {

	public SpriteBatch spriteBatch;
    public Sprite textureSprite;
	private TextureAtlas atlas;

	@Override
	public void create () {


		atlas = new TextureAtlas(Utils.getInternalPath("atlas/game_atlas.atlas"));
		spriteBatch = new SpriteBatch();
		textureSprite = atlas.createSprite("heart");
		textureSprite.setSize(5,5);
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
	   spriteBatch.dispose();
      atlas.dispose();
	}

	@Override
	public void resize(int width, int height) {
       spriteBatch.getProjectionMatrix().setToOrtho2D(0,0,width,height);
	}
}

package com.tutorial.game.ui;


import com.badlogic.gdx.ApplicationAdapter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.badlogic.gdx.Gdx.gl;

public class SceneUI extends ApplicationAdapter {

    private Stage stage;

    @Override
    public void create() {

        Skin skin = new Skin(Gdx.files.internal("ui/ui_skin.json"));

        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        //Creates image button.
        ImageButton imageButton = new ImageButton(skin);
        table.add(imageButton).expand().center().width(532).height(532);
        table.row();
        //Creates normal button.
        Button button = new Button(skin);
        table.add(button).expand().center().width(300).height(100);
        //Creates text button.
        TextButton textButton = new TextButton("Play",skin);
        table.add(textButton).expand().center().width(300).height(100);

    }

    @Override
    public void resize(int width, int height) {
       stage.getViewport().update(width, height,true);
    }

    @Override
    public void render() {
      gl.glClear(GL32.GL_COLOR_BUFFER_BIT);
      gl.glClearColor(0.9f,0.9f,0.9f,1.0f);

      stage.getViewport().apply();
      stage.act();
      stage.draw();
    }


    @Override
    public void dispose() {
        stage.dispose();
    }
}

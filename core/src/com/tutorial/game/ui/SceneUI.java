package com.tutorial.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.badlogic.gdx.Gdx.gl;

public class SceneUI extends ApplicationAdapter {

    private Stage stage;
    private Skin skin;

    @Override
    public void create() {

        skin = new Skin(Gdx.files.internal("ui/myskin.json"));


        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        Table mainTable = new Table();
        mainTable.setFillParent(true);
        stage.addActor(mainTable);

        Button button = new Button(skin);
        mainTable.add(button).center().width(300).height(100);

    }

    @Override
    public void resize(int width, int height) {

        stage.getViewport().update(width, height,true);

    }

    @Override
    public void render() {

        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl.glClearColor(1.0f,1.0f,1.0f,1.0f);

        stage.getViewport().apply();
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {

        stage.dispose();

    }

}

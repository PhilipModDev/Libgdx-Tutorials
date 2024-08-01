package com.tutorial.game.ui;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.badlogic.gdx.Gdx.gl32;

public class WidgetsExample extends ApplicationAdapter {

    private Stage stage;
    private Skin skin;

    private ProgressBar progressBar;

    @Override
    public void create() {
        skin = new Skin(Gdx.files.internal("ui/widgets_ui.json"));
        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        Gdx.input.setInputProcessor(stage);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //Creates a new text field with registered listener.
        TextField textField = new TextField("",skin);
        textField.addListener(new InputListener(){
            @Override
            public boolean mouseMoved(InputEvent event, float x, float y) {
                if (textField.getWidth() + textField.getX() >= x) {
                    System.out.println(true);
                }
                return false;
            }
        });
        //Creates a label.
        Label label = new Label("Hello Everyone.",skin);
        label.setAlignment(Align.center);

        //Creates a checkbox.
        CheckBox checkBox = new CheckBox("",skin);
        checkBox.getImage().scaleBy(21);

        //Creates a list.
        List<String> list = new List<>(skin);
        list.setItems("Hello","My","Name","is","John.");

        //Creates a SelectBox.
        SelectBox<String> selectBox = new SelectBox<>(skin);
        selectBox.setItems("Subscribe","For","More","Libgdx","Videos");
        selectBox.setAlignment(Align.center);

        //Creates a ProgressBar.
        progressBar = new ProgressBar(0,10,1,false,skin);



        //Add to the table (May have to remove some).
//        table.add(textField).expand().width(500).center();
//        table.row();
//        table.add(label).expand().width(300).height(60).center();
//        table.row();
//        table.add(checkBox).expand();
//        table.row();
//        table.add(list).width(300).expand().center();
//        table.row();
//        table.add(selectBox).width(300).expand().center();
//        table.row();
        table.add(progressBar).width(300).expand().center();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height,true);
    }


    @Override
    public void render() {
        //update progress
        if (progressBar.getValue() < progressBar.getMaxValue()){
            progressBar.setValue(progressBar.getValue() + (29 * Gdx.graphics.getDeltaTime()));
        } else {
            progressBar.setValue(0);
        }
        gl32.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gl32.glClearColor(1f,1f,1f,1f);

        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}

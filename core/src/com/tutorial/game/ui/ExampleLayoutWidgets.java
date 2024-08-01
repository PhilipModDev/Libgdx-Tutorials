package com.tutorial.game.ui;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import static com.badlogic.gdx.Gdx.gl;

public class ExampleLayoutWidgets extends ApplicationAdapter {

    private Stage stage;

    @Override
    public void create() {
        //Gets a new skin from file.
        Skin skin = new Skin(Gdx.files.internal("ui/widgets_ui.json"));
        //Create a new stage for the ui.
        stage = new Stage(new ExtendViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()));
        //Sets the input processor for taking inputs.
        Gdx.input.setInputProcessor(stage);
        /* Feel free to switch between the methods. */

//      tableExample(skin);
//      containerExample(skin);
//      scrollPaneExample(skin);
//      splitPaneExample(skin);
//      treeExample(skin);
//      verticalGroupExample(skin);
//      horizontalGroupExample(skin);
//      windowExample(skin);
    }

    private void tableExample(Skin skin) {
        //We define a table.
        Table table = new Table();
        //Set for fill parent.
        table.setFillParent(true);
        //Adds the table to the stage.
        stage.addActor(table);
        //Creates a button being displayed on left.
        Button button = new Button(skin);
        table.add(button).expand().center().left().width(300).height(100).pad(200f);
        //Creates a text button on the right.
        TextButton textButton = new TextButton("Play", skin);
        table.add(textButton).expand().center().right().width(300).height(100).pad(200f);
    }

    private void containerExample(Skin skin){
        //Creates a new table.
        Container<Button> container = new Container<>();
        container.setFillParent(true);
        //Creates a button being displayed on left.
        Button button = new Button(skin);
        container.setActor(button);
        container.width(300).height(100).align(Align.top);
        //Adds the container to the stage.
        stage.addActor(container);
    }

    private void scrollPaneExample(Skin skin){
        //Creates a new list.
        List<Label> numberList = new List<>(skin);
        //Adds all labels to the list.
        Array<Label> buttons = new Array<>();
        for (int i = 0; i <= 100; i++) {
            buttons.add(new Label(""+i,skin));
        }
        numberList.setItems(buttons);
        //Defines a new scrollPane.
        ScrollPane scrollPane = new ScrollPane(numberList,skin);
        scrollPane.getStyle().vScrollKnob.setMinWidth(32);
        scrollPane.getStyle().vScrollKnob.setMinHeight(132);
        scrollPane.setFadeScrollBars(false);
        //Then adds to the stage.
        Container<ScrollPane> container = new Container<>(scrollPane);
        container.setFillParent(true);
        container.align(Align.center).width(500);
        stage.addActor(container);
    }

    private void splitPaneExample(Skin skin){
        //Creates the first button.
        Button firtButton = new Button(skin);
        //Creates the second.
        Button secondButton = new Button(skin);
        //Crates the splitPane.
        SplitPane splitPane = new SplitPane(firtButton,secondButton,false,skin);
        splitPane.getStyle().handle.setMinWidth(25f);
        //Adds the splitPane to the stage.
        Container<SplitPane> container = new Container<>(splitPane);
        container.setFillParent(true);
        container.width(600f).height(100f);
        stage.addActor(container);
    }

    private void treeExample(Skin skin){
        //Creates a new tree
        Tree<TreeNode<?>,Image> tree = new Tree<>(skin);
        tree.getStyle().minus.setMinWidth(20f);
        tree.getStyle().minus.setMinHeight(10f);
        tree.getStyle().plus.setMinWidth(20f);
        tree.getStyle().plus.setMinHeight(20f);
        //The images to be added onto the tree.
        Image first = new Image(new Texture(Gdx.files.internal("ui/files_icon.png")));
        first.scaleBy(5);
        Image second = new Image(new Texture(Gdx.files.internal("ui/files_icon.png")));
        second.scaleBy(5);
        Image third = new Image(new Texture(Gdx.files.internal("ui/files_icon.png")));
        third.scaleBy(5);
        //Creates the first node.
        TreeNode<?> firstNode = new TreeNode<>(first);
        //Create the second node.
        TreeNode<?> secondNode = new TreeNode<>(second);
        firstNode.add(secondNode);
        //Creates the last node.
        TreeNode<?> thirdNode = new TreeNode<>(third);
        secondNode.add(thirdNode);
        //Adds the nodes to the tree and configures the tree.
        tree.add(firstNode);
        tree.setYSpacing(50);
        tree.setIndentSpacing(35);
        //Adds the tree into a container.
        Container<Tree<TreeNode<?>,Image>> container = new Container<>(tree);
        container.setFillParent(true);
        container.width(100f).height(100f);
        //Adds the container to the stage.
        stage.addActor(container);
    }

    private void verticalGroupExample(Skin skin){
        //Creates the first button.
        TextButton firstButton = new TextButton("Start",skin);
        firstButton.setTransform(true);
        firstButton.scaleBy(1);
        //Creates the second.
        TextButton secondButton = new TextButton("Play",skin);
        secondButton.setTransform(true);
        secondButton.scaleBy(1);
        //Creates a vertical group.
        VerticalGroup verticalGroup = new VerticalGroup();
        verticalGroup.space(100f);
        verticalGroup.addActor(firstButton);
        verticalGroup.addActor(secondButton);
        //Creates the container.
        Container<VerticalGroup> container = new Container<>(verticalGroup);
        container.setFillParent(true);
        //Adds the container to the stage.
        stage.addActor(container);
    }

    private void horizontalGroupExample(Skin skin){
        //Creates the first button.
        TextButton firstButton = new TextButton("Start",skin);
        firstButton.setTransform(true);
        firstButton.scaleBy(1);
        //Creates the second.
        TextButton secondButton = new TextButton("Play",skin);
        secondButton.setTransform(true);
        secondButton.scaleBy(1);
        //Creates a vertical group.
        HorizontalGroup  horizontalGroup = new HorizontalGroup();
        horizontalGroup.space(100f);
        horizontalGroup.addActor(firstButton);
        horizontalGroup.addActor(secondButton);
        //Creates the container.
        Container<HorizontalGroup> container = new Container<>( horizontalGroup);
        container.setFillParent(true);
        //Adds the container to the stage.
        stage.addActor(container);
    }

    private void windowExample(Skin skin){
        //Creates a new window. Is like table but with a title on top.
        Window window = new Window("Example Window",skin);
        window.setFillParent(true);
        Button button = new Button(skin);
        window.add(button).expand().center().width(300).height(100f);
        stage.addActor(window);
    }


    @Override
    public void resize(int width, int height) {
        //Resizes the viewport of the stage.
       stage.getViewport().update(width, height,true);
    }

    @Override
    public void render() {
      gl.glClear(GL32.GL_COLOR_BUFFER_BIT);
      gl.glClearColor(0.9f,0.9f,0.9f,1.0f);
      //Draws and applies while updating the stage.
      stage.getViewport().apply();
      stage.act();
      stage.draw();
    }


    @Override
    public void dispose() {
        //Disposes the stage.
        stage.dispose();
    }
}

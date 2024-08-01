package com.tutorial.game.camera;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.tutorial.game.Utils;

import static com.badlogic.gdx.Gdx.gl;

public class Perspective implements ApplicationListener {

    private PerspectiveCamera camera;
    private FirstPersonCameraController controller;
    private Model model;
    private ModelInstance instance;
    private ModelBuilder builder;
    private ModelBatch batch;
    private Texture texture;


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

       batch = new ModelBatch();
       builder = new ModelBuilder();
       texture = new Texture(Utils.getInternalPath("block_texture.png"));

       Material material = new Material(new TextureAttribute(TextureAttribute.Diffuse,texture));
       int attributes = VertexAttributes.Usage.Position | VertexAttributes.Usage.TextureCoordinates;
       model = builder.createBox(3,3,3,material,attributes);
       instance = new ModelInstance(model);
       instance.transform.setToTranslation(0,0,-5);
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
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera.update();
        controller.update();

        batch.begin(camera);
        batch.render(instance);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
       batch.dispose();
       model.dispose();
       texture.dispose();
    }
}

package com.tutorial.game;

import com.badlogic.gdx.ApplicationAdapter;
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

import static com.badlogic.gdx.Gdx.gl;

public class Cube extends ApplicationAdapter {

    private Model model;
    private ModelBuilder modelBuilder;
    private ModelInstance instance;
    private ModelBatch batch;
    private Texture texture;
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
        texture = new Texture(Utils.getInternalPath("block_texture.png"));
        modelBuilder = new ModelBuilder();
        Material material = new Material(new TextureAttribute(TextureAttribute.createDiffuse(texture)));
        long bitmaskAttributes = VertexAttributes.Usage.Position | VertexAttributes.Usage.TextureCoordinates;
        model = modelBuilder.createBox(1,1,1,material,bitmaskAttributes);
        instance = new ModelInstance(model);
        batch = new ModelBatch();
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
        gl.glClearColor(0.1f,0.1f,0.1f,1.0f);

        controller.update();
        camera.update();

        batch.begin(camera);
        batch.render(instance);
        batch.end();
    }

    @Override
    public void dispose() {
        model.dispose();
    }
}

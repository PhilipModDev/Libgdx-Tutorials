package com.tutorial.game.basic3D;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
import com.badlogic.gdx.math.Vector3;
import com.tutorial.game.Utils;

import java.security.Key;

import static com.badlogic.gdx.Gdx.gl;

public class Basic3D extends ApplicationAdapter {

    private PerspectiveCamera camera;
    private FirstPersonCameraController controller;
    private Model model;
    private ModelBuilder modelBuilder;
    private ModelInstance instance;
    private ModelBatch batch;
    private Material material;
    //Creates the texture.
    private Texture texture;

    @Override
    public void create() {
        //Creates the camera for the 3d world
        camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(0,0,0);
        camera.near = 0.1f;
        camera.far = 1000f;
        camera.update();
        //Creates the input controls.
        controller = new FirstPersonCameraController(camera);
        controller.setVelocity(50);
        Gdx.input.setInputProcessor(controller);
        controller.update();
        //Create the materials used to render the cube.
        texture = new Texture(Utils.getInternalPath("block_texture.png"));
        material = new Material("cube",new TextureAttribute(TextureAttribute.createDiffuse(texture)));
        long attributes = VertexAttributes.Usage.Position | VertexAttributes.Usage.TextureCoordinates;
        modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(16,16,16,material,attributes);
        instance = new ModelInstance(model);
        //Creates the batch in order to render,
        batch = new ModelBatch();
    }

    @Override
    public void resize(int width, int height) {
        //Updates the viewport for the camera.
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
        controller.update();
    }

    private final float velocity = 6.0f;
    private float counter = 0.0f;
    private boolean flyDown = false;

    @Override
    public void render() {
        //Calls OpenGL to clear the color and depth buffer.
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        //Calls OpenGL to clear the color for background. (rgba)
        gl.glClearColor(0.0f,0.1f,0.2f,1.0f);
        //Updates the inputs and camera.
        controller.update();
        camera.update();
        if (counter >= 20) {
            flyDown =true;
        }  else if (counter <= 1){
            flyDown = false;
        }

        instance.transform.setToTranslation(0,counter,counter);
        //Renders all objects.
        batch.begin(camera);
        batch.render(instance);
        batch.end();
        if (flyDown){
            counter -= velocity * Gdx.graphics.getDeltaTime();
        }else counter += velocity * Gdx.graphics.getDeltaTime();
        if (Gdx.graphics.isFullscreen() && Gdx.input.isKeyJustPressed(Input.Keys.F11)){
            Gdx.graphics.setWindowedMode(1080,720);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F11)){
            Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        }
    }

    @Override
    public void dispose() {
        //Disposes any resources.
        model.dispose();
        texture.dispose();
    }
}

package com.tutorial.game.AssetManager;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import static com.badlogic.gdx.Gdx.gl32;

public class AssetManagerDemo extends ApplicationAdapter {

    private PerspectiveCamera camera;
    private FirstPersonCameraController controller;
    private ShaderProgram shaderProgram;
    private Quad quad;

    private AssetManager assetManager;

    @Override
    public void create() {

        assetManager = new AssetManager();

        assetManager.load("block_texture.png",Texture.class);
        assetManager.finishLoading();

        camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(0,0,0);
        camera.near = 0.1f;
        camera.far = 1000f;
        camera.update();

        controller = new FirstPersonCameraController(camera);
        Gdx.input.setInputProcessor(controller);

        FileHandle vertex = Gdx.files.internal("shaders/vertex.glsl");
        FileHandle fragment = Gdx.files.internal("shaders/fragment.glsl");
        shaderProgram = new ShaderProgram(vertex,fragment);

        Texture myTexture = assetManager.get("block_texture.png",Texture.class);

        quad = new Quad(0,0,0,myTexture,shaderProgram);
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
        gl32.glClear(GL32.GL_COLOR_BUFFER_BIT | GL32.GL_DEPTH_BUFFER_BIT);
        gl32.glClearColor(0.0f,0.0f,0.0f,1.0f);

        //Add rendering code here.
        gl32.glEnable(GL32.GL_CULL_FACE);
        gl32.glCullFace(GL32.GL_CCW);
        gl32.glEnable(GL32.GL_DEPTH_TEST);
        gl32.glDepthFunc(GL32.GL_LEQUAL);

        camera.update();
        controller.update();

        //Render quad here.
        quad.render(camera);
    }

    @Override
    public void dispose() {

        assetManager.dispose();

        quad.dispose();
    }
}

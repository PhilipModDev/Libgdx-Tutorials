package com.tutorial.game.MemoryManagement;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL32;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ExampleObjectPool extends ApplicationAdapter {

    public static ExampleObjectPool exampleObjectPool;
    public static final float MAX_WIDTH = 10;
    public static final float MAX_HEIGHT = 10;
    public static final float GUI_WIDTH = 500;
    public static final float GUI_HEIGHT = 800;
    private BitmapFont debugFont;
    public OrthographicCamera camera;
    public OrthographicCamera guiCamera;
    private SpriteBatch renderBatch;
    private final Array<RedBall> ballArray = new Array<>();
    private final Pool<RedBall> ballPool = new Pool<RedBall>() {
        @Override
        protected RedBall newObject() {
            return new RedBall(1,0,0);
        }
    };

    @Override
    public void create() {
        exampleObjectPool = this;
        guiCamera = new OrthographicCamera(GUI_WIDTH,GUI_HEIGHT);
        guiCamera.update();

        camera = new OrthographicCamera(MAX_WIDTH,MAX_HEIGHT);
        camera.position.set(0,0,0);
        camera.update();

        renderBatch = new SpriteBatch();

        debugFont = new BitmapFont(Gdx.files.internal("fonts/my_font.fnt"));

    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = (MAX_HEIGHT / height) * width;
        camera.update();

        guiCamera.viewportWidth = (GUI_HEIGHT / height) * width;
        guiCamera.viewportHeight = GUI_HEIGHT;
        guiCamera.update();
    }

    @Override
    public void render() {
        updateObjects();
        Gdx.gl32.glClear(GL32.GL_DEPTH_BUFFER_BIT | GL32.GL_COLOR_BUFFER_BIT);
        Gdx.gl32.glClearColor(0f,0f,0f,0f);

        renderBatch.setProjectionMatrix(camera.combined);
        renderBatch.begin();
        int size = ballArray.size;
        for (int i = size; i --> 0;){
            RedBall redBall = ballArray.get(i);
            redBall.draw(renderBatch);
        }
        renderBatch.end();

        renderBatch.setProjectionMatrix(guiCamera.combined);
        renderBatch.begin();
        renderDebugGUI(renderBatch);
        renderBatch.end();

    }

    Vector3 coordinates = new Vector3();
    private void updateObjects(){
       if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
           addNewBall();
       }
       updateBallDirection();
    }

    private void addNewBall(){
        float x = Gdx.input.getX();
        float y = Gdx.input.getY();
        coordinates.set(x,y,0);
        coordinates.set(camera.unproject(coordinates));
        RedBall redBall = ballPool.obtain();
        redBall.init(0,0,1);
        redBall.setPosition(coordinates.x,coordinates.y);
        redBall.direction.set(1,0);
        ballArray.add(redBall);
    }

    private void updateBallDirection(){
        if (!ballArray.isEmpty()){
            int size = ballArray.size;
            for (int i = size; i --> 0;){
                RedBall redBall = ballArray.get(i);
                if (redBall.alive) {
                    redBall.update();
                    continue;
                }
                redBall.reset();
                ballArray.removeIndex(i);
                ballPool.free(redBall);
            }
        }
    }

    private void renderDebugGUI(Batch batch){
        debugFont.draw(batch,"Free Objects: "+ ballPool.getFree() +
                        "\nBatch Objects: "+ ballArray.size
                ,-guiCamera.viewportWidth / 2.2f,guiCamera.viewportHeight / 2.3f);
    }

    @Override
    public void dispose() {
        ballPool.clear();
        ballArray.forEach(RedBall::dispose);
        renderBatch.dispose();
        debugFont.dispose();
    }

     public static ExampleObjectPool getInstance() {
        return exampleObjectPool;
    }
}

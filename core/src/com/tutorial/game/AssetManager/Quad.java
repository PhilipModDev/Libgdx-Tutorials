package com.tutorial.game.AssetManager;

import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.BufferUtils;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.FloatArray;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static com.badlogic.gdx.Gdx.gl32;

public class Quad implements Disposable {
    private final Vector3 pos = new Vector3();
    private final Matrix4 model = new Matrix4();
    private final Matrix4 modelView = new Matrix4();
    private final VertexAttributes attributes = new VertexAttributes(VertexAttribute.Position(),VertexAttribute.TexCoords(0));
    private final FloatBuffer vertices;
    private final Texture texture;
    private final ShaderProgram shaderProgram;
    private final int[] vao = new int[1];
    private int vbo;
    private boolean isUploadToGPU;

    public Quad(float x,float y, float z,Texture texture, ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
        this.texture = texture;
        this.pos.set(x,y,z);

        FloatArray floatArray = new FloatArray();
        floatArray.addAll(getVertices());

        vertices = BufferUtils.newFloatBuffer(attributes.vertexSize * (floatArray.size / attributes.size()));
        vertices.put(floatArray.items);
        vertices.flip();
    }

    private void uploadToGPU(){
        isUploadToGPU = true;

        gl32.glGenVertexArrays(vao.length,vao,vao[0]);
        gl32.glBindVertexArray(vao[0]);

        vbo = gl32.glGenBuffer();
        gl32.glBindBuffer(GL32.GL_ARRAY_BUFFER,vbo);
        gl32.glBufferData(GL32.GL_ARRAY_BUFFER,vertices.limit() * Float.BYTES,vertices,GL32.GL_STATIC_DRAW);

        int ebo = gl32.glGenBuffer();
        gl32.glBindBuffer(GL32.GL_ELEMENT_ARRAY_BUFFER,ebo);
        int[] indices = getIndices();
        IntBuffer buffer = BufferUtils.newIntBuffer(indices.length);
        buffer.put(indices);
        buffer.flip();
        gl32.glBufferData(GL32.GL_ELEMENT_ARRAY_BUFFER,buffer.limit() * Float.BYTES,buffer,GL32.GL_STATIC_DRAW);
    }

    public void render(Camera camera){
        model.setToTranslation(pos);
        modelView.idt();
        modelView.mul(model);
        modelView.mul(camera.view);

        texture.bind();
        shaderProgram.bind();
        shaderProgram.setUniformMatrix("modelView",modelView);
        shaderProgram.setUniformMatrix("projection",camera.projection);

        if (!isUploadToGPU) uploadToGPU();

        gl32.glBindVertexArray(vao[0]);
        gl32.glBindBuffer(GL32.GL_ARRAY_BUFFER,vbo);

        for (int i = 0; i < attributes.size(); i++){
            VertexAttribute attribute = attributes.get(i);
            shaderProgram.setVertexAttribute(attribute.alias,attribute.numComponents,attribute.type,attribute.normalized,attributes.vertexSize,attribute.offset);
            shaderProgram.enableVertexAttribute(attribute.alias);
        }

        gl32.glDrawElements(GL32.GL_TRIANGLES,getIndices().length,GL32.GL_UNSIGNED_INT,0);

        gl32.glBindBuffer(GL32.GL_ARRAY_BUFFER,0);
        gl32.glBindVertexArray(0);
    }

    private float[] getVertices(){
        return new float[]{
            0,1,0, 0,1,
            0,0,0, 0,0,
            1,0,0, 1,0,
            1,1,0, 1,1
        };
    }

    private int[] getIndices(){
        return new int[]{
          0,1,2, 2,3,0
        };
    }

    @Override
    public void dispose() {
        gl32.glDeleteBuffer(vbo);
        gl32.glDeleteVertexArrays(vao.length,vao,0);
      texture.dispose();
      shaderProgram.dispose();
      vertices.clear();
    }
}

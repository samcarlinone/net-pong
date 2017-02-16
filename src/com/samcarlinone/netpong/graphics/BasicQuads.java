package com.samcarlinone.netpong.graphics;


import com.samcarlinone.netpong.util.BufferUtils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

/**
 * Created by CARLINSE1 on 2/7/2017.
 */
public class BasicQuads {

    private int vao, vbo;
    private int count;

    public BasicQuads(float[] vertices) {
        count = vertices.length;

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(vertices), GL_STATIC_DRAW);
        glVertexAttribPointer(Shader.VERT_ATTRIB, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(Shader.VERT_ATTRIB);

    }

    private void bind() {
        glBindVertexArray(vao);
    }

    private void draw() {
        glDrawArrays(GL_QUADS, 0, count);
    }

    public void render() {
        bind();
        draw();
    }
}

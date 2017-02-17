package com.samcarlinone.netpong.graphics;
import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.EXTTextureFilterAnisotropic;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.opengl.GLUtil;
import org.lwjgl.system.Callback;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.*;


/**
 * Created by CARLINSE1 on 2/14/2017.
 */
public class TextRenderer {
    private int texSize = 32;
    private int tex;
    private int vao;

    private int anim = 5;

    public TextRenderer() {
        createTexture();
        createVao();
    }

    private void createTexture() {
        this.tex = glGenTextures();
        glBindTexture(GL_TEXTURE_2D_ARRAY, this.tex);
        glTexParameteri(GL_TEXTURE_2D_ARRAY, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D_ARRAY, GL_TEXTURE_MIN_FILTER, GL_NEAREST);

        glTexImage3D(GL_TEXTURE_2D_ARRAY, 0, GL_RGB8, texSize, texSize, 2, 0, GL_RGB,
                GL_UNSIGNED_BYTE, (ByteBuffer) null);

        BufferedImage img = null;

        try {
            img = ImageIO.read(new File("bitmap_font.png"));
        } catch (IOException e) {
            System.err.println("Image Not Loaded");
            return;
        }

        int[] data = img.getData().getPixels(16, 0, 16, 16, new int[16*16*4]);

        ByteBuffer bb = BufferUtils.createByteBuffer(16*16 * 3);
        glTexSubImage3D(GL_TEXTURE_2D_ARRAY, 0, 0, 0, 0, 16, 16, 1, GL_RGB, GL_UNSIGNED_BYTE, bb);
        for (int i=0; i<16*16 * 4; i+=4) {
            bb.put((byte) data[i]).put((byte) data[i+1]).put((byte) data[i+2]);
        }
        bb.flip();

        //Next
        data = img.getData().getPixels(16, 16, 16, 16, new int[16*16*4]);

        bb = BufferUtils.createByteBuffer(16*16 * 3);
        glTexSubImage3D(GL_TEXTURE_2D_ARRAY, 0, 0, 0, 1, 16, 16, 1, GL_RGB, GL_UNSIGNED_BYTE, bb);
        for (int i=0; i<16*16 * 4; i+=4) {
            bb.put((byte) data[i]).put((byte) data[i+1]).put((byte) data[i+2]);
        }
        bb.flip();

        glGenerateMipmap(GL_TEXTURE_2D_ARRAY);
        glBindTexture(GL_TEXTURE_2D_ARRAY, 0);
    }

    private void createVao() {
        this.vao = glGenVertexArrays();
        int vbo = glGenBuffers();
        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        ByteBuffer bb = BufferUtils.createByteBuffer(4 * 2 * 6);
        FloatBuffer fv = bb.asFloatBuffer();
        fv.put(-1.0f).put(-1.0f);
        fv.put(1.0f).put(-1.0f);
        fv.put(1.0f).put(1.0f);
        fv.put(1.0f).put(1.0f);
        fv.put(-1.0f).put(1.0f);
        fv.put(-1.0f).put(-1.0f);
        glBufferData(GL_ARRAY_BUFFER, bb, GL_STATIC_DRAW);
        glEnableVertexAttribArray(0);
        glVertexAttribPointer(0, 2, GL_FLOAT, false, 0, 0L);

        vbo = glGenBuffers();
        glBindVertexArray(vao);
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        bb = BufferUtils.createByteBuffer(4 * 3 * 6);
        fv = bb.asFloatBuffer();
        fv.put(0f).put(0f).put(0);
        fv.put(1.0f).put(0f).put(0);
        fv.put(1.0f).put(1.0f).put(0);
        fv.put(1.0f).put(1.0f).put(0f);
        fv.put(0).put(1.0f).put(0f);
        fv.put(0).put(0).put(0f);
        glBufferData(GL_ARRAY_BUFFER, bb, GL_STATIC_DRAW);
        glEnableVertexAttribArray(1);
        glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0L);

        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindVertexArray(0);
    }

    public void render() {
        glBindVertexArray(vao);
        glBindTexture(GL_TEXTURE_2D_ARRAY, tex);
        glDrawArrays(GL_TRIANGLES, 0, 6);
        glBindTexture(GL_TEXTURE_2D_ARRAY, 0);
        glBindVertexArray(0);

        glUseProgram(0);
    }


}

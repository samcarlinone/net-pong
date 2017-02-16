package com.samcarlinone.netpong.game;

import com.samcarlinone.netpong.graphics.BasicQuads;
import com.samcarlinone.netpong.graphics.Shader;
import com.samcarlinone.netpong.math.CRect;
import com.samcarlinone.netpong.math.Matrix4f;
import com.samcarlinone.netpong.math.Vector3f;
import com.samcarlinone.netpong.util.KeyboardInput;

/**
 * Created by CARLINSE1 on 2/8/2017.
 */
public class Paddle implements CRect {
    private BasicQuads mesh;

    public float x = 0f, y = 0f, vy = 0f, width = 15f, height = 75f, speed = 5f;

    public Paddle(float x, float y) {
        this.x = x;
        this.y = y;

        mesh = new BasicQuads(new float[] {
                -1f, 1f,
                -1f, -1f,
                1f, -1f,
                1f, 1f
        });
    }

    public void render(Shader shader) {
        Matrix4f mat = new Matrix4f();
        mat.identity();
        mat.scale(new Vector3f(width, height, 0f));
        mat.translate(new Vector3f(x, y, 0f));

        shader.setUniformMat4f("trs", mat);
        mesh.render();
    }

    public void update() {
        if(KeyboardInput.isKeyDown('W')) {
            vy = speed;
        } else {
            if (KeyboardInput.isKeyDown('S')) {
                vy = -speed;
            } else {
                vy = 0f;
            }
        }

        y += vy;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}

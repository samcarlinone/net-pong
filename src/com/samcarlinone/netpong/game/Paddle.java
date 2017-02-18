package com.samcarlinone.netpong.game;

import com.samcarlinone.netpong.graphics.BasicQuads;
import com.samcarlinone.netpong.graphics.Shader;
import com.samcarlinone.netpong.math.CRect;
import com.samcarlinone.netpong.math.Matrix4f;
import com.samcarlinone.netpong.math.StaticRect;
import com.samcarlinone.netpong.math.Vector3f;
import com.samcarlinone.netpong.util.KeyboardInput;

/**
 * Created by CARLINSE1 on 2/8/2017.
 */
public class Paddle {
    private BasicQuads mesh;

    public StaticRect rect = new StaticRect(0, 0, 15, 75);
    public float speed = 5f;

    public Paddle(float x, float y) {
        rect.x = x;
        rect.y = y;

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
        mat.scale(new Vector3f(rect.w, rect.h, 0f));
        mat.translate(new Vector3f(rect.x, rect.y, 0f));

        shader.setUniformMat4f("trs", mat);
        mesh.render();
    }

    public void update() {
        if(KeyboardInput.isKeyDown('W')) {
            rect.yv = speed;
        } else {
            if (KeyboardInput.isKeyDown('S')) {
                rect.yv = -speed;
            } else {
                rect.yv = 0f;
            }
        }

        rect.update();
    }
}

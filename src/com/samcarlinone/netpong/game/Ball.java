package com.samcarlinone.netpong.game;

import com.samcarlinone.netpong.graphics.BasicMesh;
import com.samcarlinone.netpong.graphics.Shader;
import com.samcarlinone.netpong.math.CRect;
import com.samcarlinone.netpong.math.Matrix4f;
import com.samcarlinone.netpong.math.Vector3f;
import com.samcarlinone.netpong.util.KeyboardInput;

/**
 * Created by CARLINSE1 on 2/9/2017.
 */
public class Ball implements CRect {
    private BasicMesh mesh;

    public float x = 0f, y = 0f, vx = 0f, vy = 0f, scale = 10f, width = 10f, height = 10f;

    public Ball(float x, float y, float vx, float vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;

        int NUM_POINTS = 32;
        float[] points = new float[NUM_POINTS*3*2];

        for(int i=0; i<NUM_POINTS; i++) {
            points[i*6  ] = (float)Math.cos(((float)i/NUM_POINTS)*Math.PI*2);
            points[i*6+1] = (float)Math.sin(((float)i/NUM_POINTS)*Math.PI*2);

            points[i*6+2] = (float)Math.cos(((float)(i+1)/NUM_POINTS)*Math.PI*2);
            points[i*6+3] = (float)Math.sin(((float)(i+1)/NUM_POINTS)*Math.PI*2);

            points[i*6+4] = 0f;
            points[i*6+5] = 0f;
        }

        mesh = new BasicMesh(points);
    }

    public void render(Shader shader) {
        Matrix4f mat = new Matrix4f();
        mat.identity();
        //mat.translate(new Vector3f(0.5f, 0.5f, 0f));
        mat.scale(new Vector3f(scale, scale, 0f));
        mat.translate(new Vector3f(x, y, 0f));

        shader.setUniformMat4f("trs", mat);
        mesh.render();
    }

    public void update() {
        x += vx;
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

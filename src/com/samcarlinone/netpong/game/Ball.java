package com.samcarlinone.netpong.game;

import com.samcarlinone.netpong.graphics.BasicMesh;
import com.samcarlinone.netpong.graphics.Shader;
import com.samcarlinone.netpong.math.CRect;
import com.samcarlinone.netpong.math.DynamicRect;
import com.samcarlinone.netpong.math.Matrix4f;
import com.samcarlinone.netpong.math.Vector3f;
import com.samcarlinone.netpong.util.KeyboardInput;

/**
 * Created by CARLINSE1 on 2/9/2017.
 */
public class Ball {
    private BasicMesh mesh;

    public float scale = 10f;

    public DynamicRect rect = new DynamicRect(0, 0, 10, 10);

    public Ball(float x, float y, float xv, float yv) {
        rect.x = x;
        rect.y = y;
        rect.w = scale;
        rect.h = scale;
        rect.xv = xv;
        rect.yv = yv;

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
        mat.translate(new Vector3f(rect.x, rect.y, 0f));

        shader.setUniformMat4f("trs", mat);
        mesh.render();
    }

    public void update() {
        rect.update();
    }
}

package com.samcarlinone.netpong.game;

import com.samcarlinone.netpong.graphics.BasicMesh;
import com.samcarlinone.netpong.graphics.BasicQuads;
import com.samcarlinone.netpong.graphics.Shader;
import com.samcarlinone.netpong.graphics.TextRenderer;
import com.samcarlinone.netpong.math.Matrix4f;
import com.samcarlinone.netpong.util.KeyboardInput;

/**
 * Created by CARLINSE1 on 2/9/2017.
 */
public class TestShader implements Module {
    private int anim = 0;

    private Shader arrayTex;
    private TextRenderer renderer;

    public TestShader() {
        arrayTex = new Shader("arrayTexture");
        renderer = new TextRenderer();
    }

    public void render () {
        arrayTex.enable();

        Matrix4f projection = new Matrix4f();
        projection.orthographic(-1, 1, -1, 1, -1, 1);
        arrayTex.setUniformMat4f("proj", projection);

        renderer.render();

        /*
        Matrix4f projection = new Matrix4f();
        projection.orthographic(-width/2, width/2, -height/2, height/2, -1, 1);
        //projection.orthographic(-1, 1, -1, 1, -1, 1);

        basic.enable();
        basic.setUniformMat4f("proj", projection);
        p1.render(basic);
        basic.enable();
        basic.setUniformMat4f("proj", projection);
        b.render(basic);

        particle.enable();
        particle.setUniformMat4f("proj", projection);
        pm.render();
        */
    }

    public Module update() {
        renderer.update();
        return null;
    }
}

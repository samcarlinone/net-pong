package com.samcarlinone.netpong.game;

import com.samcarlinone.netpong.graphics.BasicMesh;
import com.samcarlinone.netpong.graphics.BasicQuads;
import com.samcarlinone.netpong.graphics.Shader;
import com.samcarlinone.netpong.math.Matrix4f;
import com.samcarlinone.netpong.util.KeyboardInput;

/**
 * Created by CARLINSE1 on 2/9/2017.
 */
public class Menu implements Module {
    private int anim = 0;

    private Shader basic;
    private BasicMesh arrowThing;
    private BasicQuads spaceBar;

    public Menu() {
        basic = new Shader("basic");

        arrowThing = new BasicMesh(new float[] {
                -0.7f, 0.5f,
                -0.7f, -0.5f,
                -0.3f, 0f,

                0.7f, 0.5f,
                0.7f, -0.5f,
                0.3f, 0f
        });

        spaceBar = new BasicQuads(new float[] {
                -0.2f, 0.2f,
                -0.2f, -0.2f,
                -0.1f, -0.2f,
                -0.1f, 0.2f,

                0.2f, 0.2f,
                0.2f, -0.2f,
                0.1f, -0.2f,
                0.1f, 0.2f,

                0.1f, 0f,
                -0.1f, 0f,
                -0.1f, -0.2f,
                0.1f, -0.2f,
        });
    }

    public void render () {
        basic.enable();

        Matrix4f projection = new Matrix4f();
        projection.orthographic(-1, 1, -1, 1, -1, 1);
        basic.setUniformMat4f("proj", projection);

        spaceBar.render();

        if(Math.sin(anim/20) > 0) {
            arrowThing.render();
        }

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
        anim++;

        if(KeyboardInput.isKeyDown(32)) {
            return new LocalGame();
        }

        return null;
    }
}

package com.samcarlinone.netpong.game;

import com.samcarlinone.netpong.Main;
import com.samcarlinone.netpong.graphics.*;
import com.samcarlinone.netpong.math.Matrix4f;
import com.samcarlinone.netpong.util.KeyboardInput;

import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.glGetError;

/**
 * Created by CARLINSE1 on 2/9/2017.
 */
public class TestShader implements Module {
    private Shader textShader;
    private TextRenderer renderer;
    private float pos=0;
    private Text text;
    private Text t2;
    private Text[] tex;

    public TestShader() {
        textShader = new Shader("text");

        renderer = new TextRenderer();

        tex = new Text[256];

        for(int i=0; i<256; i++) {
            tex[i] = new Text(""+(Math.random()*120 + 50), (float)Math.random()*100f, (float)Math.random()*100f);
            renderer.addText(tex[i]);
        }


        text = new Text("HAZX", 0, 0);
        renderer.addText(text);
        t2 = new Text("BRO#: ", -84, 16);
        renderer.addText(t2);
    }

    public void render () {
        textShader.enable();

        Matrix4f projection = new Matrix4f();
        projection.orthographic(-Main.width/2, Main.width/2, -Main.height/2, Main.height/2, -1, 1);

        textShader.setUniformMat4f("proj", projection);
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
        pos ++;
        text.setX(pos);
        t2.setText("BRO#: " + pos);
        renderer.update();
        return null;
    }
}

package com.samcarlinone.netpong.game;

import com.samcarlinone.netpong.Main;
import com.samcarlinone.netpong.graphics.*;
import com.samcarlinone.netpong.math.Matrix4f;
import com.samcarlinone.netpong.networking.LANAsyncConnector;
import com.samcarlinone.netpong.util.KeyboardInput;

import static org.lwjgl.opengl.GL11.GL_NO_ERROR;
import static org.lwjgl.opengl.GL11.glGetError;

/**
 * Created by CARLINSE1 on 2/9/2017.
 */
public class WaitLANConnect implements Module {
    private Shader textShader;
    private TextRenderer renderer;
    private int anim=0;
    private int stage=0;
    private Text text;

    private LANAsyncConnector connector;

    public WaitLANConnect() {
        textShader = new Shader("text");
        renderer = new TextRenderer();

        text = new Text("Waiting [/]", -320, 0);
        text.setScale(4);
        renderer.addText(text);

        connector = new LANAsyncConnector();
    }

    public void render () {
        textShader.enable();

        Matrix4f projection = new Matrix4f();
        projection.orthographic(-Main.width/2, Main.width/2, -Main.height/2, Main.height/2, -1, 1);

        textShader.setUniformMat4f("proj", projection);
        renderer.render();
    }

    public Module update() {
        anim ++;
        if(anim == 30) {
            stage++;

            switch(stage) {
                case 4:
                    stage = 0;

                case 0:
                    text.setText("Waiting [/]");
                    break;

                case 1:
                    text.setText("Waiting [|]");
                    break;

                case 2:
                    text.setText("Waiting [\\]");
                    break;

                case 3:
                    text.setText("Waiting [-]");
            }

            anim = 0;
        }

        renderer.update();

        if(connector.connect() != null) {
            System.out.println("Connected");
        }

        return null;
    }
}

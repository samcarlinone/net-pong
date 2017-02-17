package com.samcarlinone.netpong.game;

import com.samcarlinone.netpong.Main;
import com.samcarlinone.netpong.graphics.Font;
import com.samcarlinone.netpong.graphics.ParticleManager;
import com.samcarlinone.netpong.graphics.Shader;
import com.samcarlinone.netpong.math.Collision;
import com.samcarlinone.netpong.math.Matrix4f;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by CARLINSE1 on 2/9/2017.
 */
public class LocalGame implements Module {

    private Shader trs;
    private Shader part;
    private ParticleManager pm;
    private Paddle p1;
    private Paddle p2;
    private Ball b;
    private Font text;

    public LocalGame() {
        trs = new Shader("trs");
        part = new Shader("part");

        pm = new ParticleManager();

        p1 = new Paddle(-500, 0);
        p2 = new Paddle(500, 0);

        b = new Ball(0, 0, -4, 0);


    }

    public void render() {
        Matrix4f projection = new Matrix4f();
        projection.orthographic(-Main.width/2, Main.width/2, -Main.height/2, Main.height/2, -1, 1);

        trs.enable();
        trs.setUniformMat4f("proj", projection);
        p1.render(trs);
        p2.render(trs);
        b.render(trs);

        part.enable();
        part.setUniformMat4f("proj", projection);
        pm.render();
    }

    public Module update() {
        p1.update();
        p2.update();
        b.update();

        if(b.y+b.height > Main.height/2 || b.y-b.height < -Main.height/2) {
            b.vy = -b.vy;
        }

        if(Collision.cRectXcRect(p1, b)) {
            b.vx = -b.vx + Math.copySign(0.5f, -b.vx);
            b.vy += p1.vy / 5f;

            pm.spawnAngled(b.x, b.y, 20, (float)Math.PI/2f, (float)Math.PI/-2f, 10f, 60f);
        }

        if(Collision.cRectXcRect(p2, b)) {
            b.vx = -b.vx*1.1f;
            b.vy += p2.vy / 5f;

            pm.spawnAngled(b.x, b.y, 20, (float)Math.PI/2f, (float)Math.PI*3f/2f, 10f, 60f);
        }

        pm.spawn(b.x, b.y, 1);
        pm.update();

        return null;
    }

}

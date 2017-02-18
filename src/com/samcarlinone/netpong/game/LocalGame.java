package com.samcarlinone.netpong.game;

import com.samcarlinone.netpong.Main;
import com.samcarlinone.netpong.graphics.ParticleManager;
import com.samcarlinone.netpong.graphics.Shader;
import com.samcarlinone.netpong.math.Collision;
import com.samcarlinone.netpong.math.Matrix4f;
import com.samcarlinone.netpong.math.Resolver;

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

        Resolver.Dynamic_YLine(b.rect, -Main.height/2);
        Resolver.Dynamic_YLine(b.rect, Main.height/2);
        Resolver.Dynamic_XLine(b.rect, -Main.width/2);
        Resolver.Dynamic_XLine(b.rect, Main.width/2);

        if(Resolver.Dynamic_Static(b.rect, p1.rect)) {
            b.rect.xv = b.rect.xv*1.025f;
            b.rect.yv += p1.rect.yv / 5f + Math.random()/2f;

            pm.spawnAngled(b.rect.x, b.rect.y, 20, (float)Math.PI/2f, (float)Math.PI/-2f, 10f, 60f);
        }

        if(Resolver.Dynamic_Static(b.rect, p2.rect)) {
            b.rect.xv = b.rect.xv*1.025f;
            b.rect.yv += p2.rect.yv / 5f + Math.random()/2f;

            pm.spawnAngled(b.rect.x, b.rect.y, 20, (float)Math.PI/2f, (float)Math.PI*3f/2f, 10f, 60f);
        }

        pm.spawn(b.rect.x, b.rect.y, 1);
        pm.update();

        return null;
    }

}

package com.samcarlinone.netpong.math;

/**
 * Created by cobra on 2/18/2017.
 */
public class Resolver {
    public static int iterations = 10;

    public static void cRectXcRect(CRect r1, CRect r2) {
        for(int i=0; i<Resolver.iterations; i++) {
            if (r1.getX() + r1.getWidth() > r2.getX() - r2.getWidth() && r2.getX() + r2.getWidth() > r1.getX() - r1.getWidth()) {
                if (r1.getY() + r1.getHeight() > r2.getY() - r2.getHeight() && r2.getY() + r2.getHeight() > r1.getY() - r1.getHeight()) {
                    int k = 1;
                    //return true;
                }
            }
        }
    }
}

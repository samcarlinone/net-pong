package com.samcarlinone.netpong.math;

/**
 * Created by CARLINSE1 on 2/10/2017.
 */
public class Collision {
    public static boolean cRectXcRect(CRect r1, CRect r2) {
        if(r1.getX()+r1.getWidth() > r2.getX()-r2.getWidth() && r2.getX()+r2.getWidth() > r1.getX()-r1.getWidth()) {
            if(r1.getY()+r1.getHeight() > r2.getY()-r2.getHeight() && r2.getY()+r2.getHeight() > r1.getY()-r1.getHeight()) {
                return true;
            }
        }

        return false;
    }
}

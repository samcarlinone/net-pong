package com.samcarlinone.netpong.graphics;

/**
 * Created by cobra on 2/17/2017.
 */
public class Text {
    private char[] text;
    private boolean dirty;
    private float x, y;

    public Text(String in, float x, float y) {
        text = in.toCharArray();
        this.x = x;
        this.y = y;
        dirty = true;
    }

    public void setText(String text) {
        this.text = text.toCharArray();
        dirty = true;
    }

    public String getText() {
        return new String(text);
    }

    public void setX(float newX) {
        x = newX;
        dirty = true;
    }

    public void setY(float newY) {
        y = newY;
        dirty = true;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getLength() {
        return text.length;
    }

    public char[] getCharArray() {
        return text;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setClean() {
        dirty = false;
    }
}

package com.dtails.c17d.whiteboard.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Eric Ong on 7/30/2015.
 */
public class Point implements MyDrawable {

    private float x, y, r;
    private final Paint mpaint;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
        this.r = 5.0f;
//        this.mpaint = ColorPickerView.currColor;
        this.mpaint = new Paint();
    }

    public Paint getMpaint() {
        return mpaint;
    }

    public float getX() {
        return x;
    }
    public void setX(float x) {
        this.x = x;
    }
    public float getY() {
        return y;
    }
    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void draw(Canvas canvas, Path path) {
        canvas.drawCircle(x, y, r, mpaint);
    }
}

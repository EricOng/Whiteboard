package com.dtails.c17d.whiteboard.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Eric Ong on 7/30/2015.
 */
public class Point implements MyDrawable {

    private float x, y, r;
    private Paint mpaint;

    public Point(float x, float y, Paint paint){
        this.x = x;
        this.y = y;
        this.r = 5.0f;
        mpaint = paint;
    }

    public Paint getMpaint() {
        return mpaint;
    }
    public void setMpaint(Paint mpaint) {
        this.mpaint = mpaint;
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
    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, r, mpaint);
    }

    @Override
    public boolean isInstant() {
        return true;
    }


}

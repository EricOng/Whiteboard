package com.dtails.c17d.whiteboard.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Eric Ong on 7/30/2015.
 */
public class Line implements MyDrawable {
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private Paint paint;

    public Line(float startX, float startY, float endX, float endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
//        this.paint = ColorPickerView.currColor;
        this.paint = new Paint();
    }

    public float getEndX() {
        return endX;
    }

    public float getEndY() {
        return endY;
    }

    public Paint getPaint() {
        return paint;
    }

    public float getStartX() {
        return startX;
    }

    public float getStartY() {
        return startY;
    }

    @Override
    public void draw(Canvas canvas, Path path) {
        canvas.drawLine(startX, startY, endX, endY, paint);
    }
}
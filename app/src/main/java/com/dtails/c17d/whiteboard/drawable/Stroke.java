package com.dtails.c17d.whiteboard.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Eric Ong on 8/2/2015.
 */
public class Stroke implements MyDrawable {

    private float r;
    private Paint mpaint;
    private Path path;


    public Stroke(float size, Path path) {
        this.r = size;
        this.path = new Path();
        this.path.set(path);
        mpaint = new Paint();
        mpaint.setColor(Color.RED);
        mpaint.setAntiAlias(true);
        mpaint.setStrokeWidth(20);
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setStrokeJoin(Paint.Join.ROUND);
        mpaint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(this.path, mpaint);
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}

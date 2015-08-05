package com.dtails.c17d.whiteboard.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/**
 * Created by Eric Ong on 8/3/2015.
 */
public class Eraser implements MyDrawable {

    private Paint mpaint;
    private Path path;

    public Eraser(Path path) {
        this.path = new Path();
        this.path.set(path);
        this.mpaint = new Paint();
        this.mpaint.setAntiAlias(true);
        this.mpaint.setStrokeWidth(20);
        this.mpaint.setStyle(Paint.Style.STROKE);
        this.mpaint.setStrokeJoin(Paint.Join.MITER);
        this.mpaint.setStrokeCap(Paint.Cap.BUTT);
        this.mpaint.setXfermode(new PorterDuffXfermode((PorterDuff.Mode.CLEAR)));
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(this.path, this.mpaint);
    }
}

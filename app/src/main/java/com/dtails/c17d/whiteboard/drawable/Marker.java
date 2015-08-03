package com.dtails.c17d.whiteboard.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Draw with a Dry Erase Marker look.
 * <p/>
 * Created by Eric Ong on 8/2/2015.
 */
public class Marker implements MyDrawable {

    private Paint mpaint;
    private Path path;

    public Marker(Path path) {
        this.path = new Path();
        this.path.set(path);
        this.mpaint = new Paint();
        this.mpaint.setColor(Color.RED);
        this.mpaint.setAntiAlias(true);
        this.mpaint.setStrokeWidth(20);
        this.mpaint.setStyle(Paint.Style.STROKE);
        this.mpaint.setStrokeJoin(Paint.Join.MITER);
        this.mpaint.setStrokeCap(Paint.Cap.BUTT);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(this.path, this.mpaint);
    }

    @Override
    public boolean isInstant() {
        return false;
    }
}

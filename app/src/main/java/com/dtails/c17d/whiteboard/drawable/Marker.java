package com.dtails.c17d.whiteboard.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

import java.util.LinkedList;

/**
 * Draw with a Dry Erase Marker look.
 * <p/>
 * Created by Eric Ong on 8/2/2015.
 */
public class Marker implements MyDrawable {

    private Paint paint;
    private Canvas canvas;
    private Bitmap bitmap;
    private LinkedList<Path> pathes;
    private LinkedList<Color> path_colors;
    private Path path;
    private RectF container;
    private boolean broken = true;

    public Marker() {
        this.path = new Path();
//        this.paint = ColorPickerView.currColor;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(20);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.MITER);
        this.paint.setStrokeCap(Paint.Cap.BUTT);
        this.container = new RectF();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setPaintColor(Paint paint) {
        this.paint.setColor(paint.getColor());
    }

    public void updatePath(float x, float y) {
        if (broken) {
            broken = false;
            this.path.moveTo(x, y);
        } else
            this.path.lineTo(x, y);
    }

    public void commitPath() {
        this.path.computeBounds(container, true);
        int w = Math.round(container.width());
        int h = Math.round(container.height());
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
        this.canvas.drawPath(this.path, this.paint);
        broken = true;
    }

    @Override
    public void draw(Canvas canvas, Path path) {
        canvas.drawPath(path, this.paint);
    }
}

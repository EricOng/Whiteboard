package com.dtails.c17d.whiteboard.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

import com.dtails.c17d.whiteboard.views.ColorPickerView;

/**
 * Draw with a Dry Erase Marker look.
 * <p/>
 * Created by Eric Ong on 8/2/2015.
 */
public class Marker implements MyDrawable {

    private Paint paint;
    private Path path;

    public Marker(Path path) {
        this.path = new Path();
        this.path.set(path);
        this.paint = ColorPickerView.currColor;
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(20);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.MITER);
        this.paint.setStrokeCap(Paint.Cap.BUTT);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawPath(this.path, this.paint);
    }
}

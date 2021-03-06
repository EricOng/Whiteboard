package com.dtails.c17d.whiteboard.drawable;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;

/**
 * Created by Eric Ong on 8/2/2015.
 */
public class Stroke implements MyDrawable {

    private Paint paint;

    public Stroke() {
//        this.paint = ColorPickerView.currColor;
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(20);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
        this.paint.setStrokeCap(Paint.Cap.ROUND);
    }

    @Override
    public void draw(Canvas canvas, Path path) {
        canvas.drawPath(path, this.paint);
    }
}

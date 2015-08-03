package com.dtails.c17d.whiteboard.views;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import com.dtails.c17d.whiteboard.R;
import com.dtails.c17d.whiteboard.drawable.Line;
import com.dtails.c17d.whiteboard.drawable.Marker;
import com.dtails.c17d.whiteboard.drawable.MyDrawable;
import com.dtails.c17d.whiteboard.drawable.Point;
import com.dtails.c17d.whiteboard.drawable.Stroke;

/**
 * Created by Eric Ong on 7/29/2015.
 */
public class DrawView extends View {
    public static int drawObjId = 0;

    private Paint paint = new Paint();
    private float startX, startY, endX, endY;
    private Path path;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    private MyDrawable currentType;

    public DrawView(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
        path = new Path();
        inflate(context, R.layout.activity_whiteboard, null);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    public void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0, 0, new Paint(Paint.DITHER_FLAG));
        runDrawObjFactory().draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                if (checkDrawId()) {
                    path.moveTo(startX, startY);
                }
                currentType = runDrawObjFactory();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = event.getX();
                endY = event.getY();
                if (checkDrawId())
                    path.lineTo(endX, endY);
                break;
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                endY = event.getY();
                runDrawObjFactory().draw(drawCanvas);
                if (checkDrawId())
                    path.reset();
                break;
        }
        invalidate();
        return true;
    }

    private boolean checkDrawId() {
        if ((drawObjId == 2) || (drawObjId == 3))
            return true;
        return false;
    }
    /*
        Factory for adding MyDrawable Objects.
        Gets a new MyDrawable object based on an id (Set by buttons in the WhiteboardActivity).
        Otherwise returns a Point object.
     */
    private MyDrawable runDrawObjFactory() {
        if(drawObjId == 0){
            return new Point(endX, endY, paint);
        }
        else if(drawObjId == 1){
            return new Line(startX, startY, endX, endY, paint);
        } else if (drawObjId == 2) {
            return new Stroke(path);//TO-DO: get size value from BrushSize Menu/View.
        } else if (drawObjId == 3) {
            return new Marker(path);//TO-DO: get size value from BrushSize Menu/View.
        }
        return new Point(endX, endY, paint);
    }
}
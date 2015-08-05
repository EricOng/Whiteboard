package com.dtails.c17d.whiteboard.views;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import com.dtails.c17d.whiteboard.R;
import com.dtails.c17d.whiteboard.drawable.Eraser;
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
    public static int ID_POINT = 0;
    public static int ID_LINE = 1;
    public static int ID_BRUSH = 2;
    public static int ID_MARKER = 3;
    public static int ID_ERASER = 4;


    private float startX, startY, endX, endY;
    private Paint canvasPaint = new Paint(Paint.DITHER_FLAG);
    private Path path;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    public DrawView(Context context) {
        super(context);
        path = new Path();
        inflate(context, R.layout.activity_whiteboard, null);
    }

    public Bitmap getCanvasBitmap() {
        return canvasBitmap;
    }

    public void setCanvasBitmap(Bitmap canvasBitmap) {
        this.canvasBitmap = canvasBitmap;
    }

    public Canvas getDrawCanvas() {
        return drawCanvas;
    }

    public void setDrawCanvas(Canvas drawCanvas) {
        this.drawCanvas = drawCanvas;
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
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        if (!isEraser())
            runDrawObjFactory().draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                if (isPathReliant()) {
                    path.moveTo(startX, startY);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                endX = event.getX();
                endY = event.getY();
                if (isPathReliant()) {
                    path.lineTo(endX, endY);
                    if (isEraser()) {
                        runDrawObjFactory().draw(drawCanvas);
                        path.reset();
                        path.moveTo(endX, endY);
                    }
                }

                break;
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                endY = event.getY();
                runDrawObjFactory().draw(drawCanvas);
                if (isPathReliant())
                    path.reset();
                break;
        }
        invalidate();
        return true;
    }

    private boolean isEraser() {
        if (drawObjId == ID_ERASER)
            return true;
        return false;
    }

    private boolean isPathReliant() {
        if ((drawObjId == ID_BRUSH) || (drawObjId == ID_MARKER) || (drawObjId == ID_ERASER))
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
            return new Point(endX, endY);
        }
        else if(drawObjId == 1){
            return new Line(startX, startY, endX, endY);
        } else if (drawObjId == 2) {
            return new Stroke(path);//TO-DO: get size value from BrushSize Menu/View.
        } else if (drawObjId == 3) {
            return new Marker(path);//TO-DO: get size value from BrushSize Menu/View.
        } else if (drawObjId == 4) {
            return new Eraser(path);//TO-DO: get size value from BrushSize Menu/View.
        }
        return new Point(endX, endY);
    }
}
package com.dtails.c17d.whiteboard.views;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.dtails.c17d.whiteboard.R;
import com.dtails.c17d.whiteboard.drawable.Eraser;
import com.dtails.c17d.whiteboard.drawable.Line;
import com.dtails.c17d.whiteboard.drawable.Marker;
import com.dtails.c17d.whiteboard.drawable.MyDrawable;
import com.dtails.c17d.whiteboard.drawable.Point;
import com.dtails.c17d.whiteboard.drawable.Stroke;
import com.dtails.c17d.whiteboard.utils.DrawInfo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by Eric Ong on 7/29/2015.
 */
public class DrawView extends View {
    public static int markerIndex = 0;
    public static int drawMode = 3;
    //    public static int ID_POINT = 0;
//    public static int ID_LINE = 1;
//    public static int ID_BRUSH = 2;
    public static int ID_MARKER = 3;
    public static int ID_ERASER = 4;
    public static int SCROLL_MODE = -1;
    public static int ZOOM_MODE = -2;
    public static Paint currColor = new Paint();

    private float startX, startY, endX, endY;
    private Paint canvasPaint = new Paint(Paint.DITHER_FLAG);
    private Path path;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    private Rect displayRect, subRect;
    private Marker[] markers = new Marker[4];
    private Map<Integer, Bitmap> map;
    private LinkedList<DrawInfo> content;

    public DrawView(Context context) {
        super(context);
        map = new HashMap<>();
        content = new LinkedList<>();
        path = new Path();
        inflate(context, R.layout.activity_whiteboard, null);
        for (int i = 0; i < markers.length; i++) {
            markers[i] = new Marker();
        }
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

    public Paint getCanvasPaint() {
        return canvasPaint;
    }

    public void addMatrix(Matrix mat) {
        for (DrawInfo di : content) {
            di.addMatrix(mat);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        System.out.println("onSizeChange");
        Log.i("CANVAS", "bitmap: " + w + " " + h);
        displayRect = new Rect(0, 0, w, h);
        subRect = new Rect(0, 0, w / 4, h / 4);
        canvasBitmap = Bitmap.createBitmap(2 * w, 2 * h, Bitmap.Config.ARGB_8888);
        drawCanvas = new Canvas(canvasBitmap);
        drawCanvas.scale(0.25f, 0.25f);
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawBitmap(canvasBitmap, subRect, displayRect, canvasPaint);
//        for(DrawInfo di : content) {
//            Bitmap tmp = di.getBitmap().copy(Bitmap.Config.ARGB_8888, true);
//            Canvas tmp2 = new Canvas(tmp);
//            tmp2.concat(di.getMatrix());
//            tmp2.drawBitmap(tmp, 0, 0, canvasPaint);
//            canvas.drawBitmap(tmp, 0, 0, canvasPaint);
//        }
        if (!isEraser())
            runDrawObjFactory().draw(canvas, path);
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
                if (isEraser()) {
                    runDrawObjFactory().draw(drawCanvas, path);
                }

                if (drawMode == SCROLL_MODE) {
                    scrollModeActions();
                }
                path.lineTo(endX, endY);
                break;
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                endY = event.getY();
                MyDrawable obj = runDrawObjFactory();
                obj.draw(drawCanvas, path);
                path.reset();
                break;
        }

        invalidate();
        return true;
    }

    private void scrollModeActions() {
        int dx, dy;
        dx = Math.round(endX - startX) / 4;
        dy = Math.round(endY - startY) / 4;
        if (subRect.left + dx <= 0 || subRect.right + dx >= canvasBitmap.getWidth())
            dx = 0;
        if (subRect.top + dy <= 0 || subRect.bottom + dy >= canvasBitmap.getHeight())
            dy = 0;
        subRect.offset(dx, dy);
        drawCanvas.translate(4 * dx, 4 * dy);
    }

    private boolean isEraser() {
        if (drawMode == ID_ERASER)
            return true;
        return false;
    }

    private boolean isPathReliant() {
//        Uncomment when the use of lines and points are re-enabled
//        if ((drawMode == ID_BRUSH) || (drawMode == ID_MARKER) || (drawMode == ID_ERASER))
//            return true;
//        return false;
        return true;
    }

    /*
        Factory for adding MyDrawable Objects.
        Gets a new MyDrawable object based on an id (Set by buttons in the WhiteboardActivity).
        Otherwise returns a Point object.
     */
    private MyDrawable runDrawObjFactory() {
        if (drawMode == 0) {
            return new Point(endX, endY);
        } else if (drawMode == 1) {
            return new Line(startX, startY, endX, endY);
        } else if (drawMode == 2) {
            return new Stroke();
        } else if (drawMode == 3) {
            markers[markerIndex].setPaintColor(currColor);
            return markers[markerIndex];
        } else if (drawMode == 4) {
            return new Eraser();
        }
        return new Point(endX, endY) {
            @Override
            public void draw(Canvas c, Path p) {

            }
        };
    }
}
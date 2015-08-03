package com.dtails.c17d.whiteboard.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import com.dtails.c17d.whiteboard.R;
import com.dtails.c17d.whiteboard.drawable.Line;
import com.dtails.c17d.whiteboard.drawable.MyDrawable;
import com.dtails.c17d.whiteboard.drawable.Point;
import com.dtails.c17d.whiteboard.drawable.Stroke;

import java.util.LinkedList;

/**
 * Created by Eric Ong on 7/29/2015.
 */
public class DrawView extends View {
    public static int drawObjId = 0;

    private Paint paint = new Paint();
    private float startX, startY, endX, endY;
    private Path path;
    private MyDrawable currentType;
    private static LinkedList<MyDrawable> content;

    public DrawView(Context context) {
        super(context);
        content = new LinkedList<MyDrawable>();
        paint.setColor(Color.BLACK);
        path = new Path();
        inflate(context, R.layout.activity_whiteboard, null);
    }

    @Override
    public void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        for(MyDrawable l : content){
            l.draw(canvas);
        }
//        canvas.drawCircle(endX, endY, 5.0f, paint);
        runDrawObjFactory().draw(canvas);
//        canvas.drawPath(path, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                if (drawObjId == 2) {
                    path.moveTo(startX, startY);
                }
                currentType = runDrawObjFactory();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = event.getX();
                endY = event.getY();
                if (drawObjId == 2)
                    path.lineTo(endX, endY);
                if (currentType.isInstant())
                    content.add(runDrawObjFactory());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                endY = event.getY();
                content.add(runDrawObjFactory());

                if (drawObjId == 2)
                    path.reset();
                invalidate();
                break;
        }
        return true;
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
            return new Stroke(50, path);//TO-DO: get size value from BrushSize Menu/View.
        }
        return new Point(endX, endY, paint);
    }
}
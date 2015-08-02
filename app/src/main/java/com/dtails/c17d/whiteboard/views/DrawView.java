package com.dtails.c17d.whiteboard.views;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import com.dtails.c17d.whiteboard.R;
import com.dtails.c17d.whiteboard.drawable.Line;
import com.dtails.c17d.whiteboard.drawable.MyDrawable;
import com.dtails.c17d.whiteboard.drawable.Point;

import java.util.LinkedList;

/**
 * Created by Eric Ong on 7/29/2015.
 */
public class DrawView extends View {
    private Paint paint = new Paint();
    public static float startX;
    public static float startY;
    private float endX;
    private float endY;
    public Canvas mCanvas;
    public static int drawObjId = 0;
    private MyDrawable currentType;
    private static LinkedList<MyDrawable> content;

    public DrawView(Context context) {
        super(context);
        content = new LinkedList<MyDrawable>();
        paint.setColor(Color.BLACK);
        inflate(context, R.layout.activity_whiteboard, null);
    }

    @Override
    public void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        for(MyDrawable l : content){
            l.draw(canvas);
        }
//        canvas.drawCircle(endX, endY, 5.0f, paint);
        getDrawObj().draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                currentType = getDrawObj();
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                endX = event.getX();
                endY = event.getY();
                if(currentType.isInstant())
                    content.add(getDrawObj());
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                endY = event.getY();
                content.add(getDrawObj());
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
    private MyDrawable getDrawObj(){
        if(drawObjId == 0){
            return new Point(endX, endY, paint);
        }
        else if(drawObjId == 1){
            return new Line(startX, startY, endX, endY, paint);
        }
        return new Point(endX, endY, paint);
    }
}
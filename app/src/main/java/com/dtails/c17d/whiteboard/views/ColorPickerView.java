package com.dtails.c17d.whiteboard.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Wrapper for ColorPicker.
 *
 * Created by Eric Ong on 8/1/2015.
 */
public class ColorPickerView extends View {

    private FrameLayout frameLayout;
    private ImageView palette;

    public ColorPickerView(Context context) {
        super(context);

        frameLayout = new FrameLayout(context);
        palette = new ImageView(context) {
            int r = 0, g = 0, b = 255;
            @Override
            protected void onDraw(Canvas canvas) {
//                getBackgroundTintList();
                super.onDraw(canvas);
                String msg = "w: " + canvas.getWidth() + " h: " + canvas.getHeight();
                Log.i("COLOR_PICKER", msg);
                Paint color = new Paint();
//                for(int i = 0; i < canvas.getHeight(); i++) {
//                    color.setARGB(1, r, g, b);
//                    for(int j = canvas.getWidth()/2; j < canvas.getWidth()/2+10; j++) {
//                        canvas.drawPoint(j, i, color);
//                    }
//                    b += 255/canvas.getHeight();
//                }
                color.setColor(Color.RED);
                canvas.drawCircle(100, 100, 20, color);
            }
        };
//        palette.setBackgroundColor(Color.parseColor("#808080"));
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(200, 200);
//        params.gravity = Gravity.CENTER_HORIZONTAL;
//        params.leftMargin = 200;
        frameLayout.setLayoutParams(params);
        frameLayout.addView(palette);
    }

    public View getView(){
        return frameLayout;
    }
}

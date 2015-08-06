package com.dtails.c17d.whiteboard.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.dtails.c17d.whiteboard.utils.TypeConverter;

/**
 * Wrapper for ColorPicker.
 *
 * Created by Eric Ong on 8/1/2015.
 */
public class ColorPickerView extends View {

    private FrameLayout frameLayout;
    private ImageView palette;
    private Paint colors = new Paint();
    public static Paint currColor;
    private final int[] mHueBarColors = new int[258];

    public ColorPickerView(Context context) {
        super(context);
        currColor = new Paint();
        currColor.setColor(Color.BLACK);
        frameLayout = new FrameLayout(context);

        // Initialize color spectrum
        int index = 0;
        for (float i = 0; i < 256; i += 256 / 42) // Red (#f00) to pink (#f0f)
        {
            mHueBarColors[index] = Color.rgb(255, 0, (int) i);
            index++;
        }
        for (float i = 0; i < 256; i += 256 / 42) // Pink (#f0f) to blue (#00f)
        {
            mHueBarColors[index] = Color.rgb(255 - (int) i, 0, 255);
            index++;
        }
        for (float i = 0; i < 256; i += 256 / 42) // Blue (#00f) to light blue (#0ff)
        {
            mHueBarColors[index] = Color.rgb(0, (int) i, 255);
            index++;
        }
        for (float i = 0; i < 256; i += 256 / 42) // Light blue (#0ff) to green (#0f0)
        {
            mHueBarColors[index] = Color.rgb(0, 255, 255 - (int) i);
            index++;
        }
        for (float i = 0; i < 256; i += 256 / 42) // Green (#0f0) to yellow (#ff0)
        {
            mHueBarColors[index] = Color.rgb((int) i, 255, 0);
            index++;
        }
        for (float i = 0; i < 256; i += 256 / 42) // Yellow (#ff0) to red (#f00)
        {
            mHueBarColors[index] = Color.rgb(255, 255 - (int) i, 0);
            index++;
        }

        //Initialize view with colors
        palette = new ImageView(context) {
            int r = 0, g = 0, b = 255;
            @Override
            protected void onDraw(Canvas canvas) {
//                getBackgroundTintList();
                super.onDraw(canvas);
                String msg = "w: " + canvas.getWidth() + " h: " + canvas.getHeight();
                Log.i("COLOR_PICKER", msg);

                for (int x = 0; x < 256; x++) {
                    colors.setColor(mHueBarColors[x]);
                    canvas.drawLine(x + 10, 0, x + 10, 40, colors);
                }
            }

            @Override
            public boolean onTouchEvent(MotionEvent event) {
                int i = Math.round(event.getX());
                if (i >= mHueBarColors.length)
                    i = mHueBarColors.length - 1;
                currColor.setColor(mHueBarColors[i]);
                return true;
            }
        };
//        palette.setBackgroundColor(Color.parseColor("#808080"));
        palette.setLayoutParams(new ViewGroup.LayoutParams(
                TypeConverter.convertInt_DP(90, getResources().getDisplayMetrics()),
                TypeConverter.convertInt_DP(90, getResources().getDisplayMetrics())
        ));
        frameLayout.addView(palette);
    }

    public View getView(){
        return frameLayout;
    }
}

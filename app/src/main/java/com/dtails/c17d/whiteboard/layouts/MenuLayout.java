package com.dtails.c17d.whiteboard.layouts;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dtails.c17d.whiteboard.views.DrawView;

import java.util.ArrayList;

/**
 * Wrapper for menu layouts and views.
 *
 * Created by Eric Ong on 8/1/2015.
 */
public class MenuLayout extends LinearLayout {

    public MenuLayout(Context context) {
        super(context);
        this.setOrientation(LinearLayout.VERTICAL);
        for (View v : addOnClickEvents(context))
            this.addView(v);
    }

    private ArrayList<View> addOnClickEvents(Context context) {

        ArrayList<View> views = new ArrayList<View>();
        Button b_line = new Button(context);
        b_line.setText("Line");
        b_line.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        OnClickListener ocl = new OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
//                DrawView.drawMode = DrawView.ID_LINE;
                DrawView dv = ((DrawLayout) getParent()).getDrawView();
                //Test zooming in
//                Bitmap bitmap = dv.getCanvasBitmap();
//                Bitmap newBitmap = Bitmap.createBitmap(
//                        bitmap.getWidth(),
//                        bitmap.getHeight(),
//                        Bitmap.Config.ARGB_8888);
//                newBitmap.setDensity(bitmap.getDensity());
//                dv.setCanvasBitmap(newBitmap);
//
//                Canvas canvas = new Canvas(dv.getCanvasBitmap());
//                canvas.drawBitmap(bitmap,
//                        new Rect(bitmap.getWidth()/4, bitmap.getHeight()/4,
//                                3*bitmap.getWidth()/4, 3*bitmap.getHeight()/4),
//                        new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
//                        null
//                        );
//                dv.setDrawCanvas(canvas);

                Matrix mat = new Matrix();
                mat.postTranslate(dv.getDrawCanvas().getWidth() / 2, dv.getDrawCanvas().getHeight() / 2);
                mat.postScale(1.2f, 1.2f);
                dv.addMatrix(mat);
            }
        };
        b_line.setOnClickListener(ocl);

        Button b_zoom = new Button(context);
        b_zoom.setText("Zoom");
        b_zoom.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ocl = new OnClickListener(){
            @Override
            public void onClick(View v) {
                DrawView.drawMode = DrawView.ZOOM_MODE;
            }
        };

        b_zoom.setOnClickListener(ocl);

        Button b_scroll = new Button(context);
        b_scroll.setText("Scroll");
        b_scroll.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {
//                DrawView.drawMode = DrawView.ID_BRUSH;
                DrawView.drawMode = DrawView.SCROLL_MODE;

            }
        };
        b_scroll.setOnClickListener(ocl);

        CustomMarkerLayout customMarkerView_1 = new CustomMarkerLayout(context);
        customMarkerView_1.setName("Marker 1");
        CustomMarkerLayout customMarkerView_2 = new CustomMarkerLayout(context);
        customMarkerView_2.setName("Marker 2");

        final Button b_erase = new Button(context);
        b_erase.setText("Eraser");
        b_erase.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawView.drawMode = DrawView.ID_ERASER;
            }
        };
        b_erase.setOnClickListener(ocl);

//        views.add(b_line);
//        views.add(b_zoom);
        views.add(b_scroll);
        views.add(customMarkerView_1);
        views.add(customMarkerView_2);
        views.add(b_erase);
        return views;
    }
}

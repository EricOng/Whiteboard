package com.dtails.c17d.whiteboard.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dtails.c17d.whiteboard.layouts.CustomMarkerLayout;

import java.util.ArrayList;

/**
 * Wrapper for menu layouts and views.
 *
 * Created by Eric Ong on 8/1/2015.
 */
public class MenuView extends View {
    private LinearLayout b_layout;

    public MenuView(Context context) {
        super(context);

        b_layout = new LinearLayout(context);
        b_layout.setOrientation(LinearLayout.VERTICAL);

        for (View v : addOnClickEvents(context))
            b_layout.addView(v);

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
                Log.i("BUTTON_LINE", "clicked");
                DrawView.drawObjId = DrawView.ID_LINE;
            }
        };
        b_line.setOnClickListener(ocl);

        Button b_point = new Button(context);
        b_point.setText("Point");
        b_point.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ocl = new OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("BUTTON_POINT", "clicked");
                DrawView.drawObjId = DrawView.ID_POINT;
            }
        };
        b_point.setOnClickListener(ocl);

        Button b_brush = new Button(context);
        b_brush.setText("Brush");
        b_brush.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BUTTON_BRUSH", "clicked");
                DrawView.drawObjId = DrawView.ID_BRUSH;
            }
        };
        b_brush.setOnClickListener(ocl);

//        Button b_marker = new Button(context);
//        b_marker.setText("Marker");
//        b_marker.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        ocl = new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("BUTTON_MARKER", "clicked");
//                DrawView.drawObjId = DrawView.ID_MARKER;
//            }
//        };
//        b_marker.setOnClickListener(ocl);
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
                Log.i("BUTTON_ERASE", "clicked");
                DrawView.drawObjId = DrawView.ID_ERASER;
            }
        };
        b_erase.setOnClickListener(ocl);

        views.add(b_line);
        views.add(b_point);
        views.add(b_brush);
//        views.add(b_marker);
        views.add(customMarkerView_1);
        views.add(customMarkerView_2);
        views.add(b_erase);
        return views;
    }

    public View getView() {
        return b_layout;
    }
}

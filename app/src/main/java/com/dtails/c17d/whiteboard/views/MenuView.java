package com.dtails.c17d.whiteboard.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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

        b_layout.addView((new ColorPickerView(context)).getView());
    }

    private ArrayList<View> addOnClickEvents(Context context) {

        ArrayList<View> views = new ArrayList<View>();

        Button b_line = new Button(context);
        b_line.setText("Line");
        b_line.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BUTTON_LINE", "clicked");
                DrawView.drawObjId = 1;
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
                DrawView.drawObjId = 0;
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
                DrawView.drawObjId = 2;
            }
        };
        b_brush.setOnClickListener(ocl);

        views.add(b_line);
        views.add(b_point);
        views.add(b_brush);
        return views;
    }

    public View getView() {
        return b_layout;
    }
}

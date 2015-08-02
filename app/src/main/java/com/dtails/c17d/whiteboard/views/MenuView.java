package com.dtails.c17d.whiteboard.views;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dtails.c17d.whiteboard.R;

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

        Button b_point = new Button(context);
        Button b_line = new Button(context);
        b_line.setText("Line");
        b_line.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        b_point.setText("Point");
        b_point.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        OnClickListener ocl = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("BUTTON_LINE", "clicked");
                DrawView.drawObjId = 1;
            }
        };
        b_line.setOnClickListener(ocl);

        ocl = new OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("BUTTON_POINT", "clicked");
                DrawView.drawObjId = 0;
            }
        };
        b_point.setOnClickListener(ocl);

        b_layout.addView(b_line);
        b_layout.addView(b_point);

        b_layout.addView((new ColorPickerView(context)).getView());

        inflate(context, R.layout.activity_whiteboard, null);
    }

    public View getView() {
        return b_layout;
    }
}

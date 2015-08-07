package com.dtails.c17d.whiteboard.layouts;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.dtails.c17d.whiteboard.views.DrawView;

/**
 * Created by Eric Ong on 8/6/2015.
 */
public class CustomMarkerLayout extends LinearLayout {

    private LinearLayout alwaysVisibleLayer;
    private Button btnView_name;
    private ImageButton clrView;
    private ColorPickerLayout clrPickerView;

    public CustomMarkerLayout(Context context) {
        super(context);
        this.setOrientation(VERTICAL);

        alwaysVisibleLayer = new LinearLayout(context);
        alwaysVisibleLayer.setOrientation(HORIZONTAL);

        btnView_name = new Button(context);

        clrPickerView = new ColorPickerLayout(context);
        clrPickerView.setVisibility(GONE);

        clrView = new ImageButton(context);
        clrView.setBackgroundColor(Color.BLACK);
        alwaysVisibleLayer.addView(clrView);
        alwaysVisibleLayer.addView(btnView_name);
        this.addView(alwaysVisibleLayer);
        this.addView(clrPickerView);

        OnClickListener onClickListener = new OnClickListener() {

            private boolean show = false;

            @Override
            public void onClick(View v) {
                if (!show)
                    clrPickerView.setVisibility(VISIBLE);
                else
                    clrPickerView.setVisibility(GONE);
                show = !show;
            }
        };
        clrView.setOnClickListener(onClickListener);

        onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawView.drawObjId = DrawView.ID_MARKER;
                DrawView.markerIndex = 1;
                setMarkerColor(clrPickerView.currColor.getColor());
                DrawView.currColor = clrPickerView.currColor;
            }
        };
        btnView_name.setOnClickListener(onClickListener);
        clrPickerView.getPalette().setOnClickListener(onClickListener);

    }

    public void setName(CharSequence name) {
        btnView_name.setText(name);
    }

    public void setMarkerColor(int color) {
        clrView.setBackgroundColor(color);
    }
}


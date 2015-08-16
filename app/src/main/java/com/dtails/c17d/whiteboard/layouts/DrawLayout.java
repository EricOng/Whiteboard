package com.dtails.c17d.whiteboard.layouts;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

import com.dtails.c17d.whiteboard.views.DrawView;

/**
 * Created by Eric Ong on 8/4/2015.
 */
public class DrawLayout extends LinearLayout {

    DrawView drawView;
    MenuLayout menuLayout;

    public DrawLayout(Context context) {
        super(context);
        drawView = new DrawView(context);
        drawView.setBackgroundColor(Color.WHITE);
        menuLayout = new MenuLayout(context);
        this.addView(menuLayout);
        this.addView(drawView);
    }

    public DrawView getDrawView() {
        return drawView;
    }

    public void setDrawView(DrawView drawView) {
        this.drawView = drawView;
    }

    public MenuLayout getMenuLayout() {
        return menuLayout;
    }

    public void setMenuLayout(MenuLayout menuLayout) {
        this.menuLayout = menuLayout;
    }


}

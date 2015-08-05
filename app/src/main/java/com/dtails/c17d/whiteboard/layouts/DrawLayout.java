package com.dtails.c17d.whiteboard.layouts;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;

import com.dtails.c17d.whiteboard.views.DrawView;
import com.dtails.c17d.whiteboard.views.MenuView;

/**
 * Created by Eric Ong on 8/4/2015.
 */
public class DrawLayout extends LinearLayout {

    DrawView drawView;
    MenuView menuView;

    public DrawLayout(Context context) {
        super(context);

        drawView = new DrawView(context);
        drawView.setBackgroundColor(Color.WHITE);
        menuView = new MenuView(context);
        this.addView(menuView.getView());
        this.addView(drawView);
    }

    public DrawView getDrawView() {
        return drawView;
    }

    public void setDrawView(DrawView drawView) {
        this.drawView = drawView;
    }

    public MenuView getMenuView() {
        return menuView;
    }

    public void setMenuView(MenuView menuView) {
        this.menuView = menuView;
    }
}

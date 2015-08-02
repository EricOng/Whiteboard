package com.dtails.c17d.whiteboard.drawable;

import android.graphics.Canvas;

/**
 * Interface for creating objects to be drawn onto Canvas objects.
 * Created by Eric Ong on 7/30/2015.
 */
public interface MyDrawable {
     void draw(Canvas canvas);
     boolean isInstant();
}

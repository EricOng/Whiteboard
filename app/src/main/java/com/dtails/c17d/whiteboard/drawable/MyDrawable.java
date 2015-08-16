package com.dtails.c17d.whiteboard.drawable;

import android.graphics.Canvas;
import android.graphics.Path;

/**
 * Interface for creating objects to be drawn onto Canvas objects.
 * Created by Eric Ong on 7/30/2015.
 *
 */
public interface MyDrawable {
     /**
      * @param canvas the canvas to draw to.
      * @param path   the path to be drawn, can be null if drawing is not dependent on a path
      */
     void draw(Canvas canvas, Path path);
}

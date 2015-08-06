package com.dtails.c17d.whiteboard.utils;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Eric Ong on 8/4/2015.
 */
public class TypeConverter {

    public static int convertInt_DP(int val, DisplayMetrics displayMetrics) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, val, displayMetrics);
    }
}

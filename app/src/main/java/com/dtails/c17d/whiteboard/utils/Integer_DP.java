package com.dtails.c17d.whiteboard.utils;

import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by Eric Ong on 8/4/2015.
 */
public class Integer_DP {

    private int dp;

    public Integer_DP(int val, DisplayMetrics displayMetrics) {
        dp = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, val, displayMetrics);
    }

    public int getInt_DP() {
        return dp;
    }
}

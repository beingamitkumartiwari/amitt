package com.getfreerecharge.trainschedule.interfacess;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

/**
 * Created by amit on 4/18/2017.
 */

public class  RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}

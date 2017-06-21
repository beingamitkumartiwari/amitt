package com.getfreerecharge.trainschedule.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.scheduleoftrains.Day;

import java.util.ArrayList;

/**
 * Created by amit on 3/27/2017.
 */

public class ScheduleOfTrainAdapter extends RecyclerView.Adapter<ScheduleOfTrainAdapter.ScheduleOfTrainViewHolder> {

    Context context;
    ArrayList<Day> rowitem;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";
    public ScheduleOfTrainAdapter(Context context, ArrayList<Day> rowitem) {
        this.context = context;
        this.rowitem = rowitem;
    }

    @Override
    public ScheduleOfTrainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_schedule_set_layout, parent, false);
        return new ScheduleOfTrainViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ScheduleOfTrainViewHolder holder, int position) {
        Day day = rowitem.get(position);
        holder.day.setText(day.getDayCode().toString());
        holder.yes_or_no.setText(day.getRuns().toString());
    }

    @Override
    public int getItemCount() {
        return rowitem.size();
    }

    public class ScheduleOfTrainViewHolder extends RecyclerView.ViewHolder{
        TextView day, yes_or_no;
        public ScheduleOfTrainViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.day);
            yes_or_no = (TextView) itemView.findViewById(R.id.yes_or_no);
            Typeface tf2 = Typeface.createFromAsset(context.getAssets(), fontPaththree);
            day.setTypeface(tf2);
            yes_or_no.setTypeface(tf2);
        }
    }

}

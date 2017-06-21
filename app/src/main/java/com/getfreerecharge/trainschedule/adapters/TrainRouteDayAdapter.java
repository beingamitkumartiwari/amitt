package com.getfreerecharge.trainschedule.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.trainroutes.Day;

import java.util.ArrayList;

/**
 * Created by amit on 3/24/2017.
 */

public class TrainRouteDayAdapter extends RecyclerView.Adapter<TrainRouteDayAdapter.TrainRouteDayAdapterViewHolder> {

    Context context;
    ArrayList<Day> rowitem;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    public TrainRouteDayAdapter(Context context, ArrayList<Day> rowitem) {
        this.context = context;
        this.rowitem = rowitem;
    }

    @Override
    public TrainRouteDayAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_route_set_days_layout, parent, false);
        return new TrainRouteDayAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrainRouteDayAdapterViewHolder holder, int position) {
        Day day = rowitem.get(position);
        holder.days.setText(day.getDayCode().toString());
        holder.yes_or_no.setText(day.getRuns().toString());
    }

    @Override
    public int getItemCount() {
        return rowitem.size();
    }

    public class TrainRouteDayAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView days, yes_or_no;
        public TrainRouteDayAdapterViewHolder(View itemView) {
            super(itemView);
            days = (TextView) itemView.findViewById(R.id.days);
            yes_or_no = (TextView) itemView.findViewById(R.id.yes_or_no);

            Typeface tf2 = Typeface.createFromAsset(context.getAssets(), fontPaththree);

            days.setTypeface(tf2);
            yes_or_no.setTypeface(tf2);
        }
    }
}

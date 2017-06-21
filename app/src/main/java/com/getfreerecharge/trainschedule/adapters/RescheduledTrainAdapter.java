package com.getfreerecharge.trainschedule.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.rescheduledtrains.Train;

import java.util.ArrayList;

/**
 * Created by amit on 3/28/2017.
 */

public class RescheduledTrainAdapter extends RecyclerView.Adapter <RescheduledTrainAdapter.rescheduledTrainAdapterViewHolder> {

    Context context;
    ArrayList<Train> rowitem;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";
    public RescheduledTrainAdapter(Context context, ArrayList<Train> rowitem) {
        this.context = context;
        this.rowitem = rowitem;
    }

    @Override
    public rescheduledTrainAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.reschedule_train_set_layout, parent, false);
        return new rescheduledTrainAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(rescheduledTrainAdapterViewHolder holder, int position) {
        Train t = rowitem.get(position);
        holder.train_name.setText(t.getName().toString());
        holder.train_number.setText(t.getNumber().toString());
        holder.to.setText(t.getTo().getName().toString());
        holder.from.setText(t.getFrom().getName().toString());
        holder.reschedule_date.setText(t.getRescheduledDate().toString());
        holder.reschedule_time.setText(t.getRescheduledTime().toString());
        holder.time_diff.setText(t.getTimeDiff().toString());

    }

    @Override
    public int getItemCount() {
        return rowitem.size();
    }

    public class rescheduledTrainAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView time_diff, reschedule_time, reschedule_date, to, from, train_number, train_name;
        public rescheduledTrainAdapterViewHolder(View itemView) {
            super(itemView);
            train_name = (TextView) itemView.findViewById(R.id.train_name);
            train_number = (TextView) itemView.findViewById(R.id.train_number);
            from = (TextView) itemView.findViewById(R.id.from);
            to = (TextView) itemView.findViewById(R.id.to);
            reschedule_date = (TextView) itemView.findViewById(R.id.reschedule_date);
            reschedule_time = (TextView) itemView.findViewById(R.id.reschedule_time);
            time_diff = (TextView) itemView.findViewById(R.id.time_diff);
            Typeface tf2 = Typeface.createFromAsset(context.getAssets(), fontPaththree);
            train_name.setTypeface(tf2);
            train_number.setTypeface(tf2);
            from.setTypeface(tf2);
            to.setTypeface(tf2);
            reschedule_date.setTypeface(tf2);
            reschedule_time.setTypeface(tf2);
            time_diff.setTypeface(tf2);
        }
    }
}

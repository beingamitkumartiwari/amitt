package com.getfreerecharge.trainschedule.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.trainarrival.Train;

import java.util.ArrayList;

/**
 * Created by amit on 3/30/2017.
 */

public class TrainArrivalStationAdapter extends RecyclerView.Adapter <TrainArrivalStationAdapter.trainArrivalStationAdapterViewHolder> {

    Context context;
    ArrayList<Train> rowitem;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";
    public TrainArrivalStationAdapter(Context context, ArrayList<Train> rowitem) {
        this.context = context;
        this.rowitem = rowitem;
    }

    @Override
    public trainArrivalStationAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_arrival_at_station_set_layout, parent, false);
        return new trainArrivalStationAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(trainArrivalStationAdapterViewHolder holder, int position) {
        Train t = rowitem.get(position);
        holder.train_name.setText(t.getName().toString());
        holder.train_number.setText(t.getNumber().toString());
        holder.actual_arrival.setText(t.getActarr().toString());
        holder.actual_departer.setText(t.getActdep().toString());
        holder.scheduled_arrival.setText(t.getScharr().toString());
        holder.scheduled_departer.setText(t.getSchdep().toString());
        holder.delayed_arrival.setText(t.getDelayarr().toString());
        holder.delayed_departer.setText(t.getDelaydep().toString());
    }

    @Override
    public int getItemCount() {
        return rowitem.size();
    }

    public class trainArrivalStationAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView delayed_departer, delayed_arrival, scheduled_departer, scheduled_arrival,
                actual_departer, actual_arrival, train_number, train_name,
                textname, textno, actualtext, actualdeptext, schetext, scheddeptext, deltext,
                deldeptext;

        public trainArrivalStationAdapterViewHolder(View itemView) {
            super(itemView);

            train_name = (TextView) itemView.findViewById(R.id.train_name);
            train_number = (TextView) itemView.findViewById(R.id.train_number);
            actual_arrival = (TextView) itemView.findViewById(R.id.actual_arrival);
            actual_departer = (TextView) itemView.findViewById(R.id.actual_departer);
            scheduled_arrival = (TextView) itemView.findViewById(R.id.scheduled_arrival);
            scheduled_departer = (TextView) itemView.findViewById(R.id.scheduled_departer);
            delayed_arrival = (TextView) itemView.findViewById(R.id.delayed_arrival);
            delayed_departer = (TextView) itemView.findViewById(R.id.delayed_departer);

            textname = (TextView) itemView.findViewById(R.id.textname);
            textno = (TextView) itemView.findViewById(R.id.textno);
            actualtext = (TextView) itemView.findViewById(R.id.actualtext);
            actualdeptext = (TextView) itemView.findViewById(R.id.actualdeptext);
            schetext = (TextView) itemView.findViewById(R.id.schetext);
            scheddeptext = (TextView) itemView.findViewById(R.id.scheddeptext);
            deltext = (TextView) itemView.findViewById(R.id.deltext);
            deldeptext = (TextView) itemView.findViewById(R.id.deldeptext);

            Typeface tf2 = Typeface.createFromAsset(context.getAssets(), fontPaththree);

            train_name.setTypeface(tf2);
            train_number.setTypeface(tf2);
            actual_arrival.setTypeface(tf2);
            actual_departer.setTypeface(tf2);
            scheduled_arrival.setTypeface(tf2);
            scheduled_departer.setTypeface(tf2);
            delayed_arrival.setTypeface(tf2);
            delayed_departer.setTypeface(tf2);

            textname.setTypeface(tf2);
            textno.setTypeface(tf2);
            actualtext.setTypeface(tf2);
            actualdeptext.setTypeface(tf2);
            schetext.setTypeface(tf2);
            scheddeptext.setTypeface(tf2);
            deltext.setTypeface(tf2);
            deldeptext.setTypeface(tf2);
        }
    }

}

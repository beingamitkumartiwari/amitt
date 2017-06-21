package com.getfreerecharge.trainschedule.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.trainbetweenstation.Train;

import java.util.ArrayList;

/**
 * Created by amit on 4/11/2017.
 */

public class TrainBetweenStationAdapter extends RecyclerView.Adapter <TrainBetweenStationAdapter.TrainBetweenStationAdapterViewHolder> {

    Context context;
    ArrayList<Train> rowitem;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";
    public TrainBetweenStationAdapter(Context context, ArrayList<Train> rowitem) {
        this.context = context;
        this.rowitem = rowitem;
    }

    @Override
    public TrainBetweenStationAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_between_station_set_layout, parent, false);
        return new TrainBetweenStationAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TrainBetweenStationAdapterViewHolder holder, int position) {
        Train t = rowitem.get(position);
        holder.code.setText(t.getNumber().toString());
        holder.name.setText(t.getName().toString());
        holder.from.setText(t.getFrom().getCode().toString());
        holder.time.setText(t.getSrcDepartureTime().toString());
    }

    @Override
    public int getItemCount() {
        return rowitem.size();
    }

    public class TrainBetweenStationAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView code, name, from, time;
        public TrainBetweenStationAdapterViewHolder(View itemView) {
            super(itemView);
            code = (TextView) itemView.findViewById(R.id.code);
            name = (TextView) itemView.findViewById(R.id.name);
            from = (TextView) itemView.findViewById(R.id.from);
            time = (TextView) itemView.findViewById(R.id.time);

            Typeface tf2 = Typeface.createFromAsset(context.getAssets(), fontPaththree);

            code.setTypeface(tf2);
            name.setTypeface(tf2);
            from.setTypeface(tf2);
            from.setTypeface(tf2);
            time.setTypeface(tf2);
        }
    }
}

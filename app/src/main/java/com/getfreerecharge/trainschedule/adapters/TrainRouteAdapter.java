package com.getfreerecharge.trainschedule.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.trainroutes.Route;

import java.util.ArrayList;

/**
 * Created by amit on 3/24/2017.
 */

public class TrainRouteAdapter extends RecyclerView.Adapter<TrainRouteAdapter.trainRouteViewHolder> {

    Context context;

    ArrayList<Route> rowitemthree;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";
    public TrainRouteAdapter(Context context,  ArrayList<Route> rowitemthree) {
        this.context = context;
        this.rowitemthree = rowitemthree;
    }

    @Override
    public trainRouteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemViewthree = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_route_set_layout, parent, false);

        return new  trainRouteViewHolder(itemViewthree);
    }

    @Override
    public void onBindViewHolder(trainRouteViewHolder holder, int position) {
        Route route = rowitemthree.get(position);
        holder.station_name.setText(route.getFullname().toString());
        holder.station_code.setText(route.getCode().toString());
        holder.arrival_time.setText(route.getScharr().toString());
        holder.departure_time.setText(route.getSchdep().toString());
        holder.halt_time.setText(route.getHalt().toString());
        holder.platform_no.setText(route.getNo().toString());
        holder.distance_km.setText(route.getDistance().toString());
    }

    @Override
    public int getItemCount() {
        return rowitemthree.size();
    }

    public class trainRouteViewHolder extends RecyclerView.ViewHolder
    {
        TextView station_name, station_code, arrival_time, departure_time, halt_time, platform_no, distance_km, textarrival,
                textdep, texthalt, textplate, textdist;

        public trainRouteViewHolder(View itemViewthree) {
            super(itemViewthree);

            station_code= (TextView) itemViewthree.findViewById(R.id.station_code);
            station_name= (TextView) itemViewthree.findViewById(R.id.station_name);
            arrival_time= (TextView) itemViewthree.findViewById(R.id.arrival_time);
            departure_time= (TextView) itemViewthree.findViewById(R.id.departure_time);
            halt_time= (TextView) itemViewthree.findViewById(R.id.halt_time);
            platform_no= (TextView) itemViewthree.findViewById(R.id.platform_no);
            distance_km= (TextView) itemViewthree.findViewById(R.id.distance_km);

            textarrival= (TextView) itemViewthree.findViewById(R.id.textarrival);
            textdep= (TextView) itemViewthree.findViewById(R.id.textdep);
            texthalt= (TextView) itemViewthree.findViewById(R.id.texthalt);
            textplate= (TextView) itemViewthree.findViewById(R.id.textplate);
            textdist= (TextView) itemViewthree.findViewById(R.id.textdist);

            Typeface tf2 = Typeface.createFromAsset(context.getAssets(), fontPaththree);

            station_code.setTypeface(tf2);
            station_name.setTypeface(tf2);
            arrival_time.setTypeface(tf2);
            departure_time.setTypeface(tf2);
            halt_time.setTypeface(tf2);
            platform_no.setTypeface(tf2);
            distance_km.setTypeface(tf2);

            textarrival.setTypeface(tf2);
            textdep.setTypeface(tf2);
            texthalt.setTypeface(tf2);
            textplate.setTypeface(tf2);
            textdist.setTypeface(tf2);

        }
    }
}

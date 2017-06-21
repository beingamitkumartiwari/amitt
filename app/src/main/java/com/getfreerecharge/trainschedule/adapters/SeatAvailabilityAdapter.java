package com.getfreerecharge.trainschedule.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.seatavailabilites.Availability;

import java.util.ArrayList;

/**
 * Created by amit on 4/6/2017.
 */

public class SeatAvailabilityAdapter extends RecyclerView.Adapter <SeatAvailabilityAdapter.SeatAvailabilityAdapterViewHolder>{

    Context context;
    ArrayList<Availability> rowitem;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";
    public SeatAvailabilityAdapter(Context context, ArrayList<Availability> rowitem) {
        this.context = context;
        this.rowitem = rowitem;
    }

    @Override
    public SeatAvailabilityAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.seat_availability_set_layout, parent, false);
        return new SeatAvailabilityAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SeatAvailabilityAdapterViewHolder holder, int position) {
        Availability av = rowitem.get(position);
        holder.date.setText(av.getDate().toString());
        holder.availability.setText(av.getStatus().toString());

    }

    @Override
    public int getItemCount() {
        return rowitem.size();
    }

    public class SeatAvailabilityAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView date, availability;
        public SeatAvailabilityAdapterViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date);
            availability = (TextView) itemView.findViewById(R.id.availability);
            Typeface tf2 = Typeface.createFromAsset(context.getAssets(), fontPaththree);
            date.setTypeface(tf2);
            availability.setTypeface(tf2);
        }
    }
}

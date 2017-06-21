package com.getfreerecharge.trainschedule.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.pnr.Passenger;

import java.util.ArrayList;

/**
 * Created by amit on 3/22/2017.
 */

public class PnrStatusAdapter extends BaseAdapter {

    Context context;
    ArrayList<Passenger> rowitem;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    public PnrStatusAdapter(Context context, ArrayList<Passenger> rowitem) {
        this.context = context;
        this.rowitem = rowitem;
    }

    @Override
    public int getCount() {
        return rowitem.size();
    }

    @Override
    public Object getItem(int position) {
        return rowitem.size();
    }

    @Override
    public long getItemId(int position) {
        return rowitem.indexOf(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.pnr_status_set_layout, null);
            pnrListViewHolder pnrListViewHolder = new pnrListViewHolder(convertView);
            pnrListViewHolder.coach_position = (TextView) convertView.findViewById(R.id.coach_position);
            pnrListViewHolder.booking_status = (TextView) convertView.findViewById(R.id.booking_status);
            pnrListViewHolder.no_of_passenger = (TextView) convertView.findViewById(R.id.no_of_passenger);
            pnrListViewHolder.current_booking_status = (TextView) convertView.findViewById(R.id.current_booking_status);
            convertView.setTag(pnrListViewHolder);
        }
        Passenger passenger = rowitem.get(position);

        pnrListViewHolder holder = (pnrListViewHolder) convertView.getTag();
        holder.coach_position.setText(passenger.getCoachPosition().toString());
        holder.booking_status.setText(passenger.getBookingStatus().toString());
        holder.no_of_passenger.setText(passenger.getNo().toString());
        holder.current_booking_status.setText(passenger.getCurrentStatus().toString());

        return convertView;
    }

    public class pnrListViewHolder {
        TextView coach_position, booking_status, no_of_passenger, current_booking_status,
                coachtext, bookingtext, numbertext, statustext;


        public pnrListViewHolder(View items) {
            coach_position = (TextView) items.findViewById(R.id.coach_position);
            booking_status = (TextView) items.findViewById(R.id.booking_status);
            no_of_passenger = (TextView) items.findViewById(R.id.no_of_passenger);
            current_booking_status = (TextView) items.findViewById(R.id.current_booking_status);

            coachtext = (TextView) items.findViewById(R.id.coachtext);
            bookingtext = (TextView) items.findViewById(R.id.bookingtext);
            numbertext = (TextView) items.findViewById(R.id.numbertext);
            statustext = (TextView) items.findViewById(R.id.statustext);

            Typeface tf2 = Typeface.createFromAsset(context.getAssets(), fontPaththree);

            coach_position.setTypeface(tf2);
            booking_status.setTypeface(tf2);
            no_of_passenger.setTypeface(tf2);
            current_booking_status.setTypeface(tf2);

            coachtext.setTypeface(tf2);
            bookingtext.setTypeface(tf2);
            numbertext.setTypeface(tf2);
            statustext.setTypeface(tf2);
        }
    }

}

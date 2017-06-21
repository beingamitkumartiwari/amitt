package com.getfreerecharge.trainschedule.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.fairenquirey.Fare;

import java.util.ArrayList;

/**
 * Created by amit on 4/8/2017.
 */

public class FairEnquiryAdapter extends RecyclerView.Adapter <FairEnquiryAdapter.FairEnquiryAdapterViewHolder>{

    Context context;
    ArrayList<Fare> rowitem;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    public FairEnquiryAdapter(Context context, ArrayList<Fare> rowitem) {
        this.context = context;
        this.rowitem = rowitem;
    }

    @Override
    public FairEnquiryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fair_enquiry_set_layout, parent, false);
        return new FairEnquiryAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FairEnquiryAdapterViewHolder holder, int position) {
        Fare f = rowitem.get(position);
        holder.name.setText(f.getName().toString());
        holder.fair.setText(f.getFare().toString());
    }

    @Override
    public int getItemCount() {
        return rowitem.size();
    }

    public class FairEnquiryAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView  name, fair, fairtext;
        public FairEnquiryAdapterViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            fair = (TextView) itemView.findViewById(R.id.fair);
            fairtext = (TextView) itemView.findViewById(R.id.fairtext);
            Typeface tf2 = Typeface.createFromAsset(context.getAssets(), fontPaththree);
            name.setTypeface(tf2);
            fair.setTypeface(tf2);
            fairtext.setTypeface(tf2);
        }
    }

}

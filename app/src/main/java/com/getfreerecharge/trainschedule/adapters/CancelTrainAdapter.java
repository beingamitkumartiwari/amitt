package com.getfreerecharge.trainschedule.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.cancelledtrains.Train;
import com.getfreerecharge.trainschedule.models.cancelledtrains.Train_;

import java.util.ArrayList;

/**
 * Created by amit on 3/28/2017.
 */

public class CancelTrainAdapter extends RecyclerView.Adapter <CancelTrainAdapter.cancelTrainAdapterViewHolder> {

    Context context;
    ArrayList<Train> rowitem;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";

    public CancelTrainAdapter(Context context, ArrayList<Train> rowitem) {
        this.context = context;
        this.rowitem = rowitem;
    }

    @Override
    public cancelTrainAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cancel_train_set_layout, parent, false);

        return new cancelTrainAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(cancelTrainAdapterViewHolder holder, int position) {
        Train t = rowitem.get(position);
        holder.tain_no.setText(t.getTrain().getNumber().toString());
        holder.tain_time.setText(t.getTrain().getStartTime().toString());
        holder.tain_name.setText(t.getTrain().getName().toString());
        holder.tain_source.setText(t.getSource().getName().toString());
        holder.tain_dest.setText(t.getDest().getName().toString());
    }

    @Override
    public int getItemCount() {
        return rowitem.size();
    }

    public class cancelTrainAdapterViewHolder extends RecyclerView.ViewHolder{

        TextView tain_no, tain_time, tain_name, tain_source, tain_dest;
        public cancelTrainAdapterViewHolder(View itemView) {
            super(itemView);
            tain_no = (TextView) itemView.findViewById(R.id.tain_no);
            tain_name = (TextView) itemView.findViewById(R.id.tain_name);
            tain_time = (TextView) itemView.findViewById(R.id.tain_time);
            tain_source = (TextView) itemView.findViewById(R.id.tain_source);
            tain_dest = (TextView) itemView.findViewById(R.id.tain_dest);

            Typeface tf2 = Typeface.createFromAsset(context.getAssets(), fontPaththree);

            tain_no.setTypeface(tf2);
            tain_name.setTypeface(tf2);
            tain_time.setTypeface(tf2);
            tain_source.setTypeface(tf2);
            tain_dest.setTypeface(tf2);
        }
    }
}

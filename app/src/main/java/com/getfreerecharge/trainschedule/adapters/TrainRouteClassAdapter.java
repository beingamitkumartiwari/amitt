package com.getfreerecharge.trainschedule.adapters;




import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.trainroutes.Class;

import java.util.ArrayList;

/**
 * Created by amit on 3/24/2017.
 */

public class TrainRouteClassAdapter extends RecyclerView.Adapter<TrainRouteClassAdapter.classAdapterViewHolder>{

    Context context;
    ArrayList<Class> rowitem;
    String fontPathone = "fonts/Quicksand-Bold.ttf";
    String fontPathtwo = "fonts/Quicksand-Light.ttf";
    String fontPaththree = "fonts/Quicksand-Medium.ttf";
    String fontPathfour = "fonts/Quicksand-Regular.ttf";
    public TrainRouteClassAdapter(Context context, ArrayList<Class> rowitem) {
        this.context = context;
        this.rowitem = rowitem;
    }

    @Override
    public classAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.train_route_set_class_layout, parent, false);

        return new classAdapterViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(classAdapterViewHolder holder, int position) {
        Class cl = rowitem.get(position);
        holder.classes.setText(cl.getClassCode().toString());
        holder.yes_or_no.setText(cl.getAvailable().toString());
    }

    @Override
    public int getItemCount() {
        return rowitem.size();
    }

    public class classAdapterViewHolder extends RecyclerView.ViewHolder
    {
        TextView classes, yes_or_no;
        public classAdapterViewHolder(View itemView) {
            super(itemView);
            classes = (TextView) itemView.findViewById(R.id.classes);
            yes_or_no = (TextView) itemView.findViewById(R.id.yes_or_no);

            Typeface tf2 = Typeface.createFromAsset(context.getAssets(), fontPaththree);

            classes.setTypeface(tf2);
            yes_or_no.setTypeface(tf2);
        }
    }
}

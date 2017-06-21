package com.getfreerecharge.trainschedule.adapters;

import android.content.Context;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.getfreerecharge.trainschedule.R;
import com.getfreerecharge.trainschedule.models.trainsugession.SuggestedTrain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amit on 4/27/2017.
 */

public class TesttwoAdapter extends ArrayAdapter<SuggestedTrain> {

//    List<SuggestedTrain> objects;



    ArrayList<SuggestedTrain> suggestedTrains, tempsuggestedTrains, suggestions;

    public TesttwoAdapter(Context context, ArrayList<SuggestedTrain> objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        this.suggestedTrains = objects;
        this.tempsuggestedTrains = new ArrayList<SuggestedTrain>(objects);
        this.suggestions = new ArrayList<SuggestedTrain>(objects);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SuggestedTrain suggestedTrain = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.train_suggestion_set_layout, parent, false);
        }
        TextView textnumber = (TextView) convertView.findViewById(R.id.text_train_number);
        TextView textname = (TextView) convertView.findViewById(R.id.text_train_name);
        if (textnumber != null)
            textnumber.setText(suggestedTrain.getNumber());
        if (textname != null)
            textname.setText(suggestedTrain.getName());
        // Now assign alternate color for rows
        if (position % 2 == 0)
            convertView.setBackgroundColor(getContext().getColor(R.color.odd));
        else
            convertView.setBackgroundColor(getContext().getColor(R.color.even));

        return convertView;
    }


//    @Override
//    public Filter getFilter() {
//        return myFilter;
//    }
//
//    Filter myFilter = new Filter() {
//        @Override
//        public CharSequence convertResultToString(Object resultValue) {
//            SuggestedTrain suggestedTrain = (SuggestedTrain) resultValue;
//            return suggestedTrain.getName();
//        }
//
//        @Override
//        protected FilterResults performFiltering(CharSequence constraint) {
//            if (constraint != null) {
//                suggestions.clear();
//                for (SuggestedTrain train : tempsuggestedTrains) {
//                    suggestions.add(train);
////                    if (train.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
////                        suggestions.add(train);
////                    }
//                }
//
//                FilterResults filterResults = new FilterResults();
//                filterResults.values = suggestions;
//                filterResults.count = suggestions.size();
//                return filterResults;
//            } else {
//                return new FilterResults();
//            }
//        }
//
//        @Override
//        protected void publishResults(CharSequence constraint, FilterResults results) {
//            ArrayList<SuggestedTrain> c = (ArrayList<SuggestedTrain>) results.values;
//            if (results != null && results.count > 0) {
//                clear();
//                for (SuggestedTrain cust : c) {
//                    add(cust);
//                    notifyDataSetChanged();
//                }
//            }
//        }
//    };
}

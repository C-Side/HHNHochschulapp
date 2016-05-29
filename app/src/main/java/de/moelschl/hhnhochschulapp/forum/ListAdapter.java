package de.moelschl.hhnhochschulapp.forum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import de.moelschl.hhnhochschulapp.R;


/**
 * Created by Hasbert on 29.05.2016.
 */
public class ListAdapter extends ArrayAdapter{

    private final Context context;
    private final String[] values;

    public ListAdapter(Context context, String[] source){
        super(context, R.layout.forum_row_layout , source);
        this.context = context;
        this.values = source;
    }

    @Override
    public  View getView(int position, View contentView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowItem = inflater.inflate(R.layout.forum_row_layout, parent, false);

        ImageView image = (ImageView) rowItem.findViewById(R.id.goIcon);
        TextView upperText = (TextView) rowItem.findViewById(R.id.upperText);
        TextView bottomText = (TextView) rowItem.findViewById(R.id.bottomText);

        upperText.setText(values[position]);
        bottomText.setText(values[position]);

        return rowItem;
    }
}
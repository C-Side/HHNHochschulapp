package de.moelschl.hhnhochschulapp.tools;

import android.content.Context;
import android.graphics.drawable.shapes.Shape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.model.ThemeListItem;

/**
 * sets the custom row layout of the Listfragment. Custom is two rows and a icon inside
 * the List instead of a simple list with only one row.
 *
 */

public class ThemeAdapter extends BaseAdapter {

    private ArrayList<ThemeListItem> workingList;
    private LayoutInflater inflater;
    private Context context;


    /**
     * constructor initialize the main work list and sets the loayout reader
     *
     * @param context {@link android.content.Context}
     * @param listItem the first shown list of the Forum.
     */
    public ThemeAdapter(Context context, ArrayList<ThemeListItem> listItem) {
        this.workingList = listItem;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    /**
     * getter for the list size
     * @return listsize
     */
    @Override
    public int getCount() {
        return workingList.size();
    }

    /**
     * getter for a single List item
     *
     * @param position of the list item
     * @return the ThemeListItem Object which holds all information
     */
    @Override
    public ThemeListItem getItem(int position) {
        return workingList.get(position);
    }

    /**
     * getter for the id
     *
     * @param position of the list item
     * @return something we dont use
     */

    @Override
    public long getItemId(int position) {
        return 0;
    }


    /**
     *    the method which loads the single information of a liast row. In our case these informations
    * are title String, descriptino String and a icon.
    *
            * @param position position in the list which is now active
    * @param convertView {@link android.view.View}
    * @param parent {@link android.view.ViewGroup}
    * @return the single list row full of information
    */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextViewHolder textViewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fo_theme_row_layout, parent, false);
            textViewHolder = new TextViewHolder(convertView);
            convertView.setTag(textViewHolder);
        } else {
            textViewHolder = (TextViewHolder) convertView.getTag();
        }

        ThemeListItem listItem = getItem(position);

        textViewHolder.topicTitle.setText(listItem.getTopic());
        textViewHolder.topicDesc.setText(listItem.getDescription());
        textViewHolder.threadCount.setText(String.valueOf(listItem.getThreadCounter()));

        return convertView;
    }


    /**
     * inner class to cast Strings into Textview or ImageView Objects
     */

    private class TextViewHolder {
        TextView topicTitle, topicDesc, threadCount;
        ImageView icon;

        public TextViewHolder(View item) {
            topicTitle = (TextView) item.findViewById(R.id.topicTitle);
            topicDesc = (TextView) item.findViewById(R.id.topicDesc);
            threadCount = (TextView) item.findViewById(R.id.threadCounter);
            //icon = (ImageView) item.findViewById(R.id.goIcon);
        }
    }
}
package de.moelschl.hhnhochschulapp.tools;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.moelschl.hhnhochschulapp.R;
import de.moelschl.hhnhochschulapp.model.ThreadListItem;

/**
 * Created by Hasbert on 25.06.2016.
 */
public class ThreadAdapter extends BaseAdapter{

    private ArrayList<ThreadListItem> workingList;
    private Context context;


    /**
     * constructor initialize the main work list and sets the loayout reader
     *
     * @param context {@link android.content.Context}
     * @param listItem the first shown list of the Forum.
     */
    public ThreadAdapter(Context context, ArrayList<ThreadListItem> listItem) {
        this.workingList = listItem;
        this.context = context;
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
    public ThreadListItem getItem(int position) {
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

        InfoViewHolder infoViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.fo_theme_row_layout, parent, false);
            infoViewHolder = new InfoViewHolder(convertView);
            convertView.setTag(infoViewHolder);
        } else {
            infoViewHolder = (InfoViewHolder) convertView.getTag();
        }

        ThreadListItem listItem = getItem(position);

        infoViewHolder.topicTitle.setText(listItem.getThemeTopic());
        infoViewHolder.topicDesc.setText(listItem.getId());
        infoViewHolder.topicTitle.setText(listItem.getQuestionHeader());
        infoViewHolder.topicDesc.setText(listItem.getQuestionText());
        infoViewHolder.topicTitle.setText(listItem.getUeserNickname());
        infoViewHolder.topicDesc.setText(listItem.getCommentCount());

        return convertView;
    }


    /**
     * inner class to cast all item into showable views
     */

    private class InfoViewHolder {
        TextView topicTitle, topicDesc;
        ImageView icon;

        public InfoViewHolder(View item) {
            topicTitle = (TextView) item.findViewById(R.id.topicTitle);
            topicDesc = (TextView) item.findViewById(R.id.topicDesc);
            //icon = (ImageView) item.findViewById(R.id.goIcon);
        }
    }
}
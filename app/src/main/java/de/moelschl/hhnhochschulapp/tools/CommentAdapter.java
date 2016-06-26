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
import de.moelschl.hhnhochschulapp.model.CommentListItem;
import de.moelschl.hhnhochschulapp.model.ThemeListItem;

/**
 * Created by Hasbert on 25.06.2016.
 */
public class CommentAdapter extends BaseAdapter{


    private ArrayList<CommentListItem> workingList;
    private LayoutInflater inflater;
    private Context context;


    /**
     * constructor initialize the main work list and sets the loayout reader
     *
     * @param context {@link android.content.Context}
     * @param listItem the first shown list of the Forum.
     */
    public CommentAdapter(Context context, ArrayList<CommentListItem> listItem) {
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
    public CommentListItem getItem(int position) {
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
     * the method which loads the single information of a liast row. In our case these informations
     * are title String, descriptino String and a icon.
     *
     * @param position position in the list which is now active
     * @param convertView {@link android.view.View}
     * @param parent {@link android.view.ViewGroup}
     * @return the single list row full of information
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommentViewHolder commentViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.fo_comments_row_layout, parent, false);
            commentViewHolder = new CommentViewHolder(convertView);
            convertView.setTag(commentViewHolder);
        } else {
            commentViewHolder = (CommentViewHolder) convertView.getTag();
        }

        CommentListItem listItem = getItem(position);

        commentViewHolder.answerText.setText(listItem.getAnswerText());
        commentViewHolder.userNickname.setText(listItem.getUserNickname());

        return convertView;
    }


    /**
     * inner class to cast Strings into Textview or ImageView Objects
     */

    private class CommentViewHolder {
        TextView answerText, userNickname;

        public CommentViewHolder(View item) {
            answerText = (TextView) item.findViewById(R.id.answertext);
            userNickname = (TextView) item.findViewById(R.id.usernicknamecom);
        }
    }
}

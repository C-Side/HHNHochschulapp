
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
import de.moelschl.hhnhochschulapp.model.ThemeListItem;

/**
 * sets the custom row layout of the Listfragment. Custom is two rows and a icon inside
 * the List instead of a simple list with only one row.
 *
 */

public class CustomAdapter extends BaseAdapter {

    private ArrayList<ThemeListItem> workingList = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;


    /**
     * constructor initialize the main work list and sets the loayout reader
     *
     * @param context {@link android.content.Context}
     * @param listItem the first shown list of the Forum.
     */
    public CustomAdapter(Context context, ArrayList<ThemeListItem> listItem) {
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
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.fo_theme_row_layout, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ThemeListItem listItem = getItem(position);

        mViewHolder.topicTitle.setText(listItem.getTitle());
        mViewHolder.topicDesc.setText(listItem.getDescription());
        //mViewHolder.icon.setImageResource(currentListData.getImgResId());

        return convertView;
    }

    /**
     * gets called by click and loads the new list data into the internal working list
     * @param nextList the new list which will be shown.
     */
    public void loadNewData(ArrayList<ThemeListItem> nextList){
        workingList = nextList;
        notifyDataSetChanged();
    }

    /**
     * gets called by back clicking and loads the new list data into the internal working list
     * @param lastList the list before button was clicked
     */
    public void goBack(ArrayList<ThemeListItem> lastList){
        workingList = lastList;
        notifyDataSetChanged();
    }

    /**
     * getter for the internal working list
     * @return internal working list
     */

    public ArrayList<ThemeListItem> getMyList(){
        return workingList;
    }

    /**
     * inner class to cast Strings into Textview or ImageView Objects
     */

    private class MyViewHolder {
        TextView topicTitle, topicDesc;
        ImageView icon;

        public MyViewHolder(View item) {
            topicTitle = (TextView) item.findViewById(R.id.topicTitle);
            topicDesc = (TextView) item.findViewById(R.id.topicDesc);
            icon = (ImageView) item.findViewById(R.id.goIcon);
        }
    }
}
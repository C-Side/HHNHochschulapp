
package de.moelschl.hhnhochschulapp.forum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import de.moelschl.hhnhochschulapp.R;

/**
 * Created by Hasbert on 01.06.2016.
 */
public class CutsomAdapter extends BaseAdapter {

    private ArrayList<ListContext> myList = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;


    public CutsomAdapter(Context context, ArrayList<ListContext> myList) {
        this.myList = myList;
        this.context = context;
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return myList.size();
    }

    @Override
    public ListContext getItem(int position) {
        return myList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder mViewHolder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.forum_row_layout, parent, false);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }

        ListContext currentListData = getItem(position);

        mViewHolder.topicTitle.setText(currentListData.getTitle());
        mViewHolder.topicDesc.setText(currentListData.getDescription());
        mViewHolder.icon.setImageResource(currentListData.getImgResId());

        return convertView;
    }

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
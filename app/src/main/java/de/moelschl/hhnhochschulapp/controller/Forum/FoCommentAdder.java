package de.moelschl.hhnhochschulapp.controller.Forum;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import de.moelschl.hhnhochschulapp.R;

/**
 * Created by Hasbert on 27.06.2016.
 */
public class FoCommentAdder extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fo_comment_adder, container, false);

        Button saveButton = (Button) rootView.findViewById(R.id.newcomment);
        saveButton.setOnClickListener(this);


        return rootView;
    }


    @Override
    public void onClick(View v) {
        //Save all in database
        //pop back stack
        //then reload fragment with inserted data
    }
}

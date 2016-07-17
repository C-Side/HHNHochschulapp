package de.moelschl.hhnhochschulapp.controller;

import android.app.Fragment;
import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import de.moelschl.hhnhochschulapp.R;

/**
 * Created by ahenning on 31.05.2016.
 */
public class KalenderFragment extends Fragment {
    private OnWindowTitleSet titleSetter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CalendarView calendarView;
        final TextView dateView;
        final TextView datetermin;

        View rootView = inflater.inflate(R.layout.fragment_kalender, container, false);


        calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);


        dateView = (TextView) rootView.findViewById(R.id.date_view);
        dateView.setText("Keine Termine");


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
               dateView.setText("Date: " + i2 + "." + i1 + "." + i);

            }

            if(dateView.equals("6.6.2016")){
                dateView.setText("Interface Projects");
                dateView.setText("A212");
                dateView.setText("8:00 - 9:30");
            }
        });

        titleSetter.setWindowTitle("Kalender");
        return rootView;
    }


    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        try {
            titleSetter = (OnWindowTitleSet) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnThreadManage or OnWindowTitleSet");
        }
    }


    /**
     * sets variable to default
     */

    @Override
    public void onDetach() {
        super.onDetach();
        titleSetter = null;
    }


}
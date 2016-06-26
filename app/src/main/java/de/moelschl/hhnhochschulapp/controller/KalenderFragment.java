package de.moelschl.hhnhochschulapp.controller;

import android.app.Fragment;
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
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CalendarView calendarView;
        final TextView dateView;

        View rootView = inflater.inflate(R.layout.fragment_kalender, container, false);


        calendarView = (CalendarView) rootView.findViewById(R.id.calendarView);
        dateView = (TextView) rootView.findViewById(R.id.date_view);
        dateView.setText("Date: ");


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                dateView.setText("Date: " + i2 + " / " + i1 + " / " + i);

            }
        });
        return rootView;
    }
}
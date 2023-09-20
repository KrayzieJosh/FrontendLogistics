package za.ac.cput.view2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import za.ac.cput.R;

public class Calendar extends AppCompatActivity {

    CalendarView calendar;
    TextView date_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //Nav Bar
        BottomNavigationView navigationView= findViewById(R.id.navigationBar);
        navigationView.setSelectedItemId(R.id.bottom_home);

        calendar = (CalendarView)
                findViewById(R.id.calendar);
        date_view = (TextView)
                findViewById(R.id.date_view);

        // Add Listener in calendar
        calendar
                .setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {
                            @Override
                            // get the value of DAYS, MONTH, YEARS
                            public void onSelectedDayChange(
                                    @NonNull CalendarView view,
                                    int year,
                                    int month,
                                    int dayOfMonth)
                            {

                                String Date
                                        = dayOfMonth + "-"
                                        + (month + 1) + "-" + year;

                                // set this date in TextView for Display
                                date_view.setText(Date);
                            }
                        });
        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.bottom_home: return true;
                case R.id.schedule:
                    startActivity(new Intent(getApplicationContext(), Schedule.class));
                    finish();
                    return true;

            }
            switch (item.getItemId()){
                case R.id.bottom_home: return true;
                case R.id.drafts:
                    startActivity(new Intent(getApplicationContext(), Drafts.class));
                    finish();
                    return true;

            }
            switch (item.getItemId()){
                case R.id.bottom_home: return true;
                case R.id.calendar:
                    startActivity(new Intent(getApplicationContext(), Calendar.class));
                    finish();
                    return true;

            }
            switch (item.getItemId()){
                case R.id.bottom_home: return true;
                case R.id.settingsCog:
                    startActivity(new Intent(getApplicationContext(), Settings.class));
                    finish();
                    return true;

            }

            return false;
        });

    }
}
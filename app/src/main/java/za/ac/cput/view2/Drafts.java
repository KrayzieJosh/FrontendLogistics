package za.ac.cput.view2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import za.ac.cput.R;

public class Drafts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drafts);

        //Nav Bar
        BottomNavigationView navigationView= findViewById(R.id.navigationBar);
        navigationView.setSelectedItemId(R.id.bottom_home);

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
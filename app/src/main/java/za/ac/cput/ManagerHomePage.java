package za.ac.cput;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import za.ac.cput.view.Delivery;
import za.ac.cput.view.History;
import za.ac.cput.view.Location;
import za.ac.cput.view.Projects;
import za.ac.cput.view.Quotes;
import za.ac.cput.view.TrackDriver;
import za.ac.cput.view2.Calendar;
import za.ac.cput.view2.Drafts;
import za.ac.cput.view2.Schedule;
import za.ac.cput.view2.Settings;

public class ManagerHomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home_page);
        //Buttons
        ImageButton backB = findViewById(R.id.backB);
        ImageButton logOutB = findViewById(R.id.logOutB);
        ImageButton profileB = findViewById(R.id.profileB);
        //Nav Bar
        BottomNavigationView navigationView= findViewById(R.id.navigationBar);
        navigationView.setSelectedItemId(R.id.bottom_home);

        CardView projectsCard = findViewById(R.id.projectsCard); // rework
        CardView deliveryCard = findViewById(R.id.deliveryCard); //rework
        CardView quotesCard = findViewById(R.id.quotesCard); // customize
        CardView trackDriverCard = findViewById(R.id.trackDriverCard); // change
        CardView locationCard = findViewById(R.id.locationCard);
        CardView historyCard = findViewById(R.id.historyCard); // change

        backB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        logOutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
        profileB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });

        projectsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)  {
                Toast.makeText(ManagerHomePage.this, "Manage Projects", Toast.LENGTH_SHORT).show();
                projectssc(); }

        });

        deliveryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManagerHomePage.this, "Delivery", Toast.LENGTH_SHORT).show();
                {deliverysc();}
            }
        });

        quotesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManagerHomePage.this, "Quotes", Toast.LENGTH_SHORT).show();
                {quotessc();}
            }
        });

        trackDriverCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManagerHomePage.this, "Track Driver", Toast.LENGTH_SHORT).show();
                {trackDriversc();}
            }
        });

        locationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManagerHomePage.this, "Location Manager", Toast.LENGTH_SHORT).show();
                {locationsc();}
            }
        });

        historyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ManagerHomePage.this, "Delivery History", Toast.LENGTH_SHORT).show();
                {historysc();}

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
    public void projectssc() {
        Intent intent = new Intent(this, Projects.class);
        startActivity(intent);
    }
    public void deliverysc() {
        Intent i = new Intent(ManagerHomePage.this, Delivery.class);
        startActivity(i);
    }
    public void quotessc(){
        Intent intent2 = new Intent(ManagerHomePage.this, Quotes.class);
        startActivity(intent2);
    }
    public void trackDriversc(){
        Intent intent3 = new Intent(ManagerHomePage.this, TrackDriver.class);
        startActivity(intent3);
    }
    public void locationsc(){
        Intent intent4 = new Intent(ManagerHomePage.this, Location.class);
        startActivity(intent4);
    }
    public void historysc(){
        Intent intent5 = new Intent(ManagerHomePage.this, History.class);
        startActivity(intent5);
    }

}
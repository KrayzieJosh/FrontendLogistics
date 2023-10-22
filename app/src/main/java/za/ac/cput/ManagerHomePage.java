package za.ac.cput;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import za.ac.cput.create.CreateDriver;
import za.ac.cput.create.CreateMaterialQuote;
import za.ac.cput.view.AddProjectManager;
import za.ac.cput.view.AddSiteManager;
import androidx.cardview.widget.CardView;
import za.ac.cput.view.Delivery;
import za.ac.cput.view.DriverRegistration;
import za.ac.cput.view.History;
import za.ac.cput.view.LocationActivity;
import za.ac.cput.view.Projects;
import za.ac.cput.view.Quotes;
import za.ac.cput.view.TrackDriver;
import za.ac.cput.view2.Calendar;
import za.ac.cput.view2.Drafts;
import za.ac.cput.view2.Schedule;
import za.ac.cput.view2.Settings;

public class ManagerHomePage extends AppCompatActivity {

    CardView projectCV, locationCV, materialQuoteCV, driversCV, siteManagerCV, projectManagerCV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home_page);

        projectCV = findViewById(R.id.projectCardView);
        locationCV = findViewById(R.id.locationCardView);
        materialQuoteCV = findViewById(R.id.driversCardView);
        siteManagerCV = findViewById(R.id.siteManagerCardView);
        projectManagerCV = findViewById(R.id.projectManagersCardView);

        //link to projects page
        projectCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                // You can start a new activity, fragment, or navigate to a different layout here
                Intent intent = new Intent(ManagerHomePage.this, Projects.class);
                startActivity(intent);
            }
        });

        //link to location page
        locationCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                // You can start a new activity, fragment, or navigate to a different layout here
                Intent intent = new Intent(ManagerHomePage.this, LocationActivity.class);
                startActivity(intent);
            }
        });

        //link to material quote page
        materialQuoteCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                // You can start a new activity, fragment, or navigate to a different layout here
                Intent intent = new Intent(ManagerHomePage.this, CreateMaterialQuote.class);
                startActivity(intent);
            }
        });

        //link to site manager
        siteManagerCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                // You can start a new activity, fragment, or navigate to a different layout here
                Intent intent = new Intent(ManagerHomePage.this, AddSiteManager.class);
                startActivity(intent);
            }
        });

        //link to project manager:
        projectManagerCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                // You can start a new activity, fragment, or navigate to a different layout here
                Intent intent = new Intent(ManagerHomePage.this, AddProjectManager.class);
                startActivity(intent);
            }
        });

        //link to driver page:
        driversCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the click event
                // You can start a new activity, fragment, or navigate to a different layout here
                Intent intent = new Intent(ManagerHomePage.this, DriverRegistration.class);
                startActivity(intent);
            }
        });
    }
}

        
    }
}

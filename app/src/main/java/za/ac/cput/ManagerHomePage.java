package za.ac.cput;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.cardview.widget.CardView;
import za.ac.cput.view.Delivery;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_home_page);

        // Buttons
        ImageButton backB = findViewById(R.id.backB);
        ImageButton logOutB = findViewById(R.id.logOutB);
        ImageButton profileB = findViewById(R.id.profileB);

        // Navigation Bar
        BottomNavigationView navigationView = findViewById(R.id.navigationBar);
        navigationView.setSelectedItemId(R.id.bottom_home);

        // CardViews
        CardView projectsCard = findViewById(R.id.projectsCard);
        CardView deliveryCard = findViewById(R.id.deliveryCard);
        CardView quotesCard = findViewById(R.id.quotesCard);
        CardView trackDriverCard = findViewById(R.id.trackDriverCard);
        CardView locationCard = findViewById(R.id.locationCard);
        CardView historyCard = findViewById(R.id.historyCard);

        // Set up a single click listener for all CardViews
        View.OnClickListener cardClickListener = v -> {
            int toastMessageId = -1;
            Intent intent = null;

            switch (v.getId()) {
                case R.id.projectsCard:
                    toastMessageId = R.string.toast_manage_projects;
                    intent = new Intent(this, Projects.class);
                    break;
                case R.id.deliveryCard:
                    toastMessageId = R.string.toast_delivery;
                    intent = new Intent(this, Delivery.class);
                    break;
                case R.id.quotesCard:
                    toastMessageId = R.string.toast_quotes;
                    intent = new Intent(this, Quotes.class);
                    break;
                case R.id.trackDriverCard:
                    toastMessageId = R.string.toast_track_driver;
                    intent = new Intent(this, TrackDriver.class);
                    break;
                case R.id.locationCard:
                    toastMessageId = R.string.toast_location_manager;
                    intent = new Intent(this, LocationActivity.class);
                    break;
                case R.id.historyCard:
                    toastMessageId = R.string.toast_delivery_history;
                    intent = new Intent(this, History.class);
                    break;
            }

            if (toastMessageId != -1) {
                Toast.makeText(ManagerHomePage.this, toastMessageId, Toast.LENGTH_SHORT).show();
            }

            if (intent != null) {
                startActivity(intent);
            }
        };

        // Set the click listener for all CardViews
        projectsCard.setOnClickListener(cardClickListener);
        deliveryCard.setOnClickListener(cardClickListener);
        quotesCard.setOnClickListener(cardClickListener);
        trackDriverCard.setOnClickListener(cardClickListener);
        locationCard.setOnClickListener(cardClickListener);
        historyCard.setOnClickListener(cardClickListener);

        // Handle navigationView item clicks
        navigationView.setOnItemSelectedListener(item -> {
            Intent intent = null;

            switch (item.getItemId()) {
                case R.id.schedule:
                    intent = new Intent(getApplicationContext(), Schedule.class);
                    break;
                case R.id.drafts:
                    intent = new Intent(getApplicationContext(), Drafts.class);
                    break;
                case R.id.calendar:
                    intent = new Intent(getApplicationContext(), Calendar.class);
                    break;
                case R.id.settingsCog:
                    intent = new Intent(getApplicationContext(), Settings.class);
                    break;
            }

            if (intent != null) {
                startActivity(intent);
                finish();
                return true;
            }

            return false;
        });
    }
}

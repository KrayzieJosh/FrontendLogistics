package za.ac.cput.view;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import za.ac.cput.R;
import za.ac.cput.domain.Location; // imports from backend not working !!
import za.ac.cput.factory.LocationFactory;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Create a Location object using the factory
        Location location = LocationFactory.createLocation(
                null, // Leave locationId as null to generate it
                "My Location",
                123,
                "Main Street",
                "Cityville",
                12345
        );

        // Find the TextView in your XML layout
        TextView textView = findViewById(R.id.locationTextView);

        // Display the location details in the TextView
        if (location != null) {
            String locationDetails = "Location ID: " + location.getLocationId() + "\n" +
                    "Name: " + location.getName() + "\n" +
                    "Street Number: " + location.getStreetNumber() + "\n" +
                    "Street Name: " + location.getStreetName() + "\n" +
                    "Town or City: " + location.getTownOrCity() + "\n" +
                    "Area Code: " + location.getAreaCode();

            textView.setText(locationDetails);
        }
    }
}

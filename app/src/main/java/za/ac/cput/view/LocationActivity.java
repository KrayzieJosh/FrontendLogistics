package za.ac.cput.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import za.ac.cput.R;
import za.ac.cput.view2.CustomMapFragment;

public class LocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Load the custom Google Maps fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        CustomMapFragment customMapFragment = new CustomMapFragment();
        fragmentManager.beginTransaction().replace(R.id.mapFragment, customMapFragment).commit();
    }
}

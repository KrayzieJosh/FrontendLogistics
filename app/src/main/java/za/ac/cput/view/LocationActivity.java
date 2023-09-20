package za.ac.cput.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import za.ac.cput.R;


public class LocationActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
        //TextField
        EditText addressBar = findViewById(R.id.editTextLocationName);

        //Button
        Button btnCreateLoc = findViewById(R.id.btnCreateLocation);
       // @Override
       // public void onMapReady(GoogleMap googleMap) {
           // mMap = googleMap;

            // Customize and use the Google Map here
        }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}

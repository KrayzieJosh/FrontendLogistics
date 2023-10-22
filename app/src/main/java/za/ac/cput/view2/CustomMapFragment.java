package za.ac.cput.view2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import za.ac.cput.R;
import java.util.Arrays;

public class CustomMapFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    public CustomMapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        // Initialize the Google Maps
        getMapAsync(this);

        // Initialize Places SDK and AutocompleteSupportFragment
        if (!Places.isInitialized()) {
            Places.initialize(requireContext(), getString(R.string.google_maps_key)); // Use a string resource for API key
        }

        AutocompleteSupportFragment autocompleteFragment =
                (AutocompleteSupportFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);

        if (autocompleteFragment != null) {
            autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));

            autocompleteFragment.setOnPlaceSelectedListener(new com.google.android.libraries.places.widget.listener.PlaceSelectionListener() {
                @Override
                public void onPlaceSelected(Place place) {
                    // Handle the selected place (e.g., update the map with the selected location)
                    LatLng selectedLocation = place.getLatLng();
                    if (mMap != null) {
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(selectedLocation));
                    }
                }

                @Override
                public void onError(com.google.android.gms.common.api.Status status) {
                    // Handle errors, if any, during the autocomplete process
                }
            });
        }

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker to Cape Town and move the camera
        LatLng capeTownLocation = new LatLng(-33.9249, 18.4241); // Cape Town coordinates
        mMap.addMarker(new MarkerOptions().position(capeTownLocation).title("Marker in Cape Town"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(capeTownLocation, 10)); // Adjust the zoom level as needed
    }
}

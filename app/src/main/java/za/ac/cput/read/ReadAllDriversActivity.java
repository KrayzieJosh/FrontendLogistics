package za.ac.cput.read;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import za.ac.cput.R;
import za.ac.cput.model.Driver;

public class ReadAllDriversActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all_drivers);

        final TextView textView = findViewById(R.id.DriverList);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.2:8080/driver/getAll";
        // Request a JSON response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Process the JSON array to create a list of DeliveryEvent objects.
                        List<Driver> drivers = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                @SuppressLint({"NewApi", "LocalSuppress"}) Driver driver = new Driver(
                                        jsonObject.getString("driverId"),
                                        jsonObject.getString("firstName"),
                                        jsonObject.getString("lastName"),
                                        jsonObject.getString("contact"),
                                        jsonObject.getString("email"),
                                        jsonObject.getString("driverPosition")
                                );
                                drivers.add(driver);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Display the list of DeliveryEvents in the TextView.
                        String displayText = "Driver:\n";
                        for (Driver event : drivers) {
                            displayText += event.toString() + "\n";
                        }
                        textView.setText(displayText);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Failed to fetch Driver.");

            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);
    }
}
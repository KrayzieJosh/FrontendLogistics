package za.ac.cput.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import za.ac.cput.R;
import za.ac.cput.model.DeliveryEvent;

public class ReadAllDeliveryEvents extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_event_list);

        final TextView textView = findViewById(R.id.DeliveryEventList);
        ;

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.0.141:8080/deliveryEvents/getall";


        // Request a JSON response from the provided URL.
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Process the JSON array to create a list of DeliveryEvent objects.
                        List<DeliveryEvent> deliveryEvents = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                @SuppressLint({"NewApi", "LocalSuppress"}) DeliveryEvent deliveryEvent = new DeliveryEvent(
                                        jsonObject.getString("deliveryEventId"),
                                        jsonObject.getString("deliveryName"),
                                        LocalDateTime.now(),
                                        jsonObject.getInt("streetNumber"),
                                        jsonObject.getString("streetName"),
                                        jsonObject.getString("townOrCity"),
                                        jsonObject.getInt("areaCode")
                                );
                                deliveryEvents.add(deliveryEvent);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        // Display the list of DeliveryEvents in the TextView.
                        String displayText = "Delivery Events:\n";
                        for (DeliveryEvent event : deliveryEvents) {
                            displayText += event.toString() + "\n";
                        }
                        textView.setText(displayText);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Failed to fetch Delivery Events.");

            }
        });

        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest);
    }
}
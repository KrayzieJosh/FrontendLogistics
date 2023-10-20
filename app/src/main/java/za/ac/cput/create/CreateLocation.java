package za.ac.cput.create;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class CreateLocation {
    private final Context context;
    private String baseUrl;

    public CreateLocation(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    public interface CreateLocationListener {
        void onSuccess(); // Define additional parameters if needed

        void onError(VolleyError error);
    }

    public void locationCreation(String locationId,String name, int streetNumber, String streetName,String townOrCity,int areaCode, CreateLocationListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseUrl;

        JSONObject jsonObject = new JSONObject();

        try {
            // Add properties to the JSON object
            jsonObject.put("locationId",locationId);
            jsonObject.put("locationName", name);
            jsonObject.put("locationStreetNumber", streetNumber);
            jsonObject.put("locationStreetName", streetName);
            jsonObject.put("locationTownOrCity", townOrCity);
            jsonObject.put("locationAreaCode", areaCode);

            // Create the POST request
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {


                            // Handle the success response from the backend
                            if (listener != null) {
                                listener.onSuccess();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle the error response
                            if (listener != null) {
                                listener.onError(error);
                            }
                        }
                    }
            );

            // Add the request to the request queue
            queue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON exception here if needed
            if (listener != null) {
                listener.onError(new VolleyError("JSON Exception: " + e.getMessage()));
            }
        }
    }
}


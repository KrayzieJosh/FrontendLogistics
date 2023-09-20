package za.ac.cput;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONObject;

public class ApiRequest {
    private RequestQueue queue;
    private Context context;

    public ApiRequest(Context context) {
        this.context = context;
        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(context);
    }

    public void makeApiRequest(String apiUrl, final ApiResponseListener listener) {
        // Request a JSON response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the JSON response here.
                        if (listener != null) {
                            listener.onSuccess(response);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here.
                        if (listener != null) {
                            listener.onError(error.toString());
                        }
                    }
                });

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }

    public interface ApiResponseListener {
        void onSuccess(JSONObject response);
        void onError(String error);
    }
}

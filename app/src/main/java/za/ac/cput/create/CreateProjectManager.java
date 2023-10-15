package za.ac.cput.create;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateProjectManager {
    private final Context context;
    private String baseUrl;

    public CreateProjectManager(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    public interface CreateProjectManagerListener {
        void onSuccess();

        void onError(VolleyError error);

    }
    public void projectManagerCreation(String projectManagerId,String position,String firstName, String middleName,
                                       String lastName,String contact, String email,
                                       CreateProjectManager.CreateProjectManagerListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseUrl;

        JSONObject jsonObject = new JSONObject();

        try {
            // Add properties to the JSON object
            jsonObject.put("projectManagerId",projectManagerId);
            jsonObject.put("position",position);
            jsonObject.put("firstName", firstName);
            jsonObject.put("middleName", middleName);
            jsonObject.put("lastName", lastName);
            jsonObject.put("contact", contact);
            jsonObject.put("email",email);
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

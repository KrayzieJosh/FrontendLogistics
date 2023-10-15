package za.ac.cput.read;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import za.ac.cput.model.ProjectManager;

public class ReadProjectManager {
    private final Context context;
    private String baseUrl;

    public ReadProjectManager(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    public interface ReadProjectManagerListener {
        void onSuccess(List<ProjectManager> projectManagers); // Define additional parameters if needed

        void onError(VolleyError error);
    }

    public void fetchProjectManagers(ReadProjectManagerListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseUrl; // Set the URL for fetching project managers

        // Create a GET request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null, // No request body for GET requests
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Handle the JSON response to retrieve project managers
                            List<ProjectManager> projectManagers = new ArrayList<>();

                            // Parse the JSON response to populate projectManagers list
                            JSONArray projectManagerArray = response.getJSONArray("data");

                            for (int i = 0; i < projectManagerArray.length(); i++) {
                                JSONObject managerObject = projectManagerArray.getJSONObject(i);

                                String projectManagerId = managerObject.getString("projectManagerId");
                                String position= managerObject.getString("position");
                                String firstName = managerObject.getString("firstName");
                                String middleName = managerObject.getString("middleName");
                                String lastName = managerObject.getString("lastName");
                                String contact = managerObject.getString("contact");
                                String email = managerObject.getString("email");

                                ProjectManager projectManager = new ProjectManager(projectManagerId,position, firstName, middleName, lastName, contact, email);
                                projectManagers.add(projectManager);
                            }

                            if (listener != null) {
                                listener.onSuccess(projectManagers);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            if (listener != null) {
                                listener.onError(new VolleyError("JSON Exception: " + e.getMessage()));
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (listener != null) {
                            listener.onError(error);
                        }
                    }
                }
        );

        queue.add(jsonObjectRequest);
    }
}


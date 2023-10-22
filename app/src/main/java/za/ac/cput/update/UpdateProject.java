package za.ac.cput.update;

import android.content.Intent;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.internal.StringResourceValueReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import za.ac.cput.create.CreateProject;
import za.ac.cput.model.Project;
import za.ac.cput.view2.ViewAndUpdateProject;

public class UpdateProject {

    private final Context context;
    private String baseUrl;

    public UpdateProject(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }
    public interface UpdateProjectStatusListener {
        void onSuccess(); // Define additional parameters if needed

        void onError(VolleyError error);
    }
    public void updateProject(Project project,UpdateProjectStatusListener listener) {


        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseUrl; // Assuming your server has an endpoint to update a specific project by its ID.

        JSONObject jsonObject = new JSONObject();

        try {
            // Add properties to the JSON object for the update.
            jsonObject.put("projectName", project.getProjectName());
            jsonObject.put("projectStatus", project.getProjectStatus());

            // Add other properties as needed for your update.

            // Create the POST request.
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Handle the success response from the backend.
                            if (listener != null) {
                                listener.onSuccess();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Handle the error response.
                            if (listener != null) {
                                listener.onError(error);
                            }
                        }
                    }
            );

            // Add the request to the request queue.
            queue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON exception here if needed.
            if (listener != null) {
                listener.onError(new VolleyError("JSON Exception: " + e.getMessage()));
            }
        }
    }

}



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

import za.ac.cput.model.ProjectManager;
import za.ac.cput.model.SiteManager;


public class CreateProject {
    private final Context context;
    private String baseUrl;

    public CreateProject(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    public interface CreateProjectListener {
        void onSuccess(); // Define additional parameters if needed

        void onError(VolleyError error);
    }

    public void projectCreation(String projectId, String projectName, String projectStatus,
                                String projectManagerId, String siteManagerId, /*Company company,*/
                                CreateProjectListener listener) {
        SiteManager siteManager = new SiteManager();
        siteManagerId =siteManager.getSiteManagerId();
        ProjectManager manager= new ProjectManager();
        projectManagerId = manager.getProjectManagerId();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseUrl;

        JSONObject jsonObject = new JSONObject();

        try {
            // Add properties to the JSON object
            jsonObject.put("projectId",projectId);
            jsonObject.put("projectName", projectName);
            jsonObject.put("projectStatus", projectStatus);

        jsonObject.put("projectManagerId", projectManagerId);
                jsonObject.put("siteManagerId", siteManagerId);
            //jsonObject.put("company", company.getCompanyId());


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
                            if (listener == null) {
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


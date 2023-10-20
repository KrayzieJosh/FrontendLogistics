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

import za.ac.cput.model.Project;


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

    public void projectCreation(Project project,
                                CreateProjectListener listener) {

        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseUrl;


        JSONObject jsonObject = new JSONObject();

        try {
            // Add properties to the JSON object
            jsonObject.put("projectId",project.getProjectId());
            jsonObject.put("projectName", project.getProjectName());
            jsonObject.put("projectStatus", project.getProjectStatus());
            jsonObject.put("driver_id",project.getDriver());

            JSONObject projectManagerObject = new JSONObject();
            projectManagerObject.put("projectManagerId", project.getProjectManager().getProjectManagerId());
            projectManagerObject.put("position",project.getProjectManager().getPosition());
            projectManagerObject.put("firstName",project.getProjectManager().getFirstName());
            projectManagerObject.put("middleName",project.getProjectManager().getMiddleName());
            projectManagerObject.put("lastName",project.getProjectManager().getLastName());
            projectManagerObject.put("email",project.getProjectManager().getEmail());

            jsonObject.put("projectManager",projectManagerObject);

            JSONObject siteManagerObject= new JSONObject();
            siteManagerObject.put("siteManagerId",project.getSiteManager().getSiteManagerId());
            siteManagerObject.put("position",project.getSiteManager().getPosition());
            siteManagerObject.put("firstName",project.getSiteManager().getFirstName());
            siteManagerObject.put("middleName",project.getSiteManager().getMiddleName());
            siteManagerObject.put("lastName",project.getSiteManager().getLastName());
            siteManagerObject.put("contact",project.getSiteManager().getContact());
            siteManagerObject.put("email",project.getSiteManager().getEmail());

            jsonObject.put("siteManager",siteManagerObject);

            JSONObject driverObject= new JSONObject();
            driverObject.put("driverId",project.getDriver().getDriverId());
            driverObject.put("firstName",project.getDriver().getFirstName());
            driverObject.put("lastName",project.getDriver().getLastName());
            driverObject.put("contact",project.getDriver().getContact());
            driverObject.put("email",project.getDriver().getEmail());
            driverObject.put("driverPosition",project.getDriver().getDriverPosition());

            jsonObject.put("driver",driverObject);

            JSONObject deliveryOrderObject = new JSONObject();
            deliveryOrderObject.put("deliveryOrderId",project.getDeliveryOrder().getDeliveryOrderId());
            deliveryOrderObject.put("deliveryAddress",project.getDeliveryOrder().getDeliveryAddress());
            deliveryOrderObject.put("deliveryDate",project.getDeliveryOrder().getDeliveryDate());

            jsonObject.put("deliveryOrder",deliveryOrderObject);









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


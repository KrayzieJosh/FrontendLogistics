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

public class CreateMaterialQuote {
    private final Context context;
    private String baseUrl;

    public CreateMaterialQuote(Context context, String baseUrl) {
        this.context = context;
        this.baseUrl = baseUrl;
    }

    public interface CreateMaterialQuoteListener {
        void onSuccess();

        void onError(VolleyError error);
    }

    public void createMaterialQuote(String materialQuoteId,String materialName, double materialPrice, String materialQuantity, double materialWeight, CreateMaterialQuoteListener listener) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = baseUrl;

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("materialQuoteId",materialQuoteId);
            jsonObject.put("materialName", materialName);
            jsonObject.put("materialPrice", materialPrice);
            jsonObject.put("materialQuantity", materialQuantity);
            jsonObject.put("materialWeight", materialWeight);


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
                            if (listener != null) {
                                listener.onError(error);
                            }
                        }
                    }
            );

            queue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onError(new VolleyError("JSON Exception: " + e.getMessage()));
            }
        }
    }
}

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

import java.util.ArrayList;
import java.util.List;

import za.ac.cput.R;
import za.ac.cput.model.MaterialQuote;


public class ReadAllMaterialQuotes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all_quotes);

        final TextView textView = findViewById(R.id.materialQuotes);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.18.178:8080/materialQuote/getAll";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        List<MaterialQuote> materialQuotes = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                @SuppressLint({"NewApi", "LocalSuppress"}) MaterialQuote materialQuote = new MaterialQuote(
                                        jsonObject.getString("materialQuoteId"),
                                        jsonObject.getString("materialName"),
                                        jsonObject.getDouble("materialPrice"),
                                        jsonObject.getString("materialQuantity"),
                                        jsonObject.getDouble("materialWeight")
                                );
                                materialQuotes.add(materialQuote);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String displayText = "Material Quotes:\n";
                        for (MaterialQuote quote : materialQuotes) {
                            displayText += quote.toString() + "\n";
                        }
                        textView.setText(displayText);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Failed to fetch Material Quotes.");
            }
        });

        queue.add(jsonArrayRequest);
    }
}

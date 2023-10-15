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
import za.ac.cput.model.DeliveryOrder;

public class ReadAllDeliveryOrders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_all_orders);

        final TextView textView = findViewById(R.id.DeliveryOrders);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.18.178:8080/deliveryOrder/getAll";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        List<DeliveryOrder> deliveryOrders = new ArrayList<>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                @SuppressLint({"NewApi", "LocalSuppress"}) DeliveryOrder deliveryOrder = new DeliveryOrder(
                                        jsonObject.getString("deliveryOrderId"),
                                        jsonObject.getString("deliveryAddress"),
                                        jsonObject.getString("deliveryDate")
                                );
                                deliveryOrders.add(deliveryOrder);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String displayText = "Delivery Orders:\n";
                        for (DeliveryOrder order : deliveryOrders) {
                            displayText += order.toString() + "\n";
                        }
                        textView.setText(displayText);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textView.setText("Failed to fetch Delivery Orders.");
            }
        });


        queue.add(jsonArrayRequest);
    }
}

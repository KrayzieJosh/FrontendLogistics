package za.ac.cput.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import za.ac.cput.R;

public class DeleteDeliveryOrder extends AppCompatActivity {

    private EditText orderIdEditText;
    private Button deleteButton;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_order);

        orderIdEditText = findViewById(R.id.DeliveryOrderId);
        deleteButton = findViewById(R.id.btnDeleteDeliveryOrder);
        responseTextView = findViewById(R.id.deleteYesOrNo);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderIdEditText.getText().toString().isEmpty()) {
                    Toast.makeText(DeleteDeliveryOrder.this, "Please enter a Delivery Order ID", Toast.LENGTH_SHORT).show();
                } else {
                    String orderId = orderIdEditText.getText().toString();
                    deleteDeliveryOrder(orderId);
                }
            }
        });
    }

    private void deleteDeliveryOrder(String orderId) {
        String url = "http://192.168.18.178:8080/deliveryOrder/delete/" + orderId;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        orderIdEditText.setText("Successfully deleted: " + response.substring(0));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                responseTextView.setText("Failed to delete Delivery Order. Error: " + error.toString());
            }
        });

        queue.add(stringRequest);
    }
}

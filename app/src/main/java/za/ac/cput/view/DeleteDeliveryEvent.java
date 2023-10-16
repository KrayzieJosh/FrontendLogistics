package za.ac.cput.view;

import android.os.Bundle;
import android.util.Log;
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

public class DeleteDeliveryEvent extends AppCompatActivity {


    private EditText deliveryEventIdEditText;
    private Button deleteButton;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_delivery_event);


        deliveryEventIdEditText = findViewById(R.id.deliveryEventId);
        deleteButton = findViewById(R.id.btnDeleteDeliveryEvent);
        responseTextView = findViewById(R.id.deleteYesOrNo);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deliveryEventIdEditText.getText().toString().isEmpty()) {
                    Toast.makeText(DeleteDeliveryEvent.this, "Please enter a Delivery Event ID", Toast.LENGTH_SHORT).show();
                } else {
                    String deliveryEventId = deliveryEventIdEditText.getText().toString();
                    deleteDeliveryEvent(deliveryEventId);
                }
            }
        });
    }

    private void deleteDeliveryEvent(String deliveryEventId) {

        String url = "http://192.168.0.141:8080/deliveryEvents/delete/" + deliveryEventId;


        RequestQueue queue = Volley.newRequestQueue(this);


        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response on Success
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Error.Response", String.valueOf(error));
                    }
                }
        );

        queue.add(deleteRequest);
    }
}

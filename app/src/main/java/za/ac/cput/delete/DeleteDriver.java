package za.ac.cput.delete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import za.ac.cput.R;
import za.ac.cput.view.DeleteDeliveryEvent;

public class DeleteDriver extends AppCompatActivity {

    private EditText driverIdEditText;
    private Button deleteButton;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_driver);

        driverIdEditText = findViewById(R.id.driverId);
        deleteButton = findViewById(R.id.btnDeleteDriver);
        responseTextView = findViewById(R.id.deleteResponse);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (driverIdEditText.getText().toString().isEmpty()) {
                    Toast.makeText(DeleteDriver.this, "Please enter a Delivery Event ID", Toast.LENGTH_SHORT).show();
                } else {
                    String driverId = driverIdEditText.getText().toString();
                    deleteDriverEvent(driverId);
                }
            }
        });
    }

    private void deleteDriverEvent(String driverEventId) {

        String url = "http://192.168.0.11:8080/driver/delete/" + driverEventId;

        RequestQueue queue = Volley.newRequestQueue(this);


        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response on Success
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener() {
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



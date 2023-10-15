package za.ac.cput.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import za.ac.cput.R;
import za.ac.cput.create.CreateDeliveryOrder;

public class DeliveryOrderCreate extends AppCompatActivity {
    EditText deliveryOrderId, deliveryAddress, deliveryDate;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_order);

        CreateDeliveryOrder deliveryOrder = new CreateDeliveryOrder(this, "http://192.168.18.178:8080/deliveryOrder/create");

        deliveryOrderId = findViewById(R.id.deliveryOrderId);
        deliveryAddress = findViewById(R.id.deliveryAddress);
        deliveryDate = findViewById(R.id.deliveryDate);
        create = findViewById(R.id.deliveryOrderSaveButton);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderId = deliveryOrderId.getText().toString();
                String address = deliveryAddress.getText().toString();
                String date = deliveryDate.getText().toString();

                deliveryOrder.createDeliveryOrder(orderId, address, date, new CreateDeliveryOrder.CreateDeliveryOrderListener() {
                    @Override
                    public void onSuccess() {
                        Log.d("Debug", "Order ID: " + orderId);
                        Log.d("Debug", "Address: " + address);
                        Log.d("Debug", "Date: " + date);
                        Toast.makeText(getApplicationContext(), "Delivery Order created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DeliveryOrderCreate.this, LandingPage.class));
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error creating Delivery Order: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Debug", "Order ID: " + orderId);
                        Log.d("Debug", "Address: " + address);
                        Log.d("Debug", "Date: " + date);
                    }
                });
            }
        });
    }
}

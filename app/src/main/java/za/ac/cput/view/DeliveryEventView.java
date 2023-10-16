package za.ac.cput.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.VolleyError;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import za.ac.cput.R;
import za.ac.cput.create.CreateDeliveryEvent;

public class DeliveryEventView  extends AppCompatActivity {
    EditText  deliveryEventId,  deliveryName,
            streetNumber,  streetName,  townOrCity,  areaCode;
    Button createDeliveryEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_event);
        CreateDeliveryEvent deliveryEvent = new CreateDeliveryEvent(this, "http://192.168.0.141:8080/deliveryEvents/create");
        UUID uuid = UUID.randomUUID();
        deliveryName = findViewById(R.id.deliveryName);
        streetNumber = findViewById(R.id.streetNumber);
        streetName = findViewById(R.id.streetName);
        townOrCity = findViewById(R.id.townOrCity);
        areaCode = findViewById(R.id.areaCode);
        @SuppressLint({"NewApi", "LocalSuppress"}) DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");


        createDeliveryEvent = findViewById(R.id.createDeliveryEvent);
        createDeliveryEvent.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                String id = uuid.toString();
                String name = deliveryName.getText().toString();
                LocalDateTime date = LocalDateTime.now();
                int strtNumber = Integer.parseInt(streetNumber.getText().toString());
                String strtName = streetName.getText().toString();
                String towmCity = townOrCity.getText().toString();
                int code = Integer.parseInt(areaCode.getText().toString());


                deliveryEvent.deliveryCreation(id, name, date, strtNumber, strtName, towmCity
                        , code, new CreateDeliveryEvent.CreateDeliveryEventListener() {


                            @Override
                            public void onSuccess() {
                                Log.d("Debug", "Id: " + id);
                                Log.d("Debug", "Name: " + name);
                                Log.d("Debug", "Date: " + date);
                                Log.d("Debug", "Street Number: " + strtNumber);
                                Log.d("Debug", "Street Name: " + strtName);
                                Log.d("Debug", "Town or City: " + towmCity);
                                Log.d("Debug", "Postal Code: " + code);

                                Toast.makeText(getApplicationContext(), "Object created successfully", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DeliveryEventView.this, LandingPage.class));
                            }

                            @Override
                            public void onError(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "Error creating object: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.d("Debug", "Id: " + id);
                                Log.d("Debug", "Name: " + name);
                                Log.d("Debug", "Date: " + date);
                                Log.d("Debug", "Street Number: " + strtNumber);
                                Log.d("Debug", "Street Name: " + strtName);
                                Log.d("Debug", "Town or City: " + towmCity);
                                Log.d("Debug", "Postal Code: " + code);
                            }
                        });

            }
        });

    }}


package za.ac.cput.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.UUID;

import za.ac.cput.R;
import za.ac.cput.create.CreateDriver;

public class DriverRegistration extends AppCompatActivity {

    EditText firstName, lastName, contact, email, driverPosition;

    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_registration);

        CreateDriver driver = new CreateDriver(this, "http://192.168.0.11:8080/driver/create");
        UUID uuid = UUID.randomUUID();
        firstName = findViewById(R.id.driverFirstNameTxt);
        lastName = findViewById(R.id.driverLastNameTxt);
        contact = findViewById(R.id.driverContactNumberTxt);
        email = findViewById(R.id.driverEmailTxt);
        driverPosition = findViewById(R.id.driverPositionTxt);
        register = findViewById(R.id.registerBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = uuid.toString();
                String firstName1 = firstName.getText().toString();
                String lastName1 =  lastName.getText().toString();
                String contact1 = contact.getText().toString();
                String email1 = email.getText().toString();
                String driverPosition1 = driverPosition.getText().toString();

                driver.driverCreation(id, firstName1, lastName1, contact1, email1, driverPosition1, new CreateDriver.CreateDriverListener() {

                    @Override
                    public void onSuccess() {
                        Log.d("Debug","Id: " + id);
                        Log.d("Debug", "First Name: " + firstName1);
                        Log.d("Debug", "Last Name: " + lastName1);
                        Log.d("Debug", "Contact: " + contact1);
                        Log.d("Debug", "Email: " + email1);
                        Log.d("Debug", "Driver Position: " + driverPosition1);
                        Toast.makeText(getApplicationContext(), "Object created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(DriverRegistration.this,LandingPage.class));
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error creating object: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Debug","Id: " + id);
                        Log.d("Debug", "First Name: " + firstName1);
                        Log.d("Debug", "Last Name: " + lastName1);
                        Log.d("Debug", "Contact: " + contact1);
                        Log.d("Debug", "Email: " + email1);
                        Log.d("Debug", "Driver Position: " + driverPosition1);
                    }

                });

            }
        });

    }
}
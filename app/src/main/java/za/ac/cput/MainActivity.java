package za.ac.cput;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import za.ac.cput.R;
import za.ac.cput.create.CreateDeliveryEvent;
import za.ac.cput.delete.DeleteDriver;
import za.ac.cput.read.ReadAllDriversActivity;
import za.ac.cput.view.DriverRegistration;
import za.ac.cput.view.LocationActivity;
import za.ac.cput.view.ReadAllDeliveryEvents;
import za.ac.cput.view.UpdateDriverActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openLocationButton = findViewById(R.id.openLocationButton);
        openLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open the LocationActivity when the button is clicked
                Intent intent = new Intent(MainActivity.this, DeleteDriver.class);
                startActivity(intent);
            }
        });
    }
}

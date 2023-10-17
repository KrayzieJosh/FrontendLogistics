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

import java.util.UUID;

import za.ac.cput.R;
import za.ac.cput.create.CreateMaterialQuote;

public class MaterialQuoteCreate extends AppCompatActivity {
    EditText materialName, materialPrice, materialQuantity, materialWeight;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materialquote);

        CreateMaterialQuote materialQuote = new CreateMaterialQuote(this, "http://192.168.18.178:8080/materialQuote/create");
        UUID uuid = UUID.randomUUID();

        materialName = findViewById(R.id.form_textFieldName);
        materialPrice = findViewById(R.id.form_textFieldPrice);
        materialQuantity = findViewById(R.id.form_textFieldQuantity);
        materialWeight = findViewById(R.id.form_textFieldWeight);
        create = findViewById(R.id.form_buttonSave);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = uuid.toString();
                String name = materialName.getText().toString();
                double price = Double.parseDouble(materialPrice.getText().toString());
                String quantity = materialQuantity.getText().toString();
                double weight = Double.parseDouble(materialWeight.getText().toString());

                materialQuote.createMaterialQuote(id,name, price, quantity, weight, new CreateMaterialQuote.CreateMaterialQuoteListener() {

                    @Override
                    public void onSuccess() {
                        Log.d("Debug", "Id: " + id);
                        Log.d("Debug", "Name: " + name);
                        Log.d("Debug", "Price: " + price);
                        Log.d("Debug", "Quantity: " + quantity);
                        Log.d("Debug", "Weight: " + weight);
                        Toast.makeText(getApplicationContext(), "Material quote created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MaterialQuoteCreate.this, LandingPage.class));
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error creating material quote: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Debug", "Name: " + name);
                        Log.d("Debug", "Price: " + price);
                        Log.d("Debug", "Quantity: " + quantity);
                        Log.d("Debug", "Weight: " + weight);
                    }
                });
            }
        });
    }
}

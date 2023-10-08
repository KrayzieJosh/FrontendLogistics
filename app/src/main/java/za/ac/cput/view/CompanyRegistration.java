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

import za.ac.cput.R;
import za.ac.cput.create.CreateCompany;

public class CompanyRegistration extends AppCompatActivity {
    EditText companyId,companyName, companyPhysicalAddress, companyEmail;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_registration);
        CreateCompany company = new CreateCompany(this, "http://192.168.18.8:8080/company/create");
        companyId= findViewById(R.id.company_id_txt);
        companyName = findViewById(R.id.company_name_txt);
        companyPhysicalAddress = findViewById(R.id.company_physical_address_txt);
        companyEmail = findViewById(R.id.company_email_txt);
        create = findViewById(R.id.add_companybtn);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id=companyId.getText().toString();
                String name = companyName.getText().toString();
                String address = companyPhysicalAddress.getText().toString();
                String email = companyEmail.getText().toString();

                company.companyCreation(id,name, address, email, new CreateCompany.CreateCompanyListener() {


                    @Override
                    public void onSuccess() {
                        Log.d("Debug","Id" + id);
                        Log.d("Debug", "Name: " + name);
                        Log.d("Debug", "Address: " + address);
                        Log.d("Debug", "Email: " + email);
                        Toast.makeText(getApplicationContext(), "Object created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CompanyRegistration.this,LandingPage.class));
                    }

                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error creating object: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Debug", "Name: " + name);
                        Log.d("Debug", "Address: " + address);
                        Log.d("Debug", "Email: " + email);
                    }
                });

            }
        });

    }
}
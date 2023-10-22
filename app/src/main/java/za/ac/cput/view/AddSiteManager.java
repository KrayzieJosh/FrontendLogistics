package za.ac.cput.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import za.ac.cput.R;
import za.ac.cput.create.CreateProjectManager;
import za.ac.cput.create.CreateSiteManager;

public class AddSiteManager extends AppCompatActivity {
    EditText siteManagerID,firstName,middleName,lastName,contact,email,position;
    Button addManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_site_manager);
        CreateSiteManager siteManager = new CreateSiteManager(this,"http://192.168.18.8:8080/siteManager/create");
        siteManagerID = findViewById(R.id.site_manager_id_txt);
        position = findViewById(R.id.site_manager_position_txt);
        firstName=findViewById(R.id.site_manager_name_txt);
        middleName = findViewById(R.id.site_manager_middle_name_txt);
        lastName = findViewById(R.id.site_manager_last_name_txt);
        contact = findViewById(R.id.site_manager_contact_txt);
        email = findViewById(R.id.site_manager_email_txt);
        addManager = findViewById(R.id.add_site_manager_btn);
        addManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFormValid();
                if (isFormValid()) {
                String id = siteManagerID.getText().toString();
                String siteManagerPosition=position.getText().toString();
                String fName=firstName.getText().toString();
                String mName=middleName.getText().toString();
                String lName = lastName.getText().toString();
                String cont = contact.getText().toString();
                String em=email.getText().toString();
                siteManager.siteManagerCreation(id,siteManagerPosition,fName,mName,lName,cont,em,new CreateSiteManager.CreateSiteManagerListener(){


                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Object created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddSiteManager.this,Projects.class));
                        siteManagerID.setText("");
                        position.setText("");
                        firstName.setText("");
                        middleName.setText("");
                        lastName.setText("");
                        contact.setText("");
                        email.setText("");

                    }

                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error creating object: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        siteManagerID.setText("");
                        position.setText("");
                        firstName.setText("");
                        middleName.setText("");
                        lastName.setText("");
                        contact.setText("");
                        email.setText("");
                    }
                }); } else {
                    // Form is not valid, show error messages or take appropriate action
                }
            }
        });


    }
    public boolean isFormValid() {
        // Check EditText fields for emptiness
        if (TextUtils.isEmpty(siteManagerID.getText().toString())) {
            siteManagerID.setError("Field cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(position.getText().toString())) {
            position.setError("Field cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(firstName.getText().toString())) {
            firstName.setError("Field cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(middleName.getText().toString())) {
            middleName.setError("Field cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(lastName.getText().toString())) {
            lastName.setError("Field cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(contact.getText().toString())) {
            contact.setError("Field cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError("Field cannot be empty");
            return false;
        }
        return true;

    }
}
package za.ac.cput.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import za.ac.cput.view2.ViewProjectManager;

public class AddProjectManager extends AppCompatActivity {
EditText projectManagerID,firstName,middleName,lastName,contact,email,projectManagerPosition;
Button addManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project_manager);
        CreateProjectManager projectManager = new CreateProjectManager(this,"http://192.168.18.8:8080/projectManager/create");
        projectManagerID = findViewById(R.id.project_manager_id_txt);
        projectManagerPosition=findViewById(R.id.project_manager_position_txt);
        firstName = findViewById(R.id.project_manager_name_txt);
        middleName=findViewById(R.id.project_manager_middle_name_txt);
        lastName=findViewById(R.id.project_manager_last_name_txt);
        contact=findViewById(R.id.project_manager_contact_txt);
        email=findViewById(R.id.project_manager_email_txt);
        addManager=findViewById(R.id.add_project_manager_btn);

        addManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFormValid();
                if (isFormValid()) {
                String id = projectManagerID.getText().toString();
                String position= projectManagerPosition.getText().toString();
                String fName=firstName.getText().toString();
                String mName=middleName.getText().toString();
                String lName = lastName.getText().toString();
                String cont = contact.getText().toString();
                String em=email.getText().toString();
                projectManager.projectManagerCreation(id,position,fName,mName,lName,cont,em,new CreateProjectManager.CreateProjectManagerListener(){


                    @Override
                    public void onSuccess() {
                        Toast.makeText(getApplicationContext(), "Object created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddProjectManager.this, ViewProjectManager.class));
                        projectManagerID.setText("");
                        projectManagerPosition.setText("");
                        firstName.setText("");
                        middleName.setText("");
                        lastName.setText("");
                        contact.setText("");
                        email.setText("");

                    }

                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error creating object: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        projectManagerID.setText("");
                        projectManagerPosition.setText("");
                        firstName.setText("");
                        middleName.setText("");
                        lastName.setText("");
                        contact.setText("");
                        email.setText("");
                    }
                });
                } else {
                    // Form is not valid, show error messages or take appropriate action
                }
            }
        });

}  public boolean isFormValid() {
        // Check EditText fields for emptiness
        if (TextUtils.isEmpty(projectManagerID.getText().toString())) {
            projectManagerID.setError("Field cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(projectManagerPosition.getText().toString())) {
            projectManagerPosition.setError("Field cannot be empty");
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

    }}
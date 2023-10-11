package za.ac.cput.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import za.ac.cput.R;
import za.ac.cput.create.CreateCompany;
import za.ac.cput.create.CreateProject;
import za.ac.cput.model.Project;
import za.ac.cput.model.DeliveryOrder;
import za.ac.cput.model.ProjectManager;
import za.ac.cput.model.SiteManager;

import com.android.volley.VolleyError;

import java.sql.Driver;
import java.util.UUID;


/*public class Projects extends AppCompatActivity {

    EditText projectId,projectName, projectStatus, driver, projectManager, siteManager, company,deliveryOrder;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        CreateProject project = new CreateProject(this, "http://192.168.0.199:8080/project/create");
        UUID uuid = UUID.randomUUID();
        projectName = findViewById(R.id.project_name_txt);
        projectStatus = findViewById(R.id.project_status_txt);
        driver = findViewById(R.id.driver_txt);
        projectManager = findViewById(R.id.project_managers_txt);
        siteManager = findViewById(R.id.site_managers_txt);
        company = findViewById(R.id.company_txt);
        deliveryOrder = findViewById(R.id.delivery_order_txt);
        create = findViewById(R.id.add_projectbtn);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = uuid.toString();
                String name = projectName.getText().toString();
                String status = projectStatus.getText().toString();
                String theDriver = driver.getText().toString();
                String projMan = projectManager.getText().toString();
                String siteMan = siteManager.getText().toString();
                String comp = company.getText().toString();
                String deliveryOrd = deliveryOrder.getText().toString();

                project.projectCreation(id,name, status, theDriver, projectManager, siteManager, company,deliveryOrder, new CreateProject.CreateProjectListener() {

                    @Override
                    public void onSuccess() {
                        Log.d("Debug","Id" + id);
                        Log.d("Debug", "Name: " + name);
                        Log.d("Debug", "status: " + status);
                        Log.d("Debug", "theDriver: " + driver);
                        Log.d("Debug", "projMan: " + projMan);
                        Log.d("Debug", "siteMan: " + siteMan);
                        Log.d("Debug", "comp: " + comp);
                        Log.d("Debug", "deliveryOrd: " + deliveryOrd);
                        Toast.makeText(getApplicationContext(), "Object created successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Projects.this,ProjectLandingPage.class));
                    }
                    @Override
                    public void onError(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error creating object: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("Debug", "Name: " + name);

                    }
                });

            }
        });

    }
}*/

package za.ac.cput.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import za.ac.cput.R;
import za.ac.cput.create.CreateProject;
import za.ac.cput.create.CreateProjectManager;
import za.ac.cput.model.ProjectManager;
import za.ac.cput.model.SiteManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;


public class Projects extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner projectManagerSpinner;
    private Spinner siteManagerSpinner;
    private ArrayAdapter<String> adapter;
    private String selectedProjectManagerId;

    EditText projectId;
    EditText projectName;
    EditText projectStatus;
    EditText driver;
    EditText projectManager;
    EditText siteManager;
    EditText company;
    EditText deliveryOrder;
    Button create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        setUpProjectManagerSpinner();
        setUpSiteManagerSpinner();
        CreateProject project = new CreateProject(this, "http://192.168.18.8:8080/project/create");
        UUID uuid = UUID.randomUUID();
        projectName = findViewById(R.id.project_name_txt);
        projectStatus = findViewById(R.id.project_status_txt);
        //driver = findViewById(R.id.driver_txt);
        projectManagerSpinner=findViewById(R.id.project_manager_spinner_sp);
        siteManagerSpinner = findViewById(R.id.siteManagerSpinner);
        //company = findViewById(R.id.company_txt);
        //deliveryOrder = findViewById(R.id.delivery_order_txt);
        create = findViewById(R.id.add_projectbtn);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFormValid();
                if (isFormValid()) {
                    String id = uuid.toString();
                    String name = projectName.getText().toString();
                    String status = projectStatus.getText().toString();
                    //String theDriver = driver.getText().toString();

                    String selectedValue = projectManagerSpinner.getSelectedItem()!= null
                            ? projectManagerSpinner.getSelectedItem().toString():"Nothing here";


                    String siteMan =  siteManagerSpinner.getSelectedItem()!= null
                            ? siteManagerSpinner.getSelectedItem().toString():"Nothing here";
                    //String comp = company.getText().toString();
                    //String deliveryOrd = deliveryOrder.getText().toString();

                    project.projectCreation(id, name, status, selectedValue, siteMan,/* company,deliveryOrder,*/ new CreateProject.CreateProjectListener() {

                        @Override
                        public void onSuccess() {
                            Log.d("Debug", "Id" + id);
                            Log.d("Debug", "Name: " + name);
                            Log.d("Debug", "status: " + status);
                            //Log.d("Debug", "theDriver: " + driver);
                            Log.d("Debug", "projMan: " + selectedValue);
                            Log.d("Debug", "siteMan: " + siteMan);
                            //Log.d("Debug", "comp: " + comp);
                            //Log.d("Debug", "deliveryOrd: " + deliveryOrd);
                            Toast.makeText(getApplicationContext(), "Object created successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Projects.this, LandingPage.class));
                        }

                        @Override
                        public void onError(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error creating object: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("Debug", "Name: " + name);
                            Log.d("Debug", "status: " + status);
                            //Log.d("Debug", "theDriver: " + driver);
                            Log.d("Debug", "projMan: " + selectedValue);
                            Log.d("Debug", "siteMan: " + siteMan);

                        }
                    });
                } else {
                    // Form is not valid, show error messages or take appropriate action
                }


            }
        });

    }

    public void setUpProjectManagerSpinner() {
        ArrayList<String> projectManagers = new ArrayList<>();
        projectManagers.add("Select a Project Manager");
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.18.8:8080/projectManager/getall";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String projectManager;
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject projectManagerObject = response.getJSONObject(i);
                        projectManager = projectManagerObject.optString("projectManagerId");
                        projectManagers.add(projectManager);
                    }

                    Toast.makeText(Projects.this, "Data fetched successfully", Toast.LENGTH_SHORT).show();

                    Spinner projectManagerSpinner = findViewById(R.id.project_manager_spinner_sp);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Projects.this, android.R.layout.simple_spinner_item, projectManagers);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    projectManagerSpinner.setAdapter(adapter);
                    projectManagerSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) Projects.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JSON Parsing Error", "Error parsing JSON: " + e.getMessage());

                    Toast.makeText(Projects.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Projects.this, "Error fetching data: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedValue = adapterView.getItemAtPosition(i).toString();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void setUpSiteManagerSpinner() {
        ArrayList<String> siteManagers = new ArrayList<>();
        siteManagers.add("Select a Site Manager");
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.18.8:8080/siteManager/getall";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String siteManager;
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject siteManagerObject = response.getJSONObject(i);
                        siteManager =siteManagerObject.optString("siteManagerId");
                        siteManagers.add(siteManager);
                    }

                    Toast.makeText(Projects.this, "Data fetched successfully", Toast.LENGTH_SHORT).show();

                    Spinner siteManagerSpinner = findViewById(R.id.siteManagerSpinner);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(Projects.this, android.R.layout.simple_spinner_item, siteManagers);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    siteManagerSpinner.setAdapter(adapter);
                    siteManagerSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) Projects.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("JSON Parsing Error", "Error parsing JSON: " + e.getMessage());

                    Toast.makeText(Projects.this, "Error parsing JSON", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Projects.this, "Error fetching data: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(request);
    }
    public boolean isFormValid() {
        // Check EditText fields for emptiness
        if (TextUtils.isEmpty(projectName.getText().toString())) {
            projectName.setError("Field cannot be empty");
            return false;
        }
        if (TextUtils.isEmpty(projectStatus.getText().toString())) {
            projectStatus.setError("Field cannot be empty");
            return false;
        }
        // Add more EditText fields as needed

        // Check if the Spinner has a valid selection
        int selectedProjectManager = projectManagerSpinner.getSelectedItemPosition();
        if (selectedProjectManager == 0) { // Assuming 0 is the position of the hint/title item
            Toast.makeText(this, "Select an actual project manager", Toast.LENGTH_SHORT).show();
            return false;
        }
        int selectedSiteManager= siteManagerSpinner.getSelectedItemPosition();
        if(selectedSiteManager==0){
            Toast.makeText(this, "Select an actual site manager", Toast.LENGTH_SHORT).show();
            return false;


        }
        return true;

    }




}


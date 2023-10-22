package za.ac.cput.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import za.ac.cput.R;
import za.ac.cput.create.CreateProject;
import za.ac.cput.create.CreateProjectManager;
import za.ac.cput.model.Company;
import za.ac.cput.model.DeliveryOrder;
import za.ac.cput.model.Driver;
import za.ac.cput.model.Project;
import za.ac.cput.model.ProjectManager;
import za.ac.cput.model.SiteManager;
import za.ac.cput.view2.ViewAndUpdateProject;

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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;


public class Projects extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner projectManagerSpinner;
    private Spinner siteManagerSpinner;
    private ArrayAdapter<String> adapter;
    private Spinner driver;
   private Spinner deliveryOrderSpinner;


    EditText projectName;
    EditText projectStatus;

    Button create, addDriver,addProjectManager,addSiteManager,addNewDeliveryOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        setUpProjectManagerSpinner();
        setUpSiteManagerSpinner();
        setUpDriverSpinner();
        setUpDeliveryOrderSpinner();

        CreateProject project = new CreateProject(this, "http://192.168.18.8:8080/project/create");

        UUID uuid = UUID.randomUUID();
        projectName = findViewById(R.id.project_name_txt);
        projectStatus = findViewById(R.id.project_status_txt);
        //driver = findViewById(R.id.driver_spinner);
        projectManagerSpinner=findViewById(R.id.project_manager_spinner_sp);
        siteManagerSpinner = findViewById(R.id.siteManagerSpinner);
        //deliveryOrderSpinner=findViewById(R.id.deliverOrderSpinner);


        //addNewDeliveryOrder = findViewById(R.id.add_orderbtn);
        create = findViewById(R.id.add_projectbtn);
        //addDriver=findViewById(R.id.add_driverbtn);
        //addProjectManager=findViewById(R.id.add_prmbtn);
       // addSiteManager = findViewById(R.id.add_stmbtn);

        addNewDeliveryOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Projects.this, DeliveryOrderCreate.class));
            }
        });
        addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Projects.this, DriverRegistration.class));
            }
        });
        addProjectManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Projects.this, AddProjectManager.class));

            }
        });
        addSiteManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Projects.this, AddSiteManager.class));

            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFormValid();
                if (isFormValid()) {
                    String id = uuid.toString();


                    String name = projectName.getText().toString();
                    String status = projectStatus.getText().toString();

                    ProjectManager selectedProjectManager = (ProjectManager) projectManagerSpinner.getSelectedItem();
                    SiteManager siteMan = (SiteManager) siteManagerSpinner.getSelectedItem();
                    Driver theDriver =(Driver) driver.getSelectedItem();


                    DeliveryOrder order = (DeliveryOrder)deliveryOrderSpinner.getSelectedItem();
                    Project createNewProject= new Project();
                    createNewProject.setProjectId(id);
                    createNewProject.setProjectName(name);
                    createNewProject.setProjectStatus(status);
                    createNewProject.setProjectManager(selectedProjectManager);
                    createNewProject.setSiteManager(siteMan);
                    createNewProject.setDriver(theDriver);
                    createNewProject.setDeliveryOrder(order);




                    project.projectCreation(createNewProject , new CreateProject.CreateProjectListener() {

                        @Override
                        public void onSuccess() {
                            Log.d("Debug", "Id" + id);
                            Log.d("Debug", "Name: " + name);
                            Log.d("Debug", "status: " + status);
                            Log.d("Debug", "theDriver: " + theDriver);
                            Log.d("Debug", "projMan: " + selectedProjectManager);
                            Log.d("Debug", "siteMan: " + siteMan);
                            Log.d("Debug", "deliveryOrd: " + order);


                            Toast.makeText(getApplicationContext(), "Object created successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error creating object: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d("Debug", "Name: " + name);
                            Log.d("Debug", "status: " + status);
                            Log.d("Debug", "theDriver: " + theDriver);
                            Log.d("Debug", "projMan: " + selectedProjectManager);
                            Log.d("Debug", "siteMan: " + siteMan);
                            Log.d("Debug", "deliveryOrd: " + order);
                        }
                    });
                } else {
                    // Form is not valid, show error messages or take appropriate action
                }


            }

        });

    }

    public void setUpProjectManagerSpinner() {
        ArrayList<ProjectManager> projectManagers = new ArrayList<>();
        String dummyId = "Select a Project Manager";
        projectManagers.add(new ProjectManager(dummyId)); // You can create a placeholder ProjectManager

        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.18.8:8080/projectManager/getall";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject projectManagerObject = response.getJSONObject(i);
                        String projectManagerId = projectManagerObject.optString("projectManagerId");
                        String position = projectManagerObject.optString("position");
                        String firstName = projectManagerObject.optString("firstName");
                        String middleName = projectManagerObject.optString("middleName");
                        String lastName = projectManagerObject.optString("lastName");
                        String contact = projectManagerObject.optString("contact");
                        String email = projectManagerObject.optString("email");

                        ProjectManager projectManager = new ProjectManager(projectManagerId,position,firstName
                        ,middleName,lastName,contact,email);
                        projectManagers.add(projectManager);
                    }

                    Toast.makeText(Projects.this, "Data fetched successfully", Toast.LENGTH_SHORT).show();

                    Spinner projectManagerSpinner = findViewById(R.id.project_manager_spinner_sp);
                    ArrayAdapter<ProjectManager> adapter = new ArrayAdapter<>(Projects.this, android.R.layout.simple_spinner_item, projectManagers);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    projectManagerSpinner.setAdapter(adapter);
                    projectManagerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            // Get the selected ProjectManager object
                            ProjectManager selectedProjectManager = (ProjectManager) adapterView.getItemAtPosition(i);

                            // You can access the properties of the selected ProjectManager
                            String selectedProjectManagerId = selectedProjectManager.getProjectManagerId();

                            // Handle the selected ProjectManager as needed
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {
                            // Handle nothing selected
                        }
                    });
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

    }




    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void setUpSiteManagerSpinner() {
        ArrayList<SiteManager> siteManagers = new ArrayList<>();
        String secondDummyId = "Select a Site Manager";
        siteManagers.add(new SiteManager(secondDummyId));
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.18.8:8080/siteManager/getall";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject siteManagerObject = response.getJSONObject(i);
                        String siteManagerId = siteManagerObject.optString("siteManagerId");
                        String position = siteManagerObject.optString("position");
                        String firstName = siteManagerObject.optString("firstName");
                        String middleName = siteManagerObject.optString("middleName");
                        String lastName = siteManagerObject.optString("lastName");
                        String contact = siteManagerObject.optString("contact");
                        String email = siteManagerObject.optString("email");

                        SiteManager siteManager = new SiteManager(siteManagerId,position,firstName
                                ,middleName,lastName,contact,email);
                        siteManagers.add(siteManager);
                    }

                    Spinner siteManagerSpinner = findViewById(R.id.siteManagerSpinner);
                    ArrayAdapter<SiteManager> adapter = new ArrayAdapter<>(Projects.this, android.R.layout.simple_spinner_item, siteManagers);
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
    public void setUpDriverSpinner() {
        ArrayList<Driver> drivers = new ArrayList<>();
        String thirdDummyId = "Select a Driver";
        drivers.add(new Driver(thirdDummyId));
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.18.8:8080/driver/getall";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject driverObject = response.getJSONObject(i);
                        String driverId = driverObject.optString("driverId");
                        String firstName = driverObject.optString("firstName");
                        String lastName = driverObject.optString("lastName");
                        String contact = driverObject.optString("contact");
                        String email = driverObject.optString("email");
                        String position = driverObject.optString("driverPosition");
                        Driver driver = new Driver(driverId,position,firstName
                                ,lastName,contact,email);
                        drivers.add(driver);
                    }

                    Toast.makeText(Projects.this, "Data fetched successfully", Toast.LENGTH_SHORT).show();

                    //Spinner driverSpinner = findViewById(R.id.driver_spinner);
                    ArrayAdapter<Driver> adapter = new ArrayAdapter<>(Projects.this, android.R.layout.simple_spinner_item, drivers);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //driverSpinner.setAdapter(adapter);
                    //driverSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) Projects.this);
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
    public void setUpDeliveryOrderSpinner() {
        ArrayList<DeliveryOrder> deliveryOrders = new ArrayList<>();
        String fourthDummyId = "Add delivery order";
       // deliveryOrders.add(new DeliveryOrder(fourthDummyId));
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://192.168.18.8:8080/deliveryOrder/getall";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject deliveryOrderObject = response.getJSONObject(i);
                        String deliveryOrderId = deliveryOrderObject.optString("deliveryOrderId");
                        String address = deliveryOrderObject.optString("deliveryAddress");
                        String date = deliveryOrderObject.optString("deliveryDate");

                        DeliveryOrder deliveryOrder = new DeliveryOrder(deliveryOrderId,address,date);
                        deliveryOrders.add(deliveryOrder);
                    }

                    Toast.makeText(Projects.this, "Data fetched successfully", Toast.LENGTH_SHORT).show();

                    //Spinner deliveryOrderSpinner = findViewById(R.id.deliverOrderSpinner);

                    ArrayAdapter<DeliveryOrder> adapter = new ArrayAdapter<>(Projects.this, android.R.layout.simple_spinner_item, deliveryOrders);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    deliveryOrderSpinner.setAdapter(adapter);
                    deliveryOrderSpinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) Projects.this);
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


package za.ac.cput.view2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.UUID;

import za.ac.cput.R;
import za.ac.cput.create.CreateProject;
import za.ac.cput.model.DeliveryOrder;
import za.ac.cput.model.Driver;
import za.ac.cput.model.Project;
import za.ac.cput.model.ProjectManager;
import za.ac.cput.model.SiteManager;
import za.ac.cput.update.UpdateProject;
import za.ac.cput.view.LandingPage;

public class ViewAndUpdateProject extends AppCompatActivity {
TextView id,name,status,pmanager,smanager,theDriver;
EditText nameText,statusText,pManagerText,smanagerText,theDriverText,orderText;
ImageView image1,image2,image3,image4,image5,image6,image11,image12,image13,image14,image15,image16;
Button update;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_and_update_project);
        UUID uuid = UUID.randomUUID();

UpdateProject updateProject =new UpdateProject(this,"http://192.168.18.8:8080/project/update");
        image1= findViewById(R.id.editbtn1);
        image2 = findViewById(R.id.imageView2);
        image3 = findViewById(R.id.imageView6);
        image4 = findViewById(R.id.imageView3);
        image5 = findViewById(R.id.imageView4);
        image6 = findViewById(R.id.imageView5);
        nameText = findViewById(R.id.editText1);
        statusText=findViewById(R.id.editText2);        
        
        image11=findViewById(R.id.editbtn2);
        image12=findViewById(R.id.editbtn3);
        image13=findViewById(R.id.editbtn4);
        image14=findViewById(R.id.editbtn5);
        image15=findViewById(R.id.editbtn6);
        image16=findViewById(R.id.editbtn7);
        update=findViewById(R.id.update_projectbtn);
        






        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id.setVisibility(View.GONE);
                nameText.setVisibility(View.VISIBLE);
                image1.setVisibility(View.GONE);
                image11.setVisibility(View.VISIBLE);

            }
        });
       image2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                name.setVisibility(View.GONE);
                statusText.setVisibility(View.VISIBLE);
                image2.setVisibility(View.GONE);
                image12.setVisibility(View.VISIBLE);

           }
            });
//        image3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                name.setVisibility(View.GONE);
//                statusText.setVisibility(View.VISIBLE);
//                image3.setVisibility(View.GONE);
//                image13.setVisibility(View.VISIBLE);
//            }
//        });
        image11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newText = nameText.getText().toString();
                id.setText(newText);
                nameText.setVisibility(View.GONE);
                id.setVisibility(View.VISIBLE);
                image1.setVisibility(View.VISIBLE);
                image11.setVisibility(View.GONE);

            }
        });
       image12.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                String newStatus = statusText.getText().toString();
                name.setText(newStatus);
                statusText.setVisibility(View.GONE);
                name.setVisibility(View.VISIBLE);
                image2.setVisibility(View.VISIBLE);
                image12.setVisibility(View.GONE);
            }
        });
       


    update.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String prId=uuid.toString();
            String newName = nameText.getText().toString();
            String newStatus = statusText.getText().toString();
            Project updatedProject = new Project(prId,newStatus);

            updatedProject.setProjectName(newName);
            updatedProject.setProjectStatus(newStatus);
            Project project = new Project().copy(updatedProject).setProjectId(newName);



            updateProject.updateProject(project, new UpdateProject.UpdateProjectStatusListener() {
                @Override
                public void onSuccess() {
                    Toast.makeText(getApplicationContext(), "Object updated successfully", Toast.LENGTH_SHORT).show();
                    Log.d("Debug", "Name: " + newName);
                    Log.d("Debug", "status: " + newStatus);
                }

                @Override
                public void onError(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Error creating object: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("Debug", "Name: " + newName);
                    Log.d("Debug", "status: " + newStatus);
                }
            });

        }
        });
        Intent intent = getIntent();

        String projectName = intent.getStringExtra("projectName");
        String projectStatus = intent.getStringExtra("projectStatus");
        String projectManager = intent.getStringExtra("projectManagerId");
        String siteManager = intent.getStringExtra("siteManagerId");
        String driver = intent.getStringExtra("driverId");
        String deliveryOrder = intent.getStringExtra("deliveryOrderId");


        id = findViewById(R.id.textView1);
        id.setText(projectName);
        name = findViewById(R.id.textView2);
        name.setText(projectStatus);
        status=findViewById(R.id.textView3);
        status.setText(projectManager);
        pmanager = findViewById(R.id.textView4);
        pmanager.setText(siteManager);
        smanager =findViewById(R.id.textView5);
        smanager.setText(driver);
        theDriver = findViewById(R.id.textView6);
        theDriver.setText(deliveryOrder);
        nameText.setText(projectName);
    }


}


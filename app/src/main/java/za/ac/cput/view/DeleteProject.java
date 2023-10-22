package za.ac.cput.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import za.ac.cput.R;

public class DeleteProject extends AppCompatActivity {

    private EditText projectIdEditText;
    private Button deleteButton;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_project);

        projectIdEditText = findViewById(R.id.projectId);
        deleteButton = findViewById(R.id.btnDeleteProject);
        responseTextView = findViewById(R.id.deleteYesOrNo);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (projectIdEditText.getText().toString().isEmpty()) {
                    Toast.makeText(DeleteProject.this, "Please enter a Project ID", Toast.LENGTH_SHORT).show();
                } else {
                    String projectId = projectIdEditText.getText().toString();
                    deleteProject(projectId);
                }
            }
        });
    }

    private void deleteProject(String projectId) {
        String url = "http://192.168.0.199:8080/project/delete/" + projectId;

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest deleteRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Handle the response on success
                        responseTextView.setText("Successfully deleted: " + response.substring(0));                        // You can update the UI or perform additional actions here
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        Log.d("Error.Response", String.valueOf(error));
                        // You can display an error message or perform other error-handling actions here
                    }
                }
        );

        queue.add(deleteRequest);
    }
}

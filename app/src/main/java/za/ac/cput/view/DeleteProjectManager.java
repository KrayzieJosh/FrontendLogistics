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


public class DeleteProjectManager extends AppCompatActivity {

    private EditText projectManagerIdEditText;
    private Button deleteButton;
    private TextView responseTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_project_manager);

        projectManagerIdEditText = findViewById(R.id.projectManagerId);
        deleteButton = findViewById(R.id.btnDeleteProjectManager);
        responseTextView = findViewById(R.id.deleteYesOrNo);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (projectManagerIdEditText.getText().toString().isEmpty()) {
                    Toast.makeText(DeleteProjectManager.this, "Please enter a Project Manager ID", Toast.LENGTH_SHORT).show();
                } else {
                    String projectManagerId = projectManagerIdEditText.getText().toString();
                    deleteProjectManager(projectManagerId);
                }
            }
        });
    }

    private void deleteProjectManager(String projectManagerId) {
        String url = "http://192.168.0.199:8080/projectManager/delete/" + projectManagerId;

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

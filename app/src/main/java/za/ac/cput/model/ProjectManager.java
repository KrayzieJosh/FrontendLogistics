package za.ac.cput.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ProjectManager {
private String projectManagerId;
private String position;
    private String firstName;
    private String middleName;
    private String lastName;
    private String contact;
    private String email;


    public ProjectManager(String projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    public ProjectManager(String projectManagerId, String position, String firstName, String middleName, String lastName, String contact, String email) {
        this.projectManagerId = projectManagerId;
        this.position = position;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contact = contact;
        this.email = email;
    }

    public ProjectManager(JSONObject projectManagerObject) throws JSONException {
        this.projectManagerId = projectManagerObject.optString("projectManagerId");
        this.position = projectManagerObject.optString("position");
        this.firstName = projectManagerObject.optString("firstName");
        this.middleName = projectManagerObject.optString("middleName");
        this.lastName = projectManagerObject.optString("lastName");
        this.contact = projectManagerObject.optString("contact");
        this.email = projectManagerObject.optString("email");
    }




    public String getProjectManagerId() {
        return projectManagerId;
    }

    public ProjectManager setProjectManagerId(String projectManagerId) {
        this.projectManagerId = projectManagerId;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return
                projectManagerId;
    }
}

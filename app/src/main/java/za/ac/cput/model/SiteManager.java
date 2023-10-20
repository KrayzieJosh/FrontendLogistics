package za.ac.cput.model;

import org.json.JSONException;
import org.json.JSONObject;

public class SiteManager {
    private String siteManagerId;
    private String position;
    private String firstName;
    private String middleName;
    private String lastName;
    private String contact;
    private String email;

    public SiteManager(String siteManagerId) {
        this.siteManagerId = siteManagerId;

    }

    public SiteManager(String siteManagerId, String position, String firstName, String middleName, String lastName, String contact, String email) {
        this.siteManagerId = siteManagerId;
        this.position = position;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.contact = contact;
        this.email = email;
    }

    public String getSiteManagerId() {
        return siteManagerId;
    }

    public SiteManager setSiteManagerId(String siteManagerId) {
        this.siteManagerId = siteManagerId;
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
        return siteManagerId;

    }
}

package za.ac.cput.model;

public class Company {
    private String companyId;
    private String companyName;
    private String companyPhysicalAddress;

    private String companyEmail;

    public Company() {
    }

    public Company(String companyId, String companyName, String companyPhysicalAddress, String companyEmail) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.companyPhysicalAddress = companyPhysicalAddress;
        this.companyEmail = companyEmail;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPhysicalAddress() {
        return companyPhysicalAddress;
    }

    public void setCompanyPhysicalAddress(String companyAddress) {
        this.companyPhysicalAddress = companyPhysicalAddress;
    }




    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyPhysicalAddress + '\'' +

                ", companyEmail='" + companyEmail + '\'' +
                '}';
    }
}

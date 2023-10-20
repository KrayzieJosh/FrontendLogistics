package za.ac.cput.model;



public class Project {
    private String projectId;
    private String projectName;
    private String projectStatus;
    private Driver driver;
    private ProjectManager projectManager;
    private SiteManager siteManager;
    private Company company;
    private DeliveryOrder deliveryOrder;

    public Project() {
    }

    public Project(String projectId, String projectName, String projectStatus, Driver driver,ProjectManager projectManager, SiteManager siteManager, Company company, DeliveryOrder deliveryOrder) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectStatus = projectStatus;
        this.driver = driver;
        this.projectManager = projectManager;
        this.siteManager = siteManager;
        this.company = company;
        this.deliveryOrder = deliveryOrder;


    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public SiteManager getSiteManager() {
        return siteManager;
    }

    public void setSiteManager(SiteManager siteManager) {
        this.siteManager = siteManager;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", projectStatus='" + projectStatus + '\'' +
                ", driver=" + driver +
                ", projectManager=" + projectManager +
                ", siteManager=" + siteManager +
                ", company=" + company +
                ", deliveryOrder=" + deliveryOrder +
                '}';
    }
}
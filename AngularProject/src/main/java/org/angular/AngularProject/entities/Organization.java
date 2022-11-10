package org.angular.AngularProject.entities;

import javax.persistence.*;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_Id", unique = true, updatable = false, insertable = false, nullable = false)
    private int organizationID;

    @Column(name = "organization_name", length = 50, unique = true, updatable = true, insertable = true, nullable = false)
    private String organizationName;

    @Column(name = "organization_email", length = 50, unique = true, updatable = true, insertable = true, nullable = false)
    private String organizationEmail;

    @Column(name = "organization_password", length = 500, unique = false, updatable = true, insertable = true, nullable = false)
    private String password;

    public Organization() {
    }

    public Organization(String organizationName, String organizationEmail, String password) {
        this.organizationName = organizationName;
        this.organizationEmail = organizationEmail;
        this.password = password;
    }

    public Organization(int organizationID, String organizationName, String organizationEmail, String password) {
        this.organizationID = organizationID;
        this.organizationName = organizationName;
        this.organizationEmail = organizationEmail;
        this.password = password;
    }

    public int getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(int organizationID) {
        this.organizationID = organizationID;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationEmail() {
        return organizationEmail;
    }

    public void setOrganizationEmail(String organizationEmail) {
        this.organizationEmail = organizationEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
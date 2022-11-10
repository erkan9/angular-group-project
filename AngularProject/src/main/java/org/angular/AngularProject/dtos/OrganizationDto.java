package org.angular.AngularProject.dtos;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class OrganizationDto {

    private int organizationID;

    private String organizationName;

    private String organizationEmail;

    private String password;

    public OrganizationDto() {
    }

    public OrganizationDto(String organizationName, String organizationEmail, String password) {
        this.organizationName = organizationName;
        this.organizationEmail = organizationEmail;
        this.password = password;
    }


    public OrganizationDto(int organizationID, String organizationName, String organizationEmail, String password) {
        this.organizationID = organizationID;
        this.organizationName = organizationName;
        this.organizationEmail = organizationEmail;
        this.password = password;
    }
}
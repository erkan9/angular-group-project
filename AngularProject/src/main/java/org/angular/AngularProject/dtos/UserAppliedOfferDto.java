package org.angular.AngularProject.dtos;

import lombok.Data;

@Data
public class UserAppliedOfferDto {

    private int jobAdId;

    private String title;

    private String jobAdType;

    private String jobAdCategory;

    private String publisherName;

    private String response;

    private String status;

    public UserAppliedOfferDto() {
    }

    public UserAppliedOfferDto(String title, String jobAdType,
                               String jobAdCategory, String publisherName, String response, String status) {

        this.title = title;
        this.jobAdType = jobAdType;
        this.jobAdCategory = jobAdCategory;
        this.publisherName = publisherName;
        this.response = response;
        this.status = status;
    }

    public UserAppliedOfferDto(int jobAdId, String title, String jobAdType,
                               String jobAdCategory, String publisherName, String response, String status) {

        this.jobAdId = jobAdId;
        this.title = title;
        this.jobAdType = jobAdType;
        this.jobAdCategory = jobAdCategory;
        this.publisherName = publisherName;
        this.response = response;
        this.status = status;
    }
}

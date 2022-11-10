package org.angular.AngularProject.dtos;

import lombok.Data;

@Data
public class JobListingDto {

    private int jobAdId;

    private String header;

    private String description;

    private int numberOfLikes;

    private String jobAdType;

    private String jobAdCategory;

    private String publisherName;

    private int jobAdPublisherID;

    private boolean isActive = true;

    public JobListingDto(int jobAdId, String header, String description, int numberOfLikes, String jobAdType, String jobAdCategory, String publisherName, int jobAdPublisherID, boolean isActive) {
        this.jobAdId = jobAdId;
        this.header = header;
        this.description = description;
        this.numberOfLikes = numberOfLikes;
        this.jobAdType = jobAdType;
        this.jobAdCategory = jobAdCategory;
        this.publisherName = publisherName;
        this.jobAdPublisherID = jobAdPublisherID;
        this.isActive = isActive;
    }
}

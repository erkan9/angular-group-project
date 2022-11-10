package org.angular.AngularProject.dtos;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class JobAdDto {

    @PositiveOrZero
    private int jobAdId;

    @NotBlank(message = "Job Ad Header cannot be Blank!")
    @NotEmpty(message = "Job Ad Header cannot be Empty!")
    @NotNull(message = "Job Ad Header cannot be Null!")
    private String header;

    private String description;

    private int numberOfLikes = 0;

    private String jobAdType;

    private String jobAdCategory;

    private int jobAdPublisherID;

    private boolean isActive = true;

    public JobAdDto() {
    }

    public JobAdDto(String header, String description, int numberOfLikes, String jobAdType,
                    String jobAdCategory, int jobAdPublisherID, boolean isActive) {

        this.header = header;
        this.description = description;
        this.numberOfLikes = numberOfLikes;
        this.jobAdType = jobAdType;
        this.jobAdCategory = jobAdCategory;
        this.jobAdPublisherID = jobAdPublisherID;
        this.isActive = isActive;
    }

    public JobAdDto(int jobAdId, String header, String description, int numberOfLikes, String jobAdType,
                    String jobAdCategory, int jobAdPublisherID, boolean isActive) {

        this.jobAdId = jobAdId;
        this.header = header;
        this.description = description;
        this.numberOfLikes = numberOfLikes;
        this.jobAdType = jobAdType;
        this.jobAdCategory = jobAdCategory;
        this.jobAdPublisherID = jobAdPublisherID;
        this.isActive = isActive;
    }
}
package org.angular.AngularProject.entities;

import javax.persistence.*;

@Entity
@Table(name = "job_ad")
public class JobAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_ad_id", unique = true, updatable = false, insertable = false, nullable = false)
    private int jobAdId;

    @Column(name = "job_ad_header", length = 100, unique = false, updatable = true, insertable = true, nullable = false)
    private String header;

    @Column(name = "job_ad_description", length = 500, unique = false, updatable = true, insertable = true, nullable = false)
    private String description;

    @Column(name = "job_ad_likes", unique = false, updatable = true, insertable = true, nullable = false)
    private int numberOfLikes = 0;

    @Column(name = "job_ad_type", length = 50, unique = false, updatable = true, insertable = true, nullable = false)
    private String jobAdType;

    @Column(name = "job_ad_category", length = 50, unique = false, updatable = true, insertable = true, nullable = false)
    private String jobAdCategory;

    @Column(name = "job_ad_publisher_id", unique = false, updatable = false, insertable = true, nullable = true)
    private int jobAdPublisherID;

    @Column(name = "job_ad_activity", unique = false, updatable = true, insertable = true, nullable = false)
    private boolean isActive = true;

    public JobAd() {
    }

    public JobAd(String header, String description, int numberOfLikes, String jobAdType, String jobAdCategory, int jobAdPublisherID, boolean isActive) {
        this.header = header;
        this.description = description;
        this.numberOfLikes = numberOfLikes;
        this.jobAdType = jobAdType;
        this.jobAdCategory = jobAdCategory;
        this.jobAdPublisherID = jobAdPublisherID;
        this.isActive = isActive;
    }

    public JobAd(int jobAdId, String header, String description, int numberOfLikes, String jobAdType, String jobAdCategory, int jobAdPublisherID, boolean isActive) {
        this.jobAdId = jobAdId;
        this.header = header;
        this.description = description;
        this.numberOfLikes = numberOfLikes;
        this.jobAdType = jobAdType;
        this.jobAdCategory = jobAdCategory;
        this.jobAdPublisherID = jobAdPublisherID;
        this.isActive = isActive;
    }

    public int getUserID() {
        return jobAdId;
    }

    public void setUserID(int userID) {
        this.jobAdId = userID;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getJobAdId() {
        return jobAdId;
    }

    public int getJobAdPublisherID() {
        return jobAdPublisherID;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public String getJobAdType() {
        return jobAdType;
    }

    public void setJobAdType(String jobAdType) {
        this.jobAdType = jobAdType;
    }

    public String getJobAdCategory() {
        return jobAdCategory;
    }

    public void setJobAdCategory(String jobAdCategory) {
        this.jobAdCategory = jobAdCategory;
    }
}
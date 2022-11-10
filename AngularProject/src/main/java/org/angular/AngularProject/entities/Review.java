package org.angular.AngularProject.entities;

import javax.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", unique = true, updatable = false, insertable = false, nullable = false)
    private int reviewId;

    @Column(name = "review_user_id", unique = false, updatable = false, insertable = true, nullable = false)
    private int userId;

    @Column(name = "review_job_ad_id", unique = false, updatable = false, insertable = true, nullable = false)
    private int jobAdId;

    @Column(name = "review_is_like", unique = false, updatable = false, insertable = true, nullable = false)
    boolean isLike;

    public Review() {
    }

    public Review(int userId, int jobAdId, boolean isLike) {
        this.userId = userId;
        this.jobAdId = jobAdId;
        this.isLike = isLike;
    }

    public Review(int reviewId, int userId, int jobAdId, boolean isLike) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.jobAdId = jobAdId;
        this.isLike = isLike;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getJobAdId() {
        return jobAdId;
    }

    public void setJobAdId(int jobAdId) {
        this.jobAdId = jobAdId;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
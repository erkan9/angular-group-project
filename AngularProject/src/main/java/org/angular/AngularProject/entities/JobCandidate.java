package org.angular.AngularProject.entities;

import javax.persistence.*;

@Entity
@Table(name = "jobs_candidates")
public class JobCandidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jobs_candide_id", unique = true, updatable = false, insertable = false, nullable = false)
    private int jobCandidateId;

    @Column(name = "candide_id", unique = false, updatable = true, insertable = true, nullable = false)
    private int candidateId;

    @Column(name = "job_ad_id", unique = false, updatable = true, insertable = true, nullable = false)
    private int jobAdId;

    @Column(name = "approval_status", unique = false, updatable = true, insertable = true, nullable = true)
    private boolean status;

    @Column(name = "feedback_status", unique = false, updatable = true, insertable = true, nullable = true)
    private boolean feedback;

    public JobCandidate() {
    }

    public JobCandidate(int candidateId, int jobAdId) {
        this.candidateId = candidateId;
        this.jobAdId = jobAdId;
    }

    public JobCandidate(int candidateId, int job_id, boolean status) {
        this.candidateId = candidateId;
        this.jobAdId = job_id;
        this.status = status;
    }

    public JobCandidate(int jobCandidateId, int candidateId, int job_id, boolean status, boolean feedback) {
        this.jobCandidateId = jobCandidateId;
        this.candidateId = candidateId;
        this.jobAdId = job_id;
        this.status = status;
        this.feedback = feedback;
    }

    public boolean isFeedback() {
        return feedback;
    }

    public int getJobCandidateId() {
        return jobCandidateId;
    }

    public void setJobCandidateId(int jobCandidateId) {
        this.jobCandidateId = jobCandidateId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }

    public int getJobAdId() {
        return jobAdId;
    }

    public void setJobAdId(int jobAdId) {
        this.jobAdId = jobAdId;
    }

    public boolean getStatus() {
        return status;
    }

    public void setFeedback(boolean feedback) {
        this.feedback = feedback;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
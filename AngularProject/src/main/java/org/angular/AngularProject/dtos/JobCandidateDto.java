package org.angular.AngularProject.dtos;

import lombok.Data;

@Data
public class JobCandidateDto {

    private int jobCandidateId;

    private int candidateId;

    private int jobAdId;

    private boolean status;

    private boolean feedback;

    public JobCandidateDto() {
    }

    public JobCandidateDto(int candidateId, int jobAdId) {
        this.candidateId = candidateId;
        this.jobAdId = jobAdId;
    }

    public JobCandidateDto(int jobCandidateId, int candidateId, int jobAdId, boolean status, boolean feedback) {
        this.jobCandidateId = jobCandidateId;
        this.candidateId = candidateId;
        this.jobAdId = jobAdId;
        this.status = status;
        this.feedback = feedback;
    }

    public JobCandidateDto(int candidateId, int job_id, boolean status, boolean feedback) {
        this.candidateId = candidateId;
        this.jobAdId = job_id;
        this.status = status;
        this.feedback = feedback;
    }

    public boolean isFeedback() {
        return feedback;
    }

    public boolean getStatus() {
        return status;
    }
}
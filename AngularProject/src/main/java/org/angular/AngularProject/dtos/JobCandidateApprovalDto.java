package org.angular.AngularProject.dtos;

import lombok.Data;
import org.angular.AngularProject.entities.User;

@Data
public class JobCandidateApprovalDto {

    private int jobCandidateId;

    private UserDto user;

    private int jobAdId;

    private boolean status;

    public JobCandidateApprovalDto() {
    }

    public JobCandidateApprovalDto(UserDto user, int jobAdId, boolean status) {
        this.user = user;
        this.jobAdId = jobAdId;
        this.status = status;
    }

    public JobCandidateApprovalDto(int jobCandidateId, UserDto user, int jobAdId, boolean status) {
        this.jobCandidateId = jobCandidateId;
        this.user = user;
        this.jobAdId = jobAdId;
        this.status = status;
    }
}
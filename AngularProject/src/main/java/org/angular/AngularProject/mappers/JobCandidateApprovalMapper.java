package org.angular.AngularProject.mappers;

import org.angular.AngularProject.dtos.JobCandidateApprovalDto;
import org.angular.AngularProject.dtos.UserDto;
import org.angular.AngularProject.entities.JobCandidate;
import org.springframework.stereotype.Component;

@Component
public class JobCandidateApprovalMapper {

    public JobCandidateApprovalDto mapJobCandidateApprovalToJobCandidateApprovalDto(JobCandidate jobCandidate, UserDto user) {

        return new JobCandidateApprovalDto(jobCandidate.getJobCandidateId(), user, jobCandidate.getJobAdId(), jobCandidate.getStatus());
    }
}
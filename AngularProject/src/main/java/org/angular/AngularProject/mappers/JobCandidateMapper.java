package org.angular.AngularProject.mappers;

import org.angular.AngularProject.dtos.JobCandidateDto;
import org.angular.AngularProject.entities.JobCandidate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobCandidateMapper {

    public JobCandidate mapJobCandidateDtoToJobCandidate(JobCandidateDto jobCandidateDto) {

        return new JobCandidate(jobCandidateDto.getCandidateId(), jobCandidateDto.getJobCandidateId(), jobCandidateDto.getJobAdId(), jobCandidateDto.getStatus(), jobCandidateDto.isFeedback());
    }

    public JobCandidateDto mapJobCandidateToJobCandidateDto(JobCandidate jobCandidate) {

        return new JobCandidateDto(jobCandidate.getCandidateId(), jobCandidate.getJobCandidateId(), jobCandidate.getJobAdId(), jobCandidate.getStatus(), jobCandidate.isFeedback());
    }

    public List<JobCandidateDto> mapListOfJobCandidateToJobCandidateDto(List<JobCandidate> listOfJobCandidates) {

        return listOfJobCandidates.stream().map(this::mapJobCandidateToJobCandidateDto).collect(Collectors.toList());
    }
}
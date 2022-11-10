package org.angular.AngularProject.services.interfaces;

import org.angular.AngularProject.dtos.*;

import java.util.List;

public interface JobAdService {

    List<JobListingDto> getAllJobAds();

    void checkIfUserCanApply(int userID, int jobAdID);

    void likeAJobAd(int userID, int jobAdID);

    List<JobCandidateDto> getAllJobAddsApplied(int candidateID);

    JobAdDto createJobAd(JobAdDto jobAdDto);

    void deleteJobAd(int jobAdID, int publisherID);

    List<UserDto> candidatesForJobAd(int jobAdd);

    JobCandidateApprovalDto approvedOrCancelCandidate(int candidateID, int jobAdID, int organizationID, boolean isApproved);

    List<UserAppliedOfferDto> getUserAppliedJobs(int userID);

    JobAdDto getJobById(int id);

    List<JobAdDto> getJobsFromOrg(int id);

    List<ApplicantDto> getApplicants(int jobAdId);

    void respond(int userID, int jobAdID, boolean approval);

    JobAdDto updateJob(JobAdDto jobAdDto);
}
package org.angular.AngularProject.services;

import org.angular.AngularProject.dtos.*;
import org.angular.AngularProject.entities.*;
import org.angular.AngularProject.exceptions.CannotApplyException;
import org.angular.AngularProject.exceptions.NotFoundException;
import org.angular.AngularProject.mappers.JobAdMapper;
import org.angular.AngularProject.mappers.JobCandidateApprovalMapper;
import org.angular.AngularProject.mappers.JobCandidateMapper;
import org.angular.AngularProject.mappers.UserMapper;
import org.angular.AngularProject.repositories.*;
import org.angular.AngularProject.services.interfaces.JobAdService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobAdImpl implements JobAdService {

    private final JobAdRepository jobAdRepository;

    private final JobCandidateRepository jobCandidateRepository;

    private final ReviewRepository reviewRepository;

    private final JobAdMapper jobAdMapper;

    private final JobCandidateMapper jobCandidateMapper;

    private final OrganizationRepository organizationRepository;

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final JobCandidateApprovalMapper jobCandidateApprovalMapper;

    public JobAdImpl(JobAdRepository jobAdRepository, JobCandidateRepository jobCandidateRepository,
                     ReviewRepository reviewRepository, JobAdMapper jobAdMapper, JobCandidateMapper jobCandidateMapper, OrganizationRepository organizationRepository, UserRepository userRepository, UserMapper userMapper, JobCandidateApprovalMapper jobCandidateApprovalMapper) {

        this.jobAdRepository = jobAdRepository;
        this.jobCandidateRepository = jobCandidateRepository;
        this.reviewRepository = reviewRepository;
        this.jobAdMapper = jobAdMapper;
        this.jobCandidateMapper = jobCandidateMapper;
        this.organizationRepository = organizationRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.jobCandidateApprovalMapper = jobCandidateApprovalMapper;
    }

    @Override
    public List<JobListingDto> getAllJobAds() {

        List<JobAd> listOfJobAdds = jobAdRepository.findAll();
        List<Organization> orgs = organizationRepository.findAll();
        List<JobListingDto> dtos = new ArrayList<>();

        listOfJobAdds.forEach(j -> dtos.add(new JobListingDto(
                j.getJobAdId(),
                j.getHeader(),
                j.getDescription(),
                j.getNumberOfLikes(),
                j.getJobAdType(),
                j.getJobAdCategory(),
                orgs.stream().filter(o -> o.getOrganizationID() == j.getJobAdPublisherID()).findFirst().get().getOrganizationName(),
                j.getJobAdPublisherID(),
                j.isActive()
        )));

        return dtos;
    }

    @Override
    public void checkIfUserCanApply(int userID, int jobAdID) {

        JobAd jobAd = jobAdRepository.findById(jobAdID).get();

        List<JobCandidate> listOfJobCandidates = jobCandidateRepository.findAll();

        boolean hasAppliedAlready = listOfJobCandidates.stream().
                anyMatch(jobCandidate -> jobCandidate.getCandidateId() == userID && jobCandidate.getJobAdId() == jobAdID);

        boolean hasApplied = false;

        if (jobAd.isActive()) {

            if (!hasAppliedAlready) {

                JobCandidate newJobCandidate = new JobCandidate(userID, jobAdID);

                jobCandidateRepository.save(newJobCandidate);

                hasApplied = true;
            }
        }

        if (!hasApplied) {

            throw new CannotApplyException("User cannot apply for that Job");
        }
    }

    @Override
    public void likeAJobAd(int userID, int jobAdID) {

        List<Review> listOfAllReviews = reviewRepository.findAll();

        boolean hasLikedBefore = listOfAllReviews.stream().anyMatch(review -> review.getUserId() == userID && review.getJobAdId() == jobAdID);

        boolean hasLiked = false;

        if (!hasLikedBefore) {

            Review review = new Review(userID, jobAdID, true);

            reviewRepository.save(review);

            hasLiked = true;

            Optional<JobAd> jobAdd = jobAdRepository.findById(jobAdID);

            JobAd newAdd = jobAdd.get();

            int updatedLikes = newAdd.getNumberOfLikes();

            newAdd.setNumberOfLikes(updatedLikes + 1);

            jobAdRepository.save(newAdd);
        }

        if (!hasLiked) {

            throw new CannotApplyException("User cannot Like!");
        }
    }

    @Override
    public List<JobCandidateDto> getAllJobAddsApplied(int candidateID) {

        List<JobCandidate> allJobCandidates = jobCandidateRepository.findJobCandidateByCandidateId(candidateID);

        return jobCandidateMapper.mapListOfJobCandidateToJobCandidateDto(allJobCandidates);
    }

    @Override
    public JobAdDto createJobAd(JobAdDto jobAdDto) {

        String jobAdType = jobAdDto.getJobAdType();

        String jobCategory = jobAdDto.getJobAdCategory();

        Optional<Organization> organization = organizationRepository.findById(jobAdDto.getJobAdPublisherID());

        Organization publisher = organization.get();

        if (publisher.getOrganizationEmail() == null) {

            throw new NotFoundException("Not found publisher");

        }
        JobAd newJobAdd = jobAdMapper.mapJobAdDtoToJobAd(jobAdDto);

        jobAdRepository.save(newJobAdd);

        int newJobAddID = newJobAdd.getUserID();

        jobAdDto.setJobAdId(newJobAddID);

        return jobAdDto;
    }

    @Override
    public void deleteJobAd(int jobAdID, int publisherID) {

        List<JobAd> listOfJobAdds = jobAdRepository.findAll();

        boolean isFound = listOfJobAdds.stream().anyMatch(jobAd -> jobAd.getJobAdPublisherID() == publisherID && jobAd.getJobAdId() == jobAdID);

        if (!isFound) {

            throw new NotFoundException("Not Found Job Add");
        }

        jobCandidateRepository.deleteJobCandidateByJobAdId(jobAdID);

        reviewRepository.deleteReviewByJobAdId(jobAdID);

        jobAdRepository.deleteById(jobAdID);
    }

    @Override
    public List<UserDto> candidatesForJobAd(int jobAddID) {

        List<JobCandidate> listOfJobCandidates = jobCandidateRepository.findJobCandidateByJobAdId(jobAddID);

        List<Integer> candidatesId = new ArrayList<>();

        for (JobCandidate jobCandidate : listOfJobCandidates) {

            candidatesId.add(jobCandidate.getJobCandidateId());
        }

        List<User> candidates = new ArrayList<>();

        for (Integer candidateID : candidatesId) {

            Optional<User> user = userRepository.findById(candidateID);

            User candidateUser = user.get();

            candidates.add(candidateUser);
        }

        return userMapper.mapListOfUserToUserDto(candidates);
    }

    @Override
    public JobCandidateApprovalDto approvedOrCancelCandidate(int candidateID, int jobAdID, int organizationID, boolean isApproved) {

        JobCandidate jobCandidate = jobCandidateRepository.findJobCandidateByJobAdIdAndCandidateId(jobAdID, candidateID);

        if (jobCandidate == null) {

            throw new NotFoundException("Not found Candidation");
        }

        Optional<JobAd> jobAd = jobAdRepository.findById(jobAdID);

        JobAd jobAd1 = jobAd.get();

        if (jobAd1.getJobAdPublisherID() == organizationID) {

            throw new NotFoundException("Not Found Publication");
        }

        jobCandidate.setStatus(isApproved);

        jobCandidateRepository.save(jobCandidate);

        Optional<User> user = userRepository.findById(candidateID);

        return jobCandidateApprovalMapper.mapJobCandidateApprovalToJobCandidateApprovalDto(jobCandidate, userMapper.mapUserToUserDto(user.get()));
    }

    @Override
    public List<UserAppliedOfferDto> getUserAppliedJobs(int userID) {
        var userCandidates = jobCandidateRepository.findJobCandidateByCandidateId(userID);


        var jobs = jobAdRepository
                .findAll()
                .stream()
                .filter(j -> userCandidates.stream().anyMatch(c -> c.getJobAdId() == j.getJobAdId()));

        List<UserAppliedOfferDto> offers = new ArrayList<>();

        jobs.forEach(j -> offers.add(
                        new UserAppliedOfferDto(
                                j.getJobAdId(),
                                j.getHeader(),
                                j.getJobAdType(),
                                j.getJobAdCategory(),
                                organizationRepository.findById(j.getJobAdPublisherID()).isEmpty() ? "" : organizationRepository.findById(j.getJobAdPublisherID()).get().getOrganizationName(),
                                jobCandidateRepository.findJobCandidateByJobAdIdAndCandidateId(j.getJobAdId(), userID).isFeedback() ?
                                        jobCandidateRepository.findJobCandidateByJobAdIdAndCandidateId(j.getJobAdId(), userID).getStatus() ? "Approve" : "Rejected" : "Pending",
                                j.isActive() ? "Active" : "Non-Active")
                )
        );

        return offers;
    }

    @Override
    public JobAdDto getJobById(int id) {
        var ad = jobAdRepository.findById(id).get();

        return jobAdMapper.mapJobAdToJobAdDto(ad);
    }

    @Override
    public List<JobAdDto> getJobsFromOrg(int id) {

        var jobs = jobAdRepository.findJobAdByJobAdPublisherID(id);
        return jobAdMapper.mapListOfJobAdToJobAdDto(jobs);
    }

    @Override
    public List<ApplicantDto> getApplicants(int jobAdId) {
        var candidates = jobCandidateRepository.findJobCandidateByJobAdId(jobAdId);
        List<ApplicantDto> applicantDtos = new ArrayList<>();

        for (var candidate : candidates) {
            var user = userRepository.findById(candidate.getCandidateId()).get();
            var statusText = candidate.isFeedback() ? candidate.getStatus() ? "Approved" : "Rejected" : "Pending";
            applicantDtos.add(
                    new ApplicantDto(
                            user.getUserID(),
                            user.getUserFirstName(),
                            user.getUserEmail(),
                            statusText));
        }

        return applicantDtos;
    }

    @Override
    public void respond(int userID, int jobAdID, boolean approval) {
        var candidate = jobCandidateRepository.findJobCandidateByJobAdIdAndCandidateId(jobAdID, userID);
        candidate.setStatus(approval);
        candidate.setFeedback(true);

        jobCandidateRepository.save(candidate);
    }

    @Override
    public JobAdDto updateJob(JobAdDto jobAdDto) {
        var job = jobAdRepository.findById(jobAdDto.getJobAdId()).get();

        job.setJobAdType(jobAdDto.getJobAdType());
        job.setJobAdCategory(jobAdDto.getJobAdCategory());
        job.setHeader(jobAdDto.getHeader());
        job.setDescription(jobAdDto.getDescription());

        jobAdRepository.save(job);

        return jobAdMapper.mapJobAdToJobAdDto(job);
    }
}
package org.angular.AngularProject.controllers;

import org.angular.AngularProject.dtos.*;
import org.angular.AngularProject.services.interfaces.JobAdService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.constraints.Positive;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Validated
public class JobAddController {

    private final JobAdService jobAdService;

    public JobAddController(JobAdService jobAdService) {
        this.jobAdService = jobAdService;
    }

    @GetMapping("/jobAdds")
    public ResponseEntity<List<JobListingDto>> getAllJobAds() {

        return ResponseEntity.ok(jobAdService.getAllJobAds());
    }

    @GetMapping(value = "/jobAdds", params = {"id"})
    public ResponseEntity<JobAdDto> getAllJobAd(@RequestParam(name = "id") int id) {

        return ResponseEntity.ok(jobAdService.getJobById(id));
    }

    @GetMapping(value = "/orgJobs", params = {"id"})
    public ResponseEntity<List<JobAdDto>> getJobsFromOrg(@RequestParam(name = "id") int id) {

        return ResponseEntity.ok(jobAdService.getJobsFromOrg(id));
    }

    @PostMapping("/jobAdds/add")
    public JobAdDto createJobAdd(@RequestBody JobAdDto jobAdDto) {

        return jobAdService.createJobAd(jobAdDto);
    }

    @PatchMapping("/jobAdds/update")
    public JobAdDto updateJob(@RequestBody JobAdDto jobAdDto) {

        return jobAdService.updateJob(jobAdDto);
    }

    @PostMapping(value = "/jobAdds/apply", params = {"candidateId", "jobAdId"})
    public void applyForJobAdd(@RequestParam(name = "candidateId")
                               @Positive(message = "User ID must be a positive number")
                               int userID,
                               @RequestParam(name = "jobAdId")
                               @Positive(message = "Job Ad ID must be a positive number")
                               int jobAdID) {

        jobAdService.checkIfUserCanApply(userID, jobAdID);

    }

    @DeleteMapping(value = "/jobAdds/delete", params = {"jobAdPublisherID", "jobAdId"})
    public void deleteJobAdd(@RequestParam(name = "jobAdPublisherID")
                               @Positive(message = "User ID must be a positive number")
                               int jobAdPublisherID,
                               @RequestParam(name = "jobAdId")
                               @Positive(message = "Job Ad ID must be a positive number")
                               int jobAdID) {

        jobAdService.deleteJobAd(jobAdID, jobAdPublisherID);
    }

    @PostMapping(value = "/jobAdds/like", params = {"userId", "jobAdId"})
    public void likeJobAdd(@RequestParam(name = "userId")
                           @Positive(message = "User ID must be a positive number")
                           int userID,
                           @RequestParam(name = "jobAdId")
                           @Positive(message = "Job Ad ID must be a positive number")
                           int jobAdID) {

        jobAdService.likeAJobAd(userID, jobAdID);
    }



    @GetMapping(value = "/jobAdds/applied", params = {"candidateId"})
    public ResponseEntity<List<JobCandidateDto>> getAllAppliedJobAdds(@RequestParam(name = "candidateId")
                                                                      @Positive(message = "Candidate ID must be a positive number")
                                                                      int userID) {

        return ResponseEntity.ok(jobAdService.getAllJobAddsApplied(userID));
    }

    @GetMapping(value = "/jobAdds/candidates",  params = "jobAdId")
    public ResponseEntity<List<ApplicantDto>> GetApplicants(@RequestParam(name ="jobAdId")int jobAdId){
        return  ResponseEntity.ok(jobAdService.getApplicants(jobAdId));
    }

    @GetMapping(value = "/jobAdds/userApplied", params = {"userId"})
    public ResponseEntity<List<UserAppliedOfferDto>> getUserAppliedJobs(@RequestParam(name = "userId")
                                                                      @Positive(message = "Candidate ID must be a positive number")
                                                                              int userId) {

        return ResponseEntity.ok(jobAdService.getUserAppliedJobs(userId));
    }

    @PostMapping(value = "/jobAdds/respond", params = {"userId", "jobAdId","approval"})
    public void respond(@RequestParam(name = "userId")
                           @Positive(message = "User ID must be a positive number")
                                   int userID,
                           @RequestParam(name = "jobAdId")
                           @Positive(message = "Job Ad ID must be a positive number")
                                   int jobAdID,
                        @RequestParam(name = "approval")
                        boolean approval
                        ) {

        jobAdService.respond(userID, jobAdID, approval);
    }

}
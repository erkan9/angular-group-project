package org.angular.AngularProject.repositories;

import org.angular.AngularProject.entities.JobCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobCandidateRepository extends JpaRepository<JobCandidate, Integer> {

    List<JobCandidate> findJobCandidateByCandidateId(int candidateId);

    List<JobCandidate> findJobCandidateByJobAdId(int jobAdId);

    List<JobCandidate> findJobCandidateByCandidateIdAndStatus(int candidateId, boolean status);

    JobCandidate findJobCandidateByJobAdIdAndCandidateId(int jobAdId, int candidateId);

    void deleteJobCandidateByCandidateId(int candidateID);

    void deleteJobCandidateByJobAdId(int jobAdID);
}
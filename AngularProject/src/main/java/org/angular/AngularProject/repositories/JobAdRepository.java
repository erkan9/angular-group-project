package org.angular.AngularProject.repositories;

import org.angular.AngularProject.entities.JobAd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobAdRepository extends JpaRepository<JobAd, Integer> {

    List<JobAd> findJobAdByHeader(String jobAdHeader);

    List<JobAd> findJobAdByJobAdCategory(String jobAdCategory);

    List<JobAd> findJobAdByJobAdType(String jobAdType);

    List<JobAd> findJobAdByJobAdPublisherID(int publisherId);
}
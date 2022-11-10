package org.angular.AngularProject.repositories;

import org.angular.AngularProject.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findReviewsByJobAdId(int jobAdId);

    List<Review> findReviewsByUserId(int userId);

    void deleteReviewByUserId(int userId);

    void deleteReviewByJobAdId(int jobAdID);
}
package org.angular.AngularProject.services;

import org.angular.AngularProject.dtos.UserDto;
import org.angular.AngularProject.entities.JobAd;
import org.angular.AngularProject.entities.Review;
import org.angular.AngularProject.entities.User;
import org.angular.AngularProject.exceptions.NotFoundException;
import org.angular.AngularProject.mappers.UserMapper;
import org.angular.AngularProject.repositories.JobAdRepository;
import org.angular.AngularProject.repositories.JobCandidateRepository;
import org.angular.AngularProject.repositories.ReviewRepository;
import org.angular.AngularProject.repositories.UserRepository;
import org.angular.AngularProject.services.interfaces.UserService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final JobCandidateRepository jobCandidateRepository;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private final JobAdRepository jobAdRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(JobCandidateRepository jobCandidateRepository, ReviewRepository reviewRepository, UserRepository userRepository, JobAdRepository jobAdRepository, UserMapper userMapper) {
        this.jobCandidateRepository = jobCandidateRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.jobAdRepository = jobAdRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    @Override
    public void deleteUser(int userID) {


        List<Review> reviews = reviewRepository.findReviewsByUserId(userID);

        int numberOfReviews = reviews.size();

        int jobAdID = reviews.get(0).getJobAdId();

        reviewRepository.deleteReviewByUserId(userID);

        Optional<JobAd> jobAdd = jobAdRepository.findById(jobAdID);

        JobAd newAdd = jobAdd.get();

        int updatedLikes = newAdd.getNumberOfLikes();

        newAdd.setNumberOfLikes(updatedLikes - numberOfReviews);

        jobAdRepository.save(newAdd);

        jobCandidateRepository.deleteJobCandidateByCandidateId(userID);

        userRepository.deleteById(userID);
    }

    @Override
    public UserDto registerUser(UserDto userDto) {

        User newUser = userMapper.mapUserDtoToUser(userDto);

        userRepository.save(newUser);

        int newUserID = newUser.getUserID();

        userDto.setUserID(newUserID);

        return userDto;
    }

    @Override
    public UserDto logIn(String username, String password) {

        List<User> allUsers = userRepository.findAll();

        boolean isUserExists = allUsers.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));

        if (!isUserExists) {

            throw new NotFoundException("User not Found");
        }
        Optional<User> searchedUser = userRepository.findByUsername(username);

        User user = searchedUser.get();

        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public UserDto getUserById(int userId) {

        Optional<User> searchedUser = userRepository.findById(userId);
        User user = searchedUser.get();

        return userMapper.mapUserToUserDto(user);
    }

    @Override
    public void updateUser(UserDto userDto) {
        var user = userRepository.findById(userDto.getUserID()).get();

        user.setUserEmail(userDto.getUserEmail());
        user.setUsername(userDto.getUsername());
        user.setUserFirstName(userDto.getUserFirstName());
        if (userDto.getPassword().length() != 0) user.setPassword(userDto.getPassword());

        userRepository.save(user);
    }
}
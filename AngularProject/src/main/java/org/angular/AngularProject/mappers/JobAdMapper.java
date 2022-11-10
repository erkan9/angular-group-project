package org.angular.AngularProject.mappers;

import org.angular.AngularProject.dtos.JobAdDto;
import org.angular.AngularProject.entities.JobAd;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobAdMapper {

    public JobAd mapJobAdDtoToJobAd(JobAdDto jobAdDto) {

        return new JobAd(jobAdDto.getJobAdId(), jobAdDto.getHeader(), jobAdDto.getDescription(),
                jobAdDto.getNumberOfLikes(), jobAdDto.getJobAdType(), jobAdDto.getJobAdCategory(), jobAdDto.getJobAdPublisherID(),
                jobAdDto.isActive());
    }

    public JobAdDto mapJobAdToJobAdDto(JobAd jobAd) {

        return new JobAdDto(jobAd.getJobAdId(), jobAd.getHeader(), jobAd.getDescription(), jobAd.getNumberOfLikes(),
                jobAd.getJobAdType(), jobAd.getJobAdCategory(), jobAd.getJobAdPublisherID(), jobAd.isActive());
    }

    public List<JobAdDto> mapListOfJobAdToJobAdDto(List<JobAd> listOfJobAds) {

        return listOfJobAds.stream().map(this::mapJobAdToJobAdDto).collect(Collectors.toList());
    }
}
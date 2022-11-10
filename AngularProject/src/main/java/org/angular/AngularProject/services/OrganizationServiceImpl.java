package org.angular.AngularProject.services;

import org.angular.AngularProject.dtos.OrganizationDto;
import org.angular.AngularProject.entities.Organization;
import org.angular.AngularProject.exceptions.NotFoundException;
import org.angular.AngularProject.mappers.OrganizationMapper;
import org.angular.AngularProject.repositories.JobAdRepository;
import org.angular.AngularProject.repositories.OrganizationRepository;
import org.angular.AngularProject.services.interfaces.OrganizationService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    private final OrganizationMapper organizationMapper;

    private final JobAdRepository jobAdRepository;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper, JobAdRepository jobAdRepository) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.jobAdRepository = jobAdRepository;
    }

    @Override
    public OrganizationDto registerOrganization(OrganizationDto organizationDto) {

        Organization newOrganization = organizationMapper.mapOrganizationDtoToOrganizationDto(organizationDto);

        organizationRepository.save(newOrganization);

        int newOrganizationOrganizationID = newOrganization.getOrganizationID();

        organizationDto.setOrganizationID(newOrganizationOrganizationID);

        return organizationDto;
    }

    @Override
    public OrganizationDto logIn(String email, String password) {

        List<Organization> allOrganizations = organizationRepository.findAll();

        boolean isOrganizationExists = allOrganizations.stream().anyMatch(organization ->
                organization.getOrganizationEmail().equals(email) && organization.getPassword().equals(password));

        if (!isOrganizationExists) {

            throw new NotFoundException("Organization Not Found");
        }

        Optional<Organization> searchedOrganization = organizationRepository.findOrganizationByOrganizationEmail(email);

        Organization organization = searchedOrganization.get();

        return organizationMapper.mapOrganizationToOrganizationDto(organization);
    }

    @Override
    public OrganizationDto getById(int orgId) {
        var org = organizationRepository.findById(orgId).get();

        return organizationMapper.mapOrganizationToOrganizationDto(org);
    }

    @Transactional
    @Override
    public void deleteOrg(int orgId) {
        var jobs = jobAdRepository.findJobAdByJobAdPublisherID(orgId);
        jobAdRepository.deleteAll(jobs);

        organizationRepository.deleteById(orgId);
    }

    @Override
    public void updateOrg(OrganizationDto orgUpdateDto) {
        var org = organizationRepository.findById(orgUpdateDto.getOrganizationID()).get();

        org.setOrganizationEmail(orgUpdateDto.getOrganizationEmail());
        org.setOrganizationName(orgUpdateDto.getOrganizationName());
        if (orgUpdateDto.getPassword().length() != 0) org.setPassword(orgUpdateDto.getPassword());

        organizationRepository.save(org);
    }
}
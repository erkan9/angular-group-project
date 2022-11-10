package org.angular.AngularProject.mappers;

import org.angular.AngularProject.dtos.OrganizationDto;
import org.angular.AngularProject.entities.Organization;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrganizationMapper {

    public Organization mapOrganizationDtoToOrganizationDto(OrganizationDto organizationDto) {

        return new Organization(organizationDto.getOrganizationID(), organizationDto.getOrganizationName(),
                organizationDto.getOrganizationEmail(), organizationDto.getPassword());
    }

    public OrganizationDto mapOrganizationToOrganizationDto(Organization organization) {

        return new OrganizationDto(organization.getOrganizationID(), organization.getOrganizationName(),
                organization.getOrganizationEmail(), organization.getPassword());
    }

    public List<OrganizationDto> mapOrganizationListToOrganizationDto(List<Organization> listOfOrganization) {

        return listOfOrganization.stream().map(this::mapOrganizationToOrganizationDto).collect(Collectors.toList());
    }
}
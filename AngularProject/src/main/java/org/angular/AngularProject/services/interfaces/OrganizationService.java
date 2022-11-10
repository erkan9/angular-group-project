package org.angular.AngularProject.services.interfaces;

import org.angular.AngularProject.dtos.OrganizationDto;

public interface OrganizationService {

    OrganizationDto registerOrganization(OrganizationDto organizationDto);

    OrganizationDto logIn(String username, String password);

    OrganizationDto getById(int orgId);

    void deleteOrg(int orgId);

    void updateOrg(OrganizationDto orgUpdateDto);
}

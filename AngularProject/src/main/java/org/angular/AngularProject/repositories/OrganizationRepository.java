package org.angular.AngularProject.repositories;

import org.angular.AngularProject.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Integer> {

    Optional<Organization> findOrganizationByOrganizationName(String name);

    Optional<Organization> findOrganizationByOrganizationEmail(String email);

    Optional<Organization> findOrganizationByPassword(String password);
}

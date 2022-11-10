package org.angular.AngularProject.controllers;

import org.angular.AngularProject.dtos.OrganizationDto;
import org.angular.AngularProject.services.interfaces.OrganizationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@CrossOrigin
@RestController
@RequestMapping("/api/v1")
@Validated
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping(value = "/organization/getById", params = {"orgId"})
    public OrganizationDto getById(@RequestParam(name = "orgId") int orgId){

        return organizationService.getById(orgId);
    }

    @PostMapping("/organization/register")
    public OrganizationDto registerUser(@Valid @RequestBody OrganizationDto organizationDto) {


        return organizationService.registerOrganization(organizationDto);
    }

    @PostMapping(value = "/organization/login", params = {"organizationEmail", "password"})
    public OrganizationDto registerUser(@RequestParam(name = "organizationEmail")
                                        @NotNull
                                        @NotEmpty
                                        @NotBlank
                                        String organizationEmail,
                                        @RequestParam(name = "password")
                                        @NotNull
                                        @NotEmpty
                                        @NotBlank
                                        String password) {

        return organizationService.logIn(organizationEmail, password);
    }

    @DeleteMapping(value = "/organization/delete", params = {"orgId"})
    public void deleteOrg(@RequestParam(name = "orgId")
                           @Positive(message = "orgId must be a positive number")
                                   int orgId) {

        organizationService.deleteOrg(orgId);
    }

    @PatchMapping("/organization/update")
    public void createJobAdd(@RequestBody OrganizationDto orgUpdateDto) {

        organizationService.updateOrg(orgUpdateDto);
    }
}
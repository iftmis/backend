package org.tamisemi.iftmis.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tamisemi.iftmis.config.Constants;
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.service.OrganisationUnitService;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

@RestController
@RequestMapping("/api")
public class OrganisationUnitResource {
    private final Logger log = LoggerFactory.getLogger(OrganisationUnitResource.class);

    private static final String ENTITY_NAME = "organisationUnit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganisationUnitService organisationUnitService;

    public OrganisationUnitResource(OrganisationUnitService organisationUnitService) {
        this.organisationUnitService = organisationUnitService;
    }

    @PostMapping("/organisation-units")
    public ResponseEntity<OrganisationUnit> createOrganisationUnit(@Valid @RequestBody OrganisationUnit organisationUnit)
        throws URISyntaxException {
        log.debug("REST request to save OrganisationUnit : {}", organisationUnit);
        if (organisationUnit.getId() != null) {
            throw new BadRequestAlertException("A new organisationUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganisationUnit result = organisationUnitService.save(organisationUnit);
        return ResponseEntity
            .created(new URI("/api/organisation-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/organisation-units")
    public ResponseEntity<OrganisationUnit> updateOrganisationUnit(@Valid @RequestBody OrganisationUnit organisationUnit)
        throws URISyntaxException {
        log.debug("REST request to update OrganisationUnit : {}", organisationUnit);
        if (organisationUnit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganisationUnit result = organisationUnitService.save(organisationUnit);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, organisationUnit.getId().toString()))
            .body(result);
    }

    @GetMapping("/organisation-units")
    public ResponseEntity<List<OrganisationUnit>> getAllOrganisationUnits(@RequestParam(value = "query", defaultValue = "_") String query) {
        List<OrganisationUnit> items;
        if (query.equals("_") || query.isEmpty()) {
            items = organisationUnitService.findAll(query);
        } else {
            items = organisationUnitService.findAll(query.toLowerCase());
        }
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/organisation-units/page")
    public ResponseEntity<Page<OrganisationUnit>> getAllPagedOrganisationUnits(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                                                  @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
                                                                                  @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                                                                  @RequestParam(value = "query", defaultValue = "_") String query) {
        Page<OrganisationUnit> items;
        if (query.equals("_") || query.isEmpty()) {
            Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
            items = organisationUnitService.findAll(pageable);
        } else {
            Pageable pageable = PageRequest.of(0, size, Sort.by(sortBy).ascending());
            items = organisationUnitService.findAll(query.toLowerCase(), pageable);
        }
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/organisation-units/{id}")
    public ResponseEntity<OrganisationUnit> getOrganisationUnit(@PathVariable Long id) {
        log.debug("REST request to get OrganisationUnit : {}", id);
        Optional<OrganisationUnit> organisationUnit = organisationUnitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organisationUnit);
    }

    @DeleteMapping("/organisation-units/{id}")
    public ResponseEntity<Void> deleteOrganisationUnit(@PathVariable Long id) {
        log.debug("REST request to delete OrganisationUnit : {}", id);
        organisationUnitService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

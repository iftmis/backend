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
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.repository.OrganisationUnitRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.OrganisationUnit}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OrganisationUnitResource {
    private final Logger log = LoggerFactory.getLogger(OrganisationUnitResource.class);

    private static final String ENTITY_NAME = "organisationUnit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganisationUnitRepository organisationUnitRepository;

    public OrganisationUnitResource(OrganisationUnitRepository organisationUnitRepository) {
        this.organisationUnitRepository = organisationUnitRepository;
    }

    /**
     * {@code POST  /organisation-units} : Create a new organisationUnit.
     *
     * @param organisationUnit the organisationUnit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organisationUnit, or with status {@code 400 (Bad Request)} if the organisationUnit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/organisation-units")
    public ResponseEntity<OrganisationUnit> createOrganisationUnit(@Valid @RequestBody OrganisationUnit organisationUnit)
        throws URISyntaxException {
        log.debug("REST request to save OrganisationUnit : {}", organisationUnit);
        if (organisationUnit.getId() != null) {
            throw new BadRequestAlertException("A new organisationUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganisationUnit result = organisationUnitRepository.save(organisationUnit);
        return ResponseEntity
            .created(new URI("/api/organisation-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /organisation-units} : Updates an existing organisationUnit.
     *
     * @param organisationUnit the organisationUnit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organisationUnit,
     * or with status {@code 400 (Bad Request)} if the organisationUnit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organisationUnit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/organisation-units")
    public ResponseEntity<OrganisationUnit> updateOrganisationUnit(@Valid @RequestBody OrganisationUnit organisationUnit)
        throws URISyntaxException {
        log.debug("REST request to update OrganisationUnit : {}", organisationUnit);
        if (organisationUnit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganisationUnit result = organisationUnitRepository.save(organisationUnit);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, organisationUnit.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /organisation-units} : get all the organisationUnits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organisationUnits in body.
     */
    @GetMapping("/organisation-units")
    public List<OrganisationUnit> getAllOrganisationUnits() {
        log.debug("REST request to get all OrganisationUnits");
        return organisationUnitRepository.findAll();
    }

    /**
     * {@code GET  /organisation-units/:id} : get the "id" organisationUnit.
     *
     * @param id the id of the organisationUnit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organisationUnit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organisation-units/{id}")
    public ResponseEntity<OrganisationUnit> getOrganisationUnit(@PathVariable Long id) {
        log.debug("REST request to get OrganisationUnit : {}", id);
        Optional<OrganisationUnit> organisationUnit = organisationUnitRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(organisationUnit);
    }

    /**
     * {@code DELETE  /organisation-units/:id} : delete the "id" organisationUnit.
     *
     * @param id the id of the organisationUnit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/organisation-units/{id}")
    public ResponseEntity<Void> deleteOrganisationUnit(@PathVariable Long id) {
        log.debug("REST request to delete OrganisationUnit : {}", id);
        organisationUnitRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

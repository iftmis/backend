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
import org.tamisemi.iftmis.domain.OrganisationUnitLevel;
import org.tamisemi.iftmis.repository.OrganisationUnitLevelRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.OrganisationUnitLevel}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OrganisationUnitLevelResource {
    private final Logger log = LoggerFactory.getLogger(OrganisationUnitLevelResource.class);

    private static final String ENTITY_NAME = "organisationUnitLevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganisationUnitLevelRepository organisationUnitLevelRepository;

    public OrganisationUnitLevelResource(OrganisationUnitLevelRepository organisationUnitLevelRepository) {
        this.organisationUnitLevelRepository = organisationUnitLevelRepository;
    }

    /**
     * {@code POST  /organisation-unit-levels} : Create a new organisationUnitLevel.
     *
     * @param organisationUnitLevel the organisationUnitLevel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organisationUnitLevel, or with status {@code 400 (Bad Request)} if the organisationUnitLevel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/organisation-unit-levels")
    public ResponseEntity<OrganisationUnitLevel> createOrganisationUnitLevel(
        @Valid @RequestBody OrganisationUnitLevel organisationUnitLevel
    )
        throws URISyntaxException {
        log.debug("REST request to save OrganisationUnitLevel : {}", organisationUnitLevel);
        if (organisationUnitLevel.getId() != null) {
            throw new BadRequestAlertException("A new organisationUnitLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganisationUnitLevel result = organisationUnitLevelRepository.save(organisationUnitLevel);
        return ResponseEntity
            .created(new URI("/api/organisation-unit-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /organisation-unit-levels} : Updates an existing organisationUnitLevel.
     *
     * @param organisationUnitLevel the organisationUnitLevel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organisationUnitLevel,
     * or with status {@code 400 (Bad Request)} if the organisationUnitLevel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organisationUnitLevel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/organisation-unit-levels")
    public ResponseEntity<OrganisationUnitLevel> updateOrganisationUnitLevel(
        @Valid @RequestBody OrganisationUnitLevel organisationUnitLevel
    )
        throws URISyntaxException {
        log.debug("REST request to update OrganisationUnitLevel : {}", organisationUnitLevel);
        if (organisationUnitLevel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganisationUnitLevel result = organisationUnitLevelRepository.save(organisationUnitLevel);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, organisationUnitLevel.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /organisation-unit-levels} : get all the organisationUnitLevels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organisationUnitLevels in body.
     */
    @GetMapping("/organisation-unit-levels")
    public List<OrganisationUnitLevel> getAllOrganisationUnitLevels() {
        log.debug("REST request to get all OrganisationUnitLevels");
        return organisationUnitLevelRepository.findAll();
    }

    /**
     * {@code GET  /organisation-unit-levels/:id} : get the "id" organisationUnitLevel.
     *
     * @param id the id of the organisationUnitLevel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organisationUnitLevel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organisation-unit-levels/{id}")
    public ResponseEntity<OrganisationUnitLevel> getOrganisationUnitLevel(@PathVariable Long id) {
        log.debug("REST request to get OrganisationUnitLevel : {}", id);
        Optional<OrganisationUnitLevel> organisationUnitLevel = organisationUnitLevelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(organisationUnitLevel);
    }

    /**
     * {@code DELETE  /organisation-unit-levels/:id} : delete the "id" organisationUnitLevel.
     *
     * @param id the id of the organisationUnitLevel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/organisation-unit-levels/{id}")
    public ResponseEntity<Void> deleteOrganisationUnitLevel(@PathVariable Long id) {
        log.debug("REST request to delete OrganisationUnitLevel : {}", id);
        organisationUnitLevelRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

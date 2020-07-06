package org.tamisemi.iftmis.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tamisemi.iftmis.service.OrganisationUnitLevelService;
import org.tamisemi.iftmis.service.dto.OrganisationUnitLevelDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.OrganisationUnitLevel}.
 */
@RestController
@RequestMapping("/api")
public class OrganisationUnitLevelResource {
    private final Logger log = LoggerFactory.getLogger(OrganisationUnitLevelResource.class);

    private static final String ENTITY_NAME = "organisationUnitLevel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganisationUnitLevelService organisationUnitLevelService;

    public OrganisationUnitLevelResource(OrganisationUnitLevelService organisationUnitLevelService) {
        this.organisationUnitLevelService = organisationUnitLevelService;
    }

    /**
     * {@code POST  /organisation-unit-levels} : Create a new organisationUnitLevel.
     *
     * @param organisationUnitLevelDTO the organisationUnitLevelDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organisationUnitLevelDTO, or with status {@code 400 (Bad Request)} if the organisationUnitLevel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/organisation-unit-levels")
    public ResponseEntity<OrganisationUnitLevelDTO> createOrganisationUnitLevel(
        @Valid @RequestBody OrganisationUnitLevelDTO organisationUnitLevelDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save OrganisationUnitLevel : {}", organisationUnitLevelDTO);
        if (organisationUnitLevelDTO.getId() != null) {
            throw new BadRequestAlertException("A new organisationUnitLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganisationUnitLevelDTO result = organisationUnitLevelService.save(organisationUnitLevelDTO);
        return ResponseEntity
            .created(new URI("/api/organisation-unit-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /organisation-unit-levels} : Updates an existing organisationUnitLevel.
     *
     * @param organisationUnitLevelDTO the organisationUnitLevelDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organisationUnitLevelDTO,
     * or with status {@code 400 (Bad Request)} if the organisationUnitLevelDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organisationUnitLevelDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/organisation-unit-levels")
    public ResponseEntity<OrganisationUnitLevelDTO> updateOrganisationUnitLevel(
        @Valid @RequestBody OrganisationUnitLevelDTO organisationUnitLevelDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update OrganisationUnitLevel : {}", organisationUnitLevelDTO);
        if (organisationUnitLevelDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganisationUnitLevelDTO result = organisationUnitLevelService.save(organisationUnitLevelDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, organisationUnitLevelDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /organisation-unit-levels} : get all the organisationUnitLevels.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organisationUnitLevels in body.
     */
    @GetMapping("/organisation-unit-levels")
    public ResponseEntity<List<OrganisationUnitLevelDTO>> getAllOrganisationUnitLevels(Pageable pageable) {
        log.debug("REST request to get a page of OrganisationUnitLevels");
        Page<OrganisationUnitLevelDTO> page = organisationUnitLevelService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /organisation-unit-levels/:id} : get the "id" organisationUnitLevel.
     *
     * @param id the id of the organisationUnitLevelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organisationUnitLevelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organisation-unit-levels/{id}")
    public ResponseEntity<OrganisationUnitLevelDTO> getOrganisationUnitLevel(@PathVariable Long id) {
        log.debug("REST request to get OrganisationUnitLevel : {}", id);
        Optional<OrganisationUnitLevelDTO> organisationUnitLevelDTO = organisationUnitLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organisationUnitLevelDTO);
    }

    /**
     * {@code DELETE  /organisation-unit-levels/:id} : delete the "id" organisationUnitLevel.
     *
     * @param id the id of the organisationUnitLevelDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/organisation-unit-levels/{id}")
    public ResponseEntity<Void> deleteOrganisationUnitLevel(@PathVariable Long id) {
        log.debug("REST request to delete OrganisationUnitLevel : {}", id);
        organisationUnitLevelService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

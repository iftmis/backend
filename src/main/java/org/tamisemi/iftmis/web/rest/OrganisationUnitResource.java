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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tamisemi.iftmis.service.OrganisationUnitQueryService;
import org.tamisemi.iftmis.service.OrganisationUnitService;
import org.tamisemi.iftmis.service.dto.OrganisationUnitCriteria;
import org.tamisemi.iftmis.service.dto.OrganisationUnitDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.OrganisationUnit}.
 */
@RestController
@RequestMapping("/api")
public class OrganisationUnitResource {
    private final Logger log = LoggerFactory.getLogger(OrganisationUnitResource.class);

    private static final String ENTITY_NAME = "organisationUnit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrganisationUnitService organisationUnitService;

    private final OrganisationUnitQueryService organisationUnitQueryService;

    public OrganisationUnitResource(
        OrganisationUnitService organisationUnitService,
        OrganisationUnitQueryService organisationUnitQueryService
    ) {
        this.organisationUnitService = organisationUnitService;
        this.organisationUnitQueryService = organisationUnitQueryService;
    }

    /**
     * {@code POST  /organisation-units} : Create a new organisationUnit.
     *
     * @param organisationUnitDTO the organisationUnitDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new organisationUnitDTO, or with status {@code 400 (Bad Request)} if the organisationUnit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/organisation-units")
    public ResponseEntity<OrganisationUnitDTO> createOrganisationUnit(@Valid @RequestBody OrganisationUnitDTO organisationUnitDTO)
        throws URISyntaxException {
        log.debug("REST request to save OrganisationUnit : {}", organisationUnitDTO);
        if (organisationUnitDTO.getId() != null) {
            throw new BadRequestAlertException("A new organisationUnit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrganisationUnitDTO result = organisationUnitService.save(organisationUnitDTO);
        return ResponseEntity
            .created(new URI("/api/organisation-units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /organisation-units} : Updates an existing organisationUnit.
     *
     * @param organisationUnitDTO the organisationUnitDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated organisationUnitDTO,
     * or with status {@code 400 (Bad Request)} if the organisationUnitDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the organisationUnitDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/organisation-units")
    public ResponseEntity<OrganisationUnitDTO> updateOrganisationUnit(@Valid @RequestBody OrganisationUnitDTO organisationUnitDTO)
        throws URISyntaxException {
        log.debug("REST request to update OrganisationUnit : {}", organisationUnitDTO);
        if (organisationUnitDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OrganisationUnitDTO result = organisationUnitService.save(organisationUnitDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, organisationUnitDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /organisation-units} : get all the organisationUnits.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organisationUnits in body.
     */
    @GetMapping("/organisation-units")
    public ResponseEntity<List<OrganisationUnitDTO>> getAllOrganisationUnits(OrganisationUnitCriteria criteria) {
        log.debug("REST request to get OrganisationUnits by criteria: {}", criteria);
        List<OrganisationUnitDTO> list = organisationUnitQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(list);
    }

    /**
     * {@code GET  /organisation-units} : get all the organisationUnits.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organisationUnits in body.
     */
    @GetMapping("/organisation-units/page")
    public ResponseEntity<List<OrganisationUnitDTO>> getPage(OrganisationUnitCriteria criteria, Pageable pageable) {
        log.debug("REST request to get OrganisationUnits by criteria: {}", criteria);
        Page<OrganisationUnitDTO> page = organisationUnitQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     *
     * @return
     */
    @GetMapping("/organisation-units/by-user")
    public ResponseEntity<List<OrganisationUnitDTO>> getUserOrganisationUnits() {
        log.debug("REST request to get a page of OrganisationUnits");
        List<OrganisationUnitDTO> ous = organisationUnitService.findByUser();
        return ResponseEntity.ok().body(ous);
    }

    /**
     * {@code GET  /organisation-units} : get all the organisationUnits.
     *
     * @param parentId the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of organisationUnits in body.
     */
    @GetMapping("/organisation-units/by-parent/{parentId}")
    public ResponseEntity<List<OrganisationUnitDTO>> getByParentOrganisationUnits(@PathVariable Long parentId) {
        log.debug("REST request to get a page of OrganisationUnits");
        List<OrganisationUnitDTO> ous = organisationUnitService.findByParent(parentId);
        return ResponseEntity.ok().body(ous);
    }

    /**
     * {@code GET  /organisation-units/:id} : get the "id" organisationUnit.
     *
     * @param id the id of the organisationUnitDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the organisationUnitDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/organisation-units/{id}")
    public ResponseEntity<OrganisationUnitDTO> getOrganisationUnit(@PathVariable Long id) {
        log.debug("REST request to get OrganisationUnit : {}", id);
        Optional<OrganisationUnitDTO> organisationUnitDTO = organisationUnitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organisationUnitDTO);
    }

    /**
     * {@code DELETE  /organisation-units/:id} : delete the "id" organisationUnit.
     *
     * @param id the id of the organisationUnitDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
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

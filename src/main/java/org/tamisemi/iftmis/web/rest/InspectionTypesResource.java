package org.tamisemi.iftmis.web.rest;

import org.tamisemi.iftmis.service.InspectionTypesService;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;
import org.tamisemi.iftmis.service.dto.InspectionTypesDTO;
import org.tamisemi.iftmis.service.dto.InspectionTypesCriteria;
import org.tamisemi.iftmis.service.InspectionTypesQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionTypes}.
 */
@RestController
@RequestMapping("/api")
public class InspectionTypesResource {

    private final Logger log = LoggerFactory.getLogger(InspectionTypesResource.class);

    private static final String ENTITY_NAME = "inspectionTypes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionTypesService inspectionTypesService;

    private final InspectionTypesQueryService inspectionTypesQueryService;

    public InspectionTypesResource(InspectionTypesService inspectionTypesService, InspectionTypesQueryService inspectionTypesQueryService) {
        this.inspectionTypesService = inspectionTypesService;
        this.inspectionTypesQueryService = inspectionTypesQueryService;
    }

    /**
     * {@code POST  /inspection-types} : Create a new inspectionTypes.
     *
     * @param inspectionTypesDTO the inspectionTypesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionTypesDTO, or with status {@code 400 (Bad Request)} if the inspectionTypes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-types")
    public ResponseEntity<InspectionTypesDTO> createInspectionTypes(@Valid @RequestBody InspectionTypesDTO inspectionTypesDTO) throws URISyntaxException {
        log.debug("REST request to save InspectionTypes : {}", inspectionTypesDTO);
        if (inspectionTypesDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionTypes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionTypesDTO result = inspectionTypesService.save(inspectionTypesDTO);
        return ResponseEntity.created(new URI("/api/inspection-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-types} : Updates an existing inspectionTypes.
     *
     * @param inspectionTypesDTO the inspectionTypesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionTypesDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionTypesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionTypesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-types")
    public ResponseEntity<InspectionTypesDTO> updateInspectionTypes(@Valid @RequestBody InspectionTypesDTO inspectionTypesDTO) throws URISyntaxException {
        log.debug("REST request to update InspectionTypes : {}", inspectionTypesDTO);
        if (inspectionTypesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionTypesDTO result = inspectionTypesService.save(inspectionTypesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionTypesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-types} : get all the inspectionTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionTypes in body.
     */
    @GetMapping("/inspection-types")
    public ResponseEntity<List<InspectionTypesDTO>> getAllInspectionTypes(InspectionTypesCriteria criteria, Pageable pageable) {
        log.debug("REST request to get InspectionTypes by criteria: {}", criteria);
        Page<InspectionTypesDTO> page = inspectionTypesQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-types/count} : count all the inspectionTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/inspection-types/count")
    public ResponseEntity<Long> countInspectionTypes(InspectionTypesCriteria criteria) {
        log.debug("REST request to count InspectionTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(inspectionTypesQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /inspection-types/:id} : get the "id" inspectionTypes.
     *
     * @param id the id of the inspectionTypesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionTypesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-types/{id}")
    public ResponseEntity<InspectionTypesDTO> getInspectionTypes(@PathVariable Long id) {
        log.debug("REST request to get InspectionTypes : {}", id);
        Optional<InspectionTypesDTO> inspectionTypesDTO = inspectionTypesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionTypesDTO);
    }

    /**
     * {@code DELETE  /inspection-types/:id} : delete the "id" inspectionTypes.
     *
     * @param id the id of the inspectionTypesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-types/{id}")
    public ResponseEntity<Void> deleteInspectionTypes(@PathVariable Long id) {
        log.debug("REST request to delete InspectionTypes : {}", id);
        inspectionTypesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

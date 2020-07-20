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
import org.tamisemi.iftmis.service.InspectionActivityQueryService;
import org.tamisemi.iftmis.service.InspectionActivityService;
import org.tamisemi.iftmis.service.dto.InspectionActivityCriteria;
import org.tamisemi.iftmis.service.dto.InspectionActivityDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionActivity}.
 */
@RestController
@RequestMapping("/api")
public class InspectionActivityResource {
    private final Logger log = LoggerFactory.getLogger(InspectionActivityResource.class);

    private static final String ENTITY_NAME = "inspectionActivity";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionActivityService inspectionActivityService;

    private final InspectionActivityQueryService inspectionActivityQueryService;

    public InspectionActivityResource(
        InspectionActivityService inspectionActivityService,
        InspectionActivityQueryService inspectionActivityQueryService
    ) {
        this.inspectionActivityService = inspectionActivityService;
        this.inspectionActivityQueryService = inspectionActivityQueryService;
    }

    /**
     * {@code POST  /inspection-activities} : Create a new inspectionActivity.
     *
     * @param inspectionActivityDTO the inspectionActivityDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionActivityDTO, or with status {@code 400 (Bad Request)} if the inspectionActivity has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-activities")
    public ResponseEntity<InspectionActivityDTO> createInspectionActivity(@Valid @RequestBody InspectionActivityDTO inspectionActivityDTO)
        throws URISyntaxException {
        log.debug("REST request to save InspectionActivity : {}", inspectionActivityDTO);
        if (inspectionActivityDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionActivity cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionActivityDTO result = inspectionActivityService.save(inspectionActivityDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-activities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-activities} : Updates an existing inspectionActivity.
     *
     * @param inspectionActivityDTO the inspectionActivityDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionActivityDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionActivityDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionActivityDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-activities")
    public ResponseEntity<InspectionActivityDTO> updateInspectionActivity(@Valid @RequestBody InspectionActivityDTO inspectionActivityDTO)
        throws URISyntaxException {
        log.debug("REST request to update InspectionActivity : {}", inspectionActivityDTO);
        if (inspectionActivityDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionActivityDTO result = inspectionActivityService.save(inspectionActivityDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionActivityDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-activities} : get all the inspectionActivities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionActivities in body.
     */
    @GetMapping("/inspection-activities")
    public ResponseEntity<List<InspectionActivityDTO>> getAllInspectionActivities(InspectionActivityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get InspectionActivities by criteria: {}", criteria);
        Page<InspectionActivityDTO> page = inspectionActivityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-activities/count} : count all the inspectionActivities.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/inspection-activities/count")
    public ResponseEntity<Long> countInspectionActivities(InspectionActivityCriteria criteria) {
        log.debug("REST request to count InspectionActivities by criteria: {}", criteria);
        return ResponseEntity.ok().body(inspectionActivityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /inspection-activities/:id} : get the "id" inspectionActivity.
     *
     * @param id the id of the inspectionActivityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionActivityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-activities/{id}")
    public ResponseEntity<InspectionActivityDTO> getInspectionActivity(@PathVariable Long id) {
        log.debug("REST request to get InspectionActivity : {}", id);
        Optional<InspectionActivityDTO> inspectionActivityDTO = inspectionActivityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionActivityDTO);
    }

    /**
     * {@code DELETE  /inspection-activities/:id} : delete the "id" inspectionActivity.
     *
     * @param id the id of the inspectionActivityDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-activities/{id}")
    public ResponseEntity<Void> deleteInspectionActivity(@PathVariable Long id) {
        log.debug("REST request to delete InspectionActivity : {}", id);
        inspectionActivityService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

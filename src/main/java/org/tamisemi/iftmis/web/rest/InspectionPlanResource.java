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
import org.tamisemi.iftmis.service.InspectionPlanService;
import org.tamisemi.iftmis.service.dto.InspectionPlanDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionPlan}.
 */
@RestController
@RequestMapping("/api")
public class InspectionPlanResource {
    private final Logger log = LoggerFactory.getLogger(InspectionPlanResource.class);

    private static final String ENTITY_NAME = "inspectionPlan";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionPlanService inspectionPlanService;

    public InspectionPlanResource(InspectionPlanService inspectionPlanService) {
        this.inspectionPlanService = inspectionPlanService;
    }

    /**
     * {@code POST  /inspection-plans} : Create a new inspectionPlan.
     *
     * @param inspectionPlanDTO the inspectionPlanDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionPlanDTO, or with status {@code 400 (Bad Request)} if the inspectionPlan has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-plans")
    public ResponseEntity<InspectionPlanDTO> createInspectionPlan(@Valid @RequestBody InspectionPlanDTO inspectionPlanDTO)
        throws URISyntaxException {
        log.debug("REST request to save InspectionPlan : {}", inspectionPlanDTO);
        if (inspectionPlanDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionPlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionPlanDTO result = inspectionPlanService.save(inspectionPlanDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-plans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-plans} : Updates an existing inspectionPlan.
     *
     * @param inspectionPlanDTO the inspectionPlanDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionPlanDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionPlanDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionPlanDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-plans")
    public ResponseEntity<InspectionPlanDTO> updateInspectionPlan(@Valid @RequestBody InspectionPlanDTO inspectionPlanDTO)
        throws URISyntaxException {
        log.debug("REST request to update InspectionPlan : {}", inspectionPlanDTO);
        if (inspectionPlanDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionPlanDTO result = inspectionPlanService.save(inspectionPlanDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionPlanDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-plans} : get all the inspectionPlans.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionPlans in body.
     */
    @GetMapping("/inspection-plans")
    public ResponseEntity<List<InspectionPlanDTO>> getAllInspectionPlans(Pageable pageable) {
        log.debug("REST request to get a page of InspectionPlans");
        Page<InspectionPlanDTO> page = inspectionPlanService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-plans/:id} : get the "id" inspectionPlan.
     *
     * @param id the id of the inspectionPlanDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionPlanDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-plans/{id}")
    public ResponseEntity<InspectionPlanDTO> getInspectionPlan(@PathVariable Long id) {
        log.debug("REST request to get InspectionPlan : {}", id);
        Optional<InspectionPlanDTO> inspectionPlanDTO = inspectionPlanService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionPlanDTO);
    }

    /**
     * {@code DELETE  /inspection-plans/:id} : delete the "id" inspectionPlan.
     *
     * @param id the id of the inspectionPlanDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-plans/{id}")
    public ResponseEntity<Void> deleteInspectionPlan(@PathVariable Long id) {
        log.debug("REST request to delete InspectionPlan : {}", id);
        inspectionPlanService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

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
import org.tamisemi.iftmis.service.InspectionActivityQuarterService;
import org.tamisemi.iftmis.service.dto.InspectionActivityQuarterDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionActivityQuarter}.
 */
@RestController
@RequestMapping("/api")
public class InspectionActivityQuarterResource {
    private final Logger log = LoggerFactory.getLogger(InspectionActivityQuarterResource.class);

    private static final String ENTITY_NAME = "inspectionActivityQuarter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionActivityQuarterService inspectionActivityQuarterService;

    public InspectionActivityQuarterResource(InspectionActivityQuarterService inspectionActivityQuarterService) {
        this.inspectionActivityQuarterService = inspectionActivityQuarterService;
    }

    /**
     * {@code POST  /inspection-activity-quarters} : Create a new inspectionActivityQuarter.
     *
     * @param inspectionActivityQuarterDTO the inspectionActivityQuarterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionActivityQuarterDTO, or with status {@code 400 (Bad Request)} if the inspectionActivityQuarter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-activity-quarters")
    public ResponseEntity<InspectionActivityQuarterDTO> createInspectionActivityQuarter(
        @Valid @RequestBody InspectionActivityQuarterDTO inspectionActivityQuarterDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save InspectionActivityQuarter : {}", inspectionActivityQuarterDTO);
        if (inspectionActivityQuarterDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionActivityQuarter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionActivityQuarterDTO result = inspectionActivityQuarterService.save(inspectionActivityQuarterDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-activity-quarters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-activity-quarters} : Updates an existing inspectionActivityQuarter.
     *
     * @param inspectionActivityQuarterDTO the inspectionActivityQuarterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionActivityQuarterDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionActivityQuarterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionActivityQuarterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-activity-quarters")
    public ResponseEntity<InspectionActivityQuarterDTO> updateInspectionActivityQuarter(
        @Valid @RequestBody InspectionActivityQuarterDTO inspectionActivityQuarterDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update InspectionActivityQuarter : {}", inspectionActivityQuarterDTO);
        if (inspectionActivityQuarterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionActivityQuarterDTO result = inspectionActivityQuarterService.save(inspectionActivityQuarterDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionActivityQuarterDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code GET  /inspection-activity-quarters} : get all the inspectionActivityQuarters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionActivityQuarters in body.
     */
    @GetMapping("/inspection-activity-quarters")
    public ResponseEntity<List<InspectionActivityQuarterDTO>> getAllInspectionActivityQuarters(Pageable pageable) {
        log.debug("REST request to get a page of InspectionActivityQuarters");
        Page<InspectionActivityQuarterDTO> page = inspectionActivityQuarterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-activity-quarters/:id} : get the "id" inspectionActivityQuarter.
     *
     * @param id the id of the inspectionActivityQuarterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionActivityQuarterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-activity-quarters/{id}")
    public ResponseEntity<InspectionActivityQuarterDTO> getInspectionActivityQuarter(@PathVariable Long id) {
        log.debug("REST request to get InspectionActivityQuarter : {}", id);
        Optional<InspectionActivityQuarterDTO> inspectionActivityQuarterDTO = inspectionActivityQuarterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionActivityQuarterDTO);
    }

    /**
     * {@code DELETE  /inspection-activity-quarters/:id} : delete the "id" inspectionActivityQuarter.
     *
     * @param id the id of the inspectionActivityQuarterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-activity-quarters/{id}")
    public ResponseEntity<Void> deleteInspectionActivityQuarter(@PathVariable Long id) {
        log.debug("REST request to delete InspectionActivityQuarter : {}", id);
        inspectionActivityQuarterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

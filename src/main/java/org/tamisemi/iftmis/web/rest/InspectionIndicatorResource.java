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
import org.tamisemi.iftmis.service.InspectionIndicatorService;
import org.tamisemi.iftmis.service.dto.InspectionIndicatorDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionIndicator}.
 */
@RestController
@RequestMapping("/api")
public class InspectionIndicatorResource {
    private final Logger log = LoggerFactory.getLogger(InspectionIndicatorResource.class);

    private static final String ENTITY_NAME = "inspectionIndicator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionIndicatorService inspectionIndicatorService;

    public InspectionIndicatorResource(InspectionIndicatorService inspectionIndicatorService) {
        this.inspectionIndicatorService = inspectionIndicatorService;
    }

    /**
     * {@code POST  /inspection-indicators} : Create a new inspectionIndicator.
     *
     * @param inspectionIndicatorDTO the inspectionIndicatorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionIndicatorDTO, or with status {@code 400 (Bad Request)} if the inspectionIndicator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-indicators")
    public ResponseEntity<InspectionIndicatorDTO> createInspectionIndicator(
        @Valid @RequestBody InspectionIndicatorDTO inspectionIndicatorDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save InspectionIndicator : {}", inspectionIndicatorDTO);
        if (inspectionIndicatorDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionIndicator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionIndicatorDTO result = inspectionIndicatorService.save(inspectionIndicatorDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-indicators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-indicators} : Updates an existing inspectionIndicator.
     *
     * @param inspectionIndicatorDTO the inspectionIndicatorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionIndicatorDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionIndicatorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionIndicatorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-indicators")
    public ResponseEntity<InspectionIndicatorDTO> updateInspectionIndicator(
        @Valid @RequestBody InspectionIndicatorDTO inspectionIndicatorDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update InspectionIndicator : {}", inspectionIndicatorDTO);
        if (inspectionIndicatorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionIndicatorDTO result = inspectionIndicatorService.save(inspectionIndicatorDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionIndicatorDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-indicators} : get all the inspectionIndicators.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionIndicators in body.
     */
    @GetMapping("/inspection-indicators")
    public ResponseEntity<List<InspectionIndicatorDTO>> getAllInspectionIndicators(Pageable pageable) {
        log.debug("REST request to get a page of InspectionIndicators");
        Page<InspectionIndicatorDTO> page = inspectionIndicatorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-indicators/:id} : get the "id" inspectionIndicator.
     *
     * @param id the id of the inspectionIndicatorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionIndicatorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-indicators/{id}")
    public ResponseEntity<InspectionIndicatorDTO> getInspectionIndicator(@PathVariable Long id) {
        log.debug("REST request to get InspectionIndicator : {}", id);
        Optional<InspectionIndicatorDTO> inspectionIndicatorDTO = inspectionIndicatorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionIndicatorDTO);
    }

    /**
     * {@code GET  /inspection-indicators/by-inspection-sub-area/:id} : get the "id" inspectionSubArea.
     *
     * @param id the id of the inspection sun area to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionIndicators with procedures.
     */
    @GetMapping("/inspection-indicators/by-inspection-sub-area/{id}")
    public ResponseEntity<List<InspectionIndicatorDTO>> getByInspectionSubArea(@PathVariable Long id) {
        log.debug("REST request to get InspectionIndicator : {}", id);
        List<InspectionIndicatorDTO> inspectionIndicators = inspectionIndicatorService.findByInspectionSubArea(id);
        return ResponseEntity.ok(inspectionIndicators);
    }

    /**
     * {@code DELETE  /inspection-indicators/:id} : delete the "id" inspectionIndicator.
     *
     * @param id the id of the inspectionIndicatorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-indicators/{id}")
    public ResponseEntity<Void> deleteInspectionIndicator(@PathVariable Long id) {
        log.debug("REST request to delete InspectionIndicator : {}", id);
        inspectionIndicatorService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

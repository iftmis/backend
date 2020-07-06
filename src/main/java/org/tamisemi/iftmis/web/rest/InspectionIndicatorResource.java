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
import org.tamisemi.iftmis.domain.InspectionIndicator;
import org.tamisemi.iftmis.repository.InspectionIndicatorRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionIndicator}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InspectionIndicatorResource {
    private final Logger log = LoggerFactory.getLogger(InspectionIndicatorResource.class);

    private static final String ENTITY_NAME = "inspectionIndicator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionIndicatorRepository inspectionIndicatorRepository;

    public InspectionIndicatorResource(InspectionIndicatorRepository inspectionIndicatorRepository) {
        this.inspectionIndicatorRepository = inspectionIndicatorRepository;
    }

    /**
     * {@code POST  /inspection-indicators} : Create a new inspectionIndicator.
     *
     * @param inspectionIndicator the inspectionIndicator to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionIndicator, or with status {@code 400 (Bad Request)} if the inspectionIndicator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-indicators")
    public ResponseEntity<InspectionIndicator> createInspectionIndicator(@Valid @RequestBody InspectionIndicator inspectionIndicator)
        throws URISyntaxException {
        log.debug("REST request to save InspectionIndicator : {}", inspectionIndicator);
        if (inspectionIndicator.getId() != null) {
            throw new BadRequestAlertException("A new inspectionIndicator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionIndicator result = inspectionIndicatorRepository.save(inspectionIndicator);
        return ResponseEntity
            .created(new URI("/api/inspection-indicators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-indicators} : Updates an existing inspectionIndicator.
     *
     * @param inspectionIndicator the inspectionIndicator to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionIndicator,
     * or with status {@code 400 (Bad Request)} if the inspectionIndicator is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionIndicator couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-indicators")
    public ResponseEntity<InspectionIndicator> updateInspectionIndicator(@Valid @RequestBody InspectionIndicator inspectionIndicator)
        throws URISyntaxException {
        log.debug("REST request to update InspectionIndicator : {}", inspectionIndicator);
        if (inspectionIndicator.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionIndicator result = inspectionIndicatorRepository.save(inspectionIndicator);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionIndicator.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-indicators} : get all the inspectionIndicators.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionIndicators in body.
     */
    @GetMapping("/inspection-indicators")
    public List<InspectionIndicator> getAllInspectionIndicators() {
        log.debug("REST request to get all InspectionIndicators");
        return inspectionIndicatorRepository.findAll();
    }

    /**
     * {@code GET  /inspection-indicators/:id} : get the "id" inspectionIndicator.
     *
     * @param id the id of the inspectionIndicator to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionIndicator, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-indicators/{id}")
    public ResponseEntity<InspectionIndicator> getInspectionIndicator(@PathVariable Long id) {
        log.debug("REST request to get InspectionIndicator : {}", id);
        Optional<InspectionIndicator> inspectionIndicator = inspectionIndicatorRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inspectionIndicator);
    }

    /**
     * {@code DELETE  /inspection-indicators/:id} : delete the "id" inspectionIndicator.
     *
     * @param id the id of the inspectionIndicator to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-indicators/{id}")
    public ResponseEntity<Void> deleteInspectionIndicator(@PathVariable Long id) {
        log.debug("REST request to delete InspectionIndicator : {}", id);
        inspectionIndicatorRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

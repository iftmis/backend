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
import org.tamisemi.iftmis.domain.InspectionFinding;
import org.tamisemi.iftmis.repository.InspectionFindingRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionFinding}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InspectionFindingResource {
    private final Logger log = LoggerFactory.getLogger(InspectionFindingResource.class);

    private static final String ENTITY_NAME = "inspectionFinding";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionFindingRepository inspectionFindingRepository;

    public InspectionFindingResource(InspectionFindingRepository inspectionFindingRepository) {
        this.inspectionFindingRepository = inspectionFindingRepository;
    }

    /**
     * {@code POST  /inspection-findings} : Create a new inspectionFinding.
     *
     * @param inspectionFinding the inspectionFinding to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionFinding, or with status {@code 400 (Bad Request)} if the inspectionFinding has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-findings")
    public ResponseEntity<InspectionFinding> createInspectionFinding(@Valid @RequestBody InspectionFinding inspectionFinding)
        throws URISyntaxException {
        log.debug("REST request to save InspectionFinding : {}", inspectionFinding);
        if (inspectionFinding.getId() != null) {
            throw new BadRequestAlertException("A new inspectionFinding cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionFinding result = inspectionFindingRepository.save(inspectionFinding);
        return ResponseEntity
            .created(new URI("/api/inspection-findings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-findings} : Updates an existing inspectionFinding.
     *
     * @param inspectionFinding the inspectionFinding to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionFinding,
     * or with status {@code 400 (Bad Request)} if the inspectionFinding is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionFinding couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-findings")
    public ResponseEntity<InspectionFinding> updateInspectionFinding(@Valid @RequestBody InspectionFinding inspectionFinding)
        throws URISyntaxException {
        log.debug("REST request to update InspectionFinding : {}", inspectionFinding);
        if (inspectionFinding.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionFinding result = inspectionFindingRepository.save(inspectionFinding);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionFinding.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-findings} : get all the inspectionFindings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionFindings in body.
     */
    @GetMapping("/inspection-findings")
    public List<InspectionFinding> getAllInspectionFindings() {
        log.debug("REST request to get all InspectionFindings");
        return inspectionFindingRepository.findAll();
    }

    /**
     * {@code GET  /inspection-findings/:id} : get the "id" inspectionFinding.
     *
     * @param id the id of the inspectionFinding to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionFinding, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-findings/{id}")
    public ResponseEntity<InspectionFinding> getInspectionFinding(@PathVariable Long id) {
        log.debug("REST request to get InspectionFinding : {}", id);
        Optional<InspectionFinding> inspectionFinding = inspectionFindingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inspectionFinding);
    }

    /**
     * {@code DELETE  /inspection-findings/:id} : delete the "id" inspectionFinding.
     *
     * @param id the id of the inspectionFinding to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-findings/{id}")
    public ResponseEntity<Void> deleteInspectionFinding(@PathVariable Long id) {
        log.debug("REST request to delete InspectionFinding : {}", id);
        inspectionFindingRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

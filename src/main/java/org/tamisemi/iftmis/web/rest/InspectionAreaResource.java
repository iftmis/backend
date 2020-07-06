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
import org.tamisemi.iftmis.domain.InspectionArea;
import org.tamisemi.iftmis.repository.InspectionAreaRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionArea}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InspectionAreaResource {
    private final Logger log = LoggerFactory.getLogger(InspectionAreaResource.class);

    private static final String ENTITY_NAME = "inspectionArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionAreaRepository inspectionAreaRepository;

    public InspectionAreaResource(InspectionAreaRepository inspectionAreaRepository) {
        this.inspectionAreaRepository = inspectionAreaRepository;
    }

    /**
     * {@code POST  /inspection-areas} : Create a new inspectionArea.
     *
     * @param inspectionArea the inspectionArea to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionArea, or with status {@code 400 (Bad Request)} if the inspectionArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-areas")
    public ResponseEntity<InspectionArea> createInspectionArea(@Valid @RequestBody InspectionArea inspectionArea)
        throws URISyntaxException {
        log.debug("REST request to save InspectionArea : {}", inspectionArea);
        if (inspectionArea.getId() != null) {
            throw new BadRequestAlertException("A new inspectionArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionArea result = inspectionAreaRepository.save(inspectionArea);
        return ResponseEntity
            .created(new URI("/api/inspection-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-areas} : Updates an existing inspectionArea.
     *
     * @param inspectionArea the inspectionArea to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionArea,
     * or with status {@code 400 (Bad Request)} if the inspectionArea is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionArea couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-areas")
    public ResponseEntity<InspectionArea> updateInspectionArea(@Valid @RequestBody InspectionArea inspectionArea)
        throws URISyntaxException {
        log.debug("REST request to update InspectionArea : {}", inspectionArea);
        if (inspectionArea.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionArea result = inspectionAreaRepository.save(inspectionArea);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionArea.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-areas} : get all the inspectionAreas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionAreas in body.
     */
    @GetMapping("/inspection-areas")
    public List<InspectionArea> getAllInspectionAreas() {
        log.debug("REST request to get all InspectionAreas");
        return inspectionAreaRepository.findAll();
    }

    /**
     * {@code GET  /inspection-areas/:id} : get the "id" inspectionArea.
     *
     * @param id the id of the inspectionArea to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionArea, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-areas/{id}")
    public ResponseEntity<InspectionArea> getInspectionArea(@PathVariable Long id) {
        log.debug("REST request to get InspectionArea : {}", id);
        Optional<InspectionArea> inspectionArea = inspectionAreaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inspectionArea);
    }

    /**
     * {@code DELETE  /inspection-areas/:id} : delete the "id" inspectionArea.
     *
     * @param id the id of the inspectionArea to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-areas/{id}")
    public ResponseEntity<Void> deleteInspectionArea(@PathVariable Long id) {
        log.debug("REST request to delete InspectionArea : {}", id);
        inspectionAreaRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

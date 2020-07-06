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
import org.tamisemi.iftmis.domain.InspectionObjective;
import org.tamisemi.iftmis.repository.InspectionObjectiveRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionObjective}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InspectionObjectiveResource {
    private final Logger log = LoggerFactory.getLogger(InspectionObjectiveResource.class);

    private static final String ENTITY_NAME = "inspectionObjective";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionObjectiveRepository inspectionObjectiveRepository;

    public InspectionObjectiveResource(InspectionObjectiveRepository inspectionObjectiveRepository) {
        this.inspectionObjectiveRepository = inspectionObjectiveRepository;
    }

    /**
     * {@code POST  /inspection-objectives} : Create a new inspectionObjective.
     *
     * @param inspectionObjective the inspectionObjective to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionObjective, or with status {@code 400 (Bad Request)} if the inspectionObjective has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-objectives")
    public ResponseEntity<InspectionObjective> createInspectionObjective(@Valid @RequestBody InspectionObjective inspectionObjective)
        throws URISyntaxException {
        log.debug("REST request to save InspectionObjective : {}", inspectionObjective);
        if (inspectionObjective.getId() != null) {
            throw new BadRequestAlertException("A new inspectionObjective cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionObjective result = inspectionObjectiveRepository.save(inspectionObjective);
        return ResponseEntity
            .created(new URI("/api/inspection-objectives/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-objectives} : Updates an existing inspectionObjective.
     *
     * @param inspectionObjective the inspectionObjective to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionObjective,
     * or with status {@code 400 (Bad Request)} if the inspectionObjective is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionObjective couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-objectives")
    public ResponseEntity<InspectionObjective> updateInspectionObjective(@Valid @RequestBody InspectionObjective inspectionObjective)
        throws URISyntaxException {
        log.debug("REST request to update InspectionObjective : {}", inspectionObjective);
        if (inspectionObjective.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionObjective result = inspectionObjectiveRepository.save(inspectionObjective);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionObjective.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-objectives} : get all the inspectionObjectives.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionObjectives in body.
     */
    @GetMapping("/inspection-objectives")
    public List<InspectionObjective> getAllInspectionObjectives() {
        log.debug("REST request to get all InspectionObjectives");
        return inspectionObjectiveRepository.findAll();
    }

    /**
     * {@code GET  /inspection-objectives/:id} : get the "id" inspectionObjective.
     *
     * @param id the id of the inspectionObjective to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionObjective, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-objectives/{id}")
    public ResponseEntity<InspectionObjective> getInspectionObjective(@PathVariable Long id) {
        log.debug("REST request to get InspectionObjective : {}", id);
        Optional<InspectionObjective> inspectionObjective = inspectionObjectiveRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inspectionObjective);
    }

    /**
     * {@code DELETE  /inspection-objectives/:id} : delete the "id" inspectionObjective.
     *
     * @param id the id of the inspectionObjective to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-objectives/{id}")
    public ResponseEntity<Void> deleteInspectionObjective(@PathVariable Long id) {
        log.debug("REST request to delete InspectionObjective : {}", id);
        inspectionObjectiveRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

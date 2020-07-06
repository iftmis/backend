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
import org.tamisemi.iftmis.domain.InspectionProcedure;
import org.tamisemi.iftmis.repository.InspectionProcedureRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionProcedure}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InspectionProcedureResource {
    private final Logger log = LoggerFactory.getLogger(InspectionProcedureResource.class);

    private static final String ENTITY_NAME = "inspectionProcedure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionProcedureRepository inspectionProcedureRepository;

    public InspectionProcedureResource(InspectionProcedureRepository inspectionProcedureRepository) {
        this.inspectionProcedureRepository = inspectionProcedureRepository;
    }

    /**
     * {@code POST  /inspection-procedures} : Create a new inspectionProcedure.
     *
     * @param inspectionProcedure the inspectionProcedure to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionProcedure, or with status {@code 400 (Bad Request)} if the inspectionProcedure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-procedures")
    public ResponseEntity<InspectionProcedure> createInspectionProcedure(@Valid @RequestBody InspectionProcedure inspectionProcedure)
        throws URISyntaxException {
        log.debug("REST request to save InspectionProcedure : {}", inspectionProcedure);
        if (inspectionProcedure.getId() != null) {
            throw new BadRequestAlertException("A new inspectionProcedure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionProcedure result = inspectionProcedureRepository.save(inspectionProcedure);
        return ResponseEntity
            .created(new URI("/api/inspection-procedures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-procedures} : Updates an existing inspectionProcedure.
     *
     * @param inspectionProcedure the inspectionProcedure to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionProcedure,
     * or with status {@code 400 (Bad Request)} if the inspectionProcedure is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionProcedure couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-procedures")
    public ResponseEntity<InspectionProcedure> updateInspectionProcedure(@Valid @RequestBody InspectionProcedure inspectionProcedure)
        throws URISyntaxException {
        log.debug("REST request to update InspectionProcedure : {}", inspectionProcedure);
        if (inspectionProcedure.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionProcedure result = inspectionProcedureRepository.save(inspectionProcedure);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionProcedure.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-procedures} : get all the inspectionProcedures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionProcedures in body.
     */
    @GetMapping("/inspection-procedures")
    public List<InspectionProcedure> getAllInspectionProcedures() {
        log.debug("REST request to get all InspectionProcedures");
        return inspectionProcedureRepository.findAll();
    }

    /**
     * {@code GET  /inspection-procedures/:id} : get the "id" inspectionProcedure.
     *
     * @param id the id of the inspectionProcedure to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionProcedure, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-procedures/{id}")
    public ResponseEntity<InspectionProcedure> getInspectionProcedure(@PathVariable Long id) {
        log.debug("REST request to get InspectionProcedure : {}", id);
        Optional<InspectionProcedure> inspectionProcedure = inspectionProcedureRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inspectionProcedure);
    }

    /**
     * {@code DELETE  /inspection-procedures/:id} : delete the "id" inspectionProcedure.
     *
     * @param id the id of the inspectionProcedure to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-procedures/{id}")
    public ResponseEntity<Void> deleteInspectionProcedure(@PathVariable Long id) {
        log.debug("REST request to delete InspectionProcedure : {}", id);
        inspectionProcedureRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

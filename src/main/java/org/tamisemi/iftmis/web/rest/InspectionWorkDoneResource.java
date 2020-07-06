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
import org.tamisemi.iftmis.domain.InspectionWorkDone;
import org.tamisemi.iftmis.repository.InspectionWorkDoneRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionWorkDone}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InspectionWorkDoneResource {
    private final Logger log = LoggerFactory.getLogger(InspectionWorkDoneResource.class);

    private static final String ENTITY_NAME = "inspectionWorkDone";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionWorkDoneRepository inspectionWorkDoneRepository;

    public InspectionWorkDoneResource(InspectionWorkDoneRepository inspectionWorkDoneRepository) {
        this.inspectionWorkDoneRepository = inspectionWorkDoneRepository;
    }

    /**
     * {@code POST  /inspection-work-dones} : Create a new inspectionWorkDone.
     *
     * @param inspectionWorkDone the inspectionWorkDone to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionWorkDone, or with status {@code 400 (Bad Request)} if the inspectionWorkDone has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-work-dones")
    public ResponseEntity<InspectionWorkDone> createInspectionWorkDone(@Valid @RequestBody InspectionWorkDone inspectionWorkDone)
        throws URISyntaxException {
        log.debug("REST request to save InspectionWorkDone : {}", inspectionWorkDone);
        if (inspectionWorkDone.getId() != null) {
            throw new BadRequestAlertException("A new inspectionWorkDone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionWorkDone result = inspectionWorkDoneRepository.save(inspectionWorkDone);
        return ResponseEntity
            .created(new URI("/api/inspection-work-dones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-work-dones} : Updates an existing inspectionWorkDone.
     *
     * @param inspectionWorkDone the inspectionWorkDone to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionWorkDone,
     * or with status {@code 400 (Bad Request)} if the inspectionWorkDone is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionWorkDone couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-work-dones")
    public ResponseEntity<InspectionWorkDone> updateInspectionWorkDone(@Valid @RequestBody InspectionWorkDone inspectionWorkDone)
        throws URISyntaxException {
        log.debug("REST request to update InspectionWorkDone : {}", inspectionWorkDone);
        if (inspectionWorkDone.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionWorkDone result = inspectionWorkDoneRepository.save(inspectionWorkDone);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionWorkDone.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-work-dones} : get all the inspectionWorkDones.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionWorkDones in body.
     */
    @GetMapping("/inspection-work-dones")
    public List<InspectionWorkDone> getAllInspectionWorkDones() {
        log.debug("REST request to get all InspectionWorkDones");
        return inspectionWorkDoneRepository.findAll();
    }

    /**
     * {@code GET  /inspection-work-dones/:id} : get the "id" inspectionWorkDone.
     *
     * @param id the id of the inspectionWorkDone to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionWorkDone, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-work-dones/{id}")
    public ResponseEntity<InspectionWorkDone> getInspectionWorkDone(@PathVariable Long id) {
        log.debug("REST request to get InspectionWorkDone : {}", id);
        Optional<InspectionWorkDone> inspectionWorkDone = inspectionWorkDoneRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inspectionWorkDone);
    }

    /**
     * {@code DELETE  /inspection-work-dones/:id} : delete the "id" inspectionWorkDone.
     *
     * @param id the id of the inspectionWorkDone to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-work-dones/{id}")
    public ResponseEntity<Void> deleteInspectionWorkDone(@PathVariable Long id) {
        log.debug("REST request to delete InspectionWorkDone : {}", id);
        inspectionWorkDoneRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

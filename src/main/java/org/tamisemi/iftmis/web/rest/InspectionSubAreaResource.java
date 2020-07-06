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
import org.tamisemi.iftmis.domain.InspectionSubArea;
import org.tamisemi.iftmis.repository.InspectionSubAreaRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionSubArea}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InspectionSubAreaResource {
    private final Logger log = LoggerFactory.getLogger(InspectionSubAreaResource.class);

    private static final String ENTITY_NAME = "inspectionSubArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionSubAreaRepository inspectionSubAreaRepository;

    public InspectionSubAreaResource(InspectionSubAreaRepository inspectionSubAreaRepository) {
        this.inspectionSubAreaRepository = inspectionSubAreaRepository;
    }

    /**
     * {@code POST  /inspection-sub-areas} : Create a new inspectionSubArea.
     *
     * @param inspectionSubArea the inspectionSubArea to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionSubArea, or with status {@code 400 (Bad Request)} if the inspectionSubArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-sub-areas")
    public ResponseEntity<InspectionSubArea> createInspectionSubArea(@Valid @RequestBody InspectionSubArea inspectionSubArea)
        throws URISyntaxException {
        log.debug("REST request to save InspectionSubArea : {}", inspectionSubArea);
        if (inspectionSubArea.getId() != null) {
            throw new BadRequestAlertException("A new inspectionSubArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionSubArea result = inspectionSubAreaRepository.save(inspectionSubArea);
        return ResponseEntity
            .created(new URI("/api/inspection-sub-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-sub-areas} : Updates an existing inspectionSubArea.
     *
     * @param inspectionSubArea the inspectionSubArea to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionSubArea,
     * or with status {@code 400 (Bad Request)} if the inspectionSubArea is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionSubArea couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-sub-areas")
    public ResponseEntity<InspectionSubArea> updateInspectionSubArea(@Valid @RequestBody InspectionSubArea inspectionSubArea)
        throws URISyntaxException {
        log.debug("REST request to update InspectionSubArea : {}", inspectionSubArea);
        if (inspectionSubArea.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionSubArea result = inspectionSubAreaRepository.save(inspectionSubArea);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionSubArea.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-sub-areas} : get all the inspectionSubAreas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionSubAreas in body.
     */
    @GetMapping("/inspection-sub-areas")
    public List<InspectionSubArea> getAllInspectionSubAreas() {
        log.debug("REST request to get all InspectionSubAreas");
        return inspectionSubAreaRepository.findAll();
    }

    /**
     * {@code GET  /inspection-sub-areas/:id} : get the "id" inspectionSubArea.
     *
     * @param id the id of the inspectionSubArea to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionSubArea, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-sub-areas/{id}")
    public ResponseEntity<InspectionSubArea> getInspectionSubArea(@PathVariable Long id) {
        log.debug("REST request to get InspectionSubArea : {}", id);
        Optional<InspectionSubArea> inspectionSubArea = inspectionSubAreaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inspectionSubArea);
    }

    /**
     * {@code DELETE  /inspection-sub-areas/:id} : delete the "id" inspectionSubArea.
     *
     * @param id the id of the inspectionSubArea to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-sub-areas/{id}")
    public ResponseEntity<Void> deleteInspectionSubArea(@PathVariable Long id) {
        log.debug("REST request to delete InspectionSubArea : {}", id);
        inspectionSubAreaRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

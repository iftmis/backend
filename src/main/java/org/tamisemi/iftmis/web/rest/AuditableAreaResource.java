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
import org.tamisemi.iftmis.domain.AuditableArea;
import org.tamisemi.iftmis.repository.AuditableAreaRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.AuditableArea}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AuditableAreaResource {
    private final Logger log = LoggerFactory.getLogger(AuditableAreaResource.class);

    private static final String ENTITY_NAME = "auditableArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuditableAreaRepository auditableAreaRepository;

    public AuditableAreaResource(AuditableAreaRepository auditableAreaRepository) {
        this.auditableAreaRepository = auditableAreaRepository;
    }

    /**
     * {@code POST  /auditable-areas} : Create a new auditableArea.
     *
     * @param auditableArea the auditableArea to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auditableArea, or with status {@code 400 (Bad Request)} if the auditableArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/auditable-areas")
    public ResponseEntity<AuditableArea> createAuditableArea(@Valid @RequestBody AuditableArea auditableArea) throws URISyntaxException {
        log.debug("REST request to save AuditableArea : {}", auditableArea);
        if (auditableArea.getId() != null) {
            throw new BadRequestAlertException("A new auditableArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuditableArea result = auditableAreaRepository.save(auditableArea);
        return ResponseEntity
            .created(new URI("/api/auditable-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /auditable-areas} : Updates an existing auditableArea.
     *
     * @param auditableArea the auditableArea to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditableArea,
     * or with status {@code 400 (Bad Request)} if the auditableArea is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auditableArea couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/auditable-areas")
    public ResponseEntity<AuditableArea> updateAuditableArea(@Valid @RequestBody AuditableArea auditableArea) throws URISyntaxException {
        log.debug("REST request to update AuditableArea : {}", auditableArea);
        if (auditableArea.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuditableArea result = auditableAreaRepository.save(auditableArea);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, auditableArea.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /auditable-areas} : get all the auditableAreas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of auditableAreas in body.
     */
    @GetMapping("/auditable-areas")
    public List<AuditableArea> getAllAuditableAreas() {
        log.debug("REST request to get all AuditableAreas");
        return auditableAreaRepository.findAll();
    }

    /**
     * {@code GET  /auditable-areas/:id} : get the "id" auditableArea.
     *
     * @param id the id of the auditableArea to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auditableArea, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/auditable-areas/{id}")
    public ResponseEntity<AuditableArea> getAuditableArea(@PathVariable Long id) {
        log.debug("REST request to get AuditableArea : {}", id);
        Optional<AuditableArea> auditableArea = auditableAreaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(auditableArea);
    }

    /**
     * {@code DELETE  /auditable-areas/:id} : delete the "id" auditableArea.
     *
     * @param id the id of the auditableArea to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/auditable-areas/{id}")
    public ResponseEntity<Void> deleteAuditableArea(@PathVariable Long id) {
        log.debug("REST request to delete AuditableArea : {}", id);
        auditableAreaRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

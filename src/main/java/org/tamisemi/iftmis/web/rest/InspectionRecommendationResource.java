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
import org.tamisemi.iftmis.domain.InspectionRecommendation;
import org.tamisemi.iftmis.repository.InspectionRecommendationRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionRecommendation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InspectionRecommendationResource {
    private final Logger log = LoggerFactory.getLogger(InspectionRecommendationResource.class);

    private static final String ENTITY_NAME = "inspectionRecommendation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionRecommendationRepository inspectionRecommendationRepository;

    public InspectionRecommendationResource(InspectionRecommendationRepository inspectionRecommendationRepository) {
        this.inspectionRecommendationRepository = inspectionRecommendationRepository;
    }

    /**
     * {@code POST  /inspection-recommendations} : Create a new inspectionRecommendation.
     *
     * @param inspectionRecommendation the inspectionRecommendation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionRecommendation, or with status {@code 400 (Bad Request)} if the inspectionRecommendation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-recommendations")
    public ResponseEntity<InspectionRecommendation> createInspectionRecommendation(
        @Valid @RequestBody InspectionRecommendation inspectionRecommendation
    )
        throws URISyntaxException {
        log.debug("REST request to save InspectionRecommendation : {}", inspectionRecommendation);
        if (inspectionRecommendation.getId() != null) {
            throw new BadRequestAlertException("A new inspectionRecommendation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionRecommendation result = inspectionRecommendationRepository.save(inspectionRecommendation);
        return ResponseEntity
            .created(new URI("/api/inspection-recommendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-recommendations} : Updates an existing inspectionRecommendation.
     *
     * @param inspectionRecommendation the inspectionRecommendation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionRecommendation,
     * or with status {@code 400 (Bad Request)} if the inspectionRecommendation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionRecommendation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-recommendations")
    public ResponseEntity<InspectionRecommendation> updateInspectionRecommendation(
        @Valid @RequestBody InspectionRecommendation inspectionRecommendation
    )
        throws URISyntaxException {
        log.debug("REST request to update InspectionRecommendation : {}", inspectionRecommendation);
        if (inspectionRecommendation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionRecommendation result = inspectionRecommendationRepository.save(inspectionRecommendation);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionRecommendation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-recommendations} : get all the inspectionRecommendations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionRecommendations in body.
     */
    @GetMapping("/inspection-recommendations")
    public List<InspectionRecommendation> getAllInspectionRecommendations() {
        log.debug("REST request to get all InspectionRecommendations");
        return inspectionRecommendationRepository.findAll();
    }

    /**
     * {@code GET  /inspection-recommendations/:id} : get the "id" inspectionRecommendation.
     *
     * @param id the id of the inspectionRecommendation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionRecommendation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-recommendations/{id}")
    public ResponseEntity<InspectionRecommendation> getInspectionRecommendation(@PathVariable Long id) {
        log.debug("REST request to get InspectionRecommendation : {}", id);
        Optional<InspectionRecommendation> inspectionRecommendation = inspectionRecommendationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inspectionRecommendation);
    }

    /**
     * {@code DELETE  /inspection-recommendations/:id} : delete the "id" inspectionRecommendation.
     *
     * @param id the id of the inspectionRecommendation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-recommendations/{id}")
    public ResponseEntity<Void> deleteInspectionRecommendation(@PathVariable Long id) {
        log.debug("REST request to delete InspectionRecommendation : {}", id);
        inspectionRecommendationRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

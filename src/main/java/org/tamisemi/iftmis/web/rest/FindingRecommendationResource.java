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
import org.tamisemi.iftmis.domain.FindingRecommendation;
import org.tamisemi.iftmis.repository.FindingRecommendationRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FindingRecommendation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FindingRecommendationResource {
    private final Logger log = LoggerFactory.getLogger(FindingRecommendationResource.class);

    private static final String ENTITY_NAME = "findingRecommendation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindingRecommendationRepository findingRecommendationRepository;

    public FindingRecommendationResource(FindingRecommendationRepository findingRecommendationRepository) {
        this.findingRecommendationRepository = findingRecommendationRepository;
    }

    /**
     * {@code POST  /finding-recommendations} : Create a new findingRecommendation.
     *
     * @param findingRecommendation the findingRecommendation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new findingRecommendation, or with status {@code 400 (Bad Request)} if the findingRecommendation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/finding-recommendations")
    public ResponseEntity<FindingRecommendation> createFindingRecommendation(
        @Valid @RequestBody FindingRecommendation findingRecommendation
    )
        throws URISyntaxException {
        log.debug("REST request to save FindingRecommendation : {}", findingRecommendation);
        if (findingRecommendation.getId() != null) {
            throw new BadRequestAlertException("A new findingRecommendation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindingRecommendation result = findingRecommendationRepository.save(findingRecommendation);
        return ResponseEntity
            .created(new URI("/api/finding-recommendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /finding-recommendations} : Updates an existing findingRecommendation.
     *
     * @param findingRecommendation the findingRecommendation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated findingRecommendation,
     * or with status {@code 400 (Bad Request)} if the findingRecommendation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the findingRecommendation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/finding-recommendations")
    public ResponseEntity<FindingRecommendation> updateFindingRecommendation(
        @Valid @RequestBody FindingRecommendation findingRecommendation
    )
        throws URISyntaxException {
        log.debug("REST request to update FindingRecommendation : {}", findingRecommendation);
        if (findingRecommendation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindingRecommendation result = findingRecommendationRepository.save(findingRecommendation);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, findingRecommendation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /finding-recommendations} : get all the findingRecommendations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of findingRecommendations in body.
     */
    @GetMapping("/finding-recommendations")
    public List<FindingRecommendation> getAllFindingRecommendations() {
        log.debug("REST request to get all FindingRecommendations");
        return findingRecommendationRepository.findAll();
    }

    /**
     * {@code GET  /finding-recommendations/:id} : get the "id" findingRecommendation.
     *
     * @param id the id of the findingRecommendation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the findingRecommendation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/finding-recommendations/{id}")
    public ResponseEntity<FindingRecommendation> getFindingRecommendation(@PathVariable Long id) {
        log.debug("REST request to get FindingRecommendation : {}", id);
        Optional<FindingRecommendation> findingRecommendation = findingRecommendationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(findingRecommendation);
    }

    /**
     * {@code DELETE  /finding-recommendations/:id} : delete the "id" findingRecommendation.
     *
     * @param id the id of the findingRecommendation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/finding-recommendations/{id}")
    public ResponseEntity<Void> deleteFindingRecommendation(@PathVariable Long id) {
        log.debug("REST request to delete FindingRecommendation : {}", id);
        findingRecommendationRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

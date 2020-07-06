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
import org.tamisemi.iftmis.domain.Finding;
import org.tamisemi.iftmis.repository.FindingRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Finding}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FindingResource {
    private final Logger log = LoggerFactory.getLogger(FindingResource.class);

    private static final String ENTITY_NAME = "finding";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindingRepository findingRepository;

    public FindingResource(FindingRepository findingRepository) {
        this.findingRepository = findingRepository;
    }

    /**
     * {@code POST  /findings} : Create a new finding.
     *
     * @param finding the finding to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new finding, or with status {@code 400 (Bad Request)} if the finding has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/findings")
    public ResponseEntity<Finding> createFinding(@Valid @RequestBody Finding finding) throws URISyntaxException {
        log.debug("REST request to save Finding : {}", finding);
        if (finding.getId() != null) {
            throw new BadRequestAlertException("A new finding cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Finding result = findingRepository.save(finding);
        return ResponseEntity
            .created(new URI("/api/findings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /findings} : Updates an existing finding.
     *
     * @param finding the finding to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated finding,
     * or with status {@code 400 (Bad Request)} if the finding is not valid,
     * or with status {@code 500 (Internal Server Error)} if the finding couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/findings")
    public ResponseEntity<Finding> updateFinding(@Valid @RequestBody Finding finding) throws URISyntaxException {
        log.debug("REST request to update Finding : {}", finding);
        if (finding.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Finding result = findingRepository.save(finding);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, finding.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /findings} : get all the findings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of findings in body.
     */
    @GetMapping("/findings")
    public List<Finding> getAllFindings() {
        log.debug("REST request to get all Findings");
        return findingRepository.findAll();
    }

    /**
     * {@code GET  /findings/:id} : get the "id" finding.
     *
     * @param id the id of the finding to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the finding, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/findings/{id}")
    public ResponseEntity<Finding> getFinding(@PathVariable Long id) {
        log.debug("REST request to get Finding : {}", id);
        Optional<Finding> finding = findingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(finding);
    }

    /**
     * {@code DELETE  /findings/:id} : delete the "id" finding.
     *
     * @param id the id of the finding to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/findings/{id}")
    public ResponseEntity<Void> deleteFinding(@PathVariable Long id) {
        log.debug("REST request to delete Finding : {}", id);
        findingRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

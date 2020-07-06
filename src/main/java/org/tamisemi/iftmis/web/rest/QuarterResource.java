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
import org.tamisemi.iftmis.domain.Quarter;
import org.tamisemi.iftmis.repository.QuarterRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Quarter}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class QuarterResource {
    private final Logger log = LoggerFactory.getLogger(QuarterResource.class);

    private static final String ENTITY_NAME = "quarter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuarterRepository quarterRepository;

    public QuarterResource(QuarterRepository quarterRepository) {
        this.quarterRepository = quarterRepository;
    }

    /**
     * {@code POST  /quarters} : Create a new quarter.
     *
     * @param quarter the quarter to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new quarter, or with status {@code 400 (Bad Request)} if the quarter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/quarters")
    public ResponseEntity<Quarter> createQuarter(@Valid @RequestBody Quarter quarter) throws URISyntaxException {
        log.debug("REST request to save Quarter : {}", quarter);
        if (quarter.getId() != null) {
            throw new BadRequestAlertException("A new quarter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Quarter result = quarterRepository.save(quarter);
        return ResponseEntity
            .created(new URI("/api/quarters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /quarters} : Updates an existing quarter.
     *
     * @param quarter the quarter to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quarter,
     * or with status {@code 400 (Bad Request)} if the quarter is not valid,
     * or with status {@code 500 (Internal Server Error)} if the quarter couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/quarters")
    public ResponseEntity<Quarter> updateQuarter(@Valid @RequestBody Quarter quarter) throws URISyntaxException {
        log.debug("REST request to update Quarter : {}", quarter);
        if (quarter.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Quarter result = quarterRepository.save(quarter);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, quarter.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /quarters} : get all the quarters.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quarters in body.
     */
    @GetMapping("/quarters")
    public List<Quarter> getAllQuarters() {
        log.debug("REST request to get all Quarters");
        return quarterRepository.findAll();
    }

    /**
     * {@code GET  /quarters/:id} : get the "id" quarter.
     *
     * @param id the id of the quarter to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the quarter, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/quarters/{id}")
    public ResponseEntity<Quarter> getQuarter(@PathVariable Long id) {
        log.debug("REST request to get Quarter : {}", id);
        Optional<Quarter> quarter = quarterRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(quarter);
    }

    /**
     * {@code DELETE  /quarters/:id} : delete the "id" quarter.
     *
     * @param id the id of the quarter to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/quarters/{id}")
    public ResponseEntity<Void> deleteQuarter(@PathVariable Long id) {
        log.debug("REST request to delete Quarter : {}", id);
        quarterRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

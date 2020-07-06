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
import org.tamisemi.iftmis.domain.FindingResponse;
import org.tamisemi.iftmis.repository.FindingResponseRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FindingResponse}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FindingResponseResource {
    private final Logger log = LoggerFactory.getLogger(FindingResponseResource.class);

    private static final String ENTITY_NAME = "findingResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindingResponseRepository findingResponseRepository;

    public FindingResponseResource(FindingResponseRepository findingResponseRepository) {
        this.findingResponseRepository = findingResponseRepository;
    }

    /**
     * {@code POST  /finding-responses} : Create a new findingResponse.
     *
     * @param findingResponse the findingResponse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new findingResponse, or with status {@code 400 (Bad Request)} if the findingResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/finding-responses")
    public ResponseEntity<FindingResponse> createFindingResponse(@Valid @RequestBody FindingResponse findingResponse)
        throws URISyntaxException {
        log.debug("REST request to save FindingResponse : {}", findingResponse);
        if (findingResponse.getId() != null) {
            throw new BadRequestAlertException("A new findingResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindingResponse result = findingResponseRepository.save(findingResponse);
        return ResponseEntity
            .created(new URI("/api/finding-responses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /finding-responses} : Updates an existing findingResponse.
     *
     * @param findingResponse the findingResponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated findingResponse,
     * or with status {@code 400 (Bad Request)} if the findingResponse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the findingResponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/finding-responses")
    public ResponseEntity<FindingResponse> updateFindingResponse(@Valid @RequestBody FindingResponse findingResponse)
        throws URISyntaxException {
        log.debug("REST request to update FindingResponse : {}", findingResponse);
        if (findingResponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindingResponse result = findingResponseRepository.save(findingResponse);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, findingResponse.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /finding-responses} : get all the findingResponses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of findingResponses in body.
     */
    @GetMapping("/finding-responses")
    public List<FindingResponse> getAllFindingResponses() {
        log.debug("REST request to get all FindingResponses");
        return findingResponseRepository.findAll();
    }

    /**
     * {@code GET  /finding-responses/:id} : get the "id" findingResponse.
     *
     * @param id the id of the findingResponse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the findingResponse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/finding-responses/{id}")
    public ResponseEntity<FindingResponse> getFindingResponse(@PathVariable Long id) {
        log.debug("REST request to get FindingResponse : {}", id);
        Optional<FindingResponse> findingResponse = findingResponseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(findingResponse);
    }

    /**
     * {@code DELETE  /finding-responses/:id} : delete the "id" findingResponse.
     *
     * @param id the id of the findingResponse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/finding-responses/{id}")
    public ResponseEntity<Void> deleteFindingResponse(@PathVariable Long id) {
        log.debug("REST request to delete FindingResponse : {}", id);
        findingResponseRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

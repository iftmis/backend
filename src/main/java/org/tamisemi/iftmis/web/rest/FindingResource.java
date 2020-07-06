package org.tamisemi.iftmis.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tamisemi.iftmis.service.FindingService;
import org.tamisemi.iftmis.service.dto.FindingDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Finding}.
 */
@RestController
@RequestMapping("/api")
public class FindingResource {
    private final Logger log = LoggerFactory.getLogger(FindingResource.class);

    private static final String ENTITY_NAME = "finding";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindingService findingService;

    public FindingResource(FindingService findingService) {
        this.findingService = findingService;
    }

    /**
     * {@code POST  /findings} : Create a new finding.
     *
     * @param findingDTO the findingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new findingDTO, or with status {@code 400 (Bad Request)} if the finding has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/findings")
    public ResponseEntity<FindingDTO> createFinding(@Valid @RequestBody FindingDTO findingDTO) throws URISyntaxException {
        log.debug("REST request to save Finding : {}", findingDTO);
        if (findingDTO.getId() != null) {
            throw new BadRequestAlertException("A new finding cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindingDTO result = findingService.save(findingDTO);
        return ResponseEntity
            .created(new URI("/api/findings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /findings} : Updates an existing finding.
     *
     * @param findingDTO the findingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated findingDTO,
     * or with status {@code 400 (Bad Request)} if the findingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the findingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/findings")
    public ResponseEntity<FindingDTO> updateFinding(@Valid @RequestBody FindingDTO findingDTO) throws URISyntaxException {
        log.debug("REST request to update Finding : {}", findingDTO);
        if (findingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindingDTO result = findingService.save(findingDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, findingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /findings} : get all the findings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of findings in body.
     */
    @GetMapping("/findings")
    public ResponseEntity<List<FindingDTO>> getAllFindings(Pageable pageable) {
        log.debug("REST request to get a page of Findings");
        Page<FindingDTO> page = findingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /findings/:id} : get the "id" finding.
     *
     * @param id the id of the findingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the findingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/findings/{id}")
    public ResponseEntity<FindingDTO> getFinding(@PathVariable Long id) {
        log.debug("REST request to get Finding : {}", id);
        Optional<FindingDTO> findingDTO = findingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(findingDTO);
    }

    /**
     * {@code DELETE  /findings/:id} : delete the "id" finding.
     *
     * @param id the id of the findingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/findings/{id}")
    public ResponseEntity<Void> deleteFinding(@PathVariable Long id) {
        log.debug("REST request to delete Finding : {}", id);
        findingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

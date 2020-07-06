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
import org.tamisemi.iftmis.service.FindingRecommendationService;
import org.tamisemi.iftmis.service.dto.FindingRecommendationDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FindingRecommendation}.
 */
@RestController
@RequestMapping("/api")
public class FindingRecommendationResource {
    private final Logger log = LoggerFactory.getLogger(FindingRecommendationResource.class);

    private static final String ENTITY_NAME = "findingRecommendation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindingRecommendationService findingRecommendationService;

    public FindingRecommendationResource(FindingRecommendationService findingRecommendationService) {
        this.findingRecommendationService = findingRecommendationService;
    }

    /**
     * {@code POST  /finding-recommendations} : Create a new findingRecommendation.
     *
     * @param findingRecommendationDTO the findingRecommendationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new findingRecommendationDTO, or with status {@code 400 (Bad Request)} if the findingRecommendation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/finding-recommendations")
    public ResponseEntity<FindingRecommendationDTO> createFindingRecommendation(
        @Valid @RequestBody FindingRecommendationDTO findingRecommendationDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save FindingRecommendation : {}", findingRecommendationDTO);
        if (findingRecommendationDTO.getId() != null) {
            throw new BadRequestAlertException("A new findingRecommendation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindingRecommendationDTO result = findingRecommendationService.save(findingRecommendationDTO);
        return ResponseEntity
            .created(new URI("/api/finding-recommendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /finding-recommendations} : Updates an existing findingRecommendation.
     *
     * @param findingRecommendationDTO the findingRecommendationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated findingRecommendationDTO,
     * or with status {@code 400 (Bad Request)} if the findingRecommendationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the findingRecommendationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/finding-recommendations")
    public ResponseEntity<FindingRecommendationDTO> updateFindingRecommendation(
        @Valid @RequestBody FindingRecommendationDTO findingRecommendationDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update FindingRecommendation : {}", findingRecommendationDTO);
        if (findingRecommendationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindingRecommendationDTO result = findingRecommendationService.save(findingRecommendationDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, findingRecommendationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /finding-recommendations} : get all the findingRecommendations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of findingRecommendations in body.
     */
    @GetMapping("/finding-recommendations")
    public ResponseEntity<List<FindingRecommendationDTO>> getAllFindingRecommendations(Pageable pageable) {
        log.debug("REST request to get a page of FindingRecommendations");
        Page<FindingRecommendationDTO> page = findingRecommendationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /finding-recommendations/:id} : get the "id" findingRecommendation.
     *
     * @param id the id of the findingRecommendationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the findingRecommendationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/finding-recommendations/{id}")
    public ResponseEntity<FindingRecommendationDTO> getFindingRecommendation(@PathVariable Long id) {
        log.debug("REST request to get FindingRecommendation : {}", id);
        Optional<FindingRecommendationDTO> findingRecommendationDTO = findingRecommendationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(findingRecommendationDTO);
    }

    /**
     * {@code DELETE  /finding-recommendations/:id} : delete the "id" findingRecommendation.
     *
     * @param id the id of the findingRecommendationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/finding-recommendations/{id}")
    public ResponseEntity<Void> deleteFindingRecommendation(@PathVariable Long id) {
        log.debug("REST request to delete FindingRecommendation : {}", id);
        findingRecommendationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

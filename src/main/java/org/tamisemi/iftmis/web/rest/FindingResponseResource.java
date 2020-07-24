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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tamisemi.iftmis.config.Constants;
import org.tamisemi.iftmis.service.FindingResponseService;
import org.tamisemi.iftmis.service.dto.FindingResponseDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FindingResponse}.
 */
@RestController
@RequestMapping("/api")
public class FindingResponseResource {
    private final Logger log = LoggerFactory.getLogger(FindingResponseResource.class);

    private static final String ENTITY_NAME = "findingResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindingResponseService findingResponseService;

    public FindingResponseResource(FindingResponseService findingResponseService) {
        this.findingResponseService = findingResponseService;
    }

    /**
     * {@code POST  /finding-responses} : Create a new findingResponse.
     *
     * @param findingResponseDTO the findingResponseDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new findingResponseDTO, or with status {@code 400 (Bad Request)} if the findingResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/finding-responses")
    public ResponseEntity<FindingResponseDTO> createFindingResponse(@Valid @RequestBody FindingResponseDTO findingResponseDTO)
        throws URISyntaxException {
        log.debug("REST request to save FindingResponse : {}", findingResponseDTO);
        if (findingResponseDTO.getId() != null) {
            throw new BadRequestAlertException("A new findingResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindingResponseDTO result = findingResponseService.save(findingResponseDTO);
        return ResponseEntity
            .created(new URI("/api/finding-responses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /finding-responses} : Updates an existing findingResponse.
     *
     * @param findingResponseDTO the findingResponseDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated findingResponseDTO,
     * or with status {@code 400 (Bad Request)} if the findingResponseDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the findingResponseDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/finding-responses")
    public ResponseEntity<FindingResponseDTO> updateFindingResponse(@Valid @RequestBody FindingResponseDTO findingResponseDTO)
        throws URISyntaxException {
        log.debug("REST request to update FindingResponse : {}", findingResponseDTO);
        if (findingResponseDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindingResponseDTO result = findingResponseService.save(findingResponseDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, findingResponseDTO.getId().toString()))
            .body(result);
    }

    /**
     *
     * @param recommendationId
     * @return
     */
    @GetMapping("/finding-responses")
    public ResponseEntity<List<FindingResponseDTO>> getAllFindingResponses(@RequestParam(value = "recommendationId") Long recommendationId) {
        List<FindingResponseDTO> items = findingResponseService.findAll(recommendationId);
        return ResponseEntity.ok().body(items);
    }

    /**
     * @param recommendationId
     * @param page
     * @param size
     * @param sortBy
     * @return
     */
    @GetMapping("/finding-responses/page")
    public ResponseEntity<List<FindingResponseDTO>> getAllPagedFindingResponses(@RequestParam(value = "recommendationId") Long recommendationId,
                                                                                @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                                                @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
                                                                                @RequestParam(value = "sortBy", defaultValue = "createdDate") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
        Page<FindingResponseDTO> items = findingResponseService.findAll(recommendationId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), items);
        return ResponseEntity.ok().headers(headers).body(items.getContent());
    }

    /**
     * {@code GET  /finding-responses/:id} : get the "id" findingResponse.
     *
     * @param id the id of the findingResponseDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the findingResponseDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/finding-responses/{id}")
    public ResponseEntity<FindingResponseDTO> getFindingResponse(@PathVariable Long id) {
        log.debug("REST request to get FindingResponse : {}", id);
        Optional<FindingResponseDTO> findingResponseDTO = findingResponseService.findOne(id);
        return ResponseUtil.wrapOrNotFound(findingResponseDTO);
    }

    /**
     * {@code DELETE  /finding-responses/:id} : delete the "id" findingResponse.
     *
     * @param id the id of the findingResponseDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/finding-responses/{id}")
    public ResponseEntity<Void> deleteFindingResponse(@PathVariable Long id) {
        log.debug("REST request to delete FindingResponse : {}", id);
        findingResponseService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

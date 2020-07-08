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
import org.tamisemi.iftmis.service.FindingCategoryService;
import org.tamisemi.iftmis.service.dto.FindingCategoryDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FindingCategory}.
 */
@RestController
@RequestMapping("/api")
public class FindingCategoryResource {
    private final Logger log = LoggerFactory.getLogger(FindingCategoryResource.class);

    private static final String ENTITY_NAME = "findingCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindingCategoryService findingCategoryService;

    public FindingCategoryResource(FindingCategoryService findingCategoryService) {
        this.findingCategoryService = findingCategoryService;
    }

    /**
     * {@code POST  /finding-categories} : Create a new findingCategory.
     *
     * @param findingCategoryDTO the findingCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new findingCategoryDTO, or with status {@code 400 (Bad Request)} if the findingCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/finding-categories")
    public ResponseEntity<FindingCategoryDTO> createFindingCategory(@Valid @RequestBody FindingCategoryDTO findingCategoryDTO)
        throws URISyntaxException {
        log.debug("REST request to save FindingCategory : {}", findingCategoryDTO);
        if (findingCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new findingCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindingCategoryDTO result = findingCategoryService.save(findingCategoryDTO);
        return ResponseEntity
            .created(new URI("/api/finding-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /finding-categories} : Updates an existing findingCategory.
     *
     * @param findingCategoryDTO the findingCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated findingCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the findingCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the findingCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/finding-categories")
    public ResponseEntity<FindingCategoryDTO> updateFindingCategory(@Valid @RequestBody FindingCategoryDTO findingCategoryDTO)
        throws URISyntaxException {
        log.debug("REST request to update FindingCategory : {}", findingCategoryDTO);
        if (findingCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindingCategoryDTO result = findingCategoryService.save(findingCategoryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, findingCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /finding-categories} : get all the findingCategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of findingCategories in body.
     */
    @GetMapping("/finding-categories")
    public ResponseEntity<?> getAllFindingCategories(Pageable pageable) {
        log.debug("REST request to get a page of FindingCategories");
        Page<FindingCategoryDTO> page = findingCategoryService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    /**
     * {@code GET  /finding-categories/:id} : get the "id" findingCategory.
     *
     * @param id the id of the findingCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the findingCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/finding-categories/{id}")
    public ResponseEntity<FindingCategoryDTO> getFindingCategory(@PathVariable Long id) {
        log.debug("REST request to get FindingCategory : {}", id);
        Optional<FindingCategoryDTO> findingCategoryDTO = findingCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(findingCategoryDTO);
    }

    /**
     * {@code DELETE  /finding-categories/:id} : delete the "id" findingCategory.
     *
     * @param id the id of the findingCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/finding-categories/{id}")
    public ResponseEntity<Void> deleteFindingCategory(@PathVariable Long id) {
        log.debug("REST request to delete FindingCategory : {}", id);
        findingCategoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

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
import org.tamisemi.iftmis.domain.FindingCategory;
import org.tamisemi.iftmis.repository.FindingCategoryRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FindingCategory}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FindingCategoryResource {
    private final Logger log = LoggerFactory.getLogger(FindingCategoryResource.class);

    private static final String ENTITY_NAME = "findingCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindingCategoryRepository findingCategoryRepository;

    public FindingCategoryResource(FindingCategoryRepository findingCategoryRepository) {
        this.findingCategoryRepository = findingCategoryRepository;
    }

    /**
     * {@code POST  /finding-categories} : Create a new findingCategory.
     *
     * @param findingCategory the findingCategory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new findingCategory, or with status {@code 400 (Bad Request)} if the findingCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/finding-categories")
    public ResponseEntity<FindingCategory> createFindingCategory(@Valid @RequestBody FindingCategory findingCategory)
        throws URISyntaxException {
        log.debug("REST request to save FindingCategory : {}", findingCategory);
        if (findingCategory.getId() != null) {
            throw new BadRequestAlertException("A new findingCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindingCategory result = findingCategoryRepository.save(findingCategory);
        return ResponseEntity
            .created(new URI("/api/finding-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /finding-categories} : Updates an existing findingCategory.
     *
     * @param findingCategory the findingCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated findingCategory,
     * or with status {@code 400 (Bad Request)} if the findingCategory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the findingCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/finding-categories")
    public ResponseEntity<FindingCategory> updateFindingCategory(@Valid @RequestBody FindingCategory findingCategory)
        throws URISyntaxException {
        log.debug("REST request to update FindingCategory : {}", findingCategory);
        if (findingCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindingCategory result = findingCategoryRepository.save(findingCategory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, findingCategory.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /finding-categories} : get all the findingCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of findingCategories in body.
     */
    @GetMapping("/finding-categories")
    public List<FindingCategory> getAllFindingCategories() {
        log.debug("REST request to get all FindingCategories");
        return findingCategoryRepository.findAll();
    }

    /**
     * {@code GET  /finding-categories/:id} : get the "id" findingCategory.
     *
     * @param id the id of the findingCategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the findingCategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/finding-categories/{id}")
    public ResponseEntity<FindingCategory> getFindingCategory(@PathVariable Long id) {
        log.debug("REST request to get FindingCategory : {}", id);
        Optional<FindingCategory> findingCategory = findingCategoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(findingCategory);
    }

    /**
     * {@code DELETE  /finding-categories/:id} : delete the "id" findingCategory.
     *
     * @param id the id of the findingCategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/finding-categories/{id}")
    public ResponseEntity<Void> deleteFindingCategory(@PathVariable Long id) {
        log.debug("REST request to delete FindingCategory : {}", id);
        findingCategoryRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

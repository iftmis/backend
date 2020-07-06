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
import org.tamisemi.iftmis.domain.FindingSubCategory;
import org.tamisemi.iftmis.repository.FindingSubCategoryRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FindingSubCategory}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FindingSubCategoryResource {
    private final Logger log = LoggerFactory.getLogger(FindingSubCategoryResource.class);

    private static final String ENTITY_NAME = "findingSubCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindingSubCategoryRepository findingSubCategoryRepository;

    public FindingSubCategoryResource(FindingSubCategoryRepository findingSubCategoryRepository) {
        this.findingSubCategoryRepository = findingSubCategoryRepository;
    }

    /**
     * {@code POST  /finding-sub-categories} : Create a new findingSubCategory.
     *
     * @param findingSubCategory the findingSubCategory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new findingSubCategory, or with status {@code 400 (Bad Request)} if the findingSubCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/finding-sub-categories")
    public ResponseEntity<FindingSubCategory> createFindingSubCategory(@Valid @RequestBody FindingSubCategory findingSubCategory)
        throws URISyntaxException {
        log.debug("REST request to save FindingSubCategory : {}", findingSubCategory);
        if (findingSubCategory.getId() != null) {
            throw new BadRequestAlertException("A new findingSubCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindingSubCategory result = findingSubCategoryRepository.save(findingSubCategory);
        return ResponseEntity
            .created(new URI("/api/finding-sub-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /finding-sub-categories} : Updates an existing findingSubCategory.
     *
     * @param findingSubCategory the findingSubCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated findingSubCategory,
     * or with status {@code 400 (Bad Request)} if the findingSubCategory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the findingSubCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/finding-sub-categories")
    public ResponseEntity<FindingSubCategory> updateFindingSubCategory(@Valid @RequestBody FindingSubCategory findingSubCategory)
        throws URISyntaxException {
        log.debug("REST request to update FindingSubCategory : {}", findingSubCategory);
        if (findingSubCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindingSubCategory result = findingSubCategoryRepository.save(findingSubCategory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, findingSubCategory.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /finding-sub-categories} : get all the findingSubCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of findingSubCategories in body.
     */
    @GetMapping("/finding-sub-categories")
    public List<FindingSubCategory> getAllFindingSubCategories() {
        log.debug("REST request to get all FindingSubCategories");
        return findingSubCategoryRepository.findAll();
    }

    /**
     * {@code GET  /finding-sub-categories/:id} : get the "id" findingSubCategory.
     *
     * @param id the id of the findingSubCategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the findingSubCategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/finding-sub-categories/{id}")
    public ResponseEntity<FindingSubCategory> getFindingSubCategory(@PathVariable Long id) {
        log.debug("REST request to get FindingSubCategory : {}", id);
        Optional<FindingSubCategory> findingSubCategory = findingSubCategoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(findingSubCategory);
    }

    /**
     * {@code DELETE  /finding-sub-categories/:id} : delete the "id" findingSubCategory.
     *
     * @param id the id of the findingSubCategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/finding-sub-categories/{id}")
    public ResponseEntity<Void> deleteFindingSubCategory(@PathVariable Long id) {
        log.debug("REST request to delete FindingSubCategory : {}", id);
        findingSubCategoryRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

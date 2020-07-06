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
import org.tamisemi.iftmis.service.FindingSubCategoryService;
import org.tamisemi.iftmis.service.dto.FindingSubCategoryDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FindingSubCategory}.
 */
@RestController
@RequestMapping("/api")
public class FindingSubCategoryResource {
    private final Logger log = LoggerFactory.getLogger(FindingSubCategoryResource.class);

    private static final String ENTITY_NAME = "findingSubCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindingSubCategoryService findingSubCategoryService;

    public FindingSubCategoryResource(FindingSubCategoryService findingSubCategoryService) {
        this.findingSubCategoryService = findingSubCategoryService;
    }

    /**
     * {@code POST  /finding-sub-categories} : Create a new findingSubCategory.
     *
     * @param findingSubCategoryDTO the findingSubCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new findingSubCategoryDTO, or with status {@code 400 (Bad Request)} if the findingSubCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/finding-sub-categories")
    public ResponseEntity<FindingSubCategoryDTO> createFindingSubCategory(@Valid @RequestBody FindingSubCategoryDTO findingSubCategoryDTO)
        throws URISyntaxException {
        log.debug("REST request to save FindingSubCategory : {}", findingSubCategoryDTO);
        if (findingSubCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new findingSubCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindingSubCategoryDTO result = findingSubCategoryService.save(findingSubCategoryDTO);
        return ResponseEntity
            .created(new URI("/api/finding-sub-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /finding-sub-categories} : Updates an existing findingSubCategory.
     *
     * @param findingSubCategoryDTO the findingSubCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated findingSubCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the findingSubCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the findingSubCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/finding-sub-categories")
    public ResponseEntity<FindingSubCategoryDTO> updateFindingSubCategory(@Valid @RequestBody FindingSubCategoryDTO findingSubCategoryDTO)
        throws URISyntaxException {
        log.debug("REST request to update FindingSubCategory : {}", findingSubCategoryDTO);
        if (findingSubCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindingSubCategoryDTO result = findingSubCategoryService.save(findingSubCategoryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, findingSubCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /finding-sub-categories} : get all the findingSubCategories.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of findingSubCategories in body.
     */
    @GetMapping("/finding-sub-categories")
    public ResponseEntity<List<FindingSubCategoryDTO>> getAllFindingSubCategories(Pageable pageable) {
        log.debug("REST request to get a page of FindingSubCategories");
        Page<FindingSubCategoryDTO> page = findingSubCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /finding-sub-categories/:id} : get the "id" findingSubCategory.
     *
     * @param id the id of the findingSubCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the findingSubCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/finding-sub-categories/{id}")
    public ResponseEntity<FindingSubCategoryDTO> getFindingSubCategory(@PathVariable Long id) {
        log.debug("REST request to get FindingSubCategory : {}", id);
        Optional<FindingSubCategoryDTO> findingSubCategoryDTO = findingSubCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(findingSubCategoryDTO);
    }

    /**
     * {@code DELETE  /finding-sub-categories/:id} : delete the "id" findingSubCategory.
     *
     * @param id the id of the findingSubCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/finding-sub-categories/{id}")
    public ResponseEntity<Void> deleteFindingSubCategory(@PathVariable Long id) {
        log.debug("REST request to delete FindingSubCategory : {}", id);
        findingSubCategoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

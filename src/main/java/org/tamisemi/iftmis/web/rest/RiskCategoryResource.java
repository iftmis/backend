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
import org.tamisemi.iftmis.service.RiskCategoryService;
import org.tamisemi.iftmis.service.dto.RiskCategoryDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.RiskCategory}.
 */
@RestController
@RequestMapping("/api")
public class RiskCategoryResource {
    private final Logger log = LoggerFactory.getLogger(RiskCategoryResource.class);

    private static final String ENTITY_NAME = "riskCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskCategoryService riskCategoryService;

    public RiskCategoryResource(RiskCategoryService riskCategoryService) {
        this.riskCategoryService = riskCategoryService;
    }

    /**
     * {@code POST  /risk-categories} : Create a new riskCategory.
     *
     * @param riskCategoryDTO the riskCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskCategoryDTO, or with status {@code 400 (Bad Request)} if the riskCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/risk-categories")
    public ResponseEntity<RiskCategoryDTO> createRiskCategory(@Valid @RequestBody RiskCategoryDTO riskCategoryDTO)
        throws URISyntaxException {
        log.debug("REST request to save RiskCategory : {}", riskCategoryDTO);
        if (riskCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new riskCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RiskCategoryDTO result = riskCategoryService.save(riskCategoryDTO);
        return ResponseEntity
            .created(new URI("/api/risk-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /risk-categories} : Updates an existing riskCategory.
     *
     * @param riskCategoryDTO the riskCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the riskCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/risk-categories")
    public ResponseEntity<RiskCategoryDTO> updateRiskCategory(@Valid @RequestBody RiskCategoryDTO riskCategoryDTO)
        throws URISyntaxException {
        log.debug("REST request to update RiskCategory : {}", riskCategoryDTO);
        if (riskCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RiskCategoryDTO result = riskCategoryService.save(riskCategoryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, riskCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * @return
     */
    @GetMapping("/risk-categories")
    public ResponseEntity<List<RiskCategoryDTO>> getAllRiskCategories() {
        log.debug("REST request to get a list of Risk Categories");
        List<RiskCategoryDTO> list = riskCategoryService.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * @param page
     * @param size
     * @param sortBy
     * @return
     */
    @GetMapping("/risk-categories/page")
    public ResponseEntity<List<RiskCategoryDTO>> getAllPagedRiskCategories(
        @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
        @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
        @RequestParam(value = "sortBy", defaultValue = "id") String sortBy
    ) {
        log.debug("REST request to get a page of Risk Categories");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<RiskCategoryDTO> items = riskCategoryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), items);
        return ResponseEntity.ok().headers(headers).body(items.getContent());
    }

    /**
     * {@code GET  /risk-categories/:id} : get the "id" riskCategory.
     *
     * @param id the id of the riskCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/risk-categories/{id}")
    public ResponseEntity<RiskCategoryDTO> getRiskCategory(@PathVariable Long id) {
        log.debug("REST request to get RiskCategory : {}", id);
        Optional<RiskCategoryDTO> riskCategoryDTO = riskCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskCategoryDTO);
    }

    /**
     * {@code DELETE  /risk-categories/:id} : delete the "id" riskCategory.
     *
     * @param id the id of the riskCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/risk-categories/{id}")
    public ResponseEntity<Void> deleteRiskCategory(@PathVariable Long id) {
        log.debug("REST request to delete RiskCategory : {}", id);
        riskCategoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

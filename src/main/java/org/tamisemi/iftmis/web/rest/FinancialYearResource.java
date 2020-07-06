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
import org.tamisemi.iftmis.domain.FinancialYear;
import org.tamisemi.iftmis.repository.FinancialYearRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FinancialYear}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FinancialYearResource {
    private final Logger log = LoggerFactory.getLogger(FinancialYearResource.class);

    private static final String ENTITY_NAME = "financialYear";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FinancialYearRepository financialYearRepository;

    public FinancialYearResource(FinancialYearRepository financialYearRepository) {
        this.financialYearRepository = financialYearRepository;
    }

    /**
     * {@code POST  /financial-years} : Create a new financialYear.
     *
     * @param financialYear the financialYear to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new financialYear, or with status {@code 400 (Bad Request)} if the financialYear has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/financial-years")
    public ResponseEntity<FinancialYear> createFinancialYear(@Valid @RequestBody FinancialYear financialYear) throws URISyntaxException {
        log.debug("REST request to save FinancialYear : {}", financialYear);
        if (financialYear.getId() != null) {
            throw new BadRequestAlertException("A new financialYear cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FinancialYear result = financialYearRepository.save(financialYear);
        return ResponseEntity
            .created(new URI("/api/financial-years/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /financial-years} : Updates an existing financialYear.
     *
     * @param financialYear the financialYear to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated financialYear,
     * or with status {@code 400 (Bad Request)} if the financialYear is not valid,
     * or with status {@code 500 (Internal Server Error)} if the financialYear couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/financial-years")
    public ResponseEntity<FinancialYear> updateFinancialYear(@Valid @RequestBody FinancialYear financialYear) throws URISyntaxException {
        log.debug("REST request to update FinancialYear : {}", financialYear);
        if (financialYear.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FinancialYear result = financialYearRepository.save(financialYear);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, financialYear.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /financial-years} : get all the financialYears.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of financialYears in body.
     */
    @GetMapping("/financial-years")
    public List<FinancialYear> getAllFinancialYears() {
        log.debug("REST request to get all FinancialYears");
        return financialYearRepository.findAll();
    }

    /**
     * {@code GET  /financial-years/:id} : get the "id" financialYear.
     *
     * @param id the id of the financialYear to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the financialYear, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/financial-years/{id}")
    public ResponseEntity<FinancialYear> getFinancialYear(@PathVariable Long id) {
        log.debug("REST request to get FinancialYear : {}", id);
        Optional<FinancialYear> financialYear = financialYearRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(financialYear);
    }

    /**
     * {@code DELETE  /financial-years/:id} : delete the "id" financialYear.
     *
     * @param id the id of the financialYear to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/financial-years/{id}")
    public ResponseEntity<Void> deleteFinancialYear(@PathVariable Long id) {
        log.debug("REST request to delete FinancialYear : {}", id);
        financialYearRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

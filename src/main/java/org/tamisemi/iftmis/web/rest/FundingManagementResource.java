package org.tamisemi.iftmis.web.rest;

import org.tamisemi.iftmis.service.FundingManagementService;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;
import org.tamisemi.iftmis.service.dto.FundingManagementDTO;
import org.tamisemi.iftmis.service.dto.FundingManagementCriteria;
import org.tamisemi.iftmis.service.FundingManagementQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FundingManagement}.
 */
@RestController
@RequestMapping("/api")
public class FundingManagementResource {

    private final Logger log = LoggerFactory.getLogger(FundingManagementResource.class);

    private static final String ENTITY_NAME = "fundingManagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FundingManagementService fundingManagementService;

    private final FundingManagementQueryService fundingManagementQueryService;

    public FundingManagementResource(FundingManagementService fundingManagementService, FundingManagementQueryService fundingManagementQueryService) {
        this.fundingManagementService = fundingManagementService;
        this.fundingManagementQueryService = fundingManagementQueryService;
    }

    /**
     * {@code POST  /funding-managements} : Create a new fundingManagement.
     *
     * @param fundingManagementDTO the fundingManagementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fundingManagementDTO, or with status {@code 400 (Bad Request)} if the fundingManagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/funding-managements")
    public ResponseEntity<FundingManagementDTO> createFundingManagement(@RequestBody FundingManagementDTO fundingManagementDTO) throws URISyntaxException {
        log.debug("REST request to save FundingManagement : {}", fundingManagementDTO);
        if (fundingManagementDTO.getId() != null) {
            throw new BadRequestAlertException("A new fundingManagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FundingManagementDTO result = fundingManagementService.save(fundingManagementDTO);
        return ResponseEntity.created(new URI("/api/funding-managements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /funding-managements} : Updates an existing fundingManagement.
     *
     * @param fundingManagementDTO the fundingManagementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fundingManagementDTO,
     * or with status {@code 400 (Bad Request)} if the fundingManagementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fundingManagementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/funding-managements")
    public ResponseEntity<FundingManagementDTO> updateFundingManagement(@RequestBody FundingManagementDTO fundingManagementDTO) throws URISyntaxException {
        log.debug("REST request to update FundingManagement : {}", fundingManagementDTO);
        if (fundingManagementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FundingManagementDTO result = fundingManagementService.save(fundingManagementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fundingManagementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /funding-managements} : get all the fundingManagements.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fundingManagements in body.
     */
    @GetMapping("/funding-managements")
    public ResponseEntity<List<FundingManagementDTO>> getAllFundingManagements(FundingManagementCriteria criteria, Pageable pageable) {
        log.debug("REST request to get FundingManagements by criteria: {}", criteria);
        Page<FundingManagementDTO> page = fundingManagementQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /funding-managements/count} : count all the fundingManagements.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/funding-managements/count")
    public ResponseEntity<Long> countFundingManagements(FundingManagementCriteria criteria) {
        log.debug("REST request to count FundingManagements by criteria: {}", criteria);
        return ResponseEntity.ok().body(fundingManagementQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /funding-managements/:id} : get the "id" fundingManagement.
     *
     * @param id the id of the fundingManagementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fundingManagementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/funding-managements/{id}")
    public ResponseEntity<FundingManagementDTO> getFundingManagement(@PathVariable Long id) {
        log.debug("REST request to get FundingManagement : {}", id);
        Optional<FundingManagementDTO> fundingManagementDTO = fundingManagementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fundingManagementDTO);
    }

    /**
     * {@code DELETE  /funding-managements/:id} : delete the "id" fundingManagement.
     *
     * @param id the id of the fundingManagementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/funding-managements/{id}")
    public ResponseEntity<Void> deleteFundingManagement(@PathVariable Long id) {
        log.debug("REST request to delete FundingManagement : {}", id);
        fundingManagementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

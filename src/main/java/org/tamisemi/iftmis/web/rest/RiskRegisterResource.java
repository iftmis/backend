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
import org.tamisemi.iftmis.service.RiskRegisterService;
import org.tamisemi.iftmis.service.dto.RiskRegisterDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.RiskRegister}.
 */
@RestController
@RequestMapping("/api")
public class RiskRegisterResource {
    private final Logger log = LoggerFactory.getLogger(RiskRegisterResource.class);

    private static final String ENTITY_NAME = "riskRegister";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskRegisterService riskRegisterService;

    public RiskRegisterResource(RiskRegisterService riskRegisterService) {
        this.riskRegisterService = riskRegisterService;
    }

    /**
     * {@code POST  /risk-registers} : Create a new riskRegister.
     *
     * @param riskRegisterDTO the riskRegisterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskRegisterDTO, or with status {@code 400 (Bad Request)} if the riskRegister has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/risk-registers")
    public ResponseEntity<RiskRegisterDTO> createRiskRegister(@Valid @RequestBody RiskRegisterDTO riskRegisterDTO)
        throws URISyntaxException {
        log.debug("REST request to save RiskRegister : {}", riskRegisterDTO);
        if (riskRegisterDTO.getId() != null) {
            throw new BadRequestAlertException("A new riskRegister cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RiskRegisterDTO result = riskRegisterService.save(riskRegisterDTO);
        return ResponseEntity
            .created(new URI("/api/risk-registers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /risk-registers} : Updates an existing riskRegister.
     *
     * @param riskRegisterDTO the riskRegisterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskRegisterDTO,
     * or with status {@code 400 (Bad Request)} if the riskRegisterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskRegisterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/risk-registers")
    public ResponseEntity<RiskRegisterDTO> updateRiskRegister(@Valid @RequestBody RiskRegisterDTO riskRegisterDTO)
        throws URISyntaxException {
        log.debug("REST request to update RiskRegister : {}", riskRegisterDTO);
        if (riskRegisterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RiskRegisterDTO result = riskRegisterService.save(riskRegisterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, riskRegisterDTO.getId().toString()))
            .body(result);
    }

    /**
     * @return
     */
    @GetMapping("/risk-registers")
    public ResponseEntity<List<RiskRegisterDTO>> getAllRiskRegisters(@RequestParam(value = "financialYearId", defaultValue = Constants.ZERO) Long financialYearId,
                                                                     @RequestParam(value = "organisationUnitId", defaultValue = Constants.ZERO) Long organisationUnitId) {
        log.debug("REST request to get a list of RiskRegisters");
        List<RiskRegisterDTO> items;
        if (financialYearId == 0) {
            if (organisationUnitId == 0) {
                items = riskRegisterService.findAll();
            } else {
                items = riskRegisterService.findAllByOrganisationUnitId(organisationUnitId);
            }
        } else {
            if (organisationUnitId == 0) {
                items = riskRegisterService.findAllByFinancialYearId(financialYearId);
            } else {
                items = riskRegisterService.findAllByFinancialYearIdAndOrganisationUnitId(financialYearId, organisationUnitId);
            }
        }
        return ResponseEntity.ok().body(items);
    }

    /**
     * @param page
     * @param size
     * @param financialYearId
     * @param sortBy
     * @return
     */
    @GetMapping("/risk-registers/page")
    public ResponseEntity<List<RiskRegisterDTO>> getAllPagedRiskRegisters(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                                          @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
                                                                          @RequestParam(value = "financialYearId", defaultValue = Constants.ZERO) Long financialYearId,
                                                                          @RequestParam(value = "organisationUnitId", defaultValue = Constants.ZERO) Long organisationUnitId,
                                                                          @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {
        log.debug("REST request to get a page of RiskRegisters");
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<RiskRegisterDTO> items;
        if (financialYearId == 0) {
            if (organisationUnitId == 0) {
                items = riskRegisterService.findAll(pageable);
            } else {
                items = riskRegisterService.findAllByOrganisationUnitId(organisationUnitId, pageable);
            }
        } else {
            if (organisationUnitId == 0) {
                items = riskRegisterService.findAllByFinancialYearId(financialYearId, pageable);
            } else {
                items = riskRegisterService.findAllByFinancialYearIdAndOrganisationUnitId(financialYearId, organisationUnitId, pageable);
            }
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), items);
        return ResponseEntity.ok().headers(headers).body(items.getContent());
    }

    /**
     * {@code GET  /risk-registers/:id} : get the "id" riskRegister.
     *
     * @param id the id of the riskRegisterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskRegisterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/risk-registers/{id}")
    public ResponseEntity<RiskRegisterDTO> getRiskRegister(@PathVariable Long id) {
        log.debug("REST request to get RiskRegister : {}", id);
        Optional<RiskRegisterDTO> riskRegisterDTO = riskRegisterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskRegisterDTO);
    }

    /**
     * {@code DELETE  /risk-registers/:id} : delete the "id" riskRegister.
     *
     * @param id the id of the riskRegisterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/risk-registers/{id}")
    public ResponseEntity<Void> deleteRiskRegister(@PathVariable Long id) {
        log.debug("REST request to delete RiskRegister : {}", id);
        riskRegisterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

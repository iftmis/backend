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
import org.tamisemi.iftmis.service.RiskQueryService;
import org.tamisemi.iftmis.service.RiskService;
import org.tamisemi.iftmis.service.dto.RiskCriteria;
import org.tamisemi.iftmis.service.dto.RiskDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Risk}.
 */
@RestController
@RequestMapping("/api")
public class RiskResource {
    private final Logger log = LoggerFactory.getLogger(RiskResource.class);

    private static final String ENTITY_NAME = "risk";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskService riskService;

    private final RiskQueryService riskQueryService;

    public RiskResource(RiskService riskService, RiskQueryService riskQueryService) {
        this.riskService = riskService;
        this.riskQueryService = riskQueryService;
    }

    /**
     * {@code POST  /risks} : Create a new risk.
     *
     * @param riskDTO the riskDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskDTO, or with status {@code 400 (Bad Request)} if the risk has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/risks")
    public ResponseEntity<RiskDTO> createRisk(@Valid @RequestBody RiskDTO riskDTO) throws URISyntaxException {
        log.debug("REST request to save Risk : {}", riskDTO);
        if (riskDTO.getId() != null) {
            throw new BadRequestAlertException("A new risk cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RiskDTO result = riskService.save(riskDTO);
        return ResponseEntity
            .created(new URI("/api/risks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /risks} : Updates an existing risk.
     *
     * @param riskDTO the riskDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskDTO,
     * or with status {@code 400 (Bad Request)} if the riskDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/risks")
    public ResponseEntity<RiskDTO> updateRisk(@Valid @RequestBody RiskDTO riskDTO) throws URISyntaxException {
        log.debug("REST request to update Risk : {}", riskDTO);
        if (riskDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RiskDTO result = riskService.save(riskDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, riskDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /risks} : get all the risks.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of risks in body.
     */
    @GetMapping("/risks")
    public ResponseEntity<List<RiskDTO>> getAllRisks(RiskCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Risks by criteria: {}", criteria);
        Page<RiskDTO> page = riskQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /risks/count} : count all the risks.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/risks/count")
    public ResponseEntity<Long> countRisks(RiskCriteria criteria) {
        log.debug("REST request to count Risks by criteria: {}", criteria);
        return ResponseEntity.ok().body(riskQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /risks/:id} : get the "id" risk.
     *
     * @param id the id of the riskDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/risks/{id}")
    public ResponseEntity<RiskDTO> getRisk(@PathVariable Long id) {
        log.debug("REST request to get Risk : {}", id);
        Optional<RiskDTO> riskDTO = riskService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskDTO);
    }

    /**
     * {@code DELETE  /risks/:id} : delete the "id" risk.
     *
     * @param id the id of the riskDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/risks/{id}")
    public ResponseEntity<Void> deleteRisk(@PathVariable Long id) {
        log.debug("REST request to delete Risk : {}", id);
        riskService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

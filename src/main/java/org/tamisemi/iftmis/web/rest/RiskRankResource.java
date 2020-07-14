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
import org.tamisemi.iftmis.service.RiskRankService;
import org.tamisemi.iftmis.service.dto.RiskRankDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.RiskRank}.
 */
@RestController
@RequestMapping("/api")
public class RiskRankResource {
    private final Logger log = LoggerFactory.getLogger(RiskRankResource.class);

    private static final String ENTITY_NAME = "riskRank";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskRankService riskRankService;

    public RiskRankResource(RiskRankService riskRankService) {
        this.riskRankService = riskRankService;
    }

    /**
     * {@code POST  /risk-ranks} : Create a new riskRank.
     *
     * @param riskRankDTO the riskRankDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskRankDTO, or with status {@code 400 (Bad Request)} if the riskRank has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/risk-ranks")
    public ResponseEntity<RiskRankDTO> createRiskRank(@Valid @RequestBody RiskRankDTO riskRankDTO) throws URISyntaxException {
        log.debug("REST request to save RiskRank : {}", riskRankDTO);
        if (riskRankDTO.getId() != null) {
            throw new BadRequestAlertException("A new riskRank cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RiskRankDTO result = riskRankService.save(riskRankDTO);
        return ResponseEntity
            .created(new URI("/api/risk-ranks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /risk-ranks} : Updates an existing riskRank.
     *
     * @param riskRankDTO the riskRankDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskRankDTO,
     * or with status {@code 400 (Bad Request)} if the riskRankDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskRankDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/risk-ranks")
    public ResponseEntity<RiskRankDTO> updateRiskRank(@Valid @RequestBody RiskRankDTO riskRankDTO) throws URISyntaxException {
        log.debug("REST request to update RiskRank : {}", riskRankDTO);
        if (riskRankDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RiskRankDTO result = riskRankService.save(riskRankDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, riskRankDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /risk-ranks} : get all the riskRanks.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskRanks in body.
     */
    @GetMapping("/risk-ranks")
    public ResponseEntity<List<RiskRankDTO>> getAllRiskRanks(Pageable pageable) {
        log.debug("REST request to get a page of RiskRanks");
        Page<RiskRankDTO> page = riskRankService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /risk-ranks/:id} : get the "id" riskRank.
     *
     * @param id the id of the riskRankDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskRankDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/risk-ranks/{id}")
    public ResponseEntity<RiskRankDTO> getRiskRank(@PathVariable Long id) {
        log.debug("REST request to get RiskRank : {}", id);
        Optional<RiskRankDTO> riskRankDTO = riskRankService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskRankDTO);
    }

    /**
     * {@code DELETE  /risk-ranks/:id} : delete the "id" riskRank.
     *
     * @param id the id of the riskRankDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/risk-ranks/{id}")
    public ResponseEntity<Void> deleteRiskRank(@PathVariable Long id) {
        log.debug("REST request to delete RiskRank : {}", id);
        riskRankService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

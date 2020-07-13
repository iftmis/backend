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
import org.tamisemi.iftmis.service.RiskRatingService;
import org.tamisemi.iftmis.service.dto.RiskRatingDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.RiskRating}.
 */
@RestController
@RequestMapping("/api")
public class RiskRatingResource {
    private final Logger log = LoggerFactory.getLogger(RiskRatingResource.class);

    private static final String ENTITY_NAME = "riskRating";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RiskRatingService riskRatingService;

    public RiskRatingResource(RiskRatingService riskRatingService) {
        this.riskRatingService = riskRatingService;
    }

    /**
     * {@code POST  /risk-ratings} : Create a new riskRating.
     *
     * @param riskRatingDTO the riskRatingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new riskRatingDTO, or with status {@code 400 (Bad Request)} if the riskRating has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/risk-ratings")
    public ResponseEntity<RiskRatingDTO> createRiskRating(@Valid @RequestBody RiskRatingDTO riskRatingDTO) throws URISyntaxException {
        log.debug("REST request to save RiskRating : {}", riskRatingDTO);
        if (riskRatingDTO.getId() != null) {
            throw new BadRequestAlertException("A new riskRating cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RiskRatingDTO result = riskRatingService.save(riskRatingDTO);
        return ResponseEntity
            .created(new URI("/api/risk-ratings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /risk-ratings} : Updates an existing riskRating.
     *
     * @param riskRatingDTO the riskRatingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated riskRatingDTO,
     * or with status {@code 400 (Bad Request)} if the riskRatingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the riskRatingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/risk-ratings")
    public ResponseEntity<RiskRatingDTO> updateRiskRating(@Valid @RequestBody RiskRatingDTO riskRatingDTO) throws URISyntaxException {
        log.debug("REST request to update RiskRating : {}", riskRatingDTO);
        if (riskRatingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RiskRatingDTO result = riskRatingService.save(riskRatingDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, riskRatingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /risk-ratings} : get all the riskRatings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of riskRatings in body.
     */
    @GetMapping("/risk-ratings")
    public ResponseEntity<List<RiskRatingDTO>> getAllRiskRatings(Pageable pageable) {
        log.debug("REST request to get a page of RiskRatings");
        Page<RiskRatingDTO> page = riskRatingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /risk-ratings/:id} : get the "id" riskRating.
     *
     * @param id the id of the riskRatingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the riskRatingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/risk-ratings/{id}")
    public ResponseEntity<RiskRatingDTO> getRiskRating(@PathVariable Long id) {
        log.debug("REST request to get RiskRating : {}", id);
        Optional<RiskRatingDTO> riskRatingDTO = riskRatingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(riskRatingDTO);
    }

    /**
     * {@code DELETE  /risk-ratings/:id} : delete the "id" riskRating.
     *
     * @param id the id of the riskRatingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/risk-ratings/{id}")
    public ResponseEntity<Void> deleteRiskRating(@PathVariable Long id) {
        log.debug("REST request to delete RiskRating : {}", id);
        riskRatingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

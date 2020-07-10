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
import org.tamisemi.iftmis.domain.Indicator;
import org.tamisemi.iftmis.service.IndicatorService;
import org.tamisemi.iftmis.service.dto.IndicatorDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Indicator}.
 */
@RestController
@RequestMapping("/api")
public class IndicatorResource {
    private final Logger log = LoggerFactory.getLogger(IndicatorResource.class);

    private static final String ENTITY_NAME = "indicator";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IndicatorService indicatorService;

    public IndicatorResource(IndicatorService indicatorService) {
        this.indicatorService = indicatorService;
    }

    /**
     * {@code POST  /indicators} : Create a new indicator.
     *
     * @param indicatorDTO the indicatorDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new indicatorDTO, or with status {@code 400 (Bad Request)} if the indicator has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/indicators")
    public ResponseEntity<IndicatorDTO> createIndicator(@Valid @RequestBody IndicatorDTO indicatorDTO) throws URISyntaxException {
        log.debug("REST request to save Indicator : {}", indicatorDTO);
        if (indicatorDTO.getId() != null) {
            throw new BadRequestAlertException("A new indicator cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IndicatorDTO result = indicatorService.save(indicatorDTO);
        return ResponseEntity
            .created(new URI("/api/indicators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /indicators} : Updates an existing indicator.
     *
     * @param indicatorDTO the indicatorDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated indicatorDTO,
     * or with status {@code 400 (Bad Request)} if the indicatorDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the indicatorDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/indicators")
    public ResponseEntity<IndicatorDTO> updateIndicator(@Valid @RequestBody IndicatorDTO indicatorDTO) throws URISyntaxException {
        log.debug("REST request to update Indicator : {}", indicatorDTO);
        if (indicatorDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        IndicatorDTO result = indicatorService.save(indicatorDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, indicatorDTO.getId().toString()))
            .body(result);
    }

    /**
     *
     * @return
     */
    @GetMapping("/indicators")
    public ResponseEntity<List<Indicator>> getAllIndicators() {
        log.debug("REST request to get a page of Indicators");
        List<Indicator> items = indicatorService.findAll();
        return ResponseEntity.ok().body(items);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping("/indicators/page")
    public ResponseEntity<List<IndicatorDTO>> getAllPagedIndicators(Pageable pageable) {
        log.debug("REST request to get a page of Indicators");
        Page<IndicatorDTO> page = indicatorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /indicators/:id} : get the "id" indicator.
     *
     * @param id the id of the indicatorDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the indicatorDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/indicators/{id}")
    public ResponseEntity<IndicatorDTO> getIndicator(@PathVariable Long id) {
        log.debug("REST request to get Indicator : {}", id);
        Optional<IndicatorDTO> indicatorDTO = indicatorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(indicatorDTO);
    }

    /**
     * {@code DELETE  /indicators/:id} : delete the "id" indicator.
     *
     * @param id the id of the indicatorDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/indicators/{id}")
    public ResponseEntity<Void> deleteIndicator(@PathVariable Long id) {
        log.debug("REST request to delete Indicator : {}", id);
        indicatorService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

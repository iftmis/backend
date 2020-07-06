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
import org.tamisemi.iftmis.service.QuarterService;
import org.tamisemi.iftmis.service.dto.QuarterDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Quarter}.
 */
@RestController
@RequestMapping("/api")
public class QuarterResource {
    private final Logger log = LoggerFactory.getLogger(QuarterResource.class);

    private static final String ENTITY_NAME = "quarter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuarterService quarterService;

    public QuarterResource(QuarterService quarterService) {
        this.quarterService = quarterService;
    }

    /**
     * {@code POST  /quarters} : Create a new quarter.
     *
     * @param quarterDTO the quarterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new quarterDTO, or with status {@code 400 (Bad Request)} if the quarter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/quarters")
    public ResponseEntity<QuarterDTO> createQuarter(@Valid @RequestBody QuarterDTO quarterDTO) throws URISyntaxException {
        log.debug("REST request to save Quarter : {}", quarterDTO);
        if (quarterDTO.getId() != null) {
            throw new BadRequestAlertException("A new quarter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuarterDTO result = quarterService.save(quarterDTO);
        return ResponseEntity
            .created(new URI("/api/quarters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /quarters} : Updates an existing quarter.
     *
     * @param quarterDTO the quarterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated quarterDTO,
     * or with status {@code 400 (Bad Request)} if the quarterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the quarterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/quarters")
    public ResponseEntity<QuarterDTO> updateQuarter(@Valid @RequestBody QuarterDTO quarterDTO) throws URISyntaxException {
        log.debug("REST request to update Quarter : {}", quarterDTO);
        if (quarterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuarterDTO result = quarterService.save(quarterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, quarterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /quarters} : get all the quarters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of quarters in body.
     */
    @GetMapping("/quarters")
    public ResponseEntity<List<QuarterDTO>> getAllQuarters(Pageable pageable) {
        log.debug("REST request to get a page of Quarters");
        Page<QuarterDTO> page = quarterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /quarters/:id} : get the "id" quarter.
     *
     * @param id the id of the quarterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the quarterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/quarters/{id}")
    public ResponseEntity<QuarterDTO> getQuarter(@PathVariable Long id) {
        log.debug("REST request to get Quarter : {}", id);
        Optional<QuarterDTO> quarterDTO = quarterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(quarterDTO);
    }

    /**
     * {@code DELETE  /quarters/:id} : delete the "id" quarter.
     *
     * @param id the id of the quarterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/quarters/{id}")
    public ResponseEntity<Void> deleteQuarter(@PathVariable Long id) {
        log.debug("REST request to delete Quarter : {}", id);
        quarterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

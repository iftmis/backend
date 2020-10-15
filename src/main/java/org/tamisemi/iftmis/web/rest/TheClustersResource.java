package org.tamisemi.iftmis.web.rest;

import org.tamisemi.iftmis.service.TheClustersService;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;
import org.tamisemi.iftmis.service.dto.TheClustersDTO;
import org.tamisemi.iftmis.service.dto.TheClustersCriteria;
import org.tamisemi.iftmis.service.TheClustersQueryService;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.TheClusters}.
 */
@RestController
@RequestMapping("/api")
public class TheClustersResource {

    private final Logger log = LoggerFactory.getLogger(TheClustersResource.class);

    private static final String ENTITY_NAME = "theClusters";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TheClustersService theClustersService;

    private final TheClustersQueryService theClustersQueryService;

    public TheClustersResource(TheClustersService theClustersService, TheClustersQueryService theClustersQueryService) {
        this.theClustersService = theClustersService;
        this.theClustersQueryService = theClustersQueryService;
    }

    /**
     * {@code POST  /the-clusters} : Create a new theClusters.
     *
     * @param theClustersDTO the theClustersDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new theClustersDTO, or with status {@code 400 (Bad Request)} if the theClusters has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/the-clusters")
    public ResponseEntity<TheClustersDTO> createTheClusters(@Valid @RequestBody TheClustersDTO theClustersDTO) throws URISyntaxException {
        log.debug("REST request to save TheClusters : {}", theClustersDTO);
        if (theClustersDTO.getId() != null) {
            throw new BadRequestAlertException("A new theClusters cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TheClustersDTO result = theClustersService.save(theClustersDTO);
        return ResponseEntity.created(new URI("/api/the-clusters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /the-clusters} : Updates an existing theClusters.
     *
     * @param theClustersDTO the theClustersDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated theClustersDTO,
     * or with status {@code 400 (Bad Request)} if the theClustersDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the theClustersDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/the-clusters")
    public ResponseEntity<TheClustersDTO> updateTheClusters(@Valid @RequestBody TheClustersDTO theClustersDTO) throws URISyntaxException {
        log.debug("REST request to update TheClusters : {}", theClustersDTO);
        if (theClustersDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TheClustersDTO result = theClustersService.save(theClustersDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, theClustersDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /the-clusters} : get all the theClusters.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of theClusters in body.
     */
    @GetMapping("/the-clusters")
    public ResponseEntity<List<TheClustersDTO>> getAllTheClusters(TheClustersCriteria criteria, Pageable pageable) {
        log.debug("REST request to get TheClusters by criteria: {}", criteria);
        Page<TheClustersDTO> page = theClustersQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /the-clusters/count} : count all the theClusters.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/the-clusters/count")
    public ResponseEntity<Long> countTheClusters(TheClustersCriteria criteria) {
        log.debug("REST request to count TheClusters by criteria: {}", criteria);
        return ResponseEntity.ok().body(theClustersQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /the-clusters/:id} : get the "id" theClusters.
     *
     * @param id the id of the theClustersDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the theClustersDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/the-clusters/{id}")
    public ResponseEntity<TheClustersDTO> getTheClusters(@PathVariable Long id) {
        log.debug("REST request to get TheClusters : {}", id);
        Optional<TheClustersDTO> theClustersDTO = theClustersService.findOne(id);
        return ResponseUtil.wrapOrNotFound(theClustersDTO);
    }

    /**
     * {@code DELETE  /the-clusters/:id} : delete the "id" theClusters.
     *
     * @param id the id of the theClustersDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/the-clusters/{id}")
    public ResponseEntity<Void> deleteTheClusters(@PathVariable Long id) {
        log.debug("REST request to delete TheClusters : {}", id);
        theClustersService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}

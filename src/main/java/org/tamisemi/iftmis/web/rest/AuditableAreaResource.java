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
import org.tamisemi.iftmis.domain.AuditableArea;
import org.tamisemi.iftmis.service.AuditableAreaService;
import org.tamisemi.iftmis.service.dto.AuditableAreaDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.AuditableArea}.
 */
@RestController
@RequestMapping("/api")
public class AuditableAreaResource {
    private final Logger log = LoggerFactory.getLogger(AuditableAreaResource.class);

    private static final String ENTITY_NAME = "auditableArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuditableAreaService auditableAreaService;

    public AuditableAreaResource(AuditableAreaService auditableAreaService) {
        this.auditableAreaService = auditableAreaService;
    }

    /**
     * {@code POST  /auditable-areas} : Create a new auditableArea.
     *
     * @param auditableAreaDTO the auditableAreaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auditableAreaDTO, or with status {@code 400 (Bad Request)} if the auditableArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/auditable-areas")
    public ResponseEntity<AuditableAreaDTO> createAuditableArea(@Valid @RequestBody AuditableAreaDTO auditableAreaDTO)
        throws URISyntaxException {
        log.debug("REST request to save AuditableArea : {}", auditableAreaDTO);
        if (auditableAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new auditableArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AuditableAreaDTO result = auditableAreaService.save(auditableAreaDTO);
        return ResponseEntity
            .created(new URI("/api/auditable-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /auditable-areas} : Updates an existing auditableArea.
     *
     * @param auditableAreaDTO the auditableAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditableAreaDTO,
     * or with status {@code 400 (Bad Request)} if the auditableAreaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auditableAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/auditable-areas")
    public ResponseEntity<AuditableAreaDTO> updateAuditableArea(@Valid @RequestBody AuditableAreaDTO auditableAreaDTO)
        throws URISyntaxException {
        log.debug("REST request to update AuditableArea : {}", auditableAreaDTO);
        if (auditableAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AuditableAreaDTO result = auditableAreaService.save(auditableAreaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, auditableAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     *
     * @return
     */
    @GetMapping("/auditable-areas")
    public ResponseEntity<List<AuditableArea>> getAllAuditableAreas() {
        log.debug("REST request to get a page of Auditable Areas");
        List<AuditableArea> items = auditableAreaService.findAll();
        return ResponseEntity.ok().body(items);
    }

    /**
     *
     * @param pageable
     * @return
     */
    @GetMapping("/auditable-areas/page")
    public ResponseEntity<List<AuditableAreaDTO>> getAllPagedAuditableAreas(Pageable pageable) {
        log.debug("REST request to get a page of AuditableAreas");
        Page<AuditableAreaDTO> page = auditableAreaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /auditable-areas/:id} : get the "id" auditableArea.
     *
     * @param id the id of the auditableAreaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auditableAreaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/auditable-areas/{id}")
    public ResponseEntity<AuditableAreaDTO> getAuditableArea(@PathVariable Long id) {
        log.debug("REST request to get AuditableArea : {}", id);
        Optional<AuditableAreaDTO> auditableAreaDTO = auditableAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(auditableAreaDTO);
    }

    /**
     * {@code DELETE  /auditable-areas/:id} : delete the "id" auditableArea.
     *
     * @param id the id of the auditableAreaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/auditable-areas/{id}")
    public ResponseEntity<Void> deleteAuditableArea(@PathVariable Long id) {
        log.debug("REST request to delete AuditableArea : {}", id);
        auditableAreaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

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
import org.tamisemi.iftmis.service.InspectionFindingService;
import org.tamisemi.iftmis.service.dto.InspectionFindingDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionFinding}.
 */
@RestController
@RequestMapping("/api")
public class InspectionFindingResource {
    private final Logger log = LoggerFactory.getLogger(InspectionFindingResource.class);

    private static final String ENTITY_NAME = "inspectionFinding";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionFindingService inspectionFindingService;

    public InspectionFindingResource(InspectionFindingService inspectionFindingService) {
        this.inspectionFindingService = inspectionFindingService;
    }

    /**
     * {@code POST  /inspection-findings} : Create a new inspectionFinding.
     *
     * @param inspectionFindingDTO the inspectionFindingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionFindingDTO, or with status {@code 400 (Bad Request)} if the inspectionFinding has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-findings")
    public ResponseEntity<InspectionFindingDTO> createInspectionFinding(@Valid @RequestBody InspectionFindingDTO inspectionFindingDTO)
        throws URISyntaxException {
        log.debug("REST request to save InspectionFinding : {}", inspectionFindingDTO);
        if (inspectionFindingDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionFinding cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionFindingDTO result = inspectionFindingService.save(inspectionFindingDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-findings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-findings} : Updates an existing inspectionFinding.
     *
     * @param inspectionFindingDTO the inspectionFindingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionFindingDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionFindingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionFindingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-findings")
    public ResponseEntity<InspectionFindingDTO> updateInspectionFinding(@Valid @RequestBody InspectionFindingDTO inspectionFindingDTO)
        throws URISyntaxException {
        log.debug("REST request to update InspectionFinding : {}", inspectionFindingDTO);
        if (inspectionFindingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionFindingDTO result = inspectionFindingService.save(inspectionFindingDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionFindingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-findings} : get all the inspectionFindings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionFindings in body.
     */
    @GetMapping("/inspection-findings")
    public ResponseEntity<List<InspectionFindingDTO>> getAllInspectionFindings(Pageable pageable) {
        log.debug("REST request to get a page of InspectionFindings");
        Page<InspectionFindingDTO> page = inspectionFindingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-findings/:id} : get the "id" inspectionFinding.
     *
     * @param id the id of the inspectionFindingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionFindingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-findings/{id}")
    public ResponseEntity<InspectionFindingDTO> getInspectionFinding(@PathVariable Long id) {
        log.debug("REST request to get InspectionFinding : {}", id);
        Optional<InspectionFindingDTO> inspectionFindingDTO = inspectionFindingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionFindingDTO);
    }

    /**
     * {@code DELETE  /inspection-findings/:id} : delete the "id" inspectionFinding.
     *
     * @param id the id of the inspectionFindingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-findings/{id}")
    public ResponseEntity<Void> deleteInspectionFinding(@PathVariable Long id) {
        log.debug("REST request to delete InspectionFinding : {}", id);
        inspectionFindingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

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
import org.tamisemi.iftmis.service.InspectionProcedureService;
import org.tamisemi.iftmis.service.dto.InspectionProcedureDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionProcedure}.
 */
@RestController
@RequestMapping("/api")
public class InspectionProcedureResource {
    private final Logger log = LoggerFactory.getLogger(InspectionProcedureResource.class);

    private static final String ENTITY_NAME = "inspectionProcedure";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionProcedureService inspectionProcedureService;

    public InspectionProcedureResource(InspectionProcedureService inspectionProcedureService) {
        this.inspectionProcedureService = inspectionProcedureService;
    }

    /**
     * {@code POST  /inspection-procedures} : Create a new inspectionProcedure.
     *
     * @param inspectionProcedureDTO the inspectionProcedureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionProcedureDTO, or with status {@code 400 (Bad Request)} if the inspectionProcedure has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-procedures")
    public ResponseEntity<InspectionProcedureDTO> createInspectionProcedure(
        @Valid @RequestBody InspectionProcedureDTO inspectionProcedureDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save InspectionProcedure : {}", inspectionProcedureDTO);
        if (inspectionProcedureDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionProcedure cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionProcedureDTO result = inspectionProcedureService.save(inspectionProcedureDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-procedures/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-procedures} : Updates an existing inspectionProcedure.
     *
     * @param inspectionProcedureDTO the inspectionProcedureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionProcedureDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionProcedureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionProcedureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-procedures")
    public ResponseEntity<InspectionProcedureDTO> updateInspectionProcedure(
        @Valid @RequestBody InspectionProcedureDTO inspectionProcedureDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update InspectionProcedure : {}", inspectionProcedureDTO);
        if (inspectionProcedureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionProcedureDTO result = inspectionProcedureService.save(inspectionProcedureDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionProcedureDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-procedures} : get all the inspectionProcedures.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionProcedures in body.
     */
    @GetMapping("/inspection-procedures")
    public ResponseEntity<List<InspectionProcedureDTO>> getAllInspectionProcedures(Pageable pageable) {
        log.debug("REST request to get a page of InspectionProcedures");
        Page<InspectionProcedureDTO> page = inspectionProcedureService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-procedures/:id} : get the "id" inspectionProcedure.
     *
     * @param id the id of the inspectionProcedureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionProcedureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-procedures/{id}")
    public ResponseEntity<InspectionProcedureDTO> getInspectionProcedure(@PathVariable Long id) {
        log.debug("REST request to get InspectionProcedure : {}", id);
        Optional<InspectionProcedureDTO> inspectionProcedureDTO = inspectionProcedureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionProcedureDTO);
    }

    /**
     * {@code DELETE  /inspection-procedures/:id} : delete the "id" inspectionProcedure.
     *
     * @param id the id of the inspectionProcedureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-procedures/{id}")
    public ResponseEntity<Void> deleteInspectionProcedure(@PathVariable Long id) {
        log.debug("REST request to delete InspectionProcedure : {}", id);
        inspectionProcedureService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

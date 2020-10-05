package org.tamisemi.iftmis.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
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
import org.tamisemi.iftmis.service.InspectionService;
import org.tamisemi.iftmis.service.dto.InspectionDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Inspection}.
 */
@RestController
@RequestMapping("/api")
public class InspectionResource {
    private final Logger log = LoggerFactory.getLogger(InspectionResource.class);

    private static final String ENTITY_NAME = "inspection";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionService inspectionService;

    public InspectionResource(InspectionService inspectionService) {
        this.inspectionService = inspectionService;
    }

    /**
     * {@code POST  /inspections} : Create a new inspection.
     *
     * @param inspectionDTO the inspectionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionDTO, or with status {@code 400 (Bad Request)} if the inspection has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspections")
    public ResponseEntity<InspectionDTO> createInspection(@Valid @RequestBody InspectionDTO inspectionDTO) throws URISyntaxException {
        log.debug("REST request to save Inspection : {}", inspectionDTO);
        if (inspectionDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspection cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionDTO result = inspectionService.save(inspectionDTO);
        return ResponseEntity
            .created(new URI("/api/inspections/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspections} : Updates an existing inspection.
     *
     * @param inspectionDTO the inspectionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspections")
    public ResponseEntity<InspectionDTO> updateInspection(@Valid @RequestBody InspectionDTO inspectionDTO) throws URISyntaxException {
        log.debug("REST request to update Inspection : {}", inspectionDTO);
        if (inspectionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionDTO result = inspectionService.save(inspectionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspections} : get all the inspections.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspections in body.
     */
    @GetMapping("/inspections/by-ou/{organisationUnitId}")
    public ResponseEntity<List<InspectionDTO>> getAllInspections(Pageable pageable, @PathVariable Long organisationUnitId) {
        log.debug("REST request to get a page of Inspections");
        Page<InspectionDTO> page = inspectionService.findAll(organisationUnitId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspections/:id} : get the "id" inspection.
     *
     * @param id the id of the inspectionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspections/{id}")
    public ResponseEntity<InspectionDTO> getInspection(@PathVariable Long id) {
        log.debug("REST request to get Inspection : {}", id);
        Optional<InspectionDTO> inspectionDTO = inspectionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionDTO);
    }

    /**
     * {@code DELETE  /inspections/:id} : delete the "id" inspection.
     *
     * @param id the id of the inspectionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspections/{id}")
    public ResponseEntity<Void> deleteInspection(@PathVariable Long id) {
        log.debug("REST request to delete Inspection : {}", id);
        inspectionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
    
    
    /**
     * {@code GET  /inspections} : get all the inspections By date Range.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspections in body.
     */
    @GetMapping("/inspections/by-date-range/{startDate}/{endDate}")
    public ResponseEntity<List<InspectionDTO>> getAllInspectionsByDateRange(Pageable pageable, @PathVariable String startDate,@PathVariable String endDate ) {
        log.debug("REST request to get a page of Inspections By date Range");
        LocalDate ISOStartDate = LocalDate.parse(startDate);
        LocalDate ISOEndDate = LocalDate.parse(endDate);
        Page<InspectionDTO> page = inspectionService.findByDateRange(ISOStartDate, ISOEndDate, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}

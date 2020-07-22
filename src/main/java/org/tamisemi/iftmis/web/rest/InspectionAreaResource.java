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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tamisemi.iftmis.service.InspectionAreaService;
import org.tamisemi.iftmis.service.dto.InspectionAreaDTO;
import org.tamisemi.iftmis.service.dto.InspectionAreaWithSubAreaDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionArea}.
 */
@RestController
@RequestMapping("/api")
public class InspectionAreaResource {
    private final Logger log = LoggerFactory.getLogger(InspectionAreaResource.class);

    private static final String ENTITY_NAME = "inspectionArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionAreaService inspectionAreaService;

    public InspectionAreaResource(InspectionAreaService inspectionAreaService) {
        this.inspectionAreaService = inspectionAreaService;
    }

    /**
     * {@code POST  /inspection-areas} : Create a new inspectionArea.
     *
     * @param inspectionAreaDTO the inspectionAreaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionAreaDTO, or with status {@code 400 (Bad Request)} if the inspectionArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-areas")
    public ResponseEntity<InspectionAreaDTO> createInspectionArea(@Valid @RequestBody InspectionAreaDTO inspectionAreaDTO)
        throws URISyntaxException {
        log.debug("REST request to save InspectionArea : {}", inspectionAreaDTO);
        if (inspectionAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionAreaDTO result = inspectionAreaService.save(inspectionAreaDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/inspection-areas/add")
    public ResponseEntity<Void> addInspectionAreas(@Valid @RequestBody List<InspectionAreaDTO> inspectionAreaDTOs) {
        inspectionAreaService.saveAll(inspectionAreaDTOs);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/inspection-areas/remove")
    public ResponseEntity<Void> removeInspectionAreas(@Valid @RequestBody List<InspectionAreaDTO> inspectionAreaDTOs) {
        inspectionAreaService.saveRemove(inspectionAreaDTOs);
        return ResponseEntity.noContent().build();
    }

    /**
     * {@code PUT  /inspection-areas} : Updates an existing inspectionArea.
     *
     * @param inspectionAreaDTO the inspectionAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionAreaDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionAreaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-areas")
    public ResponseEntity<InspectionAreaDTO> updateInspectionArea(@Valid @RequestBody InspectionAreaDTO inspectionAreaDTO)
        throws URISyntaxException {
        log.debug("REST request to update InspectionArea : {}", inspectionAreaDTO);
        if (inspectionAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionAreaDTO result = inspectionAreaService.save(inspectionAreaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-areas} : get all the inspectionAreas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionAreas in body.
     */
    @GetMapping("/inspection-areas")
    public ResponseEntity<List<InspectionAreaDTO>> getAllInspectionAreas(Pageable pageable) {
        log.debug("REST request to get a page of InspectionAreas");
        Page<InspectionAreaDTO> page = inspectionAreaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-areas} : get all the inspectionAreas by inspection id.
     *
     * @param inspectionId inspe id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionAreas in body.
     */
    @GetMapping("/inspection-areas/by-inspection/{inspectionId}")
    public ResponseEntity<List<InspectionAreaDTO>> getAllInspectionAreas(@PathVariable Long inspectionId) {
        log.debug("REST request to get a page of InspectionAreas");
        List<InspectionAreaDTO> result = inspectionAreaService.findAllByInspection(inspectionId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /inspection-areas} : get all the inspectionAreas by inspection id.
     *
     * @param inspectionId inspe id
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionAreas in body.
     */
    @GetMapping("/inspection-areas/by-inspection/{inspectionId}/with-sub-areas")
    public ResponseEntity<List<InspectionAreaWithSubAreaDTO>> getWithObjectives(@PathVariable Long inspectionId) {
        log.debug("REST request to get a page of InspectionAreas");
        List<InspectionAreaWithSubAreaDTO> result = inspectionAreaService.findAllWithSubArea(inspectionId);
        return ResponseEntity.ok().body(result);
    }

    /**
     * {@code GET  /inspection-areas/:id} : get the "id" inspectionArea.
     *
     * @param id the id of the inspectionAreaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionAreaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-areas/{id}")
    public ResponseEntity<InspectionAreaDTO> getInspectionArea(@PathVariable Long id) {
        log.debug("REST request to get InspectionArea : {}", id);
        Optional<InspectionAreaDTO> inspectionAreaDTO = inspectionAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionAreaDTO);
    }

    /**
     * {@code DELETE  /inspection-areas/:id} : delete the "id" inspectionArea.
     *
     * @param id the id of the inspectionAreaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-areas/{id}")
    public ResponseEntity<Void> deleteInspectionArea(@PathVariable Long id) {
        log.debug("REST request to delete InspectionArea : {}", id);
        inspectionAreaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

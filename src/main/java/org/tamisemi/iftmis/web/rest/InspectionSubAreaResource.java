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
import org.tamisemi.iftmis.service.InspectionSubAreaService;
import org.tamisemi.iftmis.service.dto.InspectionSubAreaDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionSubArea}.
 */
@RestController
@RequestMapping("/api")
public class InspectionSubAreaResource {
    private final Logger log = LoggerFactory.getLogger(InspectionSubAreaResource.class);

    private static final String ENTITY_NAME = "inspectionSubArea";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionSubAreaService inspectionSubAreaService;

    public InspectionSubAreaResource(InspectionSubAreaService inspectionSubAreaService) {
        this.inspectionSubAreaService = inspectionSubAreaService;
    }

    /**
     * {@code POST  /inspection-sub-areas} : Create a new inspectionSubArea.
     *
     * @param inspectionSubAreaDTO the inspectionSubAreaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionSubAreaDTO, or with status {@code 400 (Bad Request)} if the inspectionSubArea has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-sub-areas")
    public ResponseEntity<InspectionSubAreaDTO> createInspectionSubArea(@Valid @RequestBody InspectionSubAreaDTO inspectionSubAreaDTO)
        throws URISyntaxException {
        log.debug("REST request to save InspectionSubArea : {}", inspectionSubAreaDTO);
        if (inspectionSubAreaDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionSubArea cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionSubAreaDTO result = inspectionSubAreaService.save(inspectionSubAreaDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-sub-areas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-sub-areas} : Updates an existing inspectionSubArea.
     *
     * @param inspectionSubAreaDTO the inspectionSubAreaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionSubAreaDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionSubAreaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionSubAreaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-sub-areas")
    public ResponseEntity<InspectionSubAreaDTO> updateInspectionSubArea(@Valid @RequestBody InspectionSubAreaDTO inspectionSubAreaDTO)
        throws URISyntaxException {
        log.debug("REST request to update InspectionSubArea : {}", inspectionSubAreaDTO);
        if (inspectionSubAreaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionSubAreaDTO result = inspectionSubAreaService.save(inspectionSubAreaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionSubAreaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-sub-areas} : get all the inspectionSubAreas.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionSubAreas in body.
     */
    @GetMapping("/inspection-sub-areas")
    public ResponseEntity<List<InspectionSubAreaDTO>> getAllInspectionSubAreas(Pageable pageable) {
        log.debug("REST request to get a page of InspectionSubAreas");
        Page<InspectionSubAreaDTO> page = inspectionSubAreaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-sub-areas/:id} : get the "id" inspectionSubArea.
     *
     * @param id the id of the inspectionSubAreaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionSubAreaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-sub-areas/{id}")
    public ResponseEntity<InspectionSubAreaDTO> getInspectionSubArea(@PathVariable Long id) {
        log.debug("REST request to get InspectionSubArea : {}", id);
        Optional<InspectionSubAreaDTO> inspectionSubAreaDTO = inspectionSubAreaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionSubAreaDTO);
    }

    /**
     * {@code DELETE  /inspection-sub-areas/:id} : delete the "id" inspectionSubArea.
     *
     * @param id the id of the inspectionSubAreaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-sub-areas/{id}")
    public ResponseEntity<Void> deleteInspectionSubArea(@PathVariable Long id) {
        log.debug("REST request to delete InspectionSubArea : {}", id);
        inspectionSubAreaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

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
import org.tamisemi.iftmis.service.InspectionObjectiveService;
import org.tamisemi.iftmis.service.dto.InspectionObjectiveDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionObjective}.
 */
@RestController
@RequestMapping("/api")
public class InspectionObjectiveResource {
    private final Logger log = LoggerFactory.getLogger(InspectionObjectiveResource.class);

    private static final String ENTITY_NAME = "inspectionObjective";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionObjectiveService inspectionObjectiveService;

    public InspectionObjectiveResource(InspectionObjectiveService inspectionObjectiveService) {
        this.inspectionObjectiveService = inspectionObjectiveService;
    }

    /**
     * {@code POST  /inspection-objectives} : Create a new inspectionObjective.
     *
     * @param inspectionObjectiveDTO the inspectionObjectiveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionObjectiveDTO, or with status {@code 400 (Bad Request)} if the inspectionObjective has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-objectives")
    public ResponseEntity<InspectionObjectiveDTO> createInspectionObjective(
        @Valid @RequestBody InspectionObjectiveDTO inspectionObjectiveDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save InspectionObjective : {}", inspectionObjectiveDTO);
        if (inspectionObjectiveDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionObjective cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionObjectiveDTO result = inspectionObjectiveService.save(inspectionObjectiveDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-objectives/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-objectives} : Updates an existing inspectionObjective.
     *
     * @param inspectionObjectiveDTO the inspectionObjectiveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionObjectiveDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionObjectiveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionObjectiveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-objectives")
    public ResponseEntity<InspectionObjectiveDTO> updateInspectionObjective(
        @Valid @RequestBody InspectionObjectiveDTO inspectionObjectiveDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update InspectionObjective : {}", inspectionObjectiveDTO);
        if (inspectionObjectiveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionObjectiveDTO result = inspectionObjectiveService.save(inspectionObjectiveDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionObjectiveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-objectives} : get all the inspectionObjectives.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionObjectives in body.
     */
    @GetMapping("/inspection-objectives")
    public ResponseEntity<List<InspectionObjectiveDTO>> getAllInspectionObjectives(Pageable pageable) {
        log.debug("REST request to get a page of InspectionObjectives");
        Page<InspectionObjectiveDTO> page = inspectionObjectiveService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-objectives/:id} : get the "id" inspectionObjective.
     *
     * @param id the id of the inspectionObjectiveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionObjectiveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-objectives/{id}")
    public ResponseEntity<InspectionObjectiveDTO> getInspectionObjective(@PathVariable Long id) {
        log.debug("REST request to get InspectionObjective : {}", id);
        Optional<InspectionObjectiveDTO> inspectionObjectiveDTO = inspectionObjectiveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionObjectiveDTO);
    }

    /**
     * {@code DELETE  /inspection-objectives/:id} : delete the "id" inspectionObjective.
     *
     * @param id the id of the inspectionObjectiveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-objectives/{id}")
    public ResponseEntity<Void> deleteInspectionObjective(@PathVariable Long id) {
        log.debug("REST request to delete InspectionObjective : {}", id);
        inspectionObjectiveService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

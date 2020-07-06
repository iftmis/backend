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
import org.tamisemi.iftmis.service.InspectionWorkDoneService;
import org.tamisemi.iftmis.service.dto.InspectionWorkDoneDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionWorkDone}.
 */
@RestController
@RequestMapping("/api")
public class InspectionWorkDoneResource {
    private final Logger log = LoggerFactory.getLogger(InspectionWorkDoneResource.class);

    private static final String ENTITY_NAME = "inspectionWorkDone";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionWorkDoneService inspectionWorkDoneService;

    public InspectionWorkDoneResource(InspectionWorkDoneService inspectionWorkDoneService) {
        this.inspectionWorkDoneService = inspectionWorkDoneService;
    }

    /**
     * {@code POST  /inspection-work-dones} : Create a new inspectionWorkDone.
     *
     * @param inspectionWorkDoneDTO the inspectionWorkDoneDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionWorkDoneDTO, or with status {@code 400 (Bad Request)} if the inspectionWorkDone has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-work-dones")
    public ResponseEntity<InspectionWorkDoneDTO> createInspectionWorkDone(@Valid @RequestBody InspectionWorkDoneDTO inspectionWorkDoneDTO)
        throws URISyntaxException {
        log.debug("REST request to save InspectionWorkDone : {}", inspectionWorkDoneDTO);
        if (inspectionWorkDoneDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionWorkDone cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionWorkDoneDTO result = inspectionWorkDoneService.save(inspectionWorkDoneDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-work-dones/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-work-dones} : Updates an existing inspectionWorkDone.
     *
     * @param inspectionWorkDoneDTO the inspectionWorkDoneDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionWorkDoneDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionWorkDoneDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionWorkDoneDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-work-dones")
    public ResponseEntity<InspectionWorkDoneDTO> updateInspectionWorkDone(@Valid @RequestBody InspectionWorkDoneDTO inspectionWorkDoneDTO)
        throws URISyntaxException {
        log.debug("REST request to update InspectionWorkDone : {}", inspectionWorkDoneDTO);
        if (inspectionWorkDoneDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionWorkDoneDTO result = inspectionWorkDoneService.save(inspectionWorkDoneDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionWorkDoneDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-work-dones} : get all the inspectionWorkDones.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionWorkDones in body.
     */
    @GetMapping("/inspection-work-dones")
    public ResponseEntity<List<InspectionWorkDoneDTO>> getAllInspectionWorkDones(Pageable pageable) {
        log.debug("REST request to get a page of InspectionWorkDones");
        Page<InspectionWorkDoneDTO> page = inspectionWorkDoneService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-work-dones/:id} : get the "id" inspectionWorkDone.
     *
     * @param id the id of the inspectionWorkDoneDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionWorkDoneDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-work-dones/{id}")
    public ResponseEntity<InspectionWorkDoneDTO> getInspectionWorkDone(@PathVariable Long id) {
        log.debug("REST request to get InspectionWorkDone : {}", id);
        Optional<InspectionWorkDoneDTO> inspectionWorkDoneDTO = inspectionWorkDoneService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionWorkDoneDTO);
    }

    /**
     * {@code DELETE  /inspection-work-dones/:id} : delete the "id" inspectionWorkDone.
     *
     * @param id the id of the inspectionWorkDoneDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-work-dones/{id}")
    public ResponseEntity<Void> deleteInspectionWorkDone(@PathVariable Long id) {
        log.debug("REST request to delete InspectionWorkDone : {}", id);
        inspectionWorkDoneService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

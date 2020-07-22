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
import org.tamisemi.iftmis.service.InspectionBudgetService;
import org.tamisemi.iftmis.service.dto.InspectionBudgetDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionBudget}.
 */
@RestController
@RequestMapping("/api")
public class InspectionBudgetResource {
    private final Logger log = LoggerFactory.getLogger(InspectionBudgetResource.class);

    private static final String ENTITY_NAME = "inspectionBudget";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionBudgetService inspectionBudgetService;

    public InspectionBudgetResource(InspectionBudgetService inspectionBudgetService) {
        this.inspectionBudgetService = inspectionBudgetService;
    }

    /**
     * {@code POST  /inspection-budgets} : Create a new inspectionBudget.
     *
     * @param inspectionBudgetDTO the inspectionBudgetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionBudgetDTO, or with status {@code 400 (Bad Request)} if the inspectionBudget has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-budgets")
    public ResponseEntity<InspectionBudgetDTO> createInspectionBudget(@Valid @RequestBody InspectionBudgetDTO inspectionBudgetDTO)
        throws URISyntaxException {
        log.debug("REST request to save InspectionBudget : {}", inspectionBudgetDTO);
        if (inspectionBudgetDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionBudget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionBudgetDTO result = inspectionBudgetService.save(inspectionBudgetDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-budgets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-budgets} : Updates an existing inspectionBudget.
     *
     * @param inspectionBudgetDTO the inspectionBudgetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionBudgetDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionBudgetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionBudgetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-budgets")
    public ResponseEntity<InspectionBudgetDTO> updateInspectionBudget(@Valid @RequestBody InspectionBudgetDTO inspectionBudgetDTO)
        throws URISyntaxException {
        log.debug("REST request to update InspectionBudget : {}", inspectionBudgetDTO);
        if (inspectionBudgetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionBudgetDTO result = inspectionBudgetService.save(inspectionBudgetDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionBudgetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-budgets} : get all the inspectionBudgets.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionBudgets in body.
     */
    @GetMapping("/inspection-budgets")
    public ResponseEntity<List<InspectionBudgetDTO>> getAllInspectionBudgets(Pageable pageable) {
        log.debug("REST request to get a page of InspectionBudgets");
        Page<InspectionBudgetDTO> page = inspectionBudgetService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-budgets/:id} : get the "id" inspectionBudget.
     *
     * @param id the id of the inspectionBudgetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionBudgetDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-budgets/{id}")
    public ResponseEntity<InspectionBudgetDTO> getInspectionBudget(@PathVariable Long id) {
        log.debug("REST request to get InspectionBudget : {}", id);
        Optional<InspectionBudgetDTO> inspectionBudgetDTO = inspectionBudgetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionBudgetDTO);
    }

    /**
     * {@code DELETE  /inspection-budgets/:id} : delete the "id" inspectionBudget.
     *
     * @param id the id of the inspectionBudgetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-budgets/{id}")
    public ResponseEntity<Void> deleteInspectionBudget(@PathVariable Long id) {
        log.debug("REST request to delete InspectionBudget : {}", id);
        inspectionBudgetService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

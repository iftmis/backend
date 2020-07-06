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
import org.tamisemi.iftmis.service.InspectionRecommendationService;
import org.tamisemi.iftmis.service.dto.InspectionRecommendationDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionRecommendation}.
 */
@RestController
@RequestMapping("/api")
public class InspectionRecommendationResource {
    private final Logger log = LoggerFactory.getLogger(InspectionRecommendationResource.class);

    private static final String ENTITY_NAME = "inspectionRecommendation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionRecommendationService inspectionRecommendationService;

    public InspectionRecommendationResource(InspectionRecommendationService inspectionRecommendationService) {
        this.inspectionRecommendationService = inspectionRecommendationService;
    }

    /**
     * {@code POST  /inspection-recommendations} : Create a new inspectionRecommendation.
     *
     * @param inspectionRecommendationDTO the inspectionRecommendationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionRecommendationDTO, or with status {@code 400 (Bad Request)} if the inspectionRecommendation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-recommendations")
    public ResponseEntity<InspectionRecommendationDTO> createInspectionRecommendation(
        @Valid @RequestBody InspectionRecommendationDTO inspectionRecommendationDTO
    )
        throws URISyntaxException {
        log.debug("REST request to save InspectionRecommendation : {}", inspectionRecommendationDTO);
        if (inspectionRecommendationDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionRecommendation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionRecommendationDTO result = inspectionRecommendationService.save(inspectionRecommendationDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-recommendations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-recommendations} : Updates an existing inspectionRecommendation.
     *
     * @param inspectionRecommendationDTO the inspectionRecommendationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionRecommendationDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionRecommendationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionRecommendationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-recommendations")
    public ResponseEntity<InspectionRecommendationDTO> updateInspectionRecommendation(
        @Valid @RequestBody InspectionRecommendationDTO inspectionRecommendationDTO
    )
        throws URISyntaxException {
        log.debug("REST request to update InspectionRecommendation : {}", inspectionRecommendationDTO);
        if (inspectionRecommendationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionRecommendationDTO result = inspectionRecommendationService.save(inspectionRecommendationDTO);
        return ResponseEntity
            .ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionRecommendationDTO.getId().toString())
            )
            .body(result);
    }

    /**
     * {@code GET  /inspection-recommendations} : get all the inspectionRecommendations.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionRecommendations in body.
     */
    @GetMapping("/inspection-recommendations")
    public ResponseEntity<List<InspectionRecommendationDTO>> getAllInspectionRecommendations(Pageable pageable) {
        log.debug("REST request to get a page of InspectionRecommendations");
        Page<InspectionRecommendationDTO> page = inspectionRecommendationService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-recommendations/:id} : get the "id" inspectionRecommendation.
     *
     * @param id the id of the inspectionRecommendationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionRecommendationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-recommendations/{id}")
    public ResponseEntity<InspectionRecommendationDTO> getInspectionRecommendation(@PathVariable Long id) {
        log.debug("REST request to get InspectionRecommendation : {}", id);
        Optional<InspectionRecommendationDTO> inspectionRecommendationDTO = inspectionRecommendationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionRecommendationDTO);
    }

    /**
     * {@code DELETE  /inspection-recommendations/:id} : delete the "id" inspectionRecommendation.
     *
     * @param id the id of the inspectionRecommendationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-recommendations/{id}")
    public ResponseEntity<Void> deleteInspectionRecommendation(@PathVariable Long id) {
        log.debug("REST request to delete InspectionRecommendation : {}", id);
        inspectionRecommendationService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

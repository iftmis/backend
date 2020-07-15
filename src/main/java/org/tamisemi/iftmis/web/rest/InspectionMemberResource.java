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
import org.tamisemi.iftmis.service.InspectionMemberService;
import org.tamisemi.iftmis.service.dto.InspectionMemberDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionMember}.
 */
@RestController
@RequestMapping("/api")
public class InspectionMemberResource {
    private final Logger log = LoggerFactory.getLogger(InspectionMemberResource.class);

    private static final String ENTITY_NAME = "inspectionMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionMemberService inspectionMemberService;

    public InspectionMemberResource(InspectionMemberService inspectionMemberService) {
        this.inspectionMemberService = inspectionMemberService;
    }

    /**
     * {@code POST  /inspection-members} : Create a new inspectionMember.
     *
     * @param inspectionMemberDTO the inspectionMemberDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionMemberDTO, or with status {@code 400 (Bad Request)} if the inspectionMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-members")
    public ResponseEntity<InspectionMemberDTO> createInspectionMember(@Valid @RequestBody InspectionMemberDTO inspectionMemberDTO)
        throws URISyntaxException {
        log.debug("REST request to save InspectionMember : {}", inspectionMemberDTO);
        if (inspectionMemberDTO.getId() != null) {
            throw new BadRequestAlertException("A new inspectionMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionMemberDTO result = inspectionMemberService.save(inspectionMemberDTO);
        return ResponseEntity
            .created(new URI("/api/inspection-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-members} : Updates an existing inspectionMember.
     *
     * @param inspectionMemberDTO the inspectionMemberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionMemberDTO,
     * or with status {@code 400 (Bad Request)} if the inspectionMemberDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionMemberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-members")
    public ResponseEntity<InspectionMemberDTO> updateInspectionMember(@Valid @RequestBody InspectionMemberDTO inspectionMemberDTO)
        throws URISyntaxException {
        log.debug("REST request to update InspectionMember : {}", inspectionMemberDTO);
        if (inspectionMemberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionMemberDTO result = inspectionMemberService.save(inspectionMemberDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionMemberDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-members} : get all the inspectionMembers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionMembers in body.
     */
    @GetMapping("/inspection-members/by-inspection/{inspectionId}")
    public ResponseEntity<List<InspectionMemberDTO>> getAllInspectionMembers(Pageable pageable, @PathVariable Long inspectionId) {
        log.debug("REST request to get a page of InspectionMembers");
        Page<InspectionMemberDTO> page = inspectionMemberService.findAll(inspectionId, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /inspection-members/:id} : get the "id" inspectionMember.
     *
     * @param id the id of the inspectionMemberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionMemberDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-members/{id}")
    public ResponseEntity<InspectionMemberDTO> getInspectionMember(@PathVariable Long id) {
        log.debug("REST request to get InspectionMember : {}", id);
        Optional<InspectionMemberDTO> inspectionMemberDTO = inspectionMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(inspectionMemberDTO);
    }

    /**
     * {@code DELETE  /inspection-members/:id} : delete the "id" inspectionMember.
     *
     * @param id the id of the inspectionMemberDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-members/{id}")
    public ResponseEntity<Void> deleteInspectionMember(@PathVariable Long id) {
        log.debug("REST request to delete InspectionMember : {}", id);
        inspectionMemberService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

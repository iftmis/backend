package org.tamisemi.iftmis.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.tamisemi.iftmis.domain.InspectionMember;
import org.tamisemi.iftmis.repository.InspectionMemberRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.InspectionMember}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class InspectionMemberResource {
    private final Logger log = LoggerFactory.getLogger(InspectionMemberResource.class);

    private static final String ENTITY_NAME = "inspectionMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InspectionMemberRepository inspectionMemberRepository;

    public InspectionMemberResource(InspectionMemberRepository inspectionMemberRepository) {
        this.inspectionMemberRepository = inspectionMemberRepository;
    }

    /**
     * {@code POST  /inspection-members} : Create a new inspectionMember.
     *
     * @param inspectionMember the inspectionMember to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new inspectionMember, or with status {@code 400 (Bad Request)} if the inspectionMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/inspection-members")
    public ResponseEntity<InspectionMember> createInspectionMember(@Valid @RequestBody InspectionMember inspectionMember)
        throws URISyntaxException {
        log.debug("REST request to save InspectionMember : {}", inspectionMember);
        if (inspectionMember.getId() != null) {
            throw new BadRequestAlertException("A new inspectionMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InspectionMember result = inspectionMemberRepository.save(inspectionMember);
        return ResponseEntity
            .created(new URI("/api/inspection-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /inspection-members} : Updates an existing inspectionMember.
     *
     * @param inspectionMember the inspectionMember to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated inspectionMember,
     * or with status {@code 400 (Bad Request)} if the inspectionMember is not valid,
     * or with status {@code 500 (Internal Server Error)} if the inspectionMember couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/inspection-members")
    public ResponseEntity<InspectionMember> updateInspectionMember(@Valid @RequestBody InspectionMember inspectionMember)
        throws URISyntaxException {
        log.debug("REST request to update InspectionMember : {}", inspectionMember);
        if (inspectionMember.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InspectionMember result = inspectionMemberRepository.save(inspectionMember);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, inspectionMember.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /inspection-members} : get all the inspectionMembers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of inspectionMembers in body.
     */
    @GetMapping("/inspection-members")
    public List<InspectionMember> getAllInspectionMembers() {
        log.debug("REST request to get all InspectionMembers");
        return inspectionMemberRepository.findAll();
    }

    /**
     * {@code GET  /inspection-members/:id} : get the "id" inspectionMember.
     *
     * @param id the id of the inspectionMember to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the inspectionMember, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/inspection-members/{id}")
    public ResponseEntity<InspectionMember> getInspectionMember(@PathVariable Long id) {
        log.debug("REST request to get InspectionMember : {}", id);
        Optional<InspectionMember> inspectionMember = inspectionMemberRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(inspectionMember);
    }

    /**
     * {@code DELETE  /inspection-members/:id} : delete the "id" inspectionMember.
     *
     * @param id the id of the inspectionMember to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/inspection-members/{id}")
    public ResponseEntity<Void> deleteInspectionMember(@PathVariable Long id) {
        log.debug("REST request to delete InspectionMember : {}", id);
        inspectionMemberRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

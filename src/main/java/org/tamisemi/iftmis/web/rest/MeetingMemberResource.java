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
import org.tamisemi.iftmis.domain.MeetingMember;
import org.tamisemi.iftmis.repository.MeetingMemberRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.MeetingMember}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MeetingMemberResource {
    private final Logger log = LoggerFactory.getLogger(MeetingMemberResource.class);

    private static final String ENTITY_NAME = "meetingMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeetingMemberRepository meetingMemberRepository;

    public MeetingMemberResource(MeetingMemberRepository meetingMemberRepository) {
        this.meetingMemberRepository = meetingMemberRepository;
    }

    /**
     * {@code POST  /meeting-members} : Create a new meetingMember.
     *
     * @param meetingMember the meetingMember to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new meetingMember, or with status {@code 400 (Bad Request)} if the meetingMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meeting-members")
    public ResponseEntity<MeetingMember> createMeetingMember(@Valid @RequestBody MeetingMember meetingMember) throws URISyntaxException {
        log.debug("REST request to save MeetingMember : {}", meetingMember);
        if (meetingMember.getId() != null) {
            throw new BadRequestAlertException("A new meetingMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MeetingMember result = meetingMemberRepository.save(meetingMember);
        return ResponseEntity
            .created(new URI("/api/meeting-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meeting-members} : Updates an existing meetingMember.
     *
     * @param meetingMember the meetingMember to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated meetingMember,
     * or with status {@code 400 (Bad Request)} if the meetingMember is not valid,
     * or with status {@code 500 (Internal Server Error)} if the meetingMember couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meeting-members")
    public ResponseEntity<MeetingMember> updateMeetingMember(@Valid @RequestBody MeetingMember meetingMember) throws URISyntaxException {
        log.debug("REST request to update MeetingMember : {}", meetingMember);
        if (meetingMember.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeetingMember result = meetingMemberRepository.save(meetingMember);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, meetingMember.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meeting-members} : get all the meetingMembers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meetingMembers in body.
     */
    @GetMapping("/meeting-members")
    public List<MeetingMember> getAllMeetingMembers() {
        log.debug("REST request to get all MeetingMembers");
        return meetingMemberRepository.findAll();
    }

    /**
     * {@code GET  /meeting-members/:id} : get the "id" meetingMember.
     *
     * @param id the id of the meetingMember to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the meetingMember, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meeting-members/{id}")
    public ResponseEntity<MeetingMember> getMeetingMember(@PathVariable Long id) {
        log.debug("REST request to get MeetingMember : {}", id);
        Optional<MeetingMember> meetingMember = meetingMemberRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(meetingMember);
    }

    /**
     * {@code DELETE  /meeting-members/:id} : delete the "id" meetingMember.
     *
     * @param id the id of the meetingMember to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meeting-members/{id}")
    public ResponseEntity<Void> deleteMeetingMember(@PathVariable Long id) {
        log.debug("REST request to delete MeetingMember : {}", id);
        meetingMemberRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

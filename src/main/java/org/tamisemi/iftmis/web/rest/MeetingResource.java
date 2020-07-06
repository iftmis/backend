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
import org.tamisemi.iftmis.domain.Meeting;
import org.tamisemi.iftmis.repository.MeetingRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Meeting}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MeetingResource {
    private final Logger log = LoggerFactory.getLogger(MeetingResource.class);

    private static final String ENTITY_NAME = "meeting";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeetingRepository meetingRepository;

    public MeetingResource(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    /**
     * {@code POST  /meetings} : Create a new meeting.
     *
     * @param meeting the meeting to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new meeting, or with status {@code 400 (Bad Request)} if the meeting has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meetings")
    public ResponseEntity<Meeting> createMeeting(@Valid @RequestBody Meeting meeting) throws URISyntaxException {
        log.debug("REST request to save Meeting : {}", meeting);
        if (meeting.getId() != null) {
            throw new BadRequestAlertException("A new meeting cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Meeting result = meetingRepository.save(meeting);
        return ResponseEntity
            .created(new URI("/api/meetings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meetings} : Updates an existing meeting.
     *
     * @param meeting the meeting to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated meeting,
     * or with status {@code 400 (Bad Request)} if the meeting is not valid,
     * or with status {@code 500 (Internal Server Error)} if the meeting couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meetings")
    public ResponseEntity<Meeting> updateMeeting(@Valid @RequestBody Meeting meeting) throws URISyntaxException {
        log.debug("REST request to update Meeting : {}", meeting);
        if (meeting.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Meeting result = meetingRepository.save(meeting);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, meeting.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meetings} : get all the meetings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meetings in body.
     */
    @GetMapping("/meetings")
    public List<Meeting> getAllMeetings() {
        log.debug("REST request to get all Meetings");
        return meetingRepository.findAll();
    }

    /**
     * {@code GET  /meetings/:id} : get the "id" meeting.
     *
     * @param id the id of the meeting to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the meeting, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meetings/{id}")
    public ResponseEntity<Meeting> getMeeting(@PathVariable Long id) {
        log.debug("REST request to get Meeting : {}", id);
        Optional<Meeting> meeting = meetingRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(meeting);
    }

    /**
     * {@code DELETE  /meetings/:id} : delete the "id" meeting.
     *
     * @param id the id of the meeting to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meetings/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        log.debug("REST request to delete Meeting : {}", id);
        meetingRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

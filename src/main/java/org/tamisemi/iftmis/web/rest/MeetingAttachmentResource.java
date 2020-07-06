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
import org.tamisemi.iftmis.domain.MeetingAttachment;
import org.tamisemi.iftmis.repository.MeetingAttachmentRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.MeetingAttachment}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MeetingAttachmentResource {
    private final Logger log = LoggerFactory.getLogger(MeetingAttachmentResource.class);

    private static final String ENTITY_NAME = "meetingAttachment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeetingAttachmentRepository meetingAttachmentRepository;

    public MeetingAttachmentResource(MeetingAttachmentRepository meetingAttachmentRepository) {
        this.meetingAttachmentRepository = meetingAttachmentRepository;
    }

    /**
     * {@code POST  /meeting-attachments} : Create a new meetingAttachment.
     *
     * @param meetingAttachment the meetingAttachment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new meetingAttachment, or with status {@code 400 (Bad Request)} if the meetingAttachment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meeting-attachments")
    public ResponseEntity<MeetingAttachment> createMeetingAttachment(@Valid @RequestBody MeetingAttachment meetingAttachment)
        throws URISyntaxException {
        log.debug("REST request to save MeetingAttachment : {}", meetingAttachment);
        if (meetingAttachment.getId() != null) {
            throw new BadRequestAlertException("A new meetingAttachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MeetingAttachment result = meetingAttachmentRepository.save(meetingAttachment);
        return ResponseEntity
            .created(new URI("/api/meeting-attachments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meeting-attachments} : Updates an existing meetingAttachment.
     *
     * @param meetingAttachment the meetingAttachment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated meetingAttachment,
     * or with status {@code 400 (Bad Request)} if the meetingAttachment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the meetingAttachment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meeting-attachments")
    public ResponseEntity<MeetingAttachment> updateMeetingAttachment(@Valid @RequestBody MeetingAttachment meetingAttachment)
        throws URISyntaxException {
        log.debug("REST request to update MeetingAttachment : {}", meetingAttachment);
        if (meetingAttachment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeetingAttachment result = meetingAttachmentRepository.save(meetingAttachment);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, meetingAttachment.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meeting-attachments} : get all the meetingAttachments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meetingAttachments in body.
     */
    @GetMapping("/meeting-attachments")
    public List<MeetingAttachment> getAllMeetingAttachments() {
        log.debug("REST request to get all MeetingAttachments");
        return meetingAttachmentRepository.findAll();
    }

    /**
     * {@code GET  /meeting-attachments/:id} : get the "id" meetingAttachment.
     *
     * @param id the id of the meetingAttachment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the meetingAttachment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meeting-attachments/{id}")
    public ResponseEntity<MeetingAttachment> getMeetingAttachment(@PathVariable Long id) {
        log.debug("REST request to get MeetingAttachment : {}", id);
        Optional<MeetingAttachment> meetingAttachment = meetingAttachmentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(meetingAttachment);
    }

    /**
     * {@code DELETE  /meeting-attachments/:id} : delete the "id" meetingAttachment.
     *
     * @param id the id of the meetingAttachment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meeting-attachments/{id}")
    public ResponseEntity<Void> deleteMeetingAttachment(@PathVariable Long id) {
        log.debug("REST request to delete MeetingAttachment : {}", id);
        meetingAttachmentRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

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
import org.tamisemi.iftmis.service.MeetingAttachmentService;
import org.tamisemi.iftmis.service.dto.MeetingAttachmentDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.MeetingAttachment}.
 */
@RestController
@RequestMapping("/api")
public class MeetingAttachmentResource {
    private final Logger log = LoggerFactory.getLogger(MeetingAttachmentResource.class);

    private static final String ENTITY_NAME = "meetingAttachment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeetingAttachmentService meetingAttachmentService;

    public MeetingAttachmentResource(MeetingAttachmentService meetingAttachmentService) {
        this.meetingAttachmentService = meetingAttachmentService;
    }

    /**
     * {@code POST  /meeting-attachments} : Create a new meetingAttachment.
     *
     * @param meetingAttachmentDTO the meetingAttachmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new meetingAttachmentDTO, or with status {@code 400 (Bad Request)} if the meetingAttachment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meeting-attachments")
    public ResponseEntity<MeetingAttachmentDTO> createMeetingAttachment(@Valid @RequestBody MeetingAttachmentDTO meetingAttachmentDTO)
        throws URISyntaxException {
        log.debug("REST request to save MeetingAttachment : {}", meetingAttachmentDTO);
        if (meetingAttachmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new meetingAttachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MeetingAttachmentDTO result = meetingAttachmentService.save(meetingAttachmentDTO);
        return ResponseEntity
            .created(new URI("/api/meeting-attachments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meeting-attachments} : Updates an existing meetingAttachment.
     *
     * @param meetingAttachmentDTO the meetingAttachmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated meetingAttachmentDTO,
     * or with status {@code 400 (Bad Request)} if the meetingAttachmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the meetingAttachmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meeting-attachments")
    public ResponseEntity<MeetingAttachmentDTO> updateMeetingAttachment(@Valid @RequestBody MeetingAttachmentDTO meetingAttachmentDTO)
        throws URISyntaxException {
        log.debug("REST request to update MeetingAttachment : {}", meetingAttachmentDTO);
        if (meetingAttachmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeetingAttachmentDTO result = meetingAttachmentService.save(meetingAttachmentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, meetingAttachmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meeting-attachments} : get all the meetingAttachments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meetingAttachments in body.
     */
    @GetMapping("/meeting-attachments")
    public ResponseEntity<List<MeetingAttachmentDTO>> getAllMeetingAttachments(Pageable pageable) {
        log.debug("REST request to get a page of MeetingAttachments");
        Page<MeetingAttachmentDTO> page = meetingAttachmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /meeting-attachments/:id} : get the "id" meetingAttachment.
     *
     * @param id the id of the meetingAttachmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the meetingAttachmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meeting-attachments/{id}")
    public ResponseEntity<MeetingAttachmentDTO> getMeetingAttachment(@PathVariable Long id) {
        log.debug("REST request to get MeetingAttachment : {}", id);
        Optional<MeetingAttachmentDTO> meetingAttachmentDTO = meetingAttachmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(meetingAttachmentDTO);
    }

    /**
     * {@code DELETE  /meeting-attachments/:id} : delete the "id" meetingAttachment.
     *
     * @param id the id of the meetingAttachmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meeting-attachments/{id}")
    public ResponseEntity<Void> deleteMeetingAttachment(@PathVariable Long id) {
        log.debug("REST request to delete MeetingAttachment : {}", id);
        meetingAttachmentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

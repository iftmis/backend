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
import org.tamisemi.iftmis.domain.Meeting;
import org.tamisemi.iftmis.domain.enumeration.MeetingType;
import org.tamisemi.iftmis.service.MeetingService;
import org.tamisemi.iftmis.service.dto.MeetingDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Meeting}.
 */
@RestController
@RequestMapping("/api")
public class MeetingResource {
    private final Logger log = LoggerFactory.getLogger(MeetingResource.class);

    private static final String ENTITY_NAME = "meeting";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeetingService meetingService;

    public MeetingResource(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    /**
     * {@code POST  /meetings} : Create a new meeting.
     *
     * @param meetingDTO the meetingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new meetingDTO, or with status {@code 400 (Bad Request)} if the meeting has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meetings")
    public ResponseEntity<MeetingDTO> createMeeting(@Valid @RequestBody MeetingDTO meetingDTO) throws URISyntaxException {
        log.debug("REST request to save Meeting : {}", meetingDTO);
        if (meetingDTO.getId() != null) {
            throw new BadRequestAlertException("A new meeting cannot already have an ID", ENTITY_NAME, "idexists");
        }

        MeetingDTO result = meetingService.save(meetingDTO);
        return ResponseEntity
            .created(new URI("/api/meetings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meetings} : Updates an existing meeting.
     *
     * @param meetingDTO the meetingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated meetingDTO,
     * or with status {@code 400 (Bad Request)} if the meetingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the meetingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meetings")
    public ResponseEntity<MeetingDTO> updateMeeting(@Valid @RequestBody MeetingDTO meetingDTO) throws URISyntaxException {
        log.debug("REST request to update Meeting : {}", meetingDTO);
        if (meetingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeetingDTO result = meetingService.save(meetingDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, meetingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meetings} : get all the meetings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meetings in body.
     */
    @GetMapping("/meetings")
    public ResponseEntity<List<MeetingDTO>> getAllMeetings(Pageable pageable) {
        log.debug("REST request to get a page of Meetings");
        Page<MeetingDTO> page = meetingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /meetings/:id} : get the "id" meeting.
     *
     * @param id the id of the meetingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the meetingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meetings/{id}")
    public ResponseEntity<MeetingDTO> getMeeting(@PathVariable Long id) {
        log.debug("REST request to get Meeting : {}", id);
        Optional<MeetingDTO> meetingDTO = meetingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(meetingDTO);
    }

    @GetMapping("/meetings/inspection/{inspectionId}/type/{meetingType}")
    public ResponseEntity<List<MeetingDTO>> getMeetingByInspectionIdAndMeetingType(
        Pageable pageable,
        @PathVariable Long inspectionId,
        @PathVariable String meetingType
    ) {
        log.debug("REST request to get MInspection id : {}", inspectionId);
        MeetingType meetingTypeEnum = MeetingType.valueOf(meetingType);
        Page<MeetingDTO> page = meetingService.findByInspection_IdAndType(inspectionId, meetingTypeEnum, pageable);

        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code DELETE  /meetings/:id} : delete the "id" meeting.
     *
     * @param id the id of the meetingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meetings/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        log.debug("REST request to delete Meeting : {}", id);
        meetingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

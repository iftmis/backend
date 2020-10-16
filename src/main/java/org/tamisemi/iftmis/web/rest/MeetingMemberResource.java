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
import org.tamisemi.iftmis.service.MeetingMemberService;
import org.tamisemi.iftmis.service.dto.MeetingMemberDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.MeetingMember}.
 */
@RestController
@RequestMapping("/api")
public class MeetingMemberResource {
    private final Logger log = LoggerFactory.getLogger(MeetingMemberResource.class);

    private static final String ENTITY_NAME = "meetingMember";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeetingMemberService meetingMemberService;

    public MeetingMemberResource(MeetingMemberService meetingMemberService) {
        this.meetingMemberService = meetingMemberService;
    }

    /**
     * {@code POST  /meeting-members} : Create a new meetingMember.
     *
     * @param meetingMemberDTO the meetingMemberDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new meetingMemberDTO, or with status {@code 400 (Bad Request)} if the meetingMember has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meeting-members/{meetingId}")
    public ResponseEntity<MeetingMemberDTO> createMeetingMember(
        @Valid @RequestBody MeetingMemberDTO meetingMemberDTO,
        @PathVariable Long meetingId
    )
        throws URISyntaxException {
        log.debug("REST request to save MeetingMember : {}", meetingMemberDTO);
        if (meetingMemberDTO.getId() != null) {
            throw new BadRequestAlertException("A new meetingMember cannot already have an ID", ENTITY_NAME, "idexists");
        }
        meetingMemberDTO.setMeetingId(meetingId);

        MeetingMemberDTO result = meetingMemberService.save(meetingMemberDTO);
        return ResponseEntity
            .created(new URI("/api/meeting-members/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meeting-members} : Updates an existing meetingMember.
     *
     * @param meetingMemberDTO the meetingMemberDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated meetingMemberDTO,
     * or with status {@code 400 (Bad Request)} if the meetingMemberDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the meetingMemberDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meeting-members")
    public ResponseEntity<MeetingMemberDTO> updateMeetingMember(@Valid @RequestBody MeetingMemberDTO meetingMemberDTO)
        throws URISyntaxException {
        log.debug("REST request to update MeetingMember : {}", meetingMemberDTO);
        if (meetingMemberDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeetingMemberDTO result = meetingMemberService.save(meetingMemberDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, meetingMemberDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meeting-members} : get all the meetingMembers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meetingMembers in body.
     */
    @GetMapping("/meeting-members")
    public ResponseEntity<List<MeetingMemberDTO>> getAllMeetingMembers(Pageable pageable) {
        log.debug("REST request to get a page of MeetingMembers");
        Page<MeetingMemberDTO> page = meetingMemberService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /meeting-members/:id} : get the "id" meetingMember.
     *
     * @param id the id of the meetingMemberDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the meetingMemberDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meeting-members/{id}")
    public ResponseEntity<MeetingMemberDTO> getMeetingMember(@PathVariable Long id) {
        log.debug("REST request to get MeetingMember : {}", id);
        Optional<MeetingMemberDTO> meetingMemberDTO = meetingMemberService.findOne(id);
        return ResponseUtil.wrapOrNotFound(meetingMemberDTO);
    }

    /**
     * {@code DELETE  /meeting-members/:id} : delete the "id" meetingMember.
     *
     * @param id the id of the meetingMemberDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meeting-members/{id}")
    public ResponseEntity<Void> deleteMeetingMember(@PathVariable Long id) {
        log.debug("REST request to delete MeetingMember : {}", id);
        meetingMemberService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

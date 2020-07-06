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
import org.tamisemi.iftmis.service.MeetingAgendaService;
import org.tamisemi.iftmis.service.dto.MeetingAgendaDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.MeetingAgenda}.
 */
@RestController
@RequestMapping("/api")
public class MeetingAgendaResource {
    private final Logger log = LoggerFactory.getLogger(MeetingAgendaResource.class);

    private static final String ENTITY_NAME = "meetingAgenda";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeetingAgendaService meetingAgendaService;

    public MeetingAgendaResource(MeetingAgendaService meetingAgendaService) {
        this.meetingAgendaService = meetingAgendaService;
    }

    /**
     * {@code POST  /meeting-agenda} : Create a new meetingAgenda.
     *
     * @param meetingAgendaDTO the meetingAgendaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new meetingAgendaDTO, or with status {@code 400 (Bad Request)} if the meetingAgenda has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meeting-agenda")
    public ResponseEntity<MeetingAgendaDTO> createMeetingAgenda(@Valid @RequestBody MeetingAgendaDTO meetingAgendaDTO)
        throws URISyntaxException {
        log.debug("REST request to save MeetingAgenda : {}", meetingAgendaDTO);
        if (meetingAgendaDTO.getId() != null) {
            throw new BadRequestAlertException("A new meetingAgenda cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MeetingAgendaDTO result = meetingAgendaService.save(meetingAgendaDTO);
        return ResponseEntity
            .created(new URI("/api/meeting-agenda/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meeting-agenda} : Updates an existing meetingAgenda.
     *
     * @param meetingAgendaDTO the meetingAgendaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated meetingAgendaDTO,
     * or with status {@code 400 (Bad Request)} if the meetingAgendaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the meetingAgendaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meeting-agenda")
    public ResponseEntity<MeetingAgendaDTO> updateMeetingAgenda(@Valid @RequestBody MeetingAgendaDTO meetingAgendaDTO)
        throws URISyntaxException {
        log.debug("REST request to update MeetingAgenda : {}", meetingAgendaDTO);
        if (meetingAgendaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeetingAgendaDTO result = meetingAgendaService.save(meetingAgendaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, meetingAgendaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meeting-agenda} : get all the meetingAgenda.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meetingAgenda in body.
     */
    @GetMapping("/meeting-agenda")
    public ResponseEntity<List<MeetingAgendaDTO>> getAllMeetingAgenda(Pageable pageable) {
        log.debug("REST request to get a page of MeetingAgenda");
        Page<MeetingAgendaDTO> page = meetingAgendaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /meeting-agenda/:id} : get the "id" meetingAgenda.
     *
     * @param id the id of the meetingAgendaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the meetingAgendaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meeting-agenda/{id}")
    public ResponseEntity<MeetingAgendaDTO> getMeetingAgenda(@PathVariable Long id) {
        log.debug("REST request to get MeetingAgenda : {}", id);
        Optional<MeetingAgendaDTO> meetingAgendaDTO = meetingAgendaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(meetingAgendaDTO);
    }

    /**
     * {@code DELETE  /meeting-agenda/:id} : delete the "id" meetingAgenda.
     *
     * @param id the id of the meetingAgendaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meeting-agenda/{id}")
    public ResponseEntity<Void> deleteMeetingAgenda(@PathVariable Long id) {
        log.debug("REST request to delete MeetingAgenda : {}", id);
        meetingAgendaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

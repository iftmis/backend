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
import org.tamisemi.iftmis.domain.MeetingAgenda;
import org.tamisemi.iftmis.repository.MeetingAgendaRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.MeetingAgenda}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class MeetingAgendaResource {
    private final Logger log = LoggerFactory.getLogger(MeetingAgendaResource.class);

    private static final String ENTITY_NAME = "meetingAgenda";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MeetingAgendaRepository meetingAgendaRepository;

    public MeetingAgendaResource(MeetingAgendaRepository meetingAgendaRepository) {
        this.meetingAgendaRepository = meetingAgendaRepository;
    }

    /**
     * {@code POST  /meeting-agenda} : Create a new meetingAgenda.
     *
     * @param meetingAgenda the meetingAgenda to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new meetingAgenda, or with status {@code 400 (Bad Request)} if the meetingAgenda has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/meeting-agenda")
    public ResponseEntity<MeetingAgenda> createMeetingAgenda(@Valid @RequestBody MeetingAgenda meetingAgenda) throws URISyntaxException {
        log.debug("REST request to save MeetingAgenda : {}", meetingAgenda);
        if (meetingAgenda.getId() != null) {
            throw new BadRequestAlertException("A new meetingAgenda cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MeetingAgenda result = meetingAgendaRepository.save(meetingAgenda);
        return ResponseEntity
            .created(new URI("/api/meeting-agenda/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /meeting-agenda} : Updates an existing meetingAgenda.
     *
     * @param meetingAgenda the meetingAgenda to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated meetingAgenda,
     * or with status {@code 400 (Bad Request)} if the meetingAgenda is not valid,
     * or with status {@code 500 (Internal Server Error)} if the meetingAgenda couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/meeting-agenda")
    public ResponseEntity<MeetingAgenda> updateMeetingAgenda(@Valid @RequestBody MeetingAgenda meetingAgenda) throws URISyntaxException {
        log.debug("REST request to update MeetingAgenda : {}", meetingAgenda);
        if (meetingAgenda.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MeetingAgenda result = meetingAgendaRepository.save(meetingAgenda);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, meetingAgenda.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /meeting-agenda} : get all the meetingAgenda.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of meetingAgenda in body.
     */
    @GetMapping("/meeting-agenda")
    public List<MeetingAgenda> getAllMeetingAgenda() {
        log.debug("REST request to get all MeetingAgenda");
        return meetingAgendaRepository.findAll();
    }

    /**
     * {@code GET  /meeting-agenda/:id} : get the "id" meetingAgenda.
     *
     * @param id the id of the meetingAgenda to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the meetingAgenda, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/meeting-agenda/{id}")
    public ResponseEntity<MeetingAgenda> getMeetingAgenda(@PathVariable Long id) {
        log.debug("REST request to get MeetingAgenda : {}", id);
        Optional<MeetingAgenda> meetingAgenda = meetingAgendaRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(meetingAgenda);
    }

    /**
     * {@code DELETE  /meeting-agenda/:id} : delete the "id" meetingAgenda.
     *
     * @param id the id of the meetingAgenda to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/meeting-agenda/{id}")
    public ResponseEntity<Void> deleteMeetingAgenda(@PathVariable Long id) {
        log.debug("REST request to delete MeetingAgenda : {}", id);
        meetingAgendaRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

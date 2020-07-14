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
import org.tamisemi.iftmis.service.ObjectiveService;
import org.tamisemi.iftmis.service.dto.ObjectiveDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Objective}.
 */
@RestController
@RequestMapping("/api")
public class ObjectiveResource {
    private final Logger log = LoggerFactory.getLogger(ObjectiveResource.class);

    private static final String ENTITY_NAME = "objective";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObjectiveService objectiveService;

    public ObjectiveResource(ObjectiveService objectiveService) {
        this.objectiveService = objectiveService;
    }

    /**
     * {@code POST  /objectives} : Create a new objective.
     *
     * @param objectiveDTO the objectiveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new objectiveDTO, or with status {@code 400 (Bad Request)} if the objective has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/objectives")
    public ResponseEntity<ObjectiveDTO> createObjective(@Valid @RequestBody ObjectiveDTO objectiveDTO) throws URISyntaxException {
        log.debug("REST request to save Objective : {}", objectiveDTO);
        if (objectiveDTO.getId() != null) {
            throw new BadRequestAlertException("A new objective cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ObjectiveDTO result = objectiveService.save(objectiveDTO);
        return ResponseEntity
            .created(new URI("/api/objectives/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /objectives} : Updates an existing objective.
     *
     * @param objectiveDTO the objectiveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objectiveDTO,
     * or with status {@code 400 (Bad Request)} if the objectiveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the objectiveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/objectives")
    public ResponseEntity<ObjectiveDTO> updateObjective(@Valid @RequestBody ObjectiveDTO objectiveDTO) throws URISyntaxException {
        log.debug("REST request to update Objective : {}", objectiveDTO);
        if (objectiveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ObjectiveDTO result = objectiveService.save(objectiveDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, objectiveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /objectives} : get all the objectives.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of objectives in body.
     */
    @GetMapping("/objectives")
    public ResponseEntity<List<ObjectiveDTO>> getAllObjectives(Pageable pageable) {
        log.debug("REST request to get a page of Objectives");
        Page<ObjectiveDTO> page = objectiveService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /objectives/:id} : get the "id" objective.
     *
     * @param id the id of the objectiveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the objectiveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/objectives/{id}")
    public ResponseEntity<ObjectiveDTO> getObjective(@PathVariable Long id) {
        log.debug("REST request to get Objective : {}", id);
        Optional<ObjectiveDTO> objectiveDTO = objectiveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(objectiveDTO);
    }

    /**
     * {@code DELETE  /objectives/:id} : delete the "id" objective.
     *
     * @param id the id of the objectiveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/objectives/{id}")
    public ResponseEntity<Void> deleteObjective(@PathVariable Long id) {
        log.debug("REST request to delete Objective : {}", id);
        objectiveService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

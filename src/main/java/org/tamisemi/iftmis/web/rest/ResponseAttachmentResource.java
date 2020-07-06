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
import org.tamisemi.iftmis.domain.ResponseAttachment;
import org.tamisemi.iftmis.repository.ResponseAttachmentRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.ResponseAttachment}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ResponseAttachmentResource {
    private final Logger log = LoggerFactory.getLogger(ResponseAttachmentResource.class);

    private static final String ENTITY_NAME = "responseAttachment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResponseAttachmentRepository responseAttachmentRepository;

    public ResponseAttachmentResource(ResponseAttachmentRepository responseAttachmentRepository) {
        this.responseAttachmentRepository = responseAttachmentRepository;
    }

    /**
     * {@code POST  /response-attachments} : Create a new responseAttachment.
     *
     * @param responseAttachment the responseAttachment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new responseAttachment, or with status {@code 400 (Bad Request)} if the responseAttachment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/response-attachments")
    public ResponseEntity<ResponseAttachment> createResponseAttachment(@Valid @RequestBody ResponseAttachment responseAttachment)
        throws URISyntaxException {
        log.debug("REST request to save ResponseAttachment : {}", responseAttachment);
        if (responseAttachment.getId() != null) {
            throw new BadRequestAlertException("A new responseAttachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResponseAttachment result = responseAttachmentRepository.save(responseAttachment);
        return ResponseEntity
            .created(new URI("/api/response-attachments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /response-attachments} : Updates an existing responseAttachment.
     *
     * @param responseAttachment the responseAttachment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated responseAttachment,
     * or with status {@code 400 (Bad Request)} if the responseAttachment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the responseAttachment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/response-attachments")
    public ResponseEntity<ResponseAttachment> updateResponseAttachment(@Valid @RequestBody ResponseAttachment responseAttachment)
        throws URISyntaxException {
        log.debug("REST request to update ResponseAttachment : {}", responseAttachment);
        if (responseAttachment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResponseAttachment result = responseAttachmentRepository.save(responseAttachment);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, responseAttachment.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /response-attachments} : get all the responseAttachments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of responseAttachments in body.
     */
    @GetMapping("/response-attachments")
    public List<ResponseAttachment> getAllResponseAttachments() {
        log.debug("REST request to get all ResponseAttachments");
        return responseAttachmentRepository.findAll();
    }

    /**
     * {@code GET  /response-attachments/:id} : get the "id" responseAttachment.
     *
     * @param id the id of the responseAttachment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the responseAttachment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/response-attachments/{id}")
    public ResponseEntity<ResponseAttachment> getResponseAttachment(@PathVariable Long id) {
        log.debug("REST request to get ResponseAttachment : {}", id);
        Optional<ResponseAttachment> responseAttachment = responseAttachmentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(responseAttachment);
    }

    /**
     * {@code DELETE  /response-attachments/:id} : delete the "id" responseAttachment.
     *
     * @param id the id of the responseAttachment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/response-attachments/{id}")
    public ResponseEntity<Void> deleteResponseAttachment(@PathVariable Long id) {
        log.debug("REST request to delete ResponseAttachment : {}", id);
        responseAttachmentRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

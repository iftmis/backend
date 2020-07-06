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
import org.tamisemi.iftmis.service.ResponseAttachmentService;
import org.tamisemi.iftmis.service.dto.ResponseAttachmentDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.ResponseAttachment}.
 */
@RestController
@RequestMapping("/api")
public class ResponseAttachmentResource {
    private final Logger log = LoggerFactory.getLogger(ResponseAttachmentResource.class);

    private static final String ENTITY_NAME = "responseAttachment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResponseAttachmentService responseAttachmentService;

    public ResponseAttachmentResource(ResponseAttachmentService responseAttachmentService) {
        this.responseAttachmentService = responseAttachmentService;
    }

    /**
     * {@code POST  /response-attachments} : Create a new responseAttachment.
     *
     * @param responseAttachmentDTO the responseAttachmentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new responseAttachmentDTO, or with status {@code 400 (Bad Request)} if the responseAttachment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/response-attachments")
    public ResponseEntity<ResponseAttachmentDTO> createResponseAttachment(@Valid @RequestBody ResponseAttachmentDTO responseAttachmentDTO)
        throws URISyntaxException {
        log.debug("REST request to save ResponseAttachment : {}", responseAttachmentDTO);
        if (responseAttachmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new responseAttachment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ResponseAttachmentDTO result = responseAttachmentService.save(responseAttachmentDTO);
        return ResponseEntity
            .created(new URI("/api/response-attachments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /response-attachments} : Updates an existing responseAttachment.
     *
     * @param responseAttachmentDTO the responseAttachmentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated responseAttachmentDTO,
     * or with status {@code 400 (Bad Request)} if the responseAttachmentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the responseAttachmentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/response-attachments")
    public ResponseEntity<ResponseAttachmentDTO> updateResponseAttachment(@Valid @RequestBody ResponseAttachmentDTO responseAttachmentDTO)
        throws URISyntaxException {
        log.debug("REST request to update ResponseAttachment : {}", responseAttachmentDTO);
        if (responseAttachmentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ResponseAttachmentDTO result = responseAttachmentService.save(responseAttachmentDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, responseAttachmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /response-attachments} : get all the responseAttachments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of responseAttachments in body.
     */
    @GetMapping("/response-attachments")
    public ResponseEntity<List<ResponseAttachmentDTO>> getAllResponseAttachments(Pageable pageable) {
        log.debug("REST request to get a page of ResponseAttachments");
        Page<ResponseAttachmentDTO> page = responseAttachmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /response-attachments/:id} : get the "id" responseAttachment.
     *
     * @param id the id of the responseAttachmentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the responseAttachmentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/response-attachments/{id}")
    public ResponseEntity<ResponseAttachmentDTO> getResponseAttachment(@PathVariable Long id) {
        log.debug("REST request to get ResponseAttachment : {}", id);
        Optional<ResponseAttachmentDTO> responseAttachmentDTO = responseAttachmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(responseAttachmentDTO);
    }

    /**
     * {@code DELETE  /response-attachments/:id} : delete the "id" responseAttachment.
     *
     * @param id the id of the responseAttachmentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/response-attachments/{id}")
    public ResponseEntity<Void> deleteResponseAttachment(@PathVariable Long id) {
        log.debug("REST request to delete ResponseAttachment : {}", id);
        responseAttachmentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

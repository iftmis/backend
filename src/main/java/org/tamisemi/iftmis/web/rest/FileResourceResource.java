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
import org.tamisemi.iftmis.service.FileResourceService;
import org.tamisemi.iftmis.service.dto.FileResourceDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FileResource}.
 */
@RestController
@RequestMapping("/api")
public class FileResourceResource {
    private final Logger log = LoggerFactory.getLogger(FileResourceResource.class);

    private static final String ENTITY_NAME = "fileResource";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileResourceService fileResourceService;

    public FileResourceResource(FileResourceService fileResourceService) {
        this.fileResourceService = fileResourceService;
    }

    /**
     * {@code POST  /file-resources} : Create a new fileResource.
     *
     * @param fileResourceDTO the fileResourceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileResourceDTO, or with status {@code 400 (Bad Request)} if the fileResource has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-resources")
    public ResponseEntity<FileResourceDTO> createFileResource(@Valid @RequestBody FileResourceDTO fileResourceDTO)
        throws URISyntaxException {
        log.debug("REST request to save FileResource : {}", fileResourceDTO);
        if (fileResourceDTO.getId() != null) {
            throw new BadRequestAlertException("A new fileResource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileResourceDTO result = fileResourceService.save(fileResourceDTO);
        return ResponseEntity
            .created(new URI("/api/file-resources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-resources} : Updates an existing fileResource.
     *
     * @param fileResourceDTO the fileResourceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileResourceDTO,
     * or with status {@code 400 (Bad Request)} if the fileResourceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileResourceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-resources")
    public ResponseEntity<FileResourceDTO> updateFileResource(@Valid @RequestBody FileResourceDTO fileResourceDTO)
        throws URISyntaxException {
        log.debug("REST request to update FileResource : {}", fileResourceDTO);
        if (fileResourceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileResourceDTO result = fileResourceService.save(fileResourceDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fileResourceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-resources} : get all the fileResources.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileResources in body.
     */
    @GetMapping("/file-resources")
    public ResponseEntity<List<FileResourceDTO>> getAllFileResources(Pageable pageable) {
        log.debug("REST request to get a page of FileResources");
        Page<FileResourceDTO> page = fileResourceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /file-resources/:id} : get the "id" fileResource.
     *
     * @param id the id of the fileResourceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileResourceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-resources/{id}")
    public ResponseEntity<FileResourceDTO> getFileResource(@PathVariable Long id) {
        log.debug("REST request to get FileResource : {}", id);
        Optional<FileResourceDTO> fileResourceDTO = fileResourceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fileResourceDTO);
    }

    /**
     * {@code DELETE  /file-resources/:id} : delete the "id" fileResource.
     *
     * @param id the id of the fileResourceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-resources/{id}")
    public ResponseEntity<Void> deleteFileResource(@PathVariable Long id) {
        log.debug("REST request to delete FileResource : {}", id);
        fileResourceService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

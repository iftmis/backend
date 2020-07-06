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
import org.tamisemi.iftmis.domain.FileResource;
import org.tamisemi.iftmis.repository.FileResourceRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.FileResource}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FileResourceResource {
    private final Logger log = LoggerFactory.getLogger(FileResourceResource.class);

    private static final String ENTITY_NAME = "fileResource";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileResourceRepository fileResourceRepository;

    public FileResourceResource(FileResourceRepository fileResourceRepository) {
        this.fileResourceRepository = fileResourceRepository;
    }

    /**
     * {@code POST  /file-resources} : Create a new fileResource.
     *
     * @param fileResource the fileResource to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileResource, or with status {@code 400 (Bad Request)} if the fileResource has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-resources")
    public ResponseEntity<FileResource> createFileResource(@Valid @RequestBody FileResource fileResource) throws URISyntaxException {
        log.debug("REST request to save FileResource : {}", fileResource);
        if (fileResource.getId() != null) {
            throw new BadRequestAlertException("A new fileResource cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileResource result = fileResourceRepository.save(fileResource);
        return ResponseEntity
            .created(new URI("/api/file-resources/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-resources} : Updates an existing fileResource.
     *
     * @param fileResource the fileResource to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileResource,
     * or with status {@code 400 (Bad Request)} if the fileResource is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileResource couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-resources")
    public ResponseEntity<FileResource> updateFileResource(@Valid @RequestBody FileResource fileResource) throws URISyntaxException {
        log.debug("REST request to update FileResource : {}", fileResource);
        if (fileResource.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileResource result = fileResourceRepository.save(fileResource);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fileResource.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-resources} : get all the fileResources.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileResources in body.
     */
    @GetMapping("/file-resources")
    public List<FileResource> getAllFileResources() {
        log.debug("REST request to get all FileResources");
        return fileResourceRepository.findAll();
    }

    /**
     * {@code GET  /file-resources/:id} : get the "id" fileResource.
     *
     * @param id the id of the fileResource to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileResource, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-resources/{id}")
    public ResponseEntity<FileResource> getFileResource(@PathVariable Long id) {
        log.debug("REST request to get FileResource : {}", id);
        Optional<FileResource> fileResource = fileResourceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fileResource);
    }

    /**
     * {@code DELETE  /file-resources/:id} : delete the "id" fileResource.
     *
     * @param id the id of the fileResource to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-resources/{id}")
    public ResponseEntity<Void> deleteFileResource(@PathVariable Long id) {
        log.debug("REST request to delete FileResource : {}", id);
        fileResourceRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

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
import org.tamisemi.iftmis.domain.GfsCode;
import org.tamisemi.iftmis.repository.GfsCodeRepository;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.GfsCode}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GfsCodeResource {
    private final Logger log = LoggerFactory.getLogger(GfsCodeResource.class);

    private static final String ENTITY_NAME = "gfsCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GfsCodeRepository gfsCodeRepository;

    public GfsCodeResource(GfsCodeRepository gfsCodeRepository) {
        this.gfsCodeRepository = gfsCodeRepository;
    }

    /**
     * {@code POST  /gfs-codes} : Create a new gfsCode.
     *
     * @param gfsCode the gfsCode to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gfsCode, or with status {@code 400 (Bad Request)} if the gfsCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gfs-codes")
    public ResponseEntity<GfsCode> createGfsCode(@Valid @RequestBody GfsCode gfsCode) throws URISyntaxException {
        log.debug("REST request to save GfsCode : {}", gfsCode);
        if (gfsCode.getId() != null) {
            throw new BadRequestAlertException("A new gfsCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GfsCode result = gfsCodeRepository.save(gfsCode);
        return ResponseEntity
            .created(new URI("/api/gfs-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gfs-codes} : Updates an existing gfsCode.
     *
     * @param gfsCode the gfsCode to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gfsCode,
     * or with status {@code 400 (Bad Request)} if the gfsCode is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gfsCode couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gfs-codes")
    public ResponseEntity<GfsCode> updateGfsCode(@Valid @RequestBody GfsCode gfsCode) throws URISyntaxException {
        log.debug("REST request to update GfsCode : {}", gfsCode);
        if (gfsCode.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GfsCode result = gfsCodeRepository.save(gfsCode);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, gfsCode.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gfs-codes} : get all the gfsCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gfsCodes in body.
     */
    @GetMapping("/gfs-codes")
    public List<GfsCode> getAllGfsCodes() {
        log.debug("REST request to get all GfsCodes");
        return gfsCodeRepository.findAll();
    }

    /**
     * {@code GET  /gfs-codes/:id} : get the "id" gfsCode.
     *
     * @param id the id of the gfsCode to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gfsCode, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gfs-codes/{id}")
    public ResponseEntity<GfsCode> getGfsCode(@PathVariable Long id) {
        log.debug("REST request to get GfsCode : {}", id);
        Optional<GfsCode> gfsCode = gfsCodeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(gfsCode);
    }

    /**
     * {@code DELETE  /gfs-codes/:id} : delete the "id" gfsCode.
     *
     * @param id the id of the gfsCode to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gfs-codes/{id}")
    public ResponseEntity<Void> deleteGfsCode(@PathVariable Long id) {
        log.debug("REST request to delete GfsCode : {}", id);
        gfsCodeRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

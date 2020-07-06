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
import org.tamisemi.iftmis.service.GfsCodeService;
import org.tamisemi.iftmis.service.dto.GfsCodeDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.GfsCode}.
 */
@RestController
@RequestMapping("/api")
public class GfsCodeResource {
    private final Logger log = LoggerFactory.getLogger(GfsCodeResource.class);

    private static final String ENTITY_NAME = "gfsCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GfsCodeService gfsCodeService;

    public GfsCodeResource(GfsCodeService gfsCodeService) {
        this.gfsCodeService = gfsCodeService;
    }

    /**
     * {@code POST  /gfs-codes} : Create a new gfsCode.
     *
     * @param gfsCodeDTO the gfsCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new gfsCodeDTO, or with status {@code 400 (Bad Request)} if the gfsCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/gfs-codes")
    public ResponseEntity<GfsCodeDTO> createGfsCode(@Valid @RequestBody GfsCodeDTO gfsCodeDTO) throws URISyntaxException {
        log.debug("REST request to save GfsCode : {}", gfsCodeDTO);
        if (gfsCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new gfsCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GfsCodeDTO result = gfsCodeService.save(gfsCodeDTO);
        return ResponseEntity
            .created(new URI("/api/gfs-codes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /gfs-codes} : Updates an existing gfsCode.
     *
     * @param gfsCodeDTO the gfsCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated gfsCodeDTO,
     * or with status {@code 400 (Bad Request)} if the gfsCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the gfsCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/gfs-codes")
    public ResponseEntity<GfsCodeDTO> updateGfsCode(@Valid @RequestBody GfsCodeDTO gfsCodeDTO) throws URISyntaxException {
        log.debug("REST request to update GfsCode : {}", gfsCodeDTO);
        if (gfsCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GfsCodeDTO result = gfsCodeService.save(gfsCodeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, gfsCodeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /gfs-codes} : get all the gfsCodes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of gfsCodes in body.
     */
    @GetMapping("/gfs-codes")
    public ResponseEntity<List<GfsCodeDTO>> getAllGfsCodes(Pageable pageable) {
        log.debug("REST request to get a page of GfsCodes");
        Page<GfsCodeDTO> page = gfsCodeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /gfs-codes/:id} : get the "id" gfsCode.
     *
     * @param id the id of the gfsCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the gfsCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/gfs-codes/{id}")
    public ResponseEntity<GfsCodeDTO> getGfsCode(@PathVariable Long id) {
        log.debug("REST request to get GfsCode : {}", id);
        Optional<GfsCodeDTO> gfsCodeDTO = gfsCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(gfsCodeDTO);
    }

    /**
     * {@code DELETE  /gfs-codes/:id} : delete the "id" gfsCode.
     *
     * @param id the id of the gfsCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/gfs-codes/{id}")
    public ResponseEntity<Void> deleteGfsCode(@PathVariable Long id) {
        log.debug("REST request to delete GfsCode : {}", id);
        gfsCodeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

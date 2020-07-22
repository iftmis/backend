package org.tamisemi.iftmis.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tamisemi.iftmis.config.Constants;
import org.tamisemi.iftmis.domain.User;
import org.tamisemi.iftmis.domain.enumeration.FindingSource;
import org.tamisemi.iftmis.service.FindingService;
import org.tamisemi.iftmis.service.UserService;
import org.tamisemi.iftmis.service.dto.FindingDTO;
import org.tamisemi.iftmis.service.dto.RiskRegisterDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Finding}.
 */
@RestController
@RequestMapping("/api")
public class FindingResource {
    private final Logger log = LoggerFactory.getLogger(FindingResource.class);

    private static final String ENTITY_NAME = "finding";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FindingService findingService;
    private final UserService userService;

    public FindingResource(FindingService findingService, UserService userService) {
        this.findingService = findingService;
        this.userService = userService;
    }

    /**
     * {@code POST  /findings} : Create a new finding.
     *
     * @param findingDTO the findingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new findingDTO, or with status {@code 400 (Bad Request)} if the finding has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/findings")
    public ResponseEntity<FindingDTO> createFinding(@Valid @RequestBody FindingDTO findingDTO) throws URISyntaxException {
        log.debug("REST request to save Finding : {}", findingDTO);
        if (findingDTO.getId() != null) {
            throw new BadRequestAlertException("A new finding cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FindingDTO result = findingService.save(findingDTO);
        return ResponseEntity
            .created(new URI("/api/findings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /findings} : Updates an existing finding.
     *
     * @param findingDTO the findingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated findingDTO,
     * or with status {@code 400 (Bad Request)} if the findingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the findingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/findings")
    public ResponseEntity<FindingDTO> updateFinding(@Valid @RequestBody FindingDTO findingDTO) throws URISyntaxException {
        log.debug("REST request to update Finding : {}", findingDTO);
        if (findingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FindingDTO result = findingService.save(findingDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, findingDTO.getId().toString()))
            .body(result);
    }

    /**
     * @return
     */
    @GetMapping("/findings")
    public ResponseEntity<List<FindingDTO>> getAllFindings(@RequestParam(value = "organisationUnitId", defaultValue = Constants.ZERO) Long organisationUnitId,
                                                           @RequestParam(value = "source", defaultValue = "CAG") FindingSource source) {
        List<FindingDTO> items = findingService.findAllByOrganisationUnitIdAndSource(organisationUnitId, source);
        return ResponseEntity.ok().body(items);
    }

    /**
     * @param organisationUnitId
     * @param source
     * @param page
     * @param size
     * @param sortBy
     * @return
     */
    @GetMapping("/findings/page")
    public ResponseEntity<List<FindingDTO>> getAllPagedFindings(@RequestParam(value = "organisationUnitId", defaultValue = Constants.ZERO) Long organisationUnitId,
                                                                @RequestParam(value = "source", defaultValue = "CAG") String source,
                                                                @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                                @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
                                                                @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<FindingDTO> items = findingService.findAllByOrganisationUnitIdAndSource(organisationUnitId, source, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), items);
        return ResponseEntity.ok().headers(headers).body(items.getContent());
    }

    /**
     * {@code GET  /findings/:id} : get the "id" finding.
     *
     * @param id the id of the findingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the findingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/findings/{id}")
    public ResponseEntity<FindingDTO> getFinding(@PathVariable Long id) {
        log.debug("REST request to get Finding : {}", id);
        Optional<FindingDTO> findingDTO = findingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(findingDTO);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/findings/close/{id}")
    public ResponseEntity<FindingDTO> close(@PathVariable Long id) {
        Optional<FindingDTO> row = findingService.findOne(id);
        if (row.isPresent()) {
            Optional<User> isUser = userService.getUserWithAuthorities();
            FindingDTO findingDTO = row.get();
            findingDTO.setIsClosed(true);
            findingDTO.setLastModifiedBy(isUser.get().getLogin());
            findingDTO.setLastModifiedDate(Instant.now());
            FindingDTO result = findingService.save(findingDTO);
            return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, findingDTO.getId().toString()))
                .body(result);
        } else {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
    }

    /**
     * {@code DELETE  /findings/:id} : delete the "id" finding.
     *
     * @param id the id of the findingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/findings/{id}")
    public ResponseEntity<Void> deleteFinding(@PathVariable Long id) {
        log.debug("REST request to delete Finding : {}", id);
        findingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

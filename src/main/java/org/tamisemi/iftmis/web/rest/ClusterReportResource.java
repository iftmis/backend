package org.tamisemi.iftmis.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tamisemi.iftmis.config.Constants;
import org.tamisemi.iftmis.service.ClusterReportService;
import org.tamisemi.iftmis.service.dto.ClusterReportDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.ClusterReport}.
 */
@RestController
@RequestMapping("/api")
public class ClusterReportResource {
    private final Logger log = LoggerFactory.getLogger(ClusterReportResource.class);

    private static final String ENTITY_NAME = "cluster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClusterReportService clusterService;

    public ClusterReportResource(ClusterReportService clusterService) {
        this.clusterService = clusterService;
    }


    @PostMapping("/clusterReports")
    public ResponseEntity<ClusterReportDTO> createClusterReport(@Valid @RequestBody ClusterReportDTO clusterDTO)
        throws URISyntaxException {
        if (clusterDTO.getId() != null) {
            throw new BadRequestAlertException("A new cluster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClusterReportDTO result = clusterService.save(clusterDTO);
        return ResponseEntity
            .created(new URI("/api/clusterReports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/clusterReports")
    public ResponseEntity<ClusterReportDTO> updateClusterReport(@Valid @RequestBody ClusterReportDTO clusterDTO)
        throws URISyntaxException {
        if (clusterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClusterReportDTO result = clusterService.save(clusterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, clusterDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/clusterReports")
    public ResponseEntity<?> getAllClusterReports(@RequestParam(value = "clusterId") Long clusterId) {
        List<ClusterReportDTO> items = clusterService.findAll(clusterId);
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/clusterReports/page")
    public ResponseEntity<?> getAllPagedClusterReports(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size,
                                                       @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
                                                       @RequestParam(value = "clusterId") Long clusterId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());
        Page<ClusterReportDTO> items = clusterService.findAll(clusterId, pageable);
        return ResponseEntity.ok().body(items);
    }


    @GetMapping("/clusterReports/{id}")
    public ResponseEntity<ClusterReportDTO> getClusterReport(@PathVariable Long id) {
        Optional<ClusterReportDTO> clusterDTO = clusterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clusterDTO);
    }


    @DeleteMapping("/clusterReports/{id}")
    public ResponseEntity<Void> deleteClusterReport(@PathVariable Long id) {
        clusterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

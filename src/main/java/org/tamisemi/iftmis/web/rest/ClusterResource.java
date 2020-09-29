package org.tamisemi.iftmis.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tamisemi.iftmis.service.ClusterService;
import org.tamisemi.iftmis.service.dto.ClusterDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link org.tamisemi.iftmis.domain.Cluster}.
 */
@RestController
@RequestMapping("/api")
public class ClusterResource {
    private final Logger log = LoggerFactory.getLogger(ClusterResource.class);

    private static final String ENTITY_NAME = "cluster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ClusterService clusterService;

    public ClusterResource(ClusterService clusterService) {
        this.clusterService = clusterService;
    }


    @PostMapping("/clusters")
    public ResponseEntity<ClusterDTO> createCluster(@Valid @RequestBody ClusterDTO clusterDTO)
        throws URISyntaxException {
        if (clusterDTO.getId() != null) {
            throw new BadRequestAlertException("A new cluster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClusterDTO result = clusterService.save(clusterDTO);
        return ResponseEntity
            .created(new URI("/api/clusters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/clusters")
    public ResponseEntity<ClusterDTO> updateCluster(@Valid @RequestBody ClusterDTO clusterDTO)
        throws URISyntaxException {
        if (clusterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ClusterDTO result = clusterService.save(clusterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, clusterDTO.getId().toString()))
            .body(result);
    }


    @GetMapping("/clusters")
    public ResponseEntity<?> getAllClusters() {
        List<ClusterDTO> page = clusterService.findAll();
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("/clusters/page")
    public ResponseEntity<?> getAllPagedClusters(Pageable pageable) {
        Page<ClusterDTO> page = clusterService.findAll(pageable);
        return ResponseEntity.ok().body(page);
    }


    @GetMapping("/clusters/{id}")
    public ResponseEntity<ClusterDTO> getCluster(@PathVariable Long id) {
        Optional<ClusterDTO> clusterDTO = clusterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(clusterDTO);
    }


    @DeleteMapping("/clusters/{id}")
    public ResponseEntity<Void> deleteCluster(@PathVariable Long id) {
        clusterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}

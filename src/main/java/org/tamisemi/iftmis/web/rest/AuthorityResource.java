package org.tamisemi.iftmis.web.rest;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.tamisemi.iftmis.domain.Authority;
import org.tamisemi.iftmis.service.AuthorityService;
import org.tamisemi.iftmis.service.dto.AuthorityDTO;
import org.tamisemi.iftmis.web.rest.errors.BadRequestAlertException;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthorityResource {
    private final Logger log = LoggerFactory.getLogger(AuthorityResource.class);

    private static final String ENTITY_NAME = "authority";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuthorityService authorityService;

    public AuthorityResource(AuthorityService authorityService) {
        this.authorityService = authorityService;
    }

    @PostMapping("/authorities")
    public ResponseEntity<Authority> createAuthority(@Valid @RequestBody AuthorityDTO authorityDTO)
        throws URISyntaxException {
        log.debug("REST request to save Authority : {}", authorityDTO);
        Authority result = authorityService.save(authorityDTO);
        return ResponseEntity
            .created(new URI("/api/authorities/" + result.getName()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getName()))
            .body(result);
    }

    @PutMapping("/authorities")
    public ResponseEntity<Authority> updateAuthority(@Valid @RequestBody AuthorityDTO authorityDTO) throws URISyntaxException {
        log.debug("REST request to update Authority : {}", authorityDTO);
        if (authorityDTO.getName() == null) {
            throw new BadRequestAlertException("Invalid Name", ENTITY_NAME, "namenull");
        }
        Authority result = authorityService.save(authorityDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, authorityDTO.getName()))
            .body(result);
    }

    @GetMapping("/authorities")
    public ResponseEntity<List<Authority>> getAllAuthorities() {
        log.debug("REST request to get a page of Authorities");
        List<Authority> items = authorityService.findAll();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/authorities/page")
    public ResponseEntity<List<Authority>> getAllPagedAuthorities(Pageable pageable) {
        log.debug("REST request to get a page of Authorities");
        Page<Authority> page = authorityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/authorities/{name}")
    public ResponseEntity<Authority> getAuthority(@PathVariable String name) {
        log.debug("REST request to get Authority : {}", name);
        Optional<Authority> authority = authorityService.findByName(name);
        return ResponseUtil.wrapOrNotFound(authority);
    }

    @DeleteMapping("/authorities/{name}")
    public ResponseEntity<Void> deleteAuthority(@PathVariable String name) {
        log.debug("REST request to delete Authority : {}", name);
        authorityService.delete(name);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, name))
            .build();
    }
}

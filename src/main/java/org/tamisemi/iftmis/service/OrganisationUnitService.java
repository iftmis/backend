package org.tamisemi.iftmis.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.repository.OrganisationUnitRepository;
/**
 * Service Implementation for managing {@link OrganisationUnit}.
 */
@Service
@Transactional
public class OrganisationUnitService {
    private final Logger log = LoggerFactory.getLogger(OrganisationUnitService.class);

    private final OrganisationUnitRepository organisationUnitRepository;

    public OrganisationUnitService(OrganisationUnitRepository organisationUnitRepository) {
        this.organisationUnitRepository = organisationUnitRepository;
    }

    /**
     *
     * @param organisationUnit
     * @return
     */
    public OrganisationUnit save(OrganisationUnit organisationUnit) {
        return organisationUnitRepository.save(organisationUnit);
    }


    @Transactional(readOnly = true)
    public Page<OrganisationUnit> findAll(Pageable pageable) {
        return organisationUnitRepository.findAll(pageable);
    }

    /**
     *
     * @param query
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<OrganisationUnit> findAll(String query, Pageable pageable) {
        return organisationUnitRepository.findAll(query, pageable);
    }

    /**
     *
     * @param query
     * @return
     */
    @Transactional(readOnly = true)
    public List<OrganisationUnit> findAll(String query) {
        return organisationUnitRepository.findAll(query);
    }

    /**
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<OrganisationUnit> findAll() {
        return organisationUnitRepository.findAll();
    }

    /**
     * Get one organisationUnit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrganisationUnit> findOne(Long id) {
        log.debug("Request to get OrganisationUnit : {}", id);
        return organisationUnitRepository.findById(id);
    }

    /**
     * Delete the organisationUnit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrganisationUnit : {}", id);
        organisationUnitRepository.deleteById(id);
    }
}

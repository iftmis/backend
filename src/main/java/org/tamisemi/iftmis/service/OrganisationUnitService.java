package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.repository.OrganisationUnitRepository;
import org.tamisemi.iftmis.service.dto.OrganisationUnitDTO;
import org.tamisemi.iftmis.service.mapper.OrganisationUnitMapper;

/**
 * Service Implementation for managing {@link OrganisationUnit}.
 */
@Service
@Transactional
public class OrganisationUnitService {
    private final Logger log = LoggerFactory.getLogger(OrganisationUnitService.class);

    private final OrganisationUnitRepository organisationUnitRepository;

    private final OrganisationUnitMapper organisationUnitMapper;

    public OrganisationUnitService(OrganisationUnitRepository organisationUnitRepository, OrganisationUnitMapper organisationUnitMapper) {
        this.organisationUnitRepository = organisationUnitRepository;
        this.organisationUnitMapper = organisationUnitMapper;
    }

    /**
     * Save a organisationUnit.
     *
     * @param organisationUnitDTO the entity to save.
     * @return the persisted entity.
     */
    public OrganisationUnitDTO save(OrganisationUnitDTO organisationUnitDTO) {
        log.debug("Request to save OrganisationUnit : {}", organisationUnitDTO);
        OrganisationUnit organisationUnit = organisationUnitMapper.toEntity(organisationUnitDTO);
        organisationUnit = organisationUnitRepository.save(organisationUnit);
        return organisationUnitMapper.toDto(organisationUnit);
    }

    /**
     * Get all the organisationUnits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrganisationUnitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrganisationUnits");
        return organisationUnitRepository.findAll(pageable).map(organisationUnitMapper::toDto);
    }

    /**
     * Get one organisationUnit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrganisationUnitDTO> findOne(Long id) {
        log.debug("Request to get OrganisationUnit : {}", id);
        return organisationUnitRepository.findById(id).map(organisationUnitMapper::toDto);
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

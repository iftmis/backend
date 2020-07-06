package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.OrganisationUnitLevel;
import org.tamisemi.iftmis.repository.OrganisationUnitLevelRepository;
import org.tamisemi.iftmis.service.dto.OrganisationUnitLevelDTO;
import org.tamisemi.iftmis.service.mapper.OrganisationUnitLevelMapper;

/**
 * Service Implementation for managing {@link OrganisationUnitLevel}.
 */
@Service
@Transactional
public class OrganisationUnitLevelService {
    private final Logger log = LoggerFactory.getLogger(OrganisationUnitLevelService.class);

    private final OrganisationUnitLevelRepository organisationUnitLevelRepository;

    private final OrganisationUnitLevelMapper organisationUnitLevelMapper;

    public OrganisationUnitLevelService(
        OrganisationUnitLevelRepository organisationUnitLevelRepository,
        OrganisationUnitLevelMapper organisationUnitLevelMapper
    ) {
        this.organisationUnitLevelRepository = organisationUnitLevelRepository;
        this.organisationUnitLevelMapper = organisationUnitLevelMapper;
    }

    /**
     * Save a organisationUnitLevel.
     *
     * @param organisationUnitLevelDTO the entity to save.
     * @return the persisted entity.
     */
    public OrganisationUnitLevelDTO save(OrganisationUnitLevelDTO organisationUnitLevelDTO) {
        log.debug("Request to save OrganisationUnitLevel : {}", organisationUnitLevelDTO);
        OrganisationUnitLevel organisationUnitLevel = organisationUnitLevelMapper.toEntity(organisationUnitLevelDTO);
        organisationUnitLevel = organisationUnitLevelRepository.save(organisationUnitLevel);
        return organisationUnitLevelMapper.toDto(organisationUnitLevel);
    }

    /**
     * Get all the organisationUnitLevels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<OrganisationUnitLevelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OrganisationUnitLevels");
        return organisationUnitLevelRepository.findAll(pageable).map(organisationUnitLevelMapper::toDto);
    }

    /**
     * Get one organisationUnitLevel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrganisationUnitLevelDTO> findOne(Long id) {
        log.debug("Request to get OrganisationUnitLevel : {}", id);
        return organisationUnitLevelRepository.findById(id).map(organisationUnitLevelMapper::toDto);
    }

    /**
     * Delete the organisationUnitLevel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrganisationUnitLevel : {}", id);
        organisationUnitLevelRepository.deleteById(id);
    }
}

package org.tamisemi.iftmis.service;

import org.tamisemi.iftmis.domain.InspectionTypes;
import org.tamisemi.iftmis.repository.InspectionTypesRepository;
import org.tamisemi.iftmis.service.dto.InspectionTypesDTO;
import org.tamisemi.iftmis.service.mapper.InspectionTypesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InspectionTypes}.
 */
@Service
@Transactional
public class InspectionTypesService {

    private final Logger log = LoggerFactory.getLogger(InspectionTypesService.class);

    private final InspectionTypesRepository inspectionTypesRepository;

    private final InspectionTypesMapper inspectionTypesMapper;

    public InspectionTypesService(InspectionTypesRepository inspectionTypesRepository, InspectionTypesMapper inspectionTypesMapper) {
        this.inspectionTypesRepository = inspectionTypesRepository;
        this.inspectionTypesMapper = inspectionTypesMapper;
    }

    /**
     * Save a inspectionTypes.
     *
     * @param inspectionTypesDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionTypesDTO save(InspectionTypesDTO inspectionTypesDTO) {
        log.debug("Request to save InspectionTypes : {}", inspectionTypesDTO);
        InspectionTypes inspectionTypes = inspectionTypesMapper.toEntity(inspectionTypesDTO);
        inspectionTypes = inspectionTypesRepository.save(inspectionTypes);
        return inspectionTypesMapper.toDto(inspectionTypes);
    }

    /**
     * Get all the inspectionTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionTypesDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionTypes");
        return inspectionTypesRepository.findAll(pageable)
            .map(inspectionTypesMapper::toDto);
    }


    /**
     * Get one inspectionTypes by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionTypesDTO> findOne(Long id) {
        log.debug("Request to get InspectionTypes : {}", id);
        return inspectionTypesRepository.findById(id)
            .map(inspectionTypesMapper::toDto);
    }

    /**
     * Delete the inspectionTypes by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionTypes : {}", id);
        inspectionTypesRepository.deleteById(id);
    }
}

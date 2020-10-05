package org.tamisemi.iftmis.service;

import java.time.LocalDate;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.Inspection;
import org.tamisemi.iftmis.repository.InspectionRepository;
import org.tamisemi.iftmis.service.dto.InspectionDTO;
import org.tamisemi.iftmis.service.mapper.InspectionMapper;

/**
 * Service Implementation for managing {@link Inspection}.
 */
@Service
@Transactional
public class InspectionService {
    private final Logger log = LoggerFactory.getLogger(InspectionService.class);

    private final InspectionRepository inspectionRepository;

    private final InspectionMapper inspectionMapper;

    public InspectionService(InspectionRepository inspectionRepository, InspectionMapper inspectionMapper) {
        this.inspectionRepository = inspectionRepository;
        this.inspectionMapper = inspectionMapper;
    }

    /**
     * Save a inspection.
     *
     * @param inspectionDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionDTO save(InspectionDTO inspectionDTO) {
        log.debug("Request to save Inspection : {}", inspectionDTO);
        Inspection inspection = inspectionMapper.toEntity(inspectionDTO);
        inspection = inspectionRepository.save(inspection);
        return inspectionMapper.toDto(inspection);
    }

    /**
     * Get all the inspections.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionDTO> findAll(Long ouId, Pageable pageable) {
        log.debug("Request to get all Inspections");
        return inspectionRepository.findByOrganisationUnit_Id(ouId, pageable).map(inspectionMapper::toDto);
    }

    /**
     * Get one inspection by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionDTO> findOne(Long id) {
        log.debug("Request to get Inspection : {}", id);
        return inspectionRepository.findById(id).map(inspectionMapper::toDto);
    }

    /**
     * Delete the inspection by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Inspection : {}", id);
        inspectionRepository.deleteById(id);
    }
    
    
    /**
     * Get all the inspections By date range.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionDTO> findByDateRange(LocalDate startDate, LocalDate endDate , Pageable pageable) {
        log.debug("Request to get all Inspections");
        return inspectionRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(startDate, endDate, pageable).map(inspectionMapper::toDto);
    }
}

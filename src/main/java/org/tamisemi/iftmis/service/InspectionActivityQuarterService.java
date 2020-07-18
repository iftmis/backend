package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionActivityQuarter;
import org.tamisemi.iftmis.repository.InspectionActivityQuarterRepository;
import org.tamisemi.iftmis.service.dto.InspectionActivityQuarterDTO;
import org.tamisemi.iftmis.service.mapper.InspectionActivityQuarterMapper;

/**
 * Service Implementation for managing {@link InspectionActivityQuarter}.
 */
@Service
@Transactional
public class InspectionActivityQuarterService {
    private final Logger log = LoggerFactory.getLogger(InspectionActivityQuarterService.class);

    private final InspectionActivityQuarterRepository inspectionActivityQuarterRepository;

    private final InspectionActivityQuarterMapper inspectionActivityQuarterMapper;

    public InspectionActivityQuarterService(
        InspectionActivityQuarterRepository inspectionActivityQuarterRepository,
        InspectionActivityQuarterMapper inspectionActivityQuarterMapper
    ) {
        this.inspectionActivityQuarterRepository = inspectionActivityQuarterRepository;
        this.inspectionActivityQuarterMapper = inspectionActivityQuarterMapper;
    }

    /**
     * Save a inspectionActivityQuarter.
     *
     * @param inspectionActivityQuarterDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionActivityQuarterDTO save(InspectionActivityQuarterDTO inspectionActivityQuarterDTO) {
        log.debug("Request to save InspectionActivityQuarter : {}", inspectionActivityQuarterDTO);
        InspectionActivityQuarter inspectionActivityQuarter = inspectionActivityQuarterMapper.toEntity(inspectionActivityQuarterDTO);
        inspectionActivityQuarter = inspectionActivityQuarterRepository.save(inspectionActivityQuarter);
        return inspectionActivityQuarterMapper.toDto(inspectionActivityQuarter);
    }

    /**
     * Get all the inspectionActivityQuarters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionActivityQuarterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionActivityQuarters");
        return inspectionActivityQuarterRepository.findAll(pageable).map(inspectionActivityQuarterMapper::toDto);
    }

    /**
     * Get one inspectionActivityQuarter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionActivityQuarterDTO> findOne(Long id) {
        log.debug("Request to get InspectionActivityQuarter : {}", id);
        return inspectionActivityQuarterRepository.findById(id).map(inspectionActivityQuarterMapper::toDto);
    }

    /**
     * Delete the inspectionActivityQuarter by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionActivityQuarter : {}", id);
        inspectionActivityQuarterRepository.deleteById(id);
    }
}

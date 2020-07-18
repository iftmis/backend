package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionPlan;
import org.tamisemi.iftmis.repository.InspectionPlanRepository;
import org.tamisemi.iftmis.service.dto.InspectionPlanDTO;
import org.tamisemi.iftmis.service.mapper.InspectionPlanMapper;

/**
 * Service Implementation for managing {@link InspectionPlan}.
 */
@Service
@Transactional
public class InspectionPlanService {
    private final Logger log = LoggerFactory.getLogger(InspectionPlanService.class);

    private final InspectionPlanRepository inspectionPlanRepository;

    private final InspectionPlanMapper inspectionPlanMapper;

    public InspectionPlanService(InspectionPlanRepository inspectionPlanRepository, InspectionPlanMapper inspectionPlanMapper) {
        this.inspectionPlanRepository = inspectionPlanRepository;
        this.inspectionPlanMapper = inspectionPlanMapper;
    }

    /**
     * Save a inspectionPlan.
     *
     * @param inspectionPlanDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionPlanDTO save(InspectionPlanDTO inspectionPlanDTO) {
        log.debug("Request to save InspectionPlan : {}", inspectionPlanDTO);
        InspectionPlan inspectionPlan = inspectionPlanMapper.toEntity(inspectionPlanDTO);
        inspectionPlan = inspectionPlanRepository.save(inspectionPlan);
        return inspectionPlanMapper.toDto(inspectionPlan);
    }

    /**
     * Get all the inspectionPlans.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionPlanDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionPlans");
        return inspectionPlanRepository.findAll(pageable).map(inspectionPlanMapper::toDto);
    }

    /**
     * Get one inspectionPlan by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionPlanDTO> findOne(Long id) {
        log.debug("Request to get InspectionPlan : {}", id);
        return inspectionPlanRepository.findById(id).map(inspectionPlanMapper::toDto);
    }

    /**
     * Delete the inspectionPlan by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionPlan : {}", id);
        inspectionPlanRepository.deleteById(id);
    }
}

package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionBudget;
import org.tamisemi.iftmis.repository.InspectionBudgetRepository;
import org.tamisemi.iftmis.service.dto.InspectionBudgetDTO;
import org.tamisemi.iftmis.service.mapper.InspectionBudgetMapper;

/**
 * Service Implementation for managing {@link InspectionBudget}.
 */
@Service
@Transactional
public class InspectionBudgetService {
    private final Logger log = LoggerFactory.getLogger(InspectionBudgetService.class);

    private final InspectionBudgetRepository inspectionBudgetRepository;

    private final InspectionBudgetMapper inspectionBudgetMapper;

    public InspectionBudgetService(InspectionBudgetRepository inspectionBudgetRepository, InspectionBudgetMapper inspectionBudgetMapper) {
        this.inspectionBudgetRepository = inspectionBudgetRepository;
        this.inspectionBudgetMapper = inspectionBudgetMapper;
    }

    /**
     * Save a inspectionBudget.
     *
     * @param inspectionBudgetDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionBudgetDTO save(InspectionBudgetDTO inspectionBudgetDTO) {
        log.debug("Request to save InspectionBudget : {}", inspectionBudgetDTO);
        InspectionBudget inspectionBudget = inspectionBudgetMapper.toEntity(inspectionBudgetDTO);
        inspectionBudget = inspectionBudgetRepository.save(inspectionBudget);
        return inspectionBudgetMapper.toDto(inspectionBudget);
    }

    /**
     * Get all the inspectionBudgets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionBudgetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionBudgets");
        return inspectionBudgetRepository.findAll(pageable).map(inspectionBudgetMapper::toDto);
    }

    /**
     * Get one inspectionBudget by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionBudgetDTO> findOne(Long id) {
        log.debug("Request to get InspectionBudget : {}", id);
        return inspectionBudgetRepository.findById(id).map(inspectionBudgetMapper::toDto);
    }

    /**
     * Delete the inspectionBudget by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionBudget : {}", id);
        inspectionBudgetRepository.deleteById(id);
    }
}

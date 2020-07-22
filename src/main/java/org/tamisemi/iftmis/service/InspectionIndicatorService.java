package org.tamisemi.iftmis.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.Indicator;
import org.tamisemi.iftmis.domain.InspectionIndicator;
import org.tamisemi.iftmis.domain.InspectionSubArea;
import org.tamisemi.iftmis.repository.IndicatorRepository;
import org.tamisemi.iftmis.repository.InspectionIndicatorRepository;
import org.tamisemi.iftmis.service.dto.InspectionIndicatorDTO;
import org.tamisemi.iftmis.service.mapper.InspectionIndicatorMapper;

/**
 * Service Implementation for managing {@link InspectionIndicator}.
 */
@Service
@Transactional
public class InspectionIndicatorService {
    private final Logger log = LoggerFactory.getLogger(InspectionIndicatorService.class);

    private final InspectionIndicatorRepository inspectionIndicatorRepository;

    private final InspectionIndicatorMapper inspectionIndicatorMapper;

    private final IndicatorRepository indicatorRepository;

    private final InspectionProcedureService inspectionProcedureService;

    public InspectionIndicatorService(
        InspectionIndicatorRepository inspectionIndicatorRepository,
        InspectionIndicatorMapper inspectionIndicatorMapper,
        IndicatorRepository indicatorRepository,
        InspectionProcedureService inspectionProcedureService
    ) {
        this.inspectionIndicatorRepository = inspectionIndicatorRepository;
        this.inspectionIndicatorMapper = inspectionIndicatorMapper;
        this.indicatorRepository = indicatorRepository;
        this.inspectionProcedureService = inspectionProcedureService;
    }

    /**
     * Save a inspectionIndicator.
     *
     * @param inspectionIndicatorDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionIndicatorDTO save(InspectionIndicatorDTO inspectionIndicatorDTO) {
        log.debug("Request to save InspectionIndicator : {}", inspectionIndicatorDTO);
        InspectionIndicator inspectionIndicator = inspectionIndicatorMapper.toEntity(inspectionIndicatorDTO);
        inspectionIndicator = inspectionIndicatorRepository.save(inspectionIndicator);
        return inspectionIndicatorMapper.toDto(inspectionIndicator);
    }

    /**
     * Get all the inspectionIndicators.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionIndicatorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionIndicators");
        return inspectionIndicatorRepository.findAll(pageable).map(inspectionIndicatorMapper::toDto);
    }

    /**
     * Get one inspectionIndicator by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionIndicatorDTO> findOne(Long id) {
        log.debug("Request to get InspectionIndicator : {}", id);
        return inspectionIndicatorRepository.findById(id).map(inspectionIndicatorMapper::toDto);
    }

    /**
     * Delete the inspectionIndicator by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionIndicator : {}", id);
        inspectionIndicatorRepository.deleteById(id);
    }

    public void initializeBySubArea(InspectionSubArea inspectionSubArea) {
        List<Indicator> indicators = indicatorRepository.findAllBySubAreaId(inspectionSubArea.getSubArea().getId());
        for (Indicator indicator : indicators) {
            InspectionIndicator inspectionIndicator = inspectionIndicatorRepository.save(
                new InspectionIndicator(indicator, inspectionSubArea)
            );

            inspectionProcedureService.initializeByInspectionIndicator(inspectionIndicator);
        }
    }
}

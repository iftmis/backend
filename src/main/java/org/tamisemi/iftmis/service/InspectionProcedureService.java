package org.tamisemi.iftmis.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionIndicator;
import org.tamisemi.iftmis.domain.InspectionProcedure;
import org.tamisemi.iftmis.domain.Procedure;
import org.tamisemi.iftmis.repository.InspectionProcedureRepository;
import org.tamisemi.iftmis.repository.ProcedureRepository;
import org.tamisemi.iftmis.service.dto.InspectionProcedureDTO;
import org.tamisemi.iftmis.service.mapper.InspectionProcedureMapper;

/**
 * Service Implementation for managing {@link InspectionProcedure}.
 */
@Service
@Transactional
public class InspectionProcedureService {
    private final Logger log = LoggerFactory.getLogger(InspectionProcedureService.class);

    private final InspectionProcedureRepository inspectionProcedureRepository;

    private final InspectionProcedureMapper inspectionProcedureMapper;

    private final ProcedureRepository procedureRepository;

    public InspectionProcedureService(
        InspectionProcedureRepository inspectionProcedureRepository,
        InspectionProcedureMapper inspectionProcedureMapper,
        ProcedureRepository procedureRepository
    ) {
        this.inspectionProcedureRepository = inspectionProcedureRepository;
        this.inspectionProcedureMapper = inspectionProcedureMapper;
        this.procedureRepository = procedureRepository;
    }

    /**
     * Save a inspectionProcedure.
     *
     * @param inspectionProcedureDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionProcedureDTO save(InspectionProcedureDTO inspectionProcedureDTO) {
        log.debug("Request to save InspectionProcedure : {}", inspectionProcedureDTO);
        InspectionProcedure inspectionProcedure = inspectionProcedureMapper.toEntity(inspectionProcedureDTO);
        inspectionProcedure = inspectionProcedureRepository.save(inspectionProcedure);
        return inspectionProcedureMapper.toDto(inspectionProcedure);
    }

    /**
     * Get all the inspectionProcedures.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionProcedureDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionProcedures");
        return inspectionProcedureRepository.findAll(pageable).map(inspectionProcedureMapper::toDto);
    }

    /**
     * Get one inspectionProcedure by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionProcedureDTO> findOne(Long id) {
        log.debug("Request to get InspectionProcedure : {}", id);
        return inspectionProcedureRepository.findById(id).map(inspectionProcedureMapper::toDto);
    }

    /**
     * Delete the inspectionProcedure by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionProcedure : {}", id);
        inspectionProcedureRepository.deleteById(id);
    }

    public void initializeByInspectionIndicator(InspectionIndicator inspectionIndicator) {
        List<Procedure> procedures = procedureRepository.findByIndicator_Id(inspectionIndicator.getIndicator().getId());
        procedures.forEach(p -> inspectionProcedureRepository.save(new InspectionProcedure(p, inspectionIndicator)));
    }
}

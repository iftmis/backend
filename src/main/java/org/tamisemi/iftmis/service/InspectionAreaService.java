package org.tamisemi.iftmis.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionArea;
import org.tamisemi.iftmis.repository.InspectionAreaRepository;
import org.tamisemi.iftmis.service.dto.InspectionAreaDTO;
import org.tamisemi.iftmis.service.dto.InspectionAreaWithObjectiveDTO;
import org.tamisemi.iftmis.service.mapper.InspectionAreaMapper;
import org.tamisemi.iftmis.service.mapper.InspectionAreaWithObjectiveMapper;

/**
 * Service Implementation for managing {@link InspectionArea}.
 */
@Service
@Transactional
public class InspectionAreaService {
    private final Logger log = LoggerFactory.getLogger(InspectionAreaService.class);

    private final InspectionAreaRepository inspectionAreaRepository;

    private final InspectionAreaMapper inspectionAreaMapper;

    private final InspectionAreaWithObjectiveMapper inspectionAreaWithObjectiveMapper;

    public InspectionAreaService(
        InspectionAreaRepository inspectionAreaRepository,
        InspectionAreaMapper inspectionAreaMapper,
        InspectionAreaWithObjectiveMapper inspectionAreaWithObjectiveMapper
    ) {
        this.inspectionAreaRepository = inspectionAreaRepository;
        this.inspectionAreaMapper = inspectionAreaMapper;
        this.inspectionAreaWithObjectiveMapper = inspectionAreaWithObjectiveMapper;
    }

    /**
     * Save a inspectionArea.
     *
     * @param inspectionAreaDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionAreaDTO save(InspectionAreaDTO inspectionAreaDTO) {
        log.debug("Request to save InspectionArea : {}", inspectionAreaDTO);
        InspectionArea inspectionArea = inspectionAreaMapper.toEntity(inspectionAreaDTO);
        inspectionArea = inspectionAreaRepository.save(inspectionArea);
        return inspectionAreaMapper.toDto(inspectionArea);
    }

    /**
     * Get all the inspectionAreas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionAreas");
        return inspectionAreaRepository.findAll(pageable).map(inspectionAreaMapper::toDto);
    }

    /**
     * Get one inspectionArea by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionAreaDTO> findOne(Long id) {
        log.debug("Request to get InspectionArea : {}", id);
        return inspectionAreaRepository.findById(id).map(inspectionAreaMapper::toDto);
    }

    /**
     * Delete the inspectionArea by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionArea : {}", id);
        inspectionAreaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<InspectionAreaDTO> findAllByInspection(Long inspectionId) {
        return inspectionAreaRepository
            .findByInspection_Id(inspectionId)
            .stream()
            .map(inspectionAreaMapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<InspectionAreaWithObjectiveDTO> findAllWithObjective(Long inspectionId) {
        return inspectionAreaRepository
            .findByInspection_Id(inspectionId)
            .stream()
            .map(inspectionAreaWithObjectiveMapper::toDto)
            .collect(Collectors.toList());
    }

    @Transactional
    public void saveAll(List<InspectionAreaDTO> inspectionAreaDTOs) {
        List<InspectionArea> inspectionAreas = inspectionAreaDTOs.stream().map(inspectionAreaMapper::toEntity).collect(Collectors.toList());
        inspectionAreaRepository.saveAll(inspectionAreas);
    }

    @Transactional
    public void saveRemove(List<InspectionAreaDTO> inspectionAreaDTOs) {
        List<InspectionArea> inspectionAreas = inspectionAreaDTOs.stream().map(inspectionAreaMapper::toEntity).collect(Collectors.toList());
        inspectionAreaRepository.deleteInBatch(inspectionAreas);
    }
}

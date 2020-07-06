package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionSubArea;
import org.tamisemi.iftmis.repository.InspectionSubAreaRepository;
import org.tamisemi.iftmis.service.dto.InspectionSubAreaDTO;
import org.tamisemi.iftmis.service.mapper.InspectionSubAreaMapper;

/**
 * Service Implementation for managing {@link InspectionSubArea}.
 */
@Service
@Transactional
public class InspectionSubAreaService {
    private final Logger log = LoggerFactory.getLogger(InspectionSubAreaService.class);

    private final InspectionSubAreaRepository inspectionSubAreaRepository;

    private final InspectionSubAreaMapper inspectionSubAreaMapper;

    public InspectionSubAreaService(
        InspectionSubAreaRepository inspectionSubAreaRepository,
        InspectionSubAreaMapper inspectionSubAreaMapper
    ) {
        this.inspectionSubAreaRepository = inspectionSubAreaRepository;
        this.inspectionSubAreaMapper = inspectionSubAreaMapper;
    }

    /**
     * Save a inspectionSubArea.
     *
     * @param inspectionSubAreaDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionSubAreaDTO save(InspectionSubAreaDTO inspectionSubAreaDTO) {
        log.debug("Request to save InspectionSubArea : {}", inspectionSubAreaDTO);
        InspectionSubArea inspectionSubArea = inspectionSubAreaMapper.toEntity(inspectionSubAreaDTO);
        inspectionSubArea = inspectionSubAreaRepository.save(inspectionSubArea);
        return inspectionSubAreaMapper.toDto(inspectionSubArea);
    }

    /**
     * Get all the inspectionSubAreas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionSubAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionSubAreas");
        return inspectionSubAreaRepository.findAll(pageable).map(inspectionSubAreaMapper::toDto);
    }

    /**
     * Get one inspectionSubArea by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionSubAreaDTO> findOne(Long id) {
        log.debug("Request to get InspectionSubArea : {}", id);
        return inspectionSubAreaRepository.findById(id).map(inspectionSubAreaMapper::toDto);
    }

    /**
     * Delete the inspectionSubArea by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionSubArea : {}", id);
        inspectionSubAreaRepository.deleteById(id);
    }
}

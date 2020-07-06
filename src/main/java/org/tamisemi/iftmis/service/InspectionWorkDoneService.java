package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionWorkDone;
import org.tamisemi.iftmis.repository.InspectionWorkDoneRepository;
import org.tamisemi.iftmis.service.dto.InspectionWorkDoneDTO;
import org.tamisemi.iftmis.service.mapper.InspectionWorkDoneMapper;

/**
 * Service Implementation for managing {@link InspectionWorkDone}.
 */
@Service
@Transactional
public class InspectionWorkDoneService {
    private final Logger log = LoggerFactory.getLogger(InspectionWorkDoneService.class);

    private final InspectionWorkDoneRepository inspectionWorkDoneRepository;

    private final InspectionWorkDoneMapper inspectionWorkDoneMapper;

    public InspectionWorkDoneService(
        InspectionWorkDoneRepository inspectionWorkDoneRepository,
        InspectionWorkDoneMapper inspectionWorkDoneMapper
    ) {
        this.inspectionWorkDoneRepository = inspectionWorkDoneRepository;
        this.inspectionWorkDoneMapper = inspectionWorkDoneMapper;
    }

    /**
     * Save a inspectionWorkDone.
     *
     * @param inspectionWorkDoneDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionWorkDoneDTO save(InspectionWorkDoneDTO inspectionWorkDoneDTO) {
        log.debug("Request to save InspectionWorkDone : {}", inspectionWorkDoneDTO);
        InspectionWorkDone inspectionWorkDone = inspectionWorkDoneMapper.toEntity(inspectionWorkDoneDTO);
        inspectionWorkDone = inspectionWorkDoneRepository.save(inspectionWorkDone);
        return inspectionWorkDoneMapper.toDto(inspectionWorkDone);
    }

    /**
     * Get all the inspectionWorkDones.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionWorkDoneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionWorkDones");
        return inspectionWorkDoneRepository.findAll(pageable).map(inspectionWorkDoneMapper::toDto);
    }

    /**
     * Get one inspectionWorkDone by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionWorkDoneDTO> findOne(Long id) {
        log.debug("Request to get InspectionWorkDone : {}", id);
        return inspectionWorkDoneRepository.findById(id).map(inspectionWorkDoneMapper::toDto);
    }

    /**
     * Delete the inspectionWorkDone by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionWorkDone : {}", id);
        inspectionWorkDoneRepository.deleteById(id);
    }
}

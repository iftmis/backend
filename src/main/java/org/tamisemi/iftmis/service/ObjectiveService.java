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
import org.tamisemi.iftmis.domain.Objective;
import org.tamisemi.iftmis.repository.ObjectiveRepository;
import org.tamisemi.iftmis.service.dto.ObjectiveDTO;
import org.tamisemi.iftmis.service.mapper.ObjectiveMapper;

/**
 * Service Implementation for managing {@link Objective}.
 */
@Service
@Transactional
public class ObjectiveService {
    private final Logger log = LoggerFactory.getLogger(ObjectiveService.class);

    private final ObjectiveRepository objectiveRepository;

    private final ObjectiveMapper objectiveMapper;

    public ObjectiveService(ObjectiveRepository objectiveRepository, ObjectiveMapper objectiveMapper) {
        this.objectiveRepository = objectiveRepository;
        this.objectiveMapper = objectiveMapper;
    }

    /**
     * Save a objective.
     *
     * @param objectiveDTO the entity to save.
     * @return the persisted entity.
     */
    public ObjectiveDTO save(ObjectiveDTO objectiveDTO) {
        log.debug("Request to save Objective : {}", objectiveDTO);
        Objective objective = objectiveMapper.toEntity(objectiveDTO);
        objective = objectiveRepository.save(objective);
        return objectiveMapper.toDto(objective);
    }

    /**
     * Get all the objectives.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ObjectiveDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Objectives");
        return objectiveRepository.findAll(pageable).map(objectiveMapper::toDto);
    }

    /**
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<ObjectiveDTO> findAll() {
        log.debug("Request to get all Objectives");
        return objectiveRepository.findAll().stream().map(objectiveMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Get one objective by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ObjectiveDTO> findOne(Long id) {
        log.debug("Request to get Objective : {}", id);
        return objectiveRepository.findById(id).map(objectiveMapper::toDto);
    }

    /**
     * Delete the objective by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Objective : {}", id);
        objectiveRepository.deleteById(id);
    }
}

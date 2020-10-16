package org.tamisemi.iftmis.service;

import org.tamisemi.iftmis.domain.TheClusters;
import org.tamisemi.iftmis.repository.TheClustersRepository;
import org.tamisemi.iftmis.service.dto.TheClustersDTO;
import org.tamisemi.iftmis.service.mapper.TheClustersMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TheClusters}.
 */
@Service
@Transactional
public class TheClustersService {

    private final Logger log = LoggerFactory.getLogger(TheClustersService.class);

    private final TheClustersRepository theClustersRepository;

    private final TheClustersMapper theClustersMapper;

    public TheClustersService(TheClustersRepository theClustersRepository, TheClustersMapper theClustersMapper) {
        this.theClustersRepository = theClustersRepository;
        this.theClustersMapper = theClustersMapper;
    }

    /**
     * Save a theClusters.
     *
     * @param theClustersDTO the entity to save.
     * @return the persisted entity.
     */
    public TheClustersDTO save(TheClustersDTO theClustersDTO) {
        log.debug("Request to save TheClusters : {}", theClustersDTO);
        TheClusters theClusters = theClustersMapper.toEntity(theClustersDTO);
        theClusters = theClustersRepository.save(theClusters);
        return theClustersMapper.toDto(theClusters);
    }

    /**
     * Get all the theClusters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<TheClustersDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TheClusters");
        return theClustersRepository.findAll(pageable)
            .map(theClustersMapper::toDto);
    }


    /**
     * Get one theClusters by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<TheClustersDTO> findOne(Long id) {
        log.debug("Request to get TheClusters : {}", id);
        return theClustersRepository.findById(id)
            .map(theClustersMapper::toDto);
    }

    /**
     * Delete the theClusters by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete TheClusters : {}", id);
        theClustersRepository.deleteById(id);
    }
}

package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.Quarter;
import org.tamisemi.iftmis.repository.QuarterRepository;
import org.tamisemi.iftmis.service.dto.QuarterDTO;
import org.tamisemi.iftmis.service.mapper.QuarterMapper;

/**
 * Service Implementation for managing {@link Quarter}.
 */
@Service
@Transactional
public class QuarterService {
    private final Logger log = LoggerFactory.getLogger(QuarterService.class);

    private final QuarterRepository quarterRepository;

    private final QuarterMapper quarterMapper;

    public QuarterService(QuarterRepository quarterRepository, QuarterMapper quarterMapper) {
        this.quarterRepository = quarterRepository;
        this.quarterMapper = quarterMapper;
    }

    /**
     * Save a quarter.
     *
     * @param quarterDTO the entity to save.
     * @return the persisted entity.
     */
    public QuarterDTO save(QuarterDTO quarterDTO) {
        log.debug("Request to save Quarter : {}", quarterDTO);
        Quarter quarter = quarterMapper.toEntity(quarterDTO);
        quarter = quarterRepository.save(quarter);
        return quarterMapper.toDto(quarter);
    }

    /**
     * Get all the quarters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<QuarterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Quarters");
        return quarterRepository.findAll(pageable).map(quarterMapper::toDto);
    }

    /**
     * Get one quarter by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<QuarterDTO> findOne(Long id) {
        log.debug("Request to get Quarter : {}", id);
        return quarterRepository.findById(id).map(quarterMapper::toDto);
    }

    /**
     * Delete the quarter by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Quarter : {}", id);
        quarterRepository.deleteById(id);
    }
}

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
import org.tamisemi.iftmis.repository.IndicatorRepository;
import org.tamisemi.iftmis.service.dto.IndicatorDTO;
import org.tamisemi.iftmis.service.mapper.IndicatorMapper;

/**
 * Service Implementation for managing {@link Indicator}.
 */
@Service
@Transactional
public class IndicatorService {
    private final Logger log = LoggerFactory.getLogger(IndicatorService.class);

    private final IndicatorRepository indicatorRepository;

    private final IndicatorMapper indicatorMapper;

    public IndicatorService(IndicatorRepository indicatorRepository, IndicatorMapper indicatorMapper) {
        this.indicatorRepository = indicatorRepository;
        this.indicatorMapper = indicatorMapper;
    }

    /**
     * Save a indicator.
     *
     * @param indicatorDTO the entity to save.
     * @return the persisted entity.
     */
    public IndicatorDTO save(IndicatorDTO indicatorDTO) {
        log.debug("Request to save Indicator : {}", indicatorDTO);
        Indicator indicator = indicatorMapper.toEntity(indicatorDTO);
        indicator = indicatorRepository.save(indicator);
        return indicatorMapper.toDto(indicator);
    }

    /**
     * Get all the indicators.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<IndicatorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Indicators");
        return indicatorRepository.findAll(pageable).map(indicatorMapper::toDto);
    }

    @Transactional(readOnly = true)
    public List<Indicator> findAll() {
        log.debug("Request to get all Indicators");
        return indicatorRepository.findAll();
    }

    /**
     * Get one indicator by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<IndicatorDTO> findOne(Long id) {
        log.debug("Request to get Indicator : {}", id);
        return indicatorRepository.findById(id).map(indicatorMapper::toDto);
    }

    /**
     * Delete the indicator by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Indicator : {}", id);
        indicatorRepository.deleteById(id);
    }
}

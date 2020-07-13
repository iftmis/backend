package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.RiskRating;
import org.tamisemi.iftmis.repository.RiskRatingRepository;
import org.tamisemi.iftmis.service.dto.RiskRatingDTO;
import org.tamisemi.iftmis.service.mapper.RiskRatingMapper;

/**
 * Service Implementation for managing {@link RiskRating}.
 */
@Service
@Transactional
public class RiskRatingService {
    private final Logger log = LoggerFactory.getLogger(RiskRatingService.class);

    private final RiskRatingRepository riskRatingRepository;

    private final RiskRatingMapper riskRatingMapper;

    public RiskRatingService(RiskRatingRepository riskRatingRepository, RiskRatingMapper riskRatingMapper) {
        this.riskRatingRepository = riskRatingRepository;
        this.riskRatingMapper = riskRatingMapper;
    }

    /**
     * Save a riskRating.
     *
     * @param riskRatingDTO the entity to save.
     * @return the persisted entity.
     */
    public RiskRatingDTO save(RiskRatingDTO riskRatingDTO) {
        log.debug("Request to save RiskRating : {}", riskRatingDTO);
        RiskRating riskRating = riskRatingMapper.toEntity(riskRatingDTO);
        riskRating = riskRatingRepository.save(riskRating);
        return riskRatingMapper.toDto(riskRating);
    }

    /**
     * Get all the riskRatings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RiskRatingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RiskRatings");
        return riskRatingRepository.findAll(pageable).map(riskRatingMapper::toDto);
    }

    /**
     * Get one riskRating by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RiskRatingDTO> findOne(Long id) {
        log.debug("Request to get RiskRating : {}", id);
        return riskRatingRepository.findById(id).map(riskRatingMapper::toDto);
    }

    /**
     * Delete the riskRating by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RiskRating : {}", id);
        riskRatingRepository.deleteById(id);
    }
}

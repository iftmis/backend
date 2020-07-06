package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.FindingRecommendation;
import org.tamisemi.iftmis.repository.FindingRecommendationRepository;
import org.tamisemi.iftmis.service.dto.FindingRecommendationDTO;
import org.tamisemi.iftmis.service.mapper.FindingRecommendationMapper;

/**
 * Service Implementation for managing {@link FindingRecommendation}.
 */
@Service
@Transactional
public class FindingRecommendationService {
    private final Logger log = LoggerFactory.getLogger(FindingRecommendationService.class);

    private final FindingRecommendationRepository findingRecommendationRepository;

    private final FindingRecommendationMapper findingRecommendationMapper;

    public FindingRecommendationService(
        FindingRecommendationRepository findingRecommendationRepository,
        FindingRecommendationMapper findingRecommendationMapper
    ) {
        this.findingRecommendationRepository = findingRecommendationRepository;
        this.findingRecommendationMapper = findingRecommendationMapper;
    }

    /**
     * Save a findingRecommendation.
     *
     * @param findingRecommendationDTO the entity to save.
     * @return the persisted entity.
     */
    public FindingRecommendationDTO save(FindingRecommendationDTO findingRecommendationDTO) {
        log.debug("Request to save FindingRecommendation : {}", findingRecommendationDTO);
        FindingRecommendation findingRecommendation = findingRecommendationMapper.toEntity(findingRecommendationDTO);
        findingRecommendation = findingRecommendationRepository.save(findingRecommendation);
        return findingRecommendationMapper.toDto(findingRecommendation);
    }

    /**
     * Get all the findingRecommendations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FindingRecommendationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FindingRecommendations");
        return findingRecommendationRepository.findAll(pageable).map(findingRecommendationMapper::toDto);
    }

    /**
     * Get one findingRecommendation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FindingRecommendationDTO> findOne(Long id) {
        log.debug("Request to get FindingRecommendation : {}", id);
        return findingRecommendationRepository.findById(id).map(findingRecommendationMapper::toDto);
    }

    /**
     * Delete the findingRecommendation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FindingRecommendation : {}", id);
        findingRecommendationRepository.deleteById(id);
    }
}

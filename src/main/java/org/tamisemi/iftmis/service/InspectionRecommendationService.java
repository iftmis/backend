package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.InspectionRecommendation;
import org.tamisemi.iftmis.repository.InspectionRecommendationRepository;
import org.tamisemi.iftmis.service.dto.InspectionRecommendationDTO;
import org.tamisemi.iftmis.service.mapper.InspectionRecommendationMapper;

/**
 * Service Implementation for managing {@link InspectionRecommendation}.
 */
@Service
@Transactional
public class InspectionRecommendationService {
    private final Logger log = LoggerFactory.getLogger(InspectionRecommendationService.class);

    private final InspectionRecommendationRepository inspectionRecommendationRepository;

    private final InspectionRecommendationMapper inspectionRecommendationMapper;

    public InspectionRecommendationService(
        InspectionRecommendationRepository inspectionRecommendationRepository,
        InspectionRecommendationMapper inspectionRecommendationMapper
    ) {
        this.inspectionRecommendationRepository = inspectionRecommendationRepository;
        this.inspectionRecommendationMapper = inspectionRecommendationMapper;
    }

    /**
     * Save a inspectionRecommendation.
     *
     * @param inspectionRecommendationDTO the entity to save.
     * @return the persisted entity.
     */
    public InspectionRecommendationDTO save(InspectionRecommendationDTO inspectionRecommendationDTO) {
        log.debug("Request to save InspectionRecommendation : {}", inspectionRecommendationDTO);
        InspectionRecommendation inspectionRecommendation = inspectionRecommendationMapper.toEntity(inspectionRecommendationDTO);
        inspectionRecommendation = inspectionRecommendationRepository.save(inspectionRecommendation);
        return inspectionRecommendationMapper.toDto(inspectionRecommendation);
    }

    /**
     * Get all the inspectionRecommendations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionRecommendationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InspectionRecommendations");
        return inspectionRecommendationRepository.findAll(pageable).map(inspectionRecommendationMapper::toDto);
    }

    /**
     * Get one inspectionRecommendation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InspectionRecommendationDTO> findOne(Long id) {
        log.debug("Request to get InspectionRecommendation : {}", id);
        return inspectionRecommendationRepository.findById(id).map(inspectionRecommendationMapper::toDto);
    }

    /**
     * Delete the inspectionRecommendation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete InspectionRecommendation : {}", id);
        inspectionRecommendationRepository.deleteById(id);
    }
}

package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.RiskCategory;
import org.tamisemi.iftmis.repository.RiskCategoryRepository;
import org.tamisemi.iftmis.service.dto.RiskCategoryDTO;
import org.tamisemi.iftmis.service.mapper.RiskCategoryMapper;

/**
 * Service Implementation for managing {@link RiskCategory}.
 */
@Service
@Transactional
public class RiskCategoryService {
    private final Logger log = LoggerFactory.getLogger(RiskCategoryService.class);

    private final RiskCategoryRepository riskCategoryRepository;

    private final RiskCategoryMapper riskCategoryMapper;

    public RiskCategoryService(RiskCategoryRepository riskCategoryRepository, RiskCategoryMapper riskCategoryMapper) {
        this.riskCategoryRepository = riskCategoryRepository;
        this.riskCategoryMapper = riskCategoryMapper;
    }

    /**
     * Save a riskCategory.
     *
     * @param riskCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public RiskCategoryDTO save(RiskCategoryDTO riskCategoryDTO) {
        log.debug("Request to save RiskCategory : {}", riskCategoryDTO);
        RiskCategory riskCategory = riskCategoryMapper.toEntity(riskCategoryDTO);
        riskCategory = riskCategoryRepository.save(riskCategory);
        return riskCategoryMapper.toDto(riskCategory);
    }

    /**
     * Get all the riskCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RiskCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RiskCategories");
        return riskCategoryRepository.findAll(pageable).map(riskCategoryMapper::toDto);
    }

    /**
     * Get one riskCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RiskCategoryDTO> findOne(Long id) {
        log.debug("Request to get RiskCategory : {}", id);
        return riskCategoryRepository.findById(id).map(riskCategoryMapper::toDto);
    }

    /**
     * Delete the riskCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RiskCategory : {}", id);
        riskCategoryRepository.deleteById(id);
    }
}

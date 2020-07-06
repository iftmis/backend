package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.FindingCategory;
import org.tamisemi.iftmis.repository.FindingCategoryRepository;
import org.tamisemi.iftmis.service.dto.FindingCategoryDTO;
import org.tamisemi.iftmis.service.mapper.FindingCategoryMapper;

/**
 * Service Implementation for managing {@link FindingCategory}.
 */
@Service
@Transactional
public class FindingCategoryService {
    private final Logger log = LoggerFactory.getLogger(FindingCategoryService.class);

    private final FindingCategoryRepository findingCategoryRepository;

    private final FindingCategoryMapper findingCategoryMapper;

    public FindingCategoryService(FindingCategoryRepository findingCategoryRepository, FindingCategoryMapper findingCategoryMapper) {
        this.findingCategoryRepository = findingCategoryRepository;
        this.findingCategoryMapper = findingCategoryMapper;
    }

    /**
     * Save a findingCategory.
     *
     * @param findingCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public FindingCategoryDTO save(FindingCategoryDTO findingCategoryDTO) {
        log.debug("Request to save FindingCategory : {}", findingCategoryDTO);
        FindingCategory findingCategory = findingCategoryMapper.toEntity(findingCategoryDTO);
        findingCategory = findingCategoryRepository.save(findingCategory);
        return findingCategoryMapper.toDto(findingCategory);
    }

    /**
     * Get all the findingCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FindingCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FindingCategories");
        return findingCategoryRepository.findAll(pageable).map(findingCategoryMapper::toDto);
    }

    /**
     * Get one findingCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FindingCategoryDTO> findOne(Long id) {
        log.debug("Request to get FindingCategory : {}", id);
        return findingCategoryRepository.findById(id).map(findingCategoryMapper::toDto);
    }

    /**
     * Delete the findingCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FindingCategory : {}", id);
        findingCategoryRepository.deleteById(id);
    }
}

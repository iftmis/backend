package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.FindingSubCategory;
import org.tamisemi.iftmis.repository.FindingSubCategoryRepository;
import org.tamisemi.iftmis.service.dto.FindingSubCategoryDTO;
import org.tamisemi.iftmis.service.mapper.FindingSubCategoryMapper;

/**
 * Service Implementation for managing {@link FindingSubCategory}.
 */
@Service
@Transactional
public class FindingSubCategoryService {
    private final Logger log = LoggerFactory.getLogger(FindingSubCategoryService.class);

    private final FindingSubCategoryRepository findingSubCategoryRepository;

    private final FindingSubCategoryMapper findingSubCategoryMapper;

    public FindingSubCategoryService(
        FindingSubCategoryRepository findingSubCategoryRepository,
        FindingSubCategoryMapper findingSubCategoryMapper
    ) {
        this.findingSubCategoryRepository = findingSubCategoryRepository;
        this.findingSubCategoryMapper = findingSubCategoryMapper;
    }

    /**
     * Save a findingSubCategory.
     *
     * @param findingSubCategoryDTO the entity to save.
     * @return the persisted entity.
     */
    public FindingSubCategoryDTO save(FindingSubCategoryDTO findingSubCategoryDTO) {
        log.debug("Request to save FindingSubCategory : {}", findingSubCategoryDTO);
        FindingSubCategory findingSubCategory = findingSubCategoryMapper.toEntity(findingSubCategoryDTO);
        findingSubCategory = findingSubCategoryRepository.save(findingSubCategory);
        return findingSubCategoryMapper.toDto(findingSubCategory);
    }

    /**
     * Get all the findingSubCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FindingSubCategoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FindingSubCategories");
        return findingSubCategoryRepository.findAll(pageable).map(findingSubCategoryMapper::toDto);
    }

    /**
     * Get one findingSubCategory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FindingSubCategoryDTO> findOne(Long id) {
        log.debug("Request to get FindingSubCategory : {}", id);
        return findingSubCategoryRepository.findById(id).map(findingSubCategoryMapper::toDto);
    }

    /**
     * Delete the findingSubCategory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FindingSubCategory : {}", id);
        findingSubCategoryRepository.deleteById(id);
    }
}

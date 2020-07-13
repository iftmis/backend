package org.tamisemi.iftmis.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.SubArea;
import org.tamisemi.iftmis.repository.SubAreaRepository;
import org.tamisemi.iftmis.service.dto.SubAreaDTO;
import org.tamisemi.iftmis.service.mapper.SubAreaMapper;

/**
 * Service Implementation for managing {@link SubArea}.
 */
@Service
@Transactional
public class SubAreaService {
    private final Logger log = LoggerFactory.getLogger(SubAreaService.class);

    private final SubAreaRepository subAreaRepository;

    private final SubAreaMapper subAreaMapper;

    public SubAreaService(SubAreaRepository subAreaRepository, SubAreaMapper subAreaMapper) {
        this.subAreaRepository = subAreaRepository;
        this.subAreaMapper = subAreaMapper;
    }

    /**
     * Save a subArea.
     *
     * @param subAreaDTO the entity to save.
     * @return the persisted entity.
     */
    public SubAreaDTO save(SubAreaDTO subAreaDTO) {
        log.debug("Request to save SubArea : {}", subAreaDTO);
        SubArea subArea = subAreaMapper.toEntity(subAreaDTO);
        subArea = subAreaRepository.save(subArea);
        return subAreaMapper.toDto(subArea);
    }

    /**
     * Get all the subAreas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<SubAreaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all SubAreas");
        return subAreaRepository.findAll(pageable).map(subAreaMapper::toDto);
    }

    /**
     * @return
     */
    @Transactional(readOnly = true)
    public List<SubArea> findAll() {
        log.debug("Request to get all SubAreas");
        return subAreaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<SubAreaDTO> findAll(Long areaId, Pageable pageable) {
        log.debug("Request to get all SubAreas");
        return subAreaRepository.findAllByAreaId(areaId, pageable).map(subAreaMapper::toDto);
    }

    /**
     * Get one subArea by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SubAreaDTO> findOne(Long id) {
        log.debug("Request to get SubArea : {}", id);
        return subAreaRepository.findById(id).map(subAreaMapper::toDto);
    }

    /**
     * Delete the subArea by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SubArea : {}", id);
        subAreaRepository.deleteById(id);
    }
}

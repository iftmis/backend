package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.GfsCode;
import org.tamisemi.iftmis.repository.GfsCodeRepository;
import org.tamisemi.iftmis.service.dto.GfsCodeDTO;
import org.tamisemi.iftmis.service.mapper.GfsCodeMapper;

/**
 * Service Implementation for managing {@link GfsCode}.
 */
@Service
@Transactional
public class GfsCodeService {
    private final Logger log = LoggerFactory.getLogger(GfsCodeService.class);

    private final GfsCodeRepository gfsCodeRepository;

    private final GfsCodeMapper gfsCodeMapper;

    public GfsCodeService(GfsCodeRepository gfsCodeRepository, GfsCodeMapper gfsCodeMapper) {
        this.gfsCodeRepository = gfsCodeRepository;
        this.gfsCodeMapper = gfsCodeMapper;
    }

    /**
     * Save a gfsCode.
     *
     * @param gfsCodeDTO the entity to save.
     * @return the persisted entity.
     */
    public GfsCodeDTO save(GfsCodeDTO gfsCodeDTO) {
        log.debug("Request to save GfsCode : {}", gfsCodeDTO);
        GfsCode gfsCode = gfsCodeMapper.toEntity(gfsCodeDTO);
        gfsCode = gfsCodeRepository.save(gfsCode);
        return gfsCodeMapper.toDto(gfsCode);
    }

    /**
     * Get all the gfsCodes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GfsCodeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GfsCodes");
        return gfsCodeRepository.findAll(pageable).map(gfsCodeMapper::toDto);
    }

    /**
     * Get one gfsCode by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GfsCodeDTO> findOne(Long id) {
        log.debug("Request to get GfsCode : {}", id);
        return gfsCodeRepository.findById(id).map(gfsCodeMapper::toDto);
    }

    /**
     * Delete the gfsCode by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete GfsCode : {}", id);
        gfsCodeRepository.deleteById(id);
    }
}

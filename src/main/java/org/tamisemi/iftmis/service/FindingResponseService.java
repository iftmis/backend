package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.FindingResponse;
import org.tamisemi.iftmis.repository.FindingResponseRepository;
import org.tamisemi.iftmis.service.dto.FindingResponseDTO;
import org.tamisemi.iftmis.service.mapper.FindingResponseMapper;

/**
 * Service Implementation for managing {@link FindingResponse}.
 */
@Service
@Transactional
public class FindingResponseService {
    private final Logger log = LoggerFactory.getLogger(FindingResponseService.class);

    private final FindingResponseRepository findingResponseRepository;

    private final FindingResponseMapper findingResponseMapper;

    public FindingResponseService(FindingResponseRepository findingResponseRepository, FindingResponseMapper findingResponseMapper) {
        this.findingResponseRepository = findingResponseRepository;
        this.findingResponseMapper = findingResponseMapper;
    }

    /**
     * Save a findingResponse.
     *
     * @param findingResponseDTO the entity to save.
     * @return the persisted entity.
     */
    public FindingResponseDTO save(FindingResponseDTO findingResponseDTO) {
        log.debug("Request to save FindingResponse : {}", findingResponseDTO);
        FindingResponse findingResponse = findingResponseMapper.toEntity(findingResponseDTO);
        findingResponse = findingResponseRepository.save(findingResponse);
        return findingResponseMapper.toDto(findingResponse);
    }

    /**
     * Get all the findingResponses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FindingResponseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FindingResponses");
        return findingResponseRepository.findAll(pageable).map(findingResponseMapper::toDto);
    }

    /**
     * Get one findingResponse by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FindingResponseDTO> findOne(Long id) {
        log.debug("Request to get FindingResponse : {}", id);
        return findingResponseRepository.findById(id).map(findingResponseMapper::toDto);
    }

    /**
     * Delete the findingResponse by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FindingResponse : {}", id);
        findingResponseRepository.deleteById(id);
    }
}

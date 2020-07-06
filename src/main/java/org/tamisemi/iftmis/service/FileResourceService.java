package org.tamisemi.iftmis.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.FileResource;
import org.tamisemi.iftmis.repository.FileResourceRepository;
import org.tamisemi.iftmis.service.dto.FileResourceDTO;
import org.tamisemi.iftmis.service.mapper.FileResourceMapper;

/**
 * Service Implementation for managing {@link FileResource}.
 */
@Service
@Transactional
public class FileResourceService {
    private final Logger log = LoggerFactory.getLogger(FileResourceService.class);

    private final FileResourceRepository fileResourceRepository;

    private final FileResourceMapper fileResourceMapper;

    public FileResourceService(FileResourceRepository fileResourceRepository, FileResourceMapper fileResourceMapper) {
        this.fileResourceRepository = fileResourceRepository;
        this.fileResourceMapper = fileResourceMapper;
    }

    /**
     * Save a fileResource.
     *
     * @param fileResourceDTO the entity to save.
     * @return the persisted entity.
     */
    public FileResourceDTO save(FileResourceDTO fileResourceDTO) {
        log.debug("Request to save FileResource : {}", fileResourceDTO);
        FileResource fileResource = fileResourceMapper.toEntity(fileResourceDTO);
        fileResource = fileResourceRepository.save(fileResource);
        return fileResourceMapper.toDto(fileResource);
    }

    /**
     * Get all the fileResources.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FileResourceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FileResources");
        return fileResourceRepository.findAll(pageable).map(fileResourceMapper::toDto);
    }

    /**
     * Get one fileResource by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FileResourceDTO> findOne(Long id) {
        log.debug("Request to get FileResource : {}", id);
        return fileResourceRepository.findById(id).map(fileResourceMapper::toDto);
    }

    /**
     * Delete the fileResource by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FileResource : {}", id);
        fileResourceRepository.deleteById(id);
    }
}

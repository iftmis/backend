package org.tamisemi.iftmis.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.Finding;
import org.tamisemi.iftmis.domain.Finding;
import org.tamisemi.iftmis.domain.enumeration.FindingSource;
import org.tamisemi.iftmis.repository.FindingRepository;
import org.tamisemi.iftmis.service.dto.FindingDTO;
import org.tamisemi.iftmis.service.mapper.FindingMapper;

/**
 * Service Implementation for managing {@link Finding}.
 */
@Service
@Transactional
public class FindingService {
    private final Logger log = LoggerFactory.getLogger(FindingService.class);

    private final FindingRepository findingRepository;

    private final FindingMapper findingMapper;

    public FindingService(FindingRepository findingRepository, FindingMapper findingMapper) {
        this.findingRepository = findingRepository;
        this.findingMapper = findingMapper;
    }

    /**
     * Save a finding.
     *
     * @param findingDTO the entity to save.
     * @return the persisted entity.
     */
    public FindingDTO save(FindingDTO findingDTO) {
        Finding finding = findingMapper.toEntity(findingDTO);
        Finding finalFinding = finding;
        finding.getFindingRecommendations().forEach(r -> r.setFinding(finalFinding));
        finding = findingRepository.save(finding);
        return findingMapper.toDto(finding);
    }

    /**
     * Get all the findings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FindingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Findings");
        return findingRepository.findAll(pageable).map(findingMapper::toDto);
    }

    /**
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<FindingDTO> findAll() {
        log.debug("Request to get all Findings");
        return findingRepository.findAll().stream().map(findingMapper::toDto).collect(Collectors.toList());
    }

    /**
     *
     * @param organisationUnitId
     * @param source
     * @param pageable
     * @return
     */
    @Transactional(readOnly = true)
    public Page<FindingDTO> findAllByOrganisationUnitIdAndSource(Long organisationUnitId, String source, Pageable pageable) {
        FindingSource findingSource;
        if (source.equals(FindingSource.CAG.toString())) {
            findingSource = FindingSource.CAG;
        } else if (source.equals(FindingSource.PPRA.toString())) {
            findingSource = FindingSource.PPRA;
        } else if (source.equals(FindingSource.LAAC.toString())) {
            findingSource = FindingSource.LAAC;
        } else if (source.equals(FindingSource.INSPECTION.toString())) {
            findingSource = FindingSource.INSPECTION;
        } else {
            findingSource = FindingSource.IA;
        }
        return findingRepository.findAllByOrganisationUnitIdAndSource(organisationUnitId, findingSource, pageable).map(findingMapper::toDto);
    }

    /**
     *
     * @param organisationUnitId
     * @param source
     * @return
     */
    @Transactional(readOnly = true)
    public List<FindingDTO> findAllByOrganisationUnitIdAndSource(Long organisationUnitId, FindingSource source) {
        return findingRepository.findAllByOrganisationUnitIdAndSource(organisationUnitId, source).stream().map(findingMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Get one finding by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FindingDTO> findOne(Long id) {
        log.debug("Request to get Finding : {}", id);
        return findingRepository.findById(id).map(findingMapper::toDto);
    }

    /**
     * Delete the finding by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Finding : {}", id);
        findingRepository.deleteById(id);
    }
}

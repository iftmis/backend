package org.tamisemi.iftmis.service;

import org.tamisemi.iftmis.domain.FundingManagement;
import org.tamisemi.iftmis.repository.FundingManagementRepository;
import org.tamisemi.iftmis.service.dto.FundingManagementDTO;
import org.tamisemi.iftmis.service.mapper.FundingManagementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FundingManagement}.
 */
@Service
@Transactional
public class FundingManagementService {

    private final Logger log = LoggerFactory.getLogger(FundingManagementService.class);

    private final FundingManagementRepository fundingManagementRepository;

    private final FundingManagementMapper fundingManagementMapper;

    public FundingManagementService(FundingManagementRepository fundingManagementRepository, FundingManagementMapper fundingManagementMapper) {
        this.fundingManagementRepository = fundingManagementRepository;
        this.fundingManagementMapper = fundingManagementMapper;
    }

    /**
     * Save a fundingManagement.
     *
     * @param fundingManagementDTO the entity to save.
     * @return the persisted entity.
     */
    public FundingManagementDTO save(FundingManagementDTO fundingManagementDTO) {
        log.debug("Request to save FundingManagement : {}", fundingManagementDTO);
        FundingManagement fundingManagement = fundingManagementMapper.toEntity(fundingManagementDTO);
        fundingManagement = fundingManagementRepository.save(fundingManagement);
        return fundingManagementMapper.toDto(fundingManagement);
    }

    /**
     * Get all the fundingManagements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FundingManagementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FundingManagements");
        return fundingManagementRepository.findAll(pageable)
            .map(fundingManagementMapper::toDto);
    }


    /**
     * Get one fundingManagement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FundingManagementDTO> findOne(Long id) {
        log.debug("Request to get FundingManagement : {}", id);
        return fundingManagementRepository.findById(id)
            .map(fundingManagementMapper::toDto);
    }

    /**
     * Delete the fundingManagement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FundingManagement : {}", id);
        fundingManagementRepository.deleteById(id);
    }
}

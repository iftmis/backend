package org.tamisemi.iftmis.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.FinancialYear;
import org.tamisemi.iftmis.repository.FinancialYearRepository;
import org.tamisemi.iftmis.service.dto.FinancialYearDTO;
import org.tamisemi.iftmis.service.mapper.FinancialYearMapper;

/**
 * Service Implementation for managing {@link FinancialYear}.
 */
@Service
@Transactional
public class FinancialYearService {
    private final Logger log = LoggerFactory.getLogger(FinancialYearService.class);

    private final FinancialYearRepository financialYearRepository;

    private final FinancialYearMapper financialYearMapper;

    public FinancialYearService(FinancialYearRepository financialYearRepository, FinancialYearMapper financialYearMapper) {
        this.financialYearRepository = financialYearRepository;
        this.financialYearMapper = financialYearMapper;
    }

    /**
     * Save a financialYear.
     *
     * @param financialYearDTO the entity to save.
     * @return the persisted entity.
     */
    public FinancialYearDTO save(FinancialYearDTO financialYearDTO) {
        log.debug("Request to save FinancialYear : {}", financialYearDTO);
        FinancialYear financialYear = financialYearMapper.toEntity(financialYearDTO);
        financialYear = financialYearRepository.save(financialYear);
        return financialYearMapper.toDto(financialYear);
    }

    /**
     * Get all the financialYears.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<FinancialYearDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinancialYears");
        return financialYearRepository.findAll(pageable).map(financialYearMapper::toDto);
    }

    /**
     *
     * @return
     */
    @Transactional(readOnly = true)
    public List<FinancialYear> findAll() {
        log.debug("Request to get all FinancialYears");
        return financialYearRepository.findAll();
    }

    /**
     * Get one financialYear by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FinancialYearDTO> findOne(Long id) {
        log.debug("Request to get FinancialYear : {}", id);
        return financialYearRepository.findById(id).map(financialYearMapper::toDto);
    }

    /**
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = true)
    public FinancialYear getById(Long id) {
        return financialYearRepository.findById(id).orElse(null);
    }

    /**
     * Delete the financialYear by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete FinancialYear : {}", id);
        financialYearRepository.deleteById(id);
    }

    public Optional<FinancialYear> currentYear(){
        return financialYearRepository.findFirstByIsOpenedTrue();
    }

    public Boolean closeCurrentFinancialYear() {
        return financialYearRepository.closeCurrentFinancialYear() > 0;
    }
}

package org.tamisemi.iftmis.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import org.tamisemi.iftmis.domain.FundingManagement;
import org.tamisemi.iftmis.domain.*; // for static metamodels
import org.tamisemi.iftmis.repository.FundingManagementRepository;
import org.tamisemi.iftmis.service.dto.FundingManagementCriteria;
import org.tamisemi.iftmis.service.dto.FundingManagementDTO;
import org.tamisemi.iftmis.service.mapper.FundingManagementMapper;

/**
 * Service for executing complex queries for {@link FundingManagement} entities in the database.
 * The main input is a {@link FundingManagementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FundingManagementDTO} or a {@link Page} of {@link FundingManagementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FundingManagementQueryService extends QueryService<FundingManagement> {

    private final Logger log = LoggerFactory.getLogger(FundingManagementQueryService.class);

    private final FundingManagementRepository fundingManagementRepository;

    private final FundingManagementMapper fundingManagementMapper;

    public FundingManagementQueryService(FundingManagementRepository fundingManagementRepository, FundingManagementMapper fundingManagementMapper) {
        this.fundingManagementRepository = fundingManagementRepository;
        this.fundingManagementMapper = fundingManagementMapper;
    }

    /**
     * Return a {@link List} of {@link FundingManagementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FundingManagementDTO> findByCriteria(FundingManagementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FundingManagement> specification = createSpecification(criteria);
        return fundingManagementMapper.toDto(fundingManagementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FundingManagementDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FundingManagementDTO> findByCriteria(FundingManagementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FundingManagement> specification = createSpecification(criteria);
        return fundingManagementRepository.findAll(specification, page)
            .map(fundingManagementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FundingManagementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FundingManagement> specification = createSpecification(criteria);
        return fundingManagementRepository.count(specification);
    }

    /**
     * Function to convert {@link FundingManagementCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<FundingManagement> createSpecification(FundingManagementCriteria criteria) {
        Specification<FundingManagement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), FundingManagement_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), FundingManagement_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), FundingManagement_.description));
            }
        }
        return specification;
    }
}

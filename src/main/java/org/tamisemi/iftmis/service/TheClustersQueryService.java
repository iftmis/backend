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

import org.tamisemi.iftmis.domain.TheClusters;
import org.tamisemi.iftmis.domain.*; // for static metamodels
import org.tamisemi.iftmis.repository.TheClustersRepository;
import org.tamisemi.iftmis.service.dto.TheClustersCriteria;
import org.tamisemi.iftmis.service.dto.TheClustersDTO;
import org.tamisemi.iftmis.service.mapper.TheClustersMapper;

/**
 * Service for executing complex queries for {@link TheClusters} entities in the database.
 * The main input is a {@link TheClustersCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TheClustersDTO} or a {@link Page} of {@link TheClustersDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TheClustersQueryService extends QueryService<TheClusters> {

    private final Logger log = LoggerFactory.getLogger(TheClustersQueryService.class);

    private final TheClustersRepository theClustersRepository;

    private final TheClustersMapper theClustersMapper;

    public TheClustersQueryService(TheClustersRepository theClustersRepository, TheClustersMapper theClustersMapper) {
        this.theClustersRepository = theClustersRepository;
        this.theClustersMapper = theClustersMapper;
    }

    /**
     * Return a {@link List} of {@link TheClustersDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TheClustersDTO> findByCriteria(TheClustersCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TheClusters> specification = createSpecification(criteria);
        return theClustersMapper.toDto(theClustersRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TheClustersDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TheClustersDTO> findByCriteria(TheClustersCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TheClusters> specification = createSpecification(criteria);
        return theClustersRepository.findAll(specification, page)
            .map(theClustersMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TheClustersCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TheClusters> specification = createSpecification(criteria);
        return theClustersRepository.count(specification);
    }

    /**
     * Function to convert {@link TheClustersCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TheClusters> createSpecification(TheClustersCriteria criteria) {
        Specification<TheClusters> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TheClusters_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), TheClusters_.name));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), TheClusters_.code));
            }
        }
        return specification;
    }
}

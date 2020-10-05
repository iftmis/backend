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

import org.tamisemi.iftmis.domain.InspectionTypes;
import org.tamisemi.iftmis.domain.*; // for static metamodels
import org.tamisemi.iftmis.repository.InspectionTypesRepository;
import org.tamisemi.iftmis.service.dto.InspectionTypesCriteria;
import org.tamisemi.iftmis.service.dto.InspectionTypesDTO;
import org.tamisemi.iftmis.service.mapper.InspectionTypesMapper;

/**
 * Service for executing complex queries for {@link InspectionTypes} entities in the database.
 * The main input is a {@link InspectionTypesCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InspectionTypesDTO} or a {@link Page} of {@link InspectionTypesDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InspectionTypesQueryService extends QueryService<InspectionTypes> {

    private final Logger log = LoggerFactory.getLogger(InspectionTypesQueryService.class);

    private final InspectionTypesRepository inspectionTypesRepository;

    private final InspectionTypesMapper inspectionTypesMapper;

    public InspectionTypesQueryService(InspectionTypesRepository inspectionTypesRepository, InspectionTypesMapper inspectionTypesMapper) {
        this.inspectionTypesRepository = inspectionTypesRepository;
        this.inspectionTypesMapper = inspectionTypesMapper;
    }

    /**
     * Return a {@link List} of {@link InspectionTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InspectionTypesDTO> findByCriteria(InspectionTypesCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<InspectionTypes> specification = createSpecification(criteria);
        return inspectionTypesMapper.toDto(inspectionTypesRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link InspectionTypesDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionTypesDTO> findByCriteria(InspectionTypesCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<InspectionTypes> specification = createSpecification(criteria);
        return inspectionTypesRepository.findAll(specification, page)
            .map(inspectionTypesMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InspectionTypesCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<InspectionTypes> specification = createSpecification(criteria);
        return inspectionTypesRepository.count(specification);
    }

    /**
     * Function to convert {@link InspectionTypesCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<InspectionTypes> createSpecification(InspectionTypesCriteria criteria) {
        Specification<InspectionTypes> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), InspectionTypes_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), InspectionTypes_.name));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), InspectionTypes_.description));
            }
        }
        return specification;
    }
}

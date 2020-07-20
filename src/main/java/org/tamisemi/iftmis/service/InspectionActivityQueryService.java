package org.tamisemi.iftmis.service;

import io.github.jhipster.service.QueryService;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tamisemi.iftmis.domain.*; // for static metamodels
import org.tamisemi.iftmis.domain.InspectionActivity;
import org.tamisemi.iftmis.repository.InspectionActivityRepository;
import org.tamisemi.iftmis.service.dto.InspectionActivityCriteria;
import org.tamisemi.iftmis.service.dto.InspectionActivityDTO;
import org.tamisemi.iftmis.service.mapper.InspectionActivityMapper;

/**
 * Service for executing complex queries for {@link InspectionActivity} entities in the database.
 * The main input is a {@link InspectionActivityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InspectionActivityDTO} or a {@link Page} of {@link InspectionActivityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InspectionActivityQueryService extends QueryService<InspectionActivity> {
    private final Logger log = LoggerFactory.getLogger(InspectionActivityQueryService.class);

    private final InspectionActivityRepository inspectionActivityRepository;

    private final InspectionActivityMapper inspectionActivityMapper;

    public InspectionActivityQueryService(
        InspectionActivityRepository inspectionActivityRepository,
        InspectionActivityMapper inspectionActivityMapper
    ) {
        this.inspectionActivityRepository = inspectionActivityRepository;
        this.inspectionActivityMapper = inspectionActivityMapper;
    }

    /**
     * Return a {@link List} of {@link InspectionActivityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InspectionActivityDTO> findByCriteria(InspectionActivityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<InspectionActivity> specification = createSpecification(criteria);
        return inspectionActivityMapper.toDto(inspectionActivityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link InspectionActivityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InspectionActivityDTO> findByCriteria(InspectionActivityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<InspectionActivity> specification = createSpecification(criteria);
        return inspectionActivityRepository.findAll(specification, page).map(inspectionActivityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InspectionActivityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<InspectionActivity> specification = createSpecification(criteria);
        return inspectionActivityRepository.count(specification);
    }

    /**
     * Function to convert {@link InspectionActivityCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<InspectionActivity> createSpecification(InspectionActivityCriteria criteria) {
        Specification<InspectionActivity> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), InspectionActivity_.id));
            }
            if (criteria.getDays() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDays(), InspectionActivity_.days));
            }
            if (criteria.getInspectionPlanId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getInspectionPlanId(),
                            root -> root.join(InspectionActivity_.inspectionPlan, JoinType.LEFT).get(InspectionPlan_.id)
                        )
                    );
            }
            if (criteria.getObjectiveId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getObjectiveId(),
                            root -> root.join(InspectionActivity_.objective, JoinType.LEFT).get(Objective_.id)
                        )
                    );
            }
            if (criteria.getAuditableAreaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getAuditableAreaId(),
                            root -> root.join(InspectionActivity_.auditableArea, JoinType.LEFT).get(AuditableArea_.id)
                        )
                    );
            }
            if (criteria.getSubAreaId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getSubAreaId(),
                            root -> root.join(InspectionActivity_.subArea, JoinType.LEFT).get(SubArea_.id)
                        )
                    );
            }
            if (criteria.getRiskId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getRiskId(), root -> root.join(InspectionActivity_.risks, JoinType.LEFT).get(Risk_.id))
                    );
            }
            if (criteria.getOrganisationUnitsId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getOrganisationUnitsId(),
                            root -> root.join(InspectionActivity_.organisationUnits, JoinType.LEFT).get(OrganisationUnit_.id)
                        )
                    );
            }
        }
        return specification;
    }
}

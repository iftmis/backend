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
import org.tamisemi.iftmis.domain.OrganisationUnit;
import org.tamisemi.iftmis.domain.OrganisationUnitLevel_;
import org.tamisemi.iftmis.domain.OrganisationUnit_;
import org.tamisemi.iftmis.repository.OrganisationUnitRepository;
import org.tamisemi.iftmis.service.dto.OrganisationUnitCriteria;
import org.tamisemi.iftmis.service.dto.OrganisationUnitDTO;
import org.tamisemi.iftmis.service.mapper.OrganisationUnitMapper;

/**
 * Service for executing complex queries for {@link OrganisationUnit} entities in the database.
 * The main input is a {@link OrganisationUnitCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link OrganisationUnitDTO} or a {@link Page} of {@link OrganisationUnitDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class OrganisationUnitQueryService extends QueryService<OrganisationUnit> {
    private final Logger log = LoggerFactory.getLogger(OrganisationUnitQueryService.class);

    private final OrganisationUnitRepository organisationUnitRepository;

    private final OrganisationUnitMapper organisationUnitMapper;

    public OrganisationUnitQueryService(
        OrganisationUnitRepository organisationUnitRepository,
        OrganisationUnitMapper organisationUnitMapper
    ) {
        this.organisationUnitRepository = organisationUnitRepository;
        this.organisationUnitMapper = organisationUnitMapper;
    }

    /**
     * Return a {@link List} of {@link OrganisationUnitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<OrganisationUnitDTO> findByCriteria(OrganisationUnitCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<OrganisationUnit> specification = createSpecification(criteria);
        return organisationUnitMapper.toDto(organisationUnitRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link OrganisationUnitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<OrganisationUnitDTO> findByCriteria(OrganisationUnitCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<OrganisationUnit> specification = createSpecification(criteria);
        return organisationUnitRepository.findAll(specification, page).map(organisationUnitMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(OrganisationUnitCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<OrganisationUnit> specification = createSpecification(criteria);
        return organisationUnitRepository.count(specification);
    }

    /**
     * Function to convert {@link OrganisationUnitCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<OrganisationUnit> createSpecification(OrganisationUnitCriteria criteria) {
        Specification<OrganisationUnit> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), OrganisationUnit_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), OrganisationUnit_.code));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), OrganisationUnit_.name));
            }
            if (criteria.getOrganisationUnitLevelId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getOrganisationUnitLevelId(),
                            root -> root.join(OrganisationUnit_.organisationUnitLevel, JoinType.LEFT).get(OrganisationUnitLevel_.id)
                        )
                    );
            }
            if (criteria.getParentId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getParentId(),
                            root -> root.join(OrganisationUnit_.parent, JoinType.LEFT).get(OrganisationUnit_.id)
                        )
                    );
            }
        }
        return specification;
    }
}

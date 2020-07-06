package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.tamisemi.iftmis.domain.enumeration.ActionPlanCategory;
import org.tamisemi.iftmis.domain.enumeration.FindingSource;

/**
 * The Finding(findings) entity.\n@author Chris
 */
@ApiModel(description = "The Finding(findings) entity.\n@author Chris")
@Entity
@Table(name = "findings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Finding extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "source", nullable = false)
    private FindingSource source;

    @Column(name = "code")
    private String code;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "action_plan_category", nullable = false)
    private ActionPlanCategory actionPlanCategory;

    @Column(name = "is_closed")
    private Boolean isClosed;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "findings", allowSetters = true)
    private OrganisationUnit organisationUnit;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FindingSource getSource() {
        return source;
    }

    public Finding source(FindingSource source) {
        this.source = source;
        return this;
    }

    public void setSource(FindingSource source) {
        this.source = source;
    }

    public String getCode() {
        return code;
    }

    public Finding code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public Finding description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActionPlanCategory getActionPlanCategory() {
        return actionPlanCategory;
    }

    public Finding actionPlanCategory(ActionPlanCategory actionPlanCategory) {
        this.actionPlanCategory = actionPlanCategory;
        return this;
    }

    public void setActionPlanCategory(ActionPlanCategory actionPlanCategory) {
        this.actionPlanCategory = actionPlanCategory;
    }

    public Boolean isIsClosed() {
        return isClosed;
    }

    public Finding isClosed(Boolean isClosed) {
        this.isClosed = isClosed;
        return this;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public OrganisationUnit getOrganisationUnit() {
        return organisationUnit;
    }

    public Finding organisationUnit(OrganisationUnit organisationUnit) {
        this.organisationUnit = organisationUnit;
        return this;
    }

    public void setOrganisationUnit(OrganisationUnit organisationUnit) {
        this.organisationUnit = organisationUnit;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Finding)) {
            return false;
        }
        return id != null && id.equals(((Finding) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Finding{" +
            "id=" + getId() +
            ", source='" + getSource() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", actionPlanCategory='" + getActionPlanCategory() + "'" +
            ", isClosed='" + isIsClosed() + "'" +
            "}";
    }
}

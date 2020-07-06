package org.tamisemi.iftmis.domain;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The OrganisationUnitLevel(organisation_unit_levels) entity.\n@author Chris
 */
@ApiModel(description = "The OrganisationUnitLevel(organisation_unit_levels) entity.\n@author Chris")
@Entity
@Table(name = "organisation_unit_levels")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OrganisationUnitLevel extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(min = 1, max = 64)
    @Column(name = "code", length = 64, unique = true)
    private String code;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "name", length = 200, nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "level", nullable = false)
    private Integer level;

    @NotNull
    @Column(name = "is_inspection_level", nullable = false)
    private Boolean isInspectionLevel;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public OrganisationUnitLevel code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public OrganisationUnitLevel name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public OrganisationUnitLevel level(Integer level) {
        this.level = level;
        return this;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean isIsInspectionLevel() {
        return isInspectionLevel;
    }

    public OrganisationUnitLevel isInspectionLevel(Boolean isInspectionLevel) {
        this.isInspectionLevel = isInspectionLevel;
        return this;
    }

    public void setIsInspectionLevel(Boolean isInspectionLevel) {
        this.isInspectionLevel = isInspectionLevel;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganisationUnitLevel)) {
            return false;
        }
        return id != null && id.equals(((OrganisationUnitLevel) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganisationUnitLevel{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", level=" + getLevel() +
            ", isInspectionLevel='" + isIsInspectionLevel() + "'" +
            "}";
    }
}

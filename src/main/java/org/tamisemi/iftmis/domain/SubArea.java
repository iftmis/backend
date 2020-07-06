package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The InspectionProgram(inspection_programs) entity.\n@author Chris
 */
@ApiModel(description = "The InspectionProgram(inspection_programs) entity.\n@author Chris")
@Entity
@Table(name = "sub_areas")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SubArea extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "name", length = 200, nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "subAreas", allowSetters = true)
    private AuditableArea area;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SubArea name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AuditableArea getArea() {
        return area;
    }

    public SubArea area(AuditableArea auditableArea) {
        this.area = auditableArea;
        return this;
    }

    public void setArea(AuditableArea auditableArea) {
        this.area = auditableArea;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubArea)) {
            return false;
        }
        return id != null && id.equals(((SubArea) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SubArea{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}

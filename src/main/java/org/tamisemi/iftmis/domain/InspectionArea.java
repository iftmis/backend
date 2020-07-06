package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The InspectionAuditableArea(inspection_auditable_areas) entity.\n@author Chris
 */
@Entity
@Table(name = "inspection_areas")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionArea extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 2000)
    @Column(name = "name", length = 2000, nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionAreas", allowSetters = true)
    private Inspection inspection;

    @ManyToOne
    @JsonIgnoreProperties(value = "inspectionAreas", allowSetters = true)
    private AuditableArea auditableArea;

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

    public InspectionArea name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public InspectionArea inspection(Inspection inspection) {
        this.inspection = inspection;
        return this;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }

    public AuditableArea getAuditableArea() {
        return auditableArea;
    }

    public InspectionArea auditableArea(AuditableArea auditableArea) {
        this.auditableArea = auditableArea;
        return this;
    }

    public void setAuditableArea(AuditableArea auditableArea) {
        this.auditableArea = auditableArea;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionArea)) {
            return false;
        }
        return id != null && id.equals(((InspectionArea) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionArea{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}

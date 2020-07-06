package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The InspectionGeneralIndicator entity.\n@author Chris
 */
@Entity
@Table(name = "inspection_objectives")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionObjective extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 1000)
    @Column(name = "name", length = 1000, nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionObjectives", allowSetters = true)
    private InspectionArea inspectionArea;

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

    public InspectionObjective name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InspectionArea getInspectionArea() {
        return inspectionArea;
    }

    public InspectionObjective inspectionArea(InspectionArea inspectionArea) {
        this.inspectionArea = inspectionArea;
        return this;
    }

    public void setInspectionArea(InspectionArea inspectionArea) {
        this.inspectionArea = inspectionArea;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionObjective)) {
            return false;
        }
        return id != null && id.equals(((InspectionObjective) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionObjective{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}

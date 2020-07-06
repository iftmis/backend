package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The InspectionSubArea(inspection_sub_areas) entity.\n@author Chris
 */
@Entity
@Table(name = "inspection_sub_areas")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionSubArea extends AbstractAuditingEntity implements Serializable {
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
    @JsonIgnoreProperties(value = "inspectionSubAreas", allowSetters = true)
    private InspectionObjective inspectionObjective;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionSubAreas", allowSetters = true)
    private SubArea subArea;

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

    public InspectionSubArea name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InspectionObjective getInspectionObjective() {
        return inspectionObjective;
    }

    public InspectionSubArea inspectionObjective(InspectionObjective inspectionObjective) {
        this.inspectionObjective = inspectionObjective;
        return this;
    }

    public void setInspectionObjective(InspectionObjective inspectionObjective) {
        this.inspectionObjective = inspectionObjective;
    }

    public SubArea getSubArea() {
        return subArea;
    }

    public InspectionSubArea subArea(SubArea subArea) {
        this.subArea = subArea;
        return this;
    }

    public void setSubArea(SubArea subArea) {
        this.subArea = subArea;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionSubArea)) {
            return false;
        }
        return id != null && id.equals(((InspectionSubArea) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionSubArea{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}

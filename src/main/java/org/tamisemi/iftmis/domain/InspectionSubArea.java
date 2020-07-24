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

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionSubAreas", allowSetters = true)
    private InspectionArea inspectionArea;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionSubAreas", allowSetters = true)
    private SubArea subArea;

    @Column(name = "general_objective")
    private String generalObjective;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public InspectionArea getInspectionArea() {
        return inspectionArea;
    }

    public void setInspectionArea(InspectionArea inspectionArea) {
        this.inspectionArea = inspectionArea;
    }

    public String getGeneralObjective() {
        return generalObjective;
    }

    public void setGeneralObjective(String generalObjective) {
        this.generalObjective = generalObjective;
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
            "}";
    }
}

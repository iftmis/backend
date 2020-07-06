package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The InspectionProcedure entity.\n@author Chris
 */
@ApiModel(description = "The InspectionProcedure entity.\n@author Chris")
@Entity
@Table(name = "inspection_procedures")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionProcedure extends AbstractAuditingEntity implements Serializable {
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
    @JsonIgnoreProperties(value = "inspectionProcedures", allowSetters = true)
    private InspectionIndicator inspectionIndicator;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionProcedures", allowSetters = true)
    private Procedure procedure;

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

    public InspectionProcedure name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InspectionIndicator getInspectionIndicator() {
        return inspectionIndicator;
    }

    public InspectionProcedure inspectionIndicator(InspectionIndicator inspectionIndicator) {
        this.inspectionIndicator = inspectionIndicator;
        return this;
    }

    public void setInspectionIndicator(InspectionIndicator inspectionIndicator) {
        this.inspectionIndicator = inspectionIndicator;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public InspectionProcedure procedure(Procedure procedure) {
        this.procedure = procedure;
        return this;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionProcedure)) {
            return false;
        }
        return id != null && id.equals(((InspectionProcedure) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionProcedure{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}

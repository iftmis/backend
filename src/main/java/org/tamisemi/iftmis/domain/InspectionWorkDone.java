package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The InspectionWorkDone(inspection_work_dones) entity.\n@author Chris
 */
@ApiModel(description = "The InspectionWorkDone(inspection_work_dones) entity.\n@author Chris")
@Entity
@Table(name = "inspection_work_dones")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionWorkDone extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_ok")
    private Boolean isOk;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionWorkDones", allowSetters = true)
    private InspectionProcedure procedure;

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

    public InspectionWorkDone name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isIsOk() {
        return isOk;
    }

    public InspectionWorkDone isOk(Boolean isOk) {
        this.isOk = isOk;
        return this;
    }

    public void setIsOk(Boolean isOk) {
        this.isOk = isOk;
    }

    public InspectionProcedure getProcedure() {
        return procedure;
    }

    public InspectionWorkDone procedure(InspectionProcedure inspectionProcedure) {
        this.procedure = inspectionProcedure;
        return this;
    }

    public void setProcedure(InspectionProcedure inspectionProcedure) {
        this.procedure = inspectionProcedure;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionWorkDone)) {
            return false;
        }
        return id != null && id.equals(((InspectionWorkDone) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionWorkDone{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", isOk='" + isIsOk() + "'" +
            "}";
    }
}

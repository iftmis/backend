package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The InspectionIndicator(inspection_indicators) entity.\n@author Chris
 */
@Entity
@Table(name = "inspection_indicators")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionIndicator extends AbstractAuditingEntity implements Serializable {
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
    @JsonIgnoreProperties(value = "inspectionIndicators", allowSetters = true)
    private InspectionSubArea inspectionSubArea;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionIndicators", allowSetters = true)
    private Indicator indicator;

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

    public InspectionIndicator name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InspectionSubArea getInspectionSubArea() {
        return inspectionSubArea;
    }

    public InspectionIndicator inspectionSubArea(InspectionSubArea inspectionSubArea) {
        this.inspectionSubArea = inspectionSubArea;
        return this;
    }

    public void setInspectionSubArea(InspectionSubArea inspectionSubArea) {
        this.inspectionSubArea = inspectionSubArea;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public InspectionIndicator indicator(Indicator indicator) {
        this.indicator = indicator;
        return this;
    }

    public void setIndicator(Indicator indicator) {
        this.indicator = indicator;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionIndicator)) {
            return false;
        }
        return id != null && id.equals(((InspectionIndicator) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionIndicator{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}

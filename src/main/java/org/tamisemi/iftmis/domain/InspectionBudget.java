package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InspectionBudget.
 */
@Entity
@Table(name = "inspection_budget")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionBudget extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "quantity", nullable = false)
    private Float quantity;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "frequency", nullable = false)
    private Float frequency;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "unit_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionBudgets", allowSetters = true)
    private GfsCode gfsCode;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionBudgets", allowSetters = true)
    private Inspection inspection;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getQuantity() {
        return quantity;
    }

    public InspectionBudget quantity(Float quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getFrequency() {
        return frequency;
    }

    public InspectionBudget frequency(Float frequency) {
        this.frequency = frequency;
        return this;
    }

    public void setFrequency(Float frequency) {
        this.frequency = frequency;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public InspectionBudget unitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public GfsCode getGfsCode() {
        return gfsCode;
    }

    public InspectionBudget gfsCode(GfsCode gfsCode) {
        this.gfsCode = gfsCode;
        return this;
    }

    public void setGfsCode(GfsCode gfsCode) {
        this.gfsCode = gfsCode;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public InspectionBudget inspection(Inspection inspection) {
        this.inspection = inspection;
        return this;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionBudget)) {
            return false;
        }
        return id != null && id.equals(((InspectionBudget) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionBudget{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", frequency=" + getFrequency() +
            ", unitPrice=" + getUnitPrice() +
            "}";
    }
}

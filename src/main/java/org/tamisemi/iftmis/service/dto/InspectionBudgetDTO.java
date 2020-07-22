package org.tamisemi.iftmis.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionBudget} entity.
 */
public class InspectionBudgetDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    @DecimalMin(value = "0")
    private Float quantity;

    @NotNull
    @DecimalMin(value = "0")
    private Float frequency;

    @NotNull
    @DecimalMin(value = "0")
    private BigDecimal unitPrice;

    private Long gfsCodeId;

    private String gfsCodeDescription;

    private Long inspectionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public Float getFrequency() {
        return frequency;
    }

    public void setFrequency(Float frequency) {
        this.frequency = frequency;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Long getGfsCodeId() {
        return gfsCodeId;
    }

    public void setGfsCodeId(Long gfsCodeId) {
        this.gfsCodeId = gfsCodeId;
    }

    public String getGfsCodeDescription() {
        return gfsCodeDescription;
    }

    public void setGfsCodeDescription(String gfsCodeDescription) {
        this.gfsCodeDescription = gfsCodeDescription;
    }

    public Long getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspectionId = inspectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionBudgetDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionBudgetDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionBudgetDTO{" +
            "id=" + getId() +
            ", quantity=" + getQuantity() +
            ", frequency=" + getFrequency() +
            ", unitPrice=" + getUnitPrice() +
            ", gfsCodeId=" + getGfsCodeId() +
            ", gfsCodeDescription='" + getGfsCodeDescription() + "'" +
            ", inspectionId=" + getInspectionId() +
            "}";
    }
}

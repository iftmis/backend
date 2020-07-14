package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The RiskRegister(risk_registers) entity.\n@author Chris
 */
@Entity
@Table(name = "risk_registers")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RiskRegister extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "name", length = 200, nullable = false, unique = true)
    private String name;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "approved_date")
    private LocalDate approvedDate;

    @Size(max = 200)
    @Column(name = "approved_by", length = 200)
    private String approvedBy;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "riskRegisters", allowSetters = true)
    private OrganisationUnit organisationUnit;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "riskRegisters", allowSetters = true)
    private FinancialYear financialYear;

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

    public RiskRegister name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isIsApproved() {
        return isApproved;
    }

    public RiskRegister isApproved(Boolean isApproved) {
        this.isApproved = isApproved;
        return this;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public LocalDate getApprovedDate() {
        return approvedDate;
    }

    public RiskRegister approvedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
        return this;
    }

    public void setApprovedDate(LocalDate approvedDate) {
        this.approvedDate = approvedDate;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public RiskRegister approvedBy(String approvedBy) {
        this.approvedBy = approvedBy;
        return this;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public OrganisationUnit getOrganisationUnit() {
        return organisationUnit;
    }

    public RiskRegister organisationUnit(OrganisationUnit organisationUnit) {
        this.organisationUnit = organisationUnit;
        return this;
    }

    public void setOrganisationUnit(OrganisationUnit organisationUnit) {
        this.organisationUnit = organisationUnit;
    }

    public FinancialYear getFinancialYear() {
        return financialYear;
    }

    public RiskRegister financialYear(FinancialYear financialYear) {
        this.financialYear = financialYear;
        return this;
    }

    public void setFinancialYear(FinancialYear financialYear) {
        this.financialYear = financialYear;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RiskRegister)) {
            return false;
        }
        return id != null && id.equals(((RiskRegister) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RiskRegister{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", isApproved='" + isIsApproved() + "'" +
            ", approvedDate='" + getApprovedDate() + "'" +
            ", approvedBy='" + getApprovedBy() + "'" +
            "}";
    }
}

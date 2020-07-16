package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * The Risk(risks) entity.\n@author Chris
 */
@Entity
@Table(name = "risks")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Risk extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(min = 1, max = 64)
    @Column(name = "code", length = 64, unique = true)
    private String code;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description")
    private String description;

    /**
     * Risk{riskOwner(name) required} to OrganisationUnit
     */
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "risks", allowSetters = true)
    private RiskRegister riskRegister;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "risks", allowSetters = true)
    private Objective objective;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "risks", allowSetters = true)
    private RiskCategory riskCategory;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "risks", allowSetters = true)
    private OrganisationUnit riskOwner;

    @OneToMany(mappedBy = "risk")
    @JsonIgnoreProperties("risk")
    private Set<RiskRating> riskRatings = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Risk code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public Risk description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RiskRegister getRiskRegister() {
        return riskRegister;
    }

    public Risk riskRegister(RiskRegister riskRegister) {
        this.riskRegister = riskRegister;
        return this;
    }

    public void setRiskRegister(RiskRegister riskRegister) {
        this.riskRegister = riskRegister;
    }

    public Objective getObjective() {
        return objective;
    }

    public Risk objective(Objective objective) {
        this.objective = objective;
        return this;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public RiskCategory getRiskCategory() {
        return riskCategory;
    }

    public Risk riskCategory(RiskCategory riskCategory) {
        this.riskCategory = riskCategory;
        return this;
    }

    public void setRiskCategory(RiskCategory riskCategory) {
        this.riskCategory = riskCategory;
    }

    public OrganisationUnit getRiskOwner() {
        return riskOwner;
    }

    public Risk riskOwner(OrganisationUnit organsiationUnit) {
        this.riskOwner = organsiationUnit;
        return this;
    }

    public void setRiskOwner(OrganisationUnit organsiationUnit) {
        this.riskOwner = organsiationUnit;
    }

    public Set<RiskRating> getRiskRatings() {
        return riskRatings;
    }

    public void setRiskRatings(Set<RiskRating> riskRatings) {
        this.riskRatings = riskRatings;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Risk)) {
            return false;
        }
        return id != null && id.equals(((Risk) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Risk{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

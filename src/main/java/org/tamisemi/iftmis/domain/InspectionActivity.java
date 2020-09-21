package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InspectionActivity.
 */
@Entity
@Table(name = "inspection_activities")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionActivity extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "days", nullable = false)
    private Integer days;

    @Column(name = "quarter_one", nullable = true)
    private String quarter_one;

    @Column(name = "quarter_two", nullable = true)
    private String quarter_two;

    @Column(name = "quarter_three", nullable = true)
    private String quarter_three;

    @Column(name = "quarter_four", nullable = true)
    private String quarter_four;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getQuarter_two() {
        return quarter_two;
    }

    public void setQuarter_two(String quarter_two) {
        this.quarter_two = quarter_two;
    }

    public String getQuarter_three() {
        return quarter_three;
    }

    public void setQuarter_three(String quarter_three) {
        this.quarter_three = quarter_three;
    }

    public String getQuarter_four() {
        return quarter_four;
    }

    public void setQuarter_four(String quarter_four) {
        this.quarter_four = quarter_four;
    }

    public String getQuarter_one() {
        return quarter_one;
    }

    public void setQuarter_one(String quarter_one) {
        this.quarter_one = quarter_one;
    }

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionActivities", allowSetters = true)
    private InspectionPlan inspectionPlan;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionActivities", allowSetters = true)
    private Objective objective;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionActivities", allowSetters = true)
    private AuditableArea auditableArea;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionActivities", allowSetters = true)
    private SubArea subArea;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH })
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "inspection_activities_risks",
        joinColumns = @JoinColumn(name = "inspection_activity_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "risk_id", referencedColumnName = "id")
    )
    private Set<Risk> risks = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH })
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinTable(
        name = "inspection_activities_organisation_units",
        joinColumns = @JoinColumn(name = "inspection_activity_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "organisation_units_id", referencedColumnName = "id")
    )
    private Set<OrganisationUnit> organisationUnits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDays() {
        return days;
    }

    public InspectionActivity days(Integer days) {
        this.days = days;
        return this;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public InspectionPlan getInspectionPlan() {
        return inspectionPlan;
    }

    public InspectionActivity inspectionPlan(InspectionPlan inspectionPlan) {
        this.inspectionPlan = inspectionPlan;
        return this;
    }

    public void setInspectionPlan(InspectionPlan inspectionPlan) {
        this.inspectionPlan = inspectionPlan;
    }

    public Objective getObjective() {
        return objective;
    }

    public InspectionActivity objective(Objective objective) {
        this.objective = objective;
        return this;
    }

    public void setObjective(Objective objective) {
        this.objective = objective;
    }

    public AuditableArea getAuditableArea() {
        return auditableArea;
    }

    public InspectionActivity auditableArea(AuditableArea auditableArea) {
        this.auditableArea = auditableArea;
        return this;
    }

    public void setAuditableArea(AuditableArea auditableArea) {
        this.auditableArea = auditableArea;
    }

    public SubArea getSubArea() {
        return subArea;
    }

    public InspectionActivity subArea(SubArea subArea) {
        this.subArea = subArea;
        return this;
    }

    public void setSubArea(SubArea subArea) {
        this.subArea = subArea;
    }

    public Set<Risk> getRisks() {
        return risks;
    }

    public InspectionActivity risks(Set<Risk> risks) {
        this.risks = risks;
        return this;
    }

    public InspectionActivity addRisk(Risk risk) {
        this.risks.add(risk);
        return this;
    }

    public InspectionActivity removeRisk(Risk risk) {
        this.risks.remove(risk);
        return this;
    }

    public void setRisks(Set<Risk> risks) {
        this.risks = risks;
    }

    public Set<OrganisationUnit> getOrganisationUnits() {
        return organisationUnits;
    }

    public InspectionActivity organisationUnits(Set<OrganisationUnit> organisationUnits) {
        this.organisationUnits = organisationUnits;
        return this;
    }

    public InspectionActivity addOrganisationUnits(OrganisationUnit organisationUnit) {
        this.organisationUnits.add(organisationUnit);
        return this;
    }

    public InspectionActivity removeOrganisationUnits(OrganisationUnit organisationUnit) {
        this.organisationUnits.remove(organisationUnit);
        return this;
    }

    public void setOrganisationUnits(Set<OrganisationUnit> organisationUnits) {
        this.organisationUnits = organisationUnits;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionActivity)) {
            return false;
        }
        return id != null && id.equals(((InspectionActivity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionActivity{" +
            "id=" + getId() +
            ", days=" + getDays() +
            ", quarter_one='" + getQuarter_one() + '\'' +
            ", quarter_two='" + getQuarter_two() + '\'' +
            ", quarter_three='" + getQuarter_three() + '\'' +
            ", quarter_four='" + getQuarter_four() + '\'' +
            "}";
    }
}

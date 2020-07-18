package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A InspectionActivityQuarter.
 */
@Entity
@Table(name = "inspection_activity_quarters")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class InspectionActivityQuarter extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "days")
    private Integer days;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionActivityQuarters", allowSetters = true)
    private InspectionActivity activity;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "inspectionActivityQuarters", allowSetters = true)
    private Quarter quarter;

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

    public InspectionActivityQuarter days(Integer days) {
        this.days = days;
        return this;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public InspectionActivity getActivity() {
        return activity;
    }

    public InspectionActivityQuarter activity(InspectionActivity inspectionActivity) {
        this.activity = inspectionActivity;
        return this;
    }

    public void setActivity(InspectionActivity inspectionActivity) {
        this.activity = inspectionActivity;
    }

    public Quarter getQuarter() {
        return quarter;
    }

    public InspectionActivityQuarter quarter(Quarter quarter) {
        this.quarter = quarter;
        return this;
    }

    public void setQuarter(Quarter quarter) {
        this.quarter = quarter;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionActivityQuarter)) {
            return false;
        }
        return id != null && id.equals(((InspectionActivityQuarter) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionActivityQuarter{" +
            "id=" + getId() +
            ", days=" + getDays() +
            "}";
    }
}

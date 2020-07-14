package org.tamisemi.iftmis.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The RiskRank(risk_ranks) entity.\n@author Chris
 */
@Entity
@Table(name = "risk_ranks")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RiskRank extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 200)
    @Column(name = "name", length = 200, nullable = false, unique = true)
    private String name;

    @NotNull
    @Min(value = 0)
    @Column(name = "min_value", nullable = false)
    private Integer minValue;

    @NotNull
    @Max(value = 30)
    @Column(name = "max_value", nullable = false)
    private Integer maxValue;

    @Size(min = 4, max = 7)
    @Column(name = "hex_color", length = 7)
    private String hexColor;

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

    public RiskRank name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinValue() {
        return minValue;
    }

    public RiskRank minValue(Integer minValue) {
        this.minValue = minValue;
        return this;
    }

    public void setMinValue(Integer minValue) {
        this.minValue = minValue;
    }

    public Integer getMaxValue() {
        return maxValue;
    }

    public RiskRank maxValue(Integer maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    public void setMaxValue(Integer maxValue) {
        this.maxValue = maxValue;
    }

    public String getHexColor() {
        return hexColor;
    }

    public RiskRank hexColor(String hexColor) {
        this.hexColor = hexColor;
        return this;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RiskRank)) {
            return false;
        }
        return id != null && id.equals(((RiskRank) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RiskRank{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", minValue=" + getMinValue() +
            ", maxValue=" + getMaxValue() +
            ", hexColor='" + getHexColor() + "'" +
            "}";
    }
}

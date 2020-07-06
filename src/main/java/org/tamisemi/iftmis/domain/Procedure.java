package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The Procedure(procedures) entity.\n@author Chris
 */
@ApiModel(description = "The Procedure(procedures) entity.\n@author Chris")
@Entity
@Table(name = "procedures")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Procedure extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 2000)
    @Column(name = "name", length = 2000, nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "procedures", allowSetters = true)
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

    public Procedure name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Indicator getIndicator() {
        return indicator;
    }

    public Procedure indicator(Indicator indicator) {
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
        if (!(o instanceof Procedure)) {
            return false;
        }
        return id != null && id.equals(((Procedure) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Procedure{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}

package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * The Indicator(indicators) entity.\n@author Chris
 */
@Entity
@Table(name = "indicators")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Indicator extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(min = 2, max = 1000)
    @Column(name = "name", length = 1000, nullable = false, unique = true)
    private String name;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "indicators", allowSetters = true)
    private SubArea subArea;

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

    public Indicator name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubArea getSubArea() {
        return subArea;
    }

    public Indicator subArea(SubArea subArea) {
        this.subArea = subArea;
        return this;
    }

    public void setSubArea(SubArea subArea) {
        this.subArea = subArea;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Indicator)) {
            return false;
        }
        return id != null && id.equals(((Indicator) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Indicator{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}

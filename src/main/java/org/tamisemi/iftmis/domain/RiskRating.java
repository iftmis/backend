package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.tamisemi.iftmis.domain.enumeration.RatingSource;

/**
 * The RiskRating(risk_ratings) entity.\n@author Chris
 */
@Entity
@Table(name = "risk_ratings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RiskRating extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "source")
    private RatingSource source;

    @NotNull
    @Min(value = 0)
    @Column(name = "impact", nullable = false)
    private Integer impact;

    @NotNull
    @Min(value = 0)
    @Column(name = "likelihood", nullable = false)
    private Integer likelihood;

    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "comments")
    private String comments;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "riskRatings", allowSetters = true)
    private Risk risk;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RatingSource getSource() {
        return source;
    }

    public RiskRating source(RatingSource source) {
        this.source = source;
        return this;
    }

    public void setSource(RatingSource source) {
        this.source = source;
    }

    public Integer getImpact() {
        return impact;
    }

    public RiskRating impact(Integer impact) {
        this.impact = impact;
        return this;
    }

    public void setImpact(Integer impact) {
        this.impact = impact;
    }

    public Integer getLikelihood() {
        return likelihood;
    }

    public RiskRating likelihood(Integer likelihood) {
        this.likelihood = likelihood;
        return this;
    }

    public void setLikelihood(Integer likelihood) {
        this.likelihood = likelihood;
    }

    public String getComments() {
        return comments;
    }

    public RiskRating comments(String comments) {
        this.comments = comments;
        return this;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Risk getRisk() {
        return risk;
    }

    public RiskRating risk(Risk risk) {
        this.risk = risk;
        return this;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RiskRating)) {
            return false;
        }
        return id != null && id.equals(((RiskRating) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RiskRating{" +
            "id=" + getId() +
            ", source='" + getSource() + "'" +
            ", impact=" + getImpact() +
            ", likelihood=" + getLikelihood() +
            ", comments='" + getComments() + "'" +
            "}";
    }
}

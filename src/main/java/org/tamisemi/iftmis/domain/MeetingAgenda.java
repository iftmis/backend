package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

/**
 * The MeetingAgenda entity.\n@author Chris
 */
@ApiModel(description = "The MeetingAgenda entity.\n@author Chris")
@Entity
@Table(name = "meeting_agendas")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class MeetingAgenda extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "meetingAgenda", allowSetters = true)
    private Meeting meeting;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public MeetingAgenda description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public MeetingAgenda meeting(Meeting meeting) {
        this.meeting = meeting;
        return this;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeetingAgenda)) {
            return false;
        }
        return id != null && id.equals(((MeetingAgenda) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MeetingAgenda{" +
            "id=" + getId() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}

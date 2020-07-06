package org.tamisemi.iftmis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.tamisemi.iftmis.domain.enumeration.MeetingType;

/**
 * The TeamMeeting entity.\n@author Chris
 */
@ApiModel(description = "The TeamMeeting entity.\n@author Chris")
@Entity
@Table(name = "team_meetings")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Meeting extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private MeetingType type;

    @NotNull
    @Column(name = "meeting_date", nullable = false)
    private LocalDate meetingDate;

    @NotNull
    @Size(max = 200)
    @Column(name = "venue", length = 200, nullable = false)
    private String venue;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "summary")
    private String summary;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = "meetings", allowSetters = true)
    private Inspection inspection;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeetingType getType() {
        return type;
    }

    public Meeting type(MeetingType type) {
        this.type = type;
        return this;
    }

    public void setType(MeetingType type) {
        this.type = type;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public Meeting meetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
        return this;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getVenue() {
        return venue;
    }

    public Meeting venue(String venue) {
        this.venue = venue;
        return this;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getSummary() {
        return summary;
    }

    public Meeting summary(String summary) {
        this.summary = summary;
        return this;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Inspection getInspection() {
        return inspection;
    }

    public Meeting inspection(Inspection inspection) {
        this.inspection = inspection;
        return this;
    }

    public void setInspection(Inspection inspection) {
        this.inspection = inspection;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Meeting)) {
            return false;
        }
        return id != null && id.equals(((Meeting) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Meeting{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", meetingDate='" + getMeetingDate() + "'" +
            ", venue='" + getVenue() + "'" +
            ", summary='" + getSummary() + "'" +
            "}";
    }
}

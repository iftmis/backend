package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.tamisemi.iftmis.domain.enumeration.MeetingType;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.Meeting} entity.
 */
@ApiModel(description = "The TeamMeeting entity.\n@author Chris")
public class MeetingDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    private MeetingType type;

    @NotNull
    private LocalDate meetingDate;

    @NotNull
    @Size(max = 200)
    private String venue;

    private String summary;

    private Long inspectionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MeetingType getType() {
        return type;
    }

    public void setType(MeetingType type) {
        this.type = type;
    }

    public LocalDate getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(LocalDate meetingDate) {
        this.meetingDate = meetingDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(Long inspectionId) {
        this.inspectionId = inspectionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MeetingDTO)) {
            return false;
        }

        return id != null && id.equals(((MeetingDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "MeetingDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", meetingDate='" + getMeetingDate() + "'" +
            ", venue='" + getVenue() + "'" +
            ", summary='" + getSummary() + "'" +
            ", inspectionId=" + getInspectionId() +
            "}";
    }
}

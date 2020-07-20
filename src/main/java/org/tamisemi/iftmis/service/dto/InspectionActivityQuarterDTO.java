package org.tamisemi.iftmis.service.dto;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionActivityQuarter} entity.
 */
public class InspectionActivityQuarterDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    private Integer days;

    private Long activityId;

    private Long quarterId;

    private String quarterName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long inspectionActivityId) {
        this.activityId = inspectionActivityId;
    }

    public Long getQuarterId() {
        return quarterId;
    }

    public void setQuarterId(Long quarterId) {
        this.quarterId = quarterId;
    }

    public String getQuarterName() {
        return quarterName;
    }

    public void setQuarterName(String quarterName) {
        this.quarterName = quarterName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InspectionActivityQuarterDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionActivityQuarterDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionActivityQuarterDTO{" +
            "id=" + getId() +
            ", days=" + getDays() +
            ", activityId=" + getActivityId() +
            ", quarterId=" + getQuarterId() +
            ", quarterName='" + getQuarterName() + "'" +
            "}";
    }
}

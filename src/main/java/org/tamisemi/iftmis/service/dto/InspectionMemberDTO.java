package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.*;
import org.tamisemi.iftmis.domain.enumeration.InspectionRole;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.InspectionMember} entity.
 */
@ApiModel(description = "The InspectionMember entity.\n@author Chris")
public class InspectionMemberDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @NotNull
    private InspectionRole role;

    private Long userId;

    private String userFullName;

    private Long letterAttachmentId;

    private String letterAttachmentPath;

    private Long declarationAttachmentId;

    private String declarationAttachmentPath;

    private Long inspectionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public InspectionRole getRole() {
        return role;
    }

    public void setRole(InspectionRole role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public Long getLetterAttachmentId() {
        return letterAttachmentId;
    }

    public void setLetterAttachmentId(Long fileResourceId) {
        this.letterAttachmentId = fileResourceId;
    }

    public String getLetterAttachmentPath() {
        return letterAttachmentPath;
    }

    public void setLetterAttachmentPath(String fileResourcePath) {
        this.letterAttachmentPath = fileResourcePath;
    }

    public Long getDeclarationAttachmentId() {
        return declarationAttachmentId;
    }

    public void setDeclarationAttachmentId(Long fileResourceId) {
        this.declarationAttachmentId = fileResourceId;
    }

    public String getDeclarationAttachmentPath() {
        return declarationAttachmentPath;
    }

    public void setDeclarationAttachmentPath(String fileResourcePath) {
        this.declarationAttachmentPath = fileResourcePath;
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
        if (!(o instanceof InspectionMemberDTO)) {
            return false;
        }

        return id != null && id.equals(((InspectionMemberDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InspectionMemberDTO{" +
            "id=" + getId() +
            ", role='" + getRole() + "'" +
            ", userId=" + getUserId() +
            ", userFullName='" + getUserFullName() + "'" +
            ", letterAttachmentId=" + getLetterAttachmentId() +
            ", letterAttachmentPath='" + getLetterAttachmentPath() + "'" +
            ", declarationAttachmentId=" + getDeclarationAttachmentId() +
            ", declarationAttachmentPath='" + getDeclarationAttachmentPath() + "'" +
            ", inspectionId=" + getInspectionId() +
            "}";
    }
}

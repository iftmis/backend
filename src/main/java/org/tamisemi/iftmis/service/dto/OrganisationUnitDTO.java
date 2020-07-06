package org.tamisemi.iftmis.service.dto;

import io.swagger.annotations.ApiModel;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link org.tamisemi.iftmis.domain.OrganisationUnit} entity.
 */
@ApiModel(description = "The OrganisationUnit entity.\n@author Chris")
public class OrganisationUnitDTO extends AbstractAuditingDTO implements Serializable {
    private Long id;

    @Size(min = 1, max = 64)
    private String code;

    @NotNull
    @Size(min = 2, max = 200)
    private String name;

    private String address;

    private String phoneNumber;

    private String email;

    private String background;

    private byte[] logo;

    private String logoContentType;

    private Long organisationUnitLevelId;

    private String organisationUnitLevelName;

    private Long parentId;

    private String parentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public Long getOrganisationUnitLevelId() {
        return organisationUnitLevelId;
    }

    public void setOrganisationUnitLevelId(Long organisationUnitLevelId) {
        this.organisationUnitLevelId = organisationUnitLevelId;
    }

    public String getOrganisationUnitLevelName() {
        return organisationUnitLevelName;
    }

    public void setOrganisationUnitLevelName(String organisationUnitLevelName) {
        this.organisationUnitLevelName = organisationUnitLevelName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long organisationUnitId) {
        this.parentId = organisationUnitId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String organisationUnitName) {
        this.parentName = organisationUnitName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrganisationUnitDTO)) {
            return false;
        }

        return id != null && id.equals(((OrganisationUnitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrganisationUnitDTO{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", background='" + getBackground() + "'" +
            ", logo='" + getLogo() + "'" +
            ", organisationUnitLevelId=" + getOrganisationUnitLevelId() +
            ", organisationUnitLevelName='" + getOrganisationUnitLevelName() + "'" +
            ", parentId=" + getParentId() +
            ", parentName='" + getParentName() + "'" +
            "}";
    }
}
